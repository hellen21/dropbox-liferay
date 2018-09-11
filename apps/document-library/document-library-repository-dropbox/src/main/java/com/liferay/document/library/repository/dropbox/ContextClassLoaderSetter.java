/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.document.library.repository.dropbox;

import com.liferay.portal.kernel.util.ClassLoaderUtil;

import java.io.Closeable;

/**
 * @author Adolfo P??rez
 */
public class ContextClassLoaderSetter implements Closeable {

	public ContextClassLoaderSetter(ClassLoader classLoader) {
		_originalClassLoader = ClassLoaderUtil.getContextClassLoader();

		ClassLoaderUtil.setContextClassLoader(classLoader);
	}

	@Override
	public void close() {
		ClassLoaderUtil.setContextClassLoader(_originalClassLoader);
	}

	private final ClassLoader _originalClassLoader;

}