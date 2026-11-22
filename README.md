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

`connectToActiveAcad()` / `createNewAcad()` 当前使用 2024 的 ProgID。其他版本用 `AutoCadVersion` 枚举自行构造：

```java
import io.github.yahocen.jacob.activeX.ActiveXComponent;
import io.github.yahocen.acad.constant.AutoCadVersion;

// 版本无关 ProgID（AutoCAD.Application）：单版本安装时最省事
var comp = ActiveXComponent.connectToActiveInstance(AutoCadVersion.AUTOCAD.getProgId());
// 多版本并存时指定版本，如 AutoCadVersion.AUTOCAD_2020.getProgId()
if (comp == null) throw new IllegalStateException("AutoCAD 未运行");
Application acad = new Application(comp);
```

映射类是晚绑定包装，2024 生成的类可直接驱动更新版本的 AutoCAD（见「多版本兼容」）。

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

```java
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.event.ApplicationEventSink;

acad.addEventListener(new ApplicationEventSink() {
    @Override
    public void BeginQuit(Variant[] args) {
        // AutoCAD 即将退出（JadeTracer 用这个钩子联动自身退出）
    }
});
```

### 线程模型

- 单线程脚本/工具：`ComThread.InitSTA()` 即可
- 线程池并发调用、byRef 参数（如 `GetEntity`）跨 apartment 通信：`ComThread.InitMTA(true)`（JadeTracer 的选择，会创建 MainSTA 线程；否则报 "Can't map name to dispid"）
- 退出前 `ComThread.Release()`

## 包结构

| 包 | 内容 | 许可 |
|----|------|------|
| `io.github.yahocen.jacob` | 通用 COM/ActiveX 桥接（JACOB 修改分支，28 个类），与 AutoCAD 无关，可用于任何 COM 对象 | LGPL-2.1 |
| `io.github.yahocen.acad` | AutoCAD 对象模型（180 个映射类）：Application/Document/实体/集合/事件，`EntityUtil.parse` 按 ObjectName 自动解析实体类型 | 见 LICENSE |

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

整条链路是 IDispatch 晚绑定：映射类只是类型化便利包装，不编译期绑定某个 TLB。2024 生成的类可直接驱动更新版本（新版成员是旧版超集）；连接方式见「使用 → 连接指定版本」。更旧版本支持需用对应 TLB 重新生成（`extract_tlb.py` 参数化进行中）；新版独有成员可经 `Dispatch.call` 原生调用。
