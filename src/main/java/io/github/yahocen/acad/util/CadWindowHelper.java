package io.github.yahocen.acad.util;

import io.github.yahocen.jacob.activeX.ActiveXComponent;
import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef;
import lombok.extern.slf4j.Slf4j;

/**
 * CAD 窗口管理工具
 * <p>
 * 解决通过 COM 操作 AutoCAD 后窗口卡在最小化状态无法恢复的问题。
 * </p>
 * <p>
 * 根因：{@code ComThread.InitMTA(true)} 下跨套间 COM 调用会占用 CAD 的
 * 窗口消息泵，导致用户最小化 CAD 后 Windows 发送的恢复消息无法被处理。
 * </p>
 * <p>
 * 方案：用 JNA 直接调 Win32 {@code ShowWindowAsync}，绕过 COM 通道，
 * 异步恢复 CAD 窗口到正常状态。
 * </p>
 *
 * @author Yahocen
 * @since 2.1
 */
@Slf4j
public final class CadWindowHelper {

    private CadWindowHelper() {
    }

    /**
     * JNA User32 接口 — 只声明需要的方法
     */
    public interface User32 extends com.sun.jna.win32.StdCallLibrary {

        User32 INSTANCE = Native.load("user32", User32.class);

        /**
         * 异步显示窗口（跨进程安全）
         *
         * @param hWnd         窗口句柄
         * @param nCmdShow     显示命令
         * @return 非 0 表示成功
         */
        WinDef.BOOL ShowWindowAsync(WinDef.HWND hWnd, int nCmdShow);

        /**
         * 将窗口设为前台窗口
         */
        WinDef.BOOL SetForegroundWindow(WinDef.HWND hWnd);

        /**
         * 判断窗口是否最小化
         */
        WinDef.BOOL IsIconic(WinDef.HWND hWnd);

        /**
         * 获取前台窗口
         */
        WinDef.HWND GetForegroundWindow();

        /** 向窗口消息队列投递消息（不等待处理，跨进程安全） */
        WinDef.BOOL PostMessage(WinDef.HWND hWnd, int msg, WinDef.WPARAM wParam, WinDef.LPARAM lParam);
    }

    private static final int WM_KEYDOWN = 0x0100;
    private static final int WM_KEYUP = 0x0101;
    private static final int VK_ESCAPE = 0x1B;

    /**
     * 向 AutoCAD 主窗口发送两次 ESC，取消正在等待用户输入的提示（点选 / 框选 / 命令）。
     * 脚本卡在 getPoint、getSelection 之类的提示上超时后用它解除阻塞。
     */
    public static void cancelPendingPrompt(ActiveXComponent acadApp) {
        var hwnd = getHwnd(acadApp);
        if (hwnd == null) return;
        for (int i = 0; i < 2; i++) {
            User32.INSTANCE.PostMessage(hwnd, WM_KEYDOWN, new WinDef.WPARAM(VK_ESCAPE), new WinDef.LPARAM(0));
            User32.INSTANCE.PostMessage(hwnd, WM_KEYUP, new WinDef.WPARAM(VK_ESCAPE), new WinDef.LPARAM(0));
        }
    }

    // Win32 nCmdShow 常量
    private static final int SW_HIDE = 0;
    private static final int SW_SHOWNORMAL = 1;
    private static final int SW_SHOWMINIMIZED = 2;
    private static final int SW_SHOWMAXIMIZED = 3;
    private static final int SW_SHOWNOACTIVATE = 4;
    private static final int SW_SHOW = 5;
    private static final int SW_MINIMIZE = 6;
    private static final int SW_SHOWMINNOACTIVE = 7;
    private static final int SW_RESTORE = 9;

    /**
     * 获取 AutoCAD 主窗口句柄 (HWND)
     * <p>
     * 优先用 COM 的 {@code Application.HWND} 属性获取（最准确），
     * COM 不可用时 fallback 到按进程名查找。
     * </p>
     *
     * @param acadApp AutoCAD ActiveX 组件
     * @return 窗口句柄，获取失败返回 null
     */
    public static WinDef.HWND getHwnd(ActiveXComponent acadApp) {
        // 优先通过 COM 获取（AutoCAD Application 有 HWND 属性）
        try {
            Variant hwndVar = Dispatch.get(acadApp.getObject(), "HWND");
            if (!hwndVar.isNull()) {
                long hwnd = hwndVar.getLong();
                hwndVar.safeRelease();
                if (hwnd != 0) {
                    return new WinDef.HWND(Pointer.createConstant(hwnd));
                }
            }
            hwndVar.safeRelease();
        } catch (Exception e) {
            log.warn("通过 COM 获取 CAD HWND 失败: {}", e.getMessage());
        }
        return null;
    }

    /**
     * 检查 CAD 窗口是否处于最小化状态
     *
     * @param hwnd 窗口句柄
     * @return true 表示窗口当前是最小化的
     */
    public static boolean isIconic(WinDef.HWND hwnd) {
        if (hwnd == null) {
            return false;
        }
        return User32.INSTANCE.IsIconic(hwnd).booleanValue();
    }

    /**
     * 强制恢复 CAD 窗口到正常状态并置前
     * <p>
     * 如果窗口处于最小化状态，用 {@code ShowWindowAsync(SW_RESTORE)} 异步恢复，
     * 然后调 {@code SetForegroundWindow} 置前。
     * 即使 COM 通道阻塞，Win32 异步调用也能正常工作。
     * </p>
     *
     * @param hwnd 窗口句柄，null 则不做操作
     * @return true 表示窗口已恢复（或本来就不需要恢复）
     */
    public static boolean bringToFront(WinDef.HWND hwnd) {
        if (hwnd == null) {
            log.warn("bringToFront: HWND 为空，无法恢复窗口");
            return false;
        }

        var user32 = User32.INSTANCE;
        boolean wasIconic = user32.IsIconic(hwnd).booleanValue();

        if (wasIconic) {
            // 异步恢复（跨进程安全，不阻塞调用方）
            boolean ok = user32.ShowWindowAsync(hwnd, SW_RESTORE).booleanValue();
            log.info("CAD 窗口处于最小化状态，ShowWindowAsync(SW_RESTORE) -> {}", ok);
            if (!ok) {
                return false;
            }
            // 给 Windows 一点时间处理异步消息
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // 置前（即使不是最小化也尝试置前）
        boolean fg = user32.SetForegroundWindow(hwnd).booleanValue();
        if (!fg) {
            // SetForegroundWindow 受 Windows 前台锁定限制影响，
            // 失败不一定是真失败，窗口可能已经恢复只是没获得焦点
            log.debug("SetForegroundWindow 返回 false（可能受前台锁定限制，窗口应已恢复）");
        }

        return true;
    }

    /**
     * 便捷方法：从 Application 对象直接恢复 CAD 窗口
     *
     * @param acadApp AutoCAD ActiveX 组件
     * @return true 表示恢复成功
     */
    public static boolean bringToFront(ActiveXComponent acadApp) {
        WinDef.HWND hwnd = getHwnd(acadApp);
        if (hwnd == null) {
            log.warn("无法获取 CAD 窗口句柄，跳过恢复");
            return false;
        }
        return bringToFront(hwnd);
    }
}
