# autocad-activex

AutoCAD ActiveX/COM 自动化的 Java 库：通用 COM 桥接 + 类型化 AutoCAD 对象模型。

```xml
<dependency>
    <groupId>io.github.yahocen</groupId>
    <artifactId>autocad-activex</artifactId>
    <version>1.0.0</version>
</dependency>
```

- **Java 17+**，Windows only（COM/ActiveX）
- **引入即用**：jacobcom.dll 内嵌在 jar 资源里，运行时自动解压加载，无需配置 `java.library.path` 或手工放置 DLL
- **晚绑定（IDispatch）**：映射类由 AutoCAD 2024 类型库生成，但调用不绑定版本——同一套类可驱动多个 AutoCAD 版本（新版成员是旧版超集）

## 使用

以下示例与 JadeTracer 生产用法同源。前提：Windows + 已安装 AutoCAD（至少手动启动过一次，完成 COM 注册）。

### 快速开始：连接并绘图

```java
import io.github.yahocen.jacob.com.ComThread;
import io.github.yahocen.acad.Application;
import io.github.yahocen.acad.document.Document;
import io.github.yahocen.acad.document.base.Point;

// COM 初始化，必须先于任何连接/调用（单线程用 InitSTA；多线程见下文）
ComThread.InitSTA();

Application acad = Application.connectToActiveAcad(); // 连接正在运行的 AutoCAD
// Application acad = Application.createNewAcad();    // 或启动一个新实例（默认可见）

Document doc = acad.getActiveDocument();
var ms = doc.getModelSpace();

ms.addLine(new Point(0, 0, 0), new Point(100, 0, 0));
ms.addCircle(new Point(50, 50, 0), 25);
ms.addText("Hello AutoCAD", new Point(0, 10, 0), 5);
ms.addPolyline(new Point(0, 0, 0), new Point(100, 0, 0), new Point(100, 80, 0));

doc.saveAs("D:/demo.dwg");
```

DLL 加载是自动的：首次触碰 COM 类时从 jar 资源解压 jacobcom.dll，无需任何配置。

### 连接指定版本

无参的 `connectToActiveAcad()` / `createNewAcad()` 使用版本无关 ProgID `AutoCAD.Application`，经注册表 CurVer 指向**本机最新安装版本**。多版本并存或需要确定性时，用 `AutoCadVersion` 枚举显式指定：

```java
import io.github.yahocen.acad.constant.AutoCadVersion;

// 连接指定版本的运行实例（GetActiveObject 按该版本 ProgID 的 CLSID 匹配）
Application acad = Application.connectToActiveAcad(AutoCadVersion.AUTOCAD_2020);

// 启动指定版本的新实例
Application acad2 = Application.createNewAcad(AutoCadVersion.AUTOCAD_2024, true);

// 连接本机任意一个正在运行的实例（按已装版本从新到老逐个尝试）
Application any = Application.connectToAnyActiveAcad();

// 探测本机实际可启动的版本（过滤卸载残留的孤儿 ProgID）
var installed = AutoCadVersion.detectInstalled();
```

注意：版本无关 ProgID 的 `connectToActiveAcad()` 只匹配 CurVer 指向版本的运行实例，其他版本在跑也连不上——要“连任意运行实例”请用 `connectToAnyActiveAcad()`。

映射类是晚绑定包装，2024 生成的类可直接驱动 2018–2026 全部版本（见「多版本兼容」）。

### 读取与解析实体

集合迭代自动按 ObjectName 解析为具体类型（内部走 `EntityUtil.parse` + 静态注册表）：

