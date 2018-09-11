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

package com.liferay.document.library.repository.dropbox.search;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.repository.BaseRepositoryImpl;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.InputStream;

import java.util.List;
import java.util.Map;

/**
 * @author Alexander Chow
 */
public abstract class BaseDropboxRepository extends BaseRepositoryImpl {

	public abstract String getLatestVersionId(String objectId);

	public abstract String getObjectName(String objectId)
		throws PortalException;

	public abstract List<String> getObjectPaths(String objectId)
		throws PortalException;

	public abstract boolean isCancelCheckOutAllowable(String objectId)
		throws PortalException;

	public abstract boolean isCheckInAllowable(String objectId)
		throws PortalException;

	public abstract boolean isCheckOutAllowable(String objectId)
		throws PortalException;

	public abstract boolean isSupportsMinorVersions() throws PortalException;

	public abstract FileEntry toFileEntry(String objectId)
		throws PortalException;

	public abstract Folder toFolder(String objectId) throws PortalException;

	public abstract FileEntry updateFileEntry(
			String objectId, String mimeType, Map<String, Object> properties,
			InputStream is, String sourceFileName, long size,
			ServiceContext serviceContext)
		throws PortalException;

}