// Copyright 2026 Yahocen
// SPDX-License-Identifier: Apache-2.0
package io.github.yahocen.acad.util;

import io.github.yahocen.jacob.activeX.ActiveXComponent;
import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.LibraryLoader;
import io.github.yahocen.jacob.com.Variant;
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
 * 方案：经 jacobcom.dll 的 JNI 直接调 Win32 {@code ShowWindowAsync}，
 * 绕过 COM 通道，异步恢复 CAD 窗口到正常状态。不引入 JNA——库自带原生 DLL。
 * </p>
 *
 * @author Yahocen
 * @since 2.1
 */
@Slf4j
public final class CadWindowHelper {

    static {
        LibraryLoader.loadJacobLibrary();
    }

    private CadWindowHelper() {
    }

    // ---- Win32 JNI（实现见 native/src/WindowHelper.cpp，user32）----

    private static native boolean winIsIconic(long hwnd);

    private static native boolean winShowWindowAsync(long hwnd, int cmdShow);

    private static native boolean winSetForegroundWindow(long hwnd);

    private static native boolean winPostMessage(long hwnd, int msg, long wParam, long lParam);

    private static final int WM_KEYDOWN = 0x0100;
    private static final int WM_KEYUP = 0x0101;
    private static final int VK_ESCAPE = 0x1B;
    private static final int SW_RESTORE = 9;

    /**
     * 向 AutoCAD 主窗口发送两次 ESC，取消正在等待用户输入的提示（点选 / 框选 / 命令）。
     * 脚本卡在 getPoint、getSelection 之类的提示上超时后用它解除阻塞。
     */
    public static void cancelPendingPrompt(ActiveXComponent acadApp) {
        long hwnd = getHwnd(acadApp);
        if (hwnd == 0) return;
        for (int i = 0; i < 2; i++) {
            winPostMessage(hwnd, WM_KEYDOWN, VK_ESCAPE, 0);
            winPostMessage(hwnd, WM_KEYUP, VK_ESCAPE, 0);
        }
    }

    /**
     * 获取 AutoCAD 主窗口句柄 (HWND)
     * <p>
     * 通过 COM 的 {@code Application.HWND} 属性获取。
     * </p>
     *
     * @param acadApp AutoCAD ActiveX 组件
     * @return 窗口句柄，获取失败返回 0
     */
    public static long getHwnd(ActiveXComponent acadApp) {
        try {
            Variant hwndVar = Dispatch.get(acadApp.getObject(), "HWND");
            if (!hwndVar.isNull()) {
                long hwnd = hwndVar.getLong();
                hwndVar.safeRelease();
                if (hwnd != 0) {
                    return hwnd;
                }
            }
            hwndVar.safeRelease();
        } catch (Exception e) {
            log.warn("通过 COM 获取 CAD HWND 失败: {}", e.getMessage());
        }
        return 0;
    }

    /**
     * 检查 CAD 窗口是否处于最小化状态
     *
     * @param hwnd 窗口句柄，0 视为非最小化
     */
    public static boolean isIconic(long hwnd) {
        if (hwnd == 0) {
            return false;
        }
        return winIsIconic(hwnd);
    }

    /**
     * 强制恢复 CAD 窗口到正常状态并置前
     * <p>
     * 如果窗口处于最小化状态，用 {@code ShowWindowAsync(SW_RESTORE)} 异步恢复，
     * 然后调 {@code SetForegroundWindow} 置前。
     * 即使 COM 通道阻塞，Win32 异步调用也能正常工作。
     * </p>
     *
     * @param hwnd 窗口句柄，0 则不做操作
     * @return true 表示窗口已恢复（或本来就不需要恢复）
     */
    public static boolean bringToFront(long hwnd) {
        if (hwnd == 0) {
            log.warn("bringToFront: HWND 为空，无法恢复窗口");
            return false;
        }

        boolean wasIconic = winIsIconic(hwnd);
        if (wasIconic) {
            // 异步恢复（跨进程安全，不阻塞调用方）
            boolean ok = winShowWindowAsync(hwnd, SW_RESTORE);
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
        boolean fg = winSetForegroundWindow(hwnd);
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
        long hwnd = getHwnd(acadApp);
        if (hwnd == 0) {
            log.warn("无法获取 CAD 窗口句柄，跳过恢复");
            return false;
        }
        return bringToFront(hwnd);
    }
}
