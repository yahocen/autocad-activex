# jacobcom.dll 编译指南

本文档记录 JadeTracer 项目中 COM JNI 层（`jacobcom.dll`，源自 JACOB 1.21 并深度定制）的完整编译过程、所做的修改及其原因。

---

## 1. 背景

JadeTracer 使用源自 JACOB（Java-COM Bridge）的 COM JNI 层通过纯 Java 调用 AutoCAD COM 接口。原始 JACOB 1.21 的 native 层有两个致命缺陷：

1. **`putDispatchRef` 是假的** — Java 层声明了 `putDispatchRef(Variant)`，但 C++ 层的 `putVariantDispatchRef` 从未实现，导致无法以 `VT_DISPATCH|VT_BYREF` 形式传递 Dispatch 指针。AutoCAD 的 `GetEntity` 等方法需要 byRef Dispatch 作为 out 参数，没有这个就无法获取返回的实体对象。
2. **没有 COM 调用重试机制** — AutoCAD 在用户交互（选实体、模态对话框等）期间会返回 `RPC_E_CALL_REJECTED` 或 `RPC_E_SERVERCALL_RETRYLATER`，原始 JACOB 直接报错退出。

此外，编译环境也面临挑战：开发机安装了 VS 2026 Community 和 VS 2022 BuildTools，但**都没有安装 ATL/MFC 组件**，而 JACOB 源码依赖 ATL（`atlbase.h`）。

 **2026-11-06 包名改名 + DLL 改名**：为防止开源 jacob.dll 直接替换使用，做了以下变更：
 - Java 包名 `com.jacob` → `io.github.yahocen.jacob`，C++ 侧所有 JNI 类签名路径（`com/jacob` → `io/github/yahocen/jacob`）和 JNI 函数名前缀（`com_jacob` → `io_github_yahocen_jacob`）同步修改
 - DLL 文件名从 `jacob-1.21-x64.dll` 改为 `jacobcom.dll`（项目专用，不再携带 JACOB 版本号）
 - `LibraryLoader.getPreferredDLLName()` 直接返回 `"jacobcom"`，不再拼版本号和架构后缀
 - 开源 jacob.dll 放入后因 `FindClass` / 函数名 / 库名三重不匹配，直接 `UnsatisfiedLinkError`

---

## 2. 编译环境

| 组件 | 版本 / 路径 |
|------|-------------|
| MSVC | 14.50.35717 (`C:\Program Files\Microsoft Visual Studio\18\Community\VC\Tools\MSVC\14.50.35717`) |
| Windows SDK | 10.0.26100.0 (`C:\Program Files (x86)\Windows Kits\10`) |
| JDK | 21.0.7+6 OpenJ9 (`C:\Application\VMR\versions\jdk_versions\jdk-21.0.7+6-openj9`) |
| 架构 | x64 |
| ATL/MFC | **未安装**（用手写 shim 替代，见下文） |

---

## 3. 源码结构

C++ JNI 源码已从原始 JACOB 仓库合并到主项目中并深度定制，位于 `native/src/`。Java 侧源码位于 `src/main/java/io/github/yahocen/jacob/`（已从 `com/jacob/` 改名）：

```
native/src/
├── stdafx.h              # 预编译头，引用 atlbase.h（被 shim 替代）
├── jcompat.h             # MSVC 14.30+ 类型兼容层（新增）
├── shim/
│   └── atlbase.h         # 最小化 ATL shim（新增，替代系统 ATL）
├── Variant.h / .cpp      # VARIANT 包装 — byRef 修改核心
├── Dispatch.h / .cpp     # IDispatch 调用 — invokev 重试循环
├── SafeArray.h / .cpp    # SafeArray 包装 — 维度 bug 修复
├── ComThread.h / .cpp    # COM 线程管理
├── DispatchEvents.*      # 事件 sink
├── DispatchProxy.*       # Dispatch 代理
├── EnumVariant.*         # 枚举变体
├── EventProxy.*          # 事件代理
├── STA.h / .cpp          # 单线程单元
└── util.h / .cpp         # 工具函数
```

---

## 4. 修改详情

### 4.1 ATL Shim（`native/src/shim/atlbase.h`）

**问题**：JACOB 源码 `#include <atlbase.h>`，但开发机没装 ATL 组件。