```java
import java.util.List;
import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.acad.document.base.AcadEntity;
import io.github.yahocen.acad.document.element.Circle;
import io.github.yahocen.acad.document.element.LWPolyline;
import io.github.yahocen.acad.util.EntityUtil;

for (AcadEntity e : doc.getModelSpace()) {
    if (e instanceof Circle c) {
        Point center = c.getCenter();
        c.setRadius(c.getRadius() * 2);      // setter 流式返回自身
    } else if (e instanceof LWPolyline pl) {
        var first = pl.getFirstPoint();
        var last = pl.getLastPoint();
    }
    String layer = e.getLayer();
}

// COM 返回的实体数组（Explode / GetAttributes 等）→ 类型化列表
Dispatch blockRef = /* 任意块参照 */ null;
List<AcadEntity> parts = EntityUtil.parseArray(Dispatch.call(blockRef, "Explode"));

// 提示用户在 CAD 里框选一组实体
List<AcadEntity> selected = doc.getEntityList();
```

### 事件监听

事件接收器覆盖全部类型库事件：`ApplicationEventSink`（23 个）、`DocumentEventSink`（28 个）、`ObjectEventSink`（1 个），方法默认空实现，按需覆盖。**支持匿名子类**：

```java
import io.github.yahocen.jacob.com.DispatchEvents;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.event.ApplicationEventSink;

// addEventListener 返回连接点句柄，可用于解绑
DispatchEvents handle = acad.addEventListener(new ApplicationEventSink() {
    @Override
    public void BeginQuit(Variant[] args) {
        // AutoCAD 即将退出（JadeTracer 用这个钩子联动自身退出）
    }
});

// 不再需要时解除连接点，避免监听器累积泄漏
handle.safeRelease();
```

**三类事件源均已支持**：Application / Document / Object 都能绑定。AutoCAD 的 Document/Object 不实现 `IProvideClassInfo`，原生层 `GetEventIID`（`native/src/DispatchEvents.cpp`）已增强为：当 `IProvideClassInfo` 不可用时，用源对象 `IDispatch::GetTypeInfo` 的接口 GUID 在类型库中匹配其自身 coclass，取到该对象自己的默认事件接口（而非 ProgID 恒指向的 Application 事件接口）。`MultiVersionEventBindingTest` 对 2018–2026 五版本回归守护。

**线程要求**：绑定/解绑是同步 COM 调用，在任意已初始化 COM 的线程上即可建立连接点。**回调投递**才需要消息泵——事件是 AutoCAD 异步回调，须由持有对象的 STA 线程泵消息才能收到：继承 `io.github.yahocen.jacob.com.STA`，`OnInit()` 里建对象并 `addEventListener`（构造器自动启动线程，随后进入消息泵），`OnQuit()` 里释放句柄、关文档、退 CAD。只绑定不泵消息，回调不会触发。

### 线程模型

- 单线程脚本/工具：`ComThread.InitSTA()` 即可
- 线程池并发调用、byRef 参数（如 `GetEntity`）跨 apartment 通信：`ComThread.InitMTA(true)`（JadeTracer 的选择，会创建 MainSTA 线程；否则报 "Can't map name to dispid"）
- 退出前 `ComThread.Release()`

## 包结构

| 包 | 内容 | 许可 |
|----|------|------|
| `io.github.yahocen.jacob` | 通用 COM/ActiveX 桥接（JACOB 修改分支，28 个类），与 AutoCAD 无关，可用于任何 COM 对象 | LGPL-2.1 |
| `io.github.yahocen.acad` | AutoCAD 对象模型（180 个映射类）：Application/Document/实体/集合/事件，`EntityUtil.parse` 按 ObjectName 自动解析实体类型 | Apache-2.0 |

## 构建

需要：JDK 17+（构建机当前用 JDK 21）、Maven、Visual Studio（MSVC + Windows SDK，仅重编 DLL 时）。

```powershell
# 1. 重编 jacobcom.dll（修改 C++ 后才需要；产物直接落入 src/main/resources/native/）
.\native\build_dll.ps1

# 2. 安装到本地 Maven 仓库
mvn clean install
```

DLL 是预编译产物，随仓库提交——纯 Java 消费方不需要 MSVC。

## codegen

`codegen/` 下的脚本从 AutoCAD 类型库生成映射类（需要 Python 3 + pywin32，本机装有 AutoCAD）：

