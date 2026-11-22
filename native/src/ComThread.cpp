/*
 * Copyright (c) 1999-2004 Sourceforge JACOB Project.
 * All rights reserved. Originator: Dan Adler (http://danadler.com).
 * Get more information about JACOB at http://sourceforge.net/projects/jacob-project
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
#include "stdafx.h"
#include <objbase.h>
#include "ComThread.h"
// Win32 support for Ole Automation
#include <wchar.h>
#include <string.h>
#include <atlbase.h>
#include <oleauto.h>
#include <olectl.h>
#include "util.h"

extern "C"
{

// Jacob 使用 JNI 1.2+ 的函数（如 ExceptionCheck），必须显式声明 JNI 版本，
// 否则 JVM 默认按 JNI 1.1 提供函数表。返回 1_8：所有 JDK 8+ 的 JVM 都接受，
// 函数表仍由 JVM 按自身能力提供（返回更高版本会把旧 JDK 挡在门外）。
JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM *vm, void *reserved)
{
  return JNI_VERSION_1_8;
}

JNIEXPORT void JNICALL Java_io_github_yahocen_jacob_com_ComThread_doCoInitialize
  (JNIEnv *env, jclass cls, jint mode)
{
  int threadModel = mode;
  CoInitializeEx(NULL, threadModel);
}

JNIEXPORT void JNICALL Java_io_github_yahocen_jacob_com_ComThread_doCoUninitialize
  (JNIEnv *env, jclass cls)
{
  CoUninitialize();
}

}