**方案**：手写一个最小化 ATL 替代头文件，只实现 JACOB 用到的子集：

- `CComBSTR` — BSTR 智能指针（构造、拷贝、赋值、`Copy()`、`Detach()`、`Attach()`、`CopyTo()`、`Length()`、`operator&()`、`operator BSTR()`）
- `CComPtr<T>` — COM 接口智能指针（`AddRef`/`Release` 自动管理，`operator&()` 释放旧指针后返回 out-param 槽位，`CoCreateInstance()` helper）
- `CComQIPtr<T, piid>` — 构造时自动 `QueryInterface`
- `CComModule` — 空壳（JACOB 声明 `_Module` 但从不使用）
- `USES_CONVERSION` / `A2W` / `W2A` — 字符串转换宏，用 `_alloca` 在栈上分配
- `A2BSTR` — ANSI 转 BSTR
- `ATLASSERT` — 映射到 `_ASSERTE`
- `_T` / `_TCHAR` / `W2T` — TCHAR 兼容

**关键 bug 修复**：初版 `A2W` 用局部变量接收 `_alloca` 返回的指针，函数返回后栈帧销毁，指针悬空。修复方式：将 `_alloca` + `MultiByteToWideChar` 放在同一 `__forceinline` 函数内，直接返回栈缓冲区指针（调用者在同一表达式内使用，栈帧有效）。

编译时通过 `/I"native\src\shim"` 让 `#include <atlbase.h>` 优先找到 shim 而非系统头文件。

### 4.2 类型兼容层（`native/src/jcompat.h`）

**问题**：MSVC 14.30+ 将 `int` 和 `long` 视为不同类型（虽然都是 32 位），旧 JACOB 代码将 `jint*` / `long*` 传给期望 `LONG*` 的 SafeArray API，触发 C2664 错误。

**方案**：用 `void*` 参数 + `reinterpret_cast<LONG*>` 包装 4 个 SafeArray 函数：

```cpp
inline HRESULT jacob_SafeArrayGetElement(SAFEARRAY *psa, void *rgIndices, void *pv)
{
    return ::SafeArrayGetElement(psa, reinterpret_cast<LONG*>(rgIndices), pv);
}
#define SafeArrayGetElement jacob_SafeArrayGetElement
// 同理：SafeArrayPutElement, SafeArrayGetLBound, SafeArrayGetUBound
```

通过 `#define` 宏替换，对调用方透明。此头文件必须在 `<jni.h>` 和 `<oleauto.h>` 之后引入。

### 4.3 Variant.cpp — byRef Dispatch 支持

**新增两个 JNI 方法**：

#### `putVariantDispatchRef` — 设置 VT_DISPATCH|VT_BYREF

```cpp
JNIEXPORT void JNICALL Java_io_github_yahocen_jacob_com_Variant_putVariantDispatchRef
  (JNIEnv *env, jobject _this, jobject _that)
{
  VARIANT *v = extractVariant(env, _this);
  IDispatch *disp = NULL;
  if (_that) {
    disp = extractDispatch(env, _that);
  }
  if (v) {
    VariantClear(v);
    IDispatch **ps = (IDispatch **)CoTaskMemAlloc(sizeof(IDispatch*));
    *ps = disp;
    if (disp) disp->AddRef();
    V_VT(v) = VT_DISPATCH|VT_BYREF;
    V_DISPATCHREF(v) = ps;
  }
}
```

- `_that` 可为 NULL（纯 out 参数，如 GetEntity 的返回实体）
- 用 `CoTaskMemAlloc` 分配 `IDispatch*` 指针的内存，COM 方法通过 by-ref 槽位写入返回值
- 内存由 `release()` 释放（见下）

#### `getVariantDispatchRef` — 读取 VT_DISPATCH|VT_BYREF

```cpp
JNIEXPORT jobject JNICALL Java_io_github_yahocen_jacob_com_Variant_getVariantDispatchRef
  (JNIEnv *env, jobject _this)
{
  VARIANT *v = extractVariant(env, _this);
  if (v) {
    if (V_VT(v) != (VT_DISPATCH|VT_BYREF)) return NULL;
    IDispatch *disp = *V_DISPATCHREF(v);
    if (disp) disp->AddRef();
    // 构造 Java Dispatch 对象返回
    jobject newAuto = env->NewObject(autoClass, autoCons, (jlong)(uintptr_t)disp);
    return newAuto;
  }
  return NULL;
}
```