| 脚本 | 作用 |
|------|------|
| `extract_tlb.py` | 导出类型库为 `acad_typelib.json`（当前指向 acax24enu.tlb） |
| `gen_entities.py` | 生成实体类（document/element/）与事件 sink（event/） |
| `gen_noneentities.py` | 生成非图元对象（图层、样式、布局等） |
| `gen_registry.py` | 从 `@AcadEntityName` 注解生成 `EntityRegistry` 静态注册表（纯 stdlib） |
| `verify_entities.py` / `verify_noneentities.py` / `verify_types.py` | 映射类与类型库的一致性校验 |

修改映射类或重新生成后，跑一遍 verify 脚本。

## 多版本兼容

整条链路是 IDispatch 晚绑定：映射类只是类型化便利包装，不编译期绑定某个 TLB。经类型库逐接口比对，**2018（acax22）到 2026（acax25）的实体接口成员级完全一致**（仅差版本探测接口与 ComparedReference），2024 生成的这一套映射可直接驱动 2018–2026 全部版本；连接方式见「使用 → 连接指定版本」。新版独有成员可经 `Dispatch.call` 原生调用。

### 多版本回归测试

`src/test` 内置跨版本集成测试（本机需装有 AutoCAD，无则自动跳过）：

```bash
# 全部已安装版本跑「全图元覆盖 + 应用生命周期 + 连接运行实例」（每版本约 1–2 分钟）
mvn test

# 只测指定版本（ProgID 后缀或枚举名）
mvn test -Dacad.versions=24.3,25.1

# 让被测 CAD 窗口可见，便于人工观察
mvn test -Dacad.visible=true
```

- `MultiVersionEntityCoverageTest`：每版本启动 CAD → 新建文档 → 创建约 50 种图元（曲线/文字/标注/填充/面域/三维实体/块与属性/网格）→ 校验 ObjectName 与包装类 → 另存 DWG → 重开比对实体数。逐图元收集结果，单个失败不影响其余图元，输出完整「版本 × 图元」矩阵。
- `MultiVersionApplicationTest`：`createNewAcad`/`connectToActiveAcad` 的版本核对、文档生命周期、GetActiveObject 连接。
- 启动策略（`AcadTestHarness`）：优先 ProgID `CoCreateInstance` 并核对 `Version`；ProgID 被卸载残留重定向或启动被阻（如 .NET 版本管理器干扰 2025/2026）时，自动回退「直接启动该版本 acad.exe（清除 `DOTNET_ROOT`）+ `GetActiveObject` 连接」。exe 路径自动扫描 `C:/Program Files/Autodesk`，也可用 `-Dacad.exe.<版本后缀>=<路径>` 指定。
- 已知限制：`AddExtrudedSolid`/`AddRevolvedSolid` 系 Profile 为 byref Dispatch，AutoCAD 的 IDispatch 实现不接受任何后期绑定编码（VBA 早期绑定专属），测试中以可选步骤记录；三维实体由 `addBox`/`addCone` 等图元方法覆盖。

## 许可

双许可结构（详见 NOTICE）：

- **`io.github.yahocen.acad` 对象模型及其余原创代码**：[Apache-2.0](LICENSE)。源码文件带 `SPDX-License-Identifier: Apache-2.0` 标识。
- **`io.github.yahocen.jacob` COM 桥接 + jacobcom 原生库**：JACOB 修改分支，沿用 [LGPL-2.1](LICENSE-LGPL-2.1)，修改说明见 NOTICE。

再分发时请保留 LICENSE 与 NOTICE（Apache-2.0 第 4 条要求）。

## 致谢

- [freemansoft/jacob-project](https://github.com/freemansoft/jacob-project) —— JACOB 项目，久经考验的 Java-COM 桥接库及其长期维护者。本库的 COM 桥接层 fork 自 JACOB，站在它的肩膀上才能把精力集中在 AutoCAD 对象模型上。感谢 JACOB 作者 Dan Adler 与历年贡献者。
