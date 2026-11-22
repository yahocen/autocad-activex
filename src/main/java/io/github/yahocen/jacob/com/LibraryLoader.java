/*
 * Copyright (c) 1999-2007 Sourceforge JACOB Project.
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
package io.github.yahocen.jacob.com;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 * 集中加载 JNI 库 jacobcom。
 * <p>
 * 加载策略：
 * <ol>
 *   <li>System.loadLibrary("jacobcom") — 标准 JNI 加载，依赖 -Djava.library.path（开发覆盖用）</li>
 *   <li>回退：从 jar 资源 /native/jacobcom.dll 解压到临时目录后 System.load —
 *       消费方只需引入 Maven 依赖，无需手工放置 DLL</li>
 * </ol>
 */
public final class LibraryLoader {

	/** JNI 库名（不含 .dll 后缀） */
	private static final String DLL_NAME = "jacobcom";

	/** jar 内 DLL 资源路径 */
	private static final String DLL_RESOURCE = "/native/" + DLL_NAME + ".dll";

	private LibraryLoader() {}

	/**
	 * 加载 jacobcom。优先 java.library.path，其次 jar 内嵌 DLL；都失败时抛明确异常。
	 */
	public static void loadJacobLibrary() {
		try {
			JacobObject.debug("Loading library " + DLL_NAME + " using System.loadLibrary");
			System.loadLibrary(DLL_NAME);
		} catch (UnsatisfiedLinkError e) {
			try {
				Path dll = extractDll();
				JacobObject.debug("Loading " + DLL_NAME + " from jar resource: " + dll);
				System.load(dll.toString());
			} catch (IOException | UnsatisfiedLinkError ex) {
				throw new IllegalStateException(
					"Cannot load " + DLL_NAME + ".dll: not found on java.library.path and jar resource "
					+ DLL_RESOURCE + " unavailable. Ensure the autocad-activex jar is on the classpath.", ex);
			}
		}
	}

	/**
	 * 把 jar 内的 DLL 解压到临时文件。Windows 加载期间文件被锁定，
	 * deleteOnExit 可能删不掉，留给系统临时目录清理即可。
	 */
	private static Path extractDll() throws IOException {
		try (InputStream in = LibraryLoader.class.getResourceAsStream(DLL_RESOURCE)) {
			if (in == null) {
				throw new FileNotFoundException("Resource not found: " + DLL_RESOURCE);
			}
			Path tmp = Files.createTempFile(DLL_NAME + "-", ".dll");
			tmp.toFile().deleteOnExit();
			Files.copy(in, tmp, StandardCopyOption.REPLACE_EXISTING);
			return tmp;
		}
	}

	/**
	 * @return the name of the JNI library without the ".dll" extension
	 */
	public static String getPreferredDLLName() {
		return DLL_NAME;
	}
}