- 不调用 `VariantChangeType`（避免修改原 variant）
- `AddRef` 后构造新的 Java Dispatch 对象

#### `release()` — 修复 byRef 内存泄漏

原版 `release()` 只处理 `VT_BSTR|VT_BYREF` 的释放。新增对三种 byRef 类型的内存回收：

```cpp
if (V_VT(v) & VT_BYREF) {
    void *pMem = V_BSTRREF(v);
    if (pMem) {
        if (V_VT(v) == (VT_BYREF|VT_BSTR)) {
            // 释放 BSTR
        } else if (V_VT(v) == (VT_DISPATCH|VT_BYREF)) {
            // Release IDispatch*
            IDispatch **pDisp = (IDispatch**)pMem;
            if (*pDisp) (*pDisp)->Release();
        } else if ((V_VT(v) & VT_ARRAY) && (V_VT(v) & VT_BYREF)) {
            // 销毁 SafeArray
            SAFEARRAY **pSA = (SAFEARRAY**)pMem;
            if (*pSA) SafeArrayDestroy(*pSA);
        }
        CoTaskMemFree(pMem);  // 释放 CoTaskMemAlloc 的内存
    }
}
```

### 4.4 Dispatch.cpp — invokev 重试循环

**问题**：AutoCAD 在用户交互期间拒绝 COM 调用，返回 `RPC_E_CALL_REJECTED` 或 `RPC_E_SERVERCALL_RETRYLATER`。

**方案**：在 `invokev` 的两个 COM 调用点各加重试循环：

#### name2ID 调用（名称→DISPID 映射）

```cpp
int name_retries = 0;
do {
    hr = name2ID(pIDispatch, nm, (long *)&dispID, lcid);
    if ((hr == RPC_E_CALL_REJECTED || hr == RPC_E_SERVERCALL_RETRYLATER) && name_retries < 120) {
        name_retries++;
        Sleep(500);
        continue;
    }
    break;
} while (true);
```

#### IDispatch::Invoke 调用（方法/属性执行）

```cpp
int com_retries = 0;
do {
    if (com_retries > 0) {
        // 清理上次被拒绝尝试的状态
        VariantClear(v);
        VariantInit(v);
        memset(&excepInfo, 0, sizeof(EXCEPINFO));
    }
    hr = pIDispatch->Invoke(dispID, IID_NULL, lcid, (WORD)wFlags,
                            &dispparams, v, &excepInfo, (unsigned int *)uAE);
    if ((hr == RPC_E_CALL_REJECTED || hr == RPC_E_SERVERCALL_RETRYLATER) && com_retries < 120) {
        com_retries++;
        Sleep(500);
        continue;
    }
    break;
} while (true);
```

- 最多 120 次 × 500ms = 60 秒超时
- 重试前清理 `EXCEPINFO` 和返回 VARIANT，避免脏状态

### 4.5 SafeArray 维度 1-based 修复

SafeArray API 的维度参数是 1-based（`i = 1; i <= nDims`），原代码部分位置用 0-based 遍历导致越界。修复为正确的 1-based 循环。

### 4.6 SafeArray.toVariantArray 非 VARIANT 元素修复（2026-11-12）

**问题**：原 `Java_io_github_yahocen_jacob_com_SafeArray_toVariantArray` 不看元素类型，对每个元素都执行
`SafeArrayGetElement(sa, &ix, (void *)v)`，把元素直接写进 `VARIANT` 的内存。只有元素本身是
`VT_VARIANT` 时这样做才正确；AutoCAD 返回的实体数组（`GetAttributes`、`GetConstantAttributes`、
`Explode`、`GetLoopAt`、`GetDynamicBlockProperties` 等）是 `VT_ARRAY | VT_DISPATCH`，元素是裸
`IDispatch*`，写进去后 `VARIANT.vt` 是垃圾值，对结果调用 `toDispatch()` 报
`VariantChangeType failed`。块属性因此一直读不出来。

**修复**（`native/src/SafeArray.cpp`）：先用 `SafeArrayGetVartype` 取得元素类型再分情况处理：

