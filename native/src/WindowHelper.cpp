// Copyright 2026 Yahocen
// SPDX-License-Identifier: Apache-2.0
//
// Win32 window helpers for io.github.yahocen.acad.util.CadWindowHelper.
// Replaces JNA: the library already ships a native DLL, user32 is linked.

#include "stdafx.h"
#include <jni.h>
#include <windows.h>

extern "C"
{

JNIEXPORT jboolean JNICALL Java_io_github_yahocen_acad_util_CadWindowHelper_winIsIconic
  (JNIEnv *env, jclass cls, jlong hwnd)
{
  return IsIconic((HWND)(LONG_PTR)hwnd) ? JNI_TRUE : JNI_FALSE;
}

JNIEXPORT jboolean JNICALL Java_io_github_yahocen_acad_util_CadWindowHelper_winShowWindowAsync
  (JNIEnv *env, jclass cls, jlong hwnd, jint cmdShow)
{
  return ShowWindowAsync((HWND)(LONG_PTR)hwnd, cmdShow) ? JNI_TRUE : JNI_FALSE;
}

JNIEXPORT jboolean JNICALL Java_io_github_yahocen_acad_util_CadWindowHelper_winSetForegroundWindow
  (JNIEnv *env, jclass cls, jlong hwnd)
{
  return SetForegroundWindow((HWND)(LONG_PTR)hwnd) ? JNI_TRUE : JNI_FALSE;
}

JNIEXPORT jboolean JNICALL Java_io_github_yahocen_acad_util_CadWindowHelper_winPostMessage
  (JNIEnv *env, jclass cls, jlong hwnd, jint msg, jlong wParam, jlong lParam)
{
  return PostMessage((HWND)(LONG_PTR)hwnd, (UINT)msg, (WPARAM)wParam, (LPARAM)lParam) ? JNI_TRUE : JNI_FALSE;
}

}