| 元素类型 | 处理 |
|----------|------|
| `VT_VARIANT` | 与原来一致，直接取元素到 `VARIANT` |
| `VT_DISPATCH` / `VT_UNKNOWN` | 取接口指针，设置 `V_VT = vt`，指针所有权交给 `VARIANT`（`SafeArrayGetElement` 已 AddRef，不再额外 AddRef） |
| 其它标量（`double` / `int` / `BSTR` …） | 按元素类型取值后包成对应 `vt` 的 `VARIANT` |

另外两点改动：`SafeArrayGetElement` 失败时抛 `ComFailException`，不再静默返回垃圾值；每个元素
`SetObjectArrayElement` 之后执行 `DeleteLocalRef`，避免大数组耗尽 JNI 局部引用表。

`getVariant(int)` 本来就区分了 `VT_DISPATCH`，不受影响。Java 侧 `EntityUtil.parseArray(Variant)`
基于这个修复，把实体数组直接解析成 `List<AcadEntity>`。

---

## 5. 编译命令

编译脚本位于 `native/build_dll.ps1`。修改 C++ 后运行它重编 DLL，再 `mvn clean install` 发布（DLL 随 jar 资源分发）。

### 5.1 前置条件

需要 VS x64 Native Tools 环境（`vcvars64.bat`），或直接运行 PowerShell 脚本（自动加载 vcvars64）。

### 5.2 编译参数

```
cl /c /nologo /MT /EHsc /O2 /W3 \
   /utf-8 \
   /D UNICODE /D _UNICODE \
   /D _WIN32_WINNT=0x0A00 \
   /D _CRT_SECURE_NO_DEPRECATE /D _CRT_NONSTDC_NO_DEPRECATE \
   /D _STATIC_CPPLIB \
   /I"native\src\shim" /I"native\src" \
   /I"%JDK%\include" /I"%JDK%\include\win32" \
   /I"%WINSDK%\Include\10.0.26100.0\um" \
   /I"%WINSDK%\Include\10.0.26100.0\shared" \
   /I"%WINSDK%\Include\10.0.26100.0\ucrt" \
   /I"%MSVC%\include" \
   native\src\Variant.cpp /Fo"build\Variant.obj"
```

| 参数 | 说明 |
|------|------|
| `/MT` | 静态链接 CRT（不依赖 vcruntime DLL） |
| `/EHsc` | C++ 异常处理 |
| `/O2` | 速度优化 |
| `/D UNICODE /D _UNICODE` | Unicode 构建 |
| `/D _STATIC_CPPLIB` | 静态链接 C++ 标准库 |
| `/utf-8` | 源文件含 UTF-8 注释，必须加此选项 |
| `/D _WIN32_WINNT=0x0A00` | 目标 Windows 10 SDK |
| `/I"native\src\shim"` | **优先**于系统目录找到 atlbase.h shim |

### 5.3 链接参数

```
link /nologo /DLL /OUT:"src\main\resources\native\jacobcom.dll" \
     /LIBPATH:"%JDK%\lib" \
     /LIBPATH:"%WINSDK%\Lib\10.0.26100.0\um\x64" \
     /LIBPATH:"%WINSDK%\Lib\10.0.26100.0\ucrt\x64" \
     /LIBPATH:"%MSVC%\lib\x64" \
     jvm.lib oleaut32.lib ole32.lib advapi32.lib user32.lib \
     build\*.obj
```

### 5.4 源文件列表（10 个 .cpp）

```
ComThread.cpp Dispatch.cpp DispatchEvents.cpp DispatchProxy.cpp
EnumVariant.cpp EventProxy.cpp SafeArray.cpp STA.cpp util.cpp Variant.cpp
```

### 5.5 一键编译

```powershell
# PowerShell（自动加载 vcvars64 环境）
cd D:\Workspace\idea\autocad-activex
.\native\build_dll.ps1
mvn clean install
```

输出：`src\main\resources\native\jacobcom.dll`（编译后自动清理 obj/lib/exp 临时文件）

---

## 6. 部署

编译脚本直接输出到 `src\main\resources\native\jacobcom.dll`，随 jar 打包分发。消费方引入 Maven 依赖即可：
`LibraryLoader` 优先 `System.loadLibrary("jacobcom")`（java.library.path），失败时从 jar 资源解压到临时目录加载。

> **开发覆盖 DLL 时注意搜索顺序。** Windows 加载 DLL 时优先搜索 `java.exe` 所在目录，
> JDK `bin\` 里若有旧的 `jacobcom.dll` 会盖过你指定的版本，而且没有任何提示——改了 C++ 却一直在测旧 DLL。
> 用 `-Djava.library.path` 指向新 DLL 所在目录时，先确认 JDK `bin\` 里没有同名旧 DLL。

---

## 7. Java 层对应修改

C++ 层新增的 JNI 方法需要在 Java 层声明对应 native 方法：

- `Variant.java`：新增 `putVariantDispatchRef(Dispatch)` 和 `getVariantDispatchRef()` 的 `native` 声明 + 公开方法
- `Variant.java`：`release()` 已有 native 声明，C++ 侧逻辑增强

Java 层调用示例（AutoCAD GetEntity）：

```java
Variant entityVar = new Variant();
entityVar.putVariantDispatchRef(null);  // 纯 out 参数
Variant pointVar = new Variant();
pointVar.putVariantStringRef("");       // out 参数占位
Variant promptVar = new Variant("请选择实体");
acadApp.invoke("GetEntity", entityVar, pointVar, promptVar);
Dispatch entity = entityVar.getVariantDispatchRef();  // 获取返回的实体
```

---

## 8. 验证

编译成功后，通过以下步骤验证：

1. **DLL 加载**：应用启动时 `System.loadLibrary("jacobcom")` 无 `UnsatisfiedLinkError`
2. **COM 连接**：`new ActiveXComponent("AutoCAD.Application.24")` 成功连接到已运行的 AutoCAD 实例
3. **GetEntity**：在 AutoCAD 中点选实体，Java 侧通过 `getVariantDispatchRef()` 获取到实体 Dispatch 对象
4. **重试机制**：用户在 AutoCAD 交互期间，COM 调用不再因 `RPC_E_CALL_REJECTED` 失败
5. **防替换验证**：将开源 `jacob-1.21-x64.dll` 放入 `lib/` 或 JDK `bin/`，应用启动报 `UnsatisfiedLinkError`
6. **实体数组**：对带属性的块参照执行 `blockRef.getAttributes().toSafeArray().toVariantArray()`，每个元素的 `getvt()` 为 `9`（VT_DISPATCH），`toDispatch()` 可正常取到属性对象（4.6 修复前会报 `VariantChangeType failed`）

---

## 9. 注意事项

- **不要安装 ATL/MFC 组件** — shim 已经覆盖需求，安装只会增加编译复杂度
- **`/MT` 而非 `/MD`** — 静态链接 CRT，避免目标机器缺少 vcruntime
- **shim include 顺序** — `/I"native\src\shim"` 必须在 `/I"native\src"` 之前，确保 `atlbase.h` 优先命中 shim
- **JDK 路径** — `jvm.lib` 在 `%JDK%\lib`，`jni.h` 在 `%JDK%\include`，`jni_md.h` 在 `%JDK%\include\win32`
- **修改 C++ 后必须重编 DLL + `mvn install`** — DLL 随 jar 分发；修改 Java 层不需要重新编译 DLL
- **DLL 名固定为 `jacobcom.dll`** — 由 `LibraryLoader.DLL_NAME` 决定，不再携带版本号和架构后缀
- **包名已改为 `io.github.yahocen.jacob`** — C++ 侧 JNI 类签名为 `io/github/yahocen/jacob/...`，JNI 函数名前缀为 `Java_io_github_yahocen_jacob_...`，与开源 JACOB 完全不兼容
- **`JNI_OnLoad` 在 `ComThread.cpp`** — 返回 `JNI_VERSION_1_8`（Java 17 基线，所有 JDK 8+ 接受）。Jacob 使用 JNI 1.2+ 的函数（如 `ExceptionCheck`），不能省略此入口，否则 JVM 默认按 JNI 1.1 提供函数表；返回过高版本（如 21）会把旧 JDK 挡在门外
- **编译选项 `/utf-8`** — 源文件含 UTF-8 注释，必须用 `/utf-8` 让 MSVC 正确处理
- **`LibraryLoader` 加载策略** — `System.loadLibrary` 优先，回退 jar 资源解压加载；固定库名 `jacobcom`，无路径/名称覆盖机制
