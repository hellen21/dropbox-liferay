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

import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.document.library.kernel.service.DLAppHelperLocalService;
import com.liferay.document.library.kernel.service.DLFolderLocalService;
import com.liferay.document.library.repository.dropbox.configuration.DropboxRepositoryConfiguration;
//import com.liferay.document.library.repository.cmis.configuration.CMISRepositoryConfiguration;
//import com.liferay.document.library.repository.cmis.internal.constants.CMISRepositoryConstants;
import com.liferay.document.library.repository.dropbox.constant.DropboxRepositoryConstants;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
//import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.lock.LockManager;
import com.liferay.portal.kernel.repository.RepositoryFactory;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.RepositoryEntryLocalService;
import com.liferay.portal.kernel.service.RepositoryLocalService;
import com.liferay.portal.kernel.service.UserLocalService;

import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Adolfo P??rez
 */
@Component(
	immediate = true,
	property = "repository.target.class.name=" + DropboxRepositoryConstants.DROPBOX_REPOSITORY_CLASSNAME,
	service = RepositoryFactory.class
)
public class DropboxRepositoryFactory
	extends BaseDropboxRepositoryFactory<DropboxRepository> {

	@Activate
	protected void activate(Map<String, Object> properties) {
		DropboxRepositoryConfiguration repositoryConfiguration =
			ConfigurableUtil.createConfigurable(
				DropboxRepositoryConfiguration.class, properties);

		super.setDropboxRepositoryConfiguration(repositoryConfiguration);
	}

	@Override
	protected DropboxRepository createBaseRepository() {
		return new DropboxRepository();
	}

	@Override
	@Reference(unbind = "-")
	protected void setAssetEntryLocalService(
		AssetEntryLocalService assetEntryLocalService) {

		super.setAssetEntryLocalService(assetEntryLocalService);
	}

//	@Override
//	@Reference(unbind = "-")
//	protected void setCMISSessionCache(CMISSessionCache cmisSessionCache) {
//		super.setCMISSessionCache(cmisSessionCache);
//	}

	@Override
	@Reference(unbind = "-")
	protected void setCompanyLocalService(
		CompanyLocalService companyLocalService) {

		super.setCompanyLocalService(companyLocalService);
	}

	@Override
	@Reference(unbind = "-")
	protected void setDLAppHelperLocalService(
		DLAppHelperLocalService dlAppHelperLocalService) {

		super.setDLAppHelperLocalService(dlAppHelperLocalService);
	}

	@Override
	@Reference(unbind = "-")
	protected void setDLFolderLocalService(
		DLFolderLocalService dlFolderLocalService) {

		super.setDLFolderLocalService(dlFolderLocalService);
	}

	@Override
	@Reference(unbind = "-")
	protected void setLockManager(LockManager lockManager) {
		super.setLockManager(lockManager);
	}

	@Override
	@Reference(unbind = "-")
	protected void setRepositoryEntryLocalService(
		RepositoryEntryLocalService repositoryEntryLocalService) {

		super.setRepositoryEntryLocalService(repositoryEntryLocalService);
	}

	@Override
	@Reference(unbind = "-")
	protected void setRepositoryLocalService(
		RepositoryLocalService repositoryLocalService) {

		super.setRepositoryLocalService(repositoryLocalService);
	}

	@Override
	@Reference(unbind = "-")
	protected void setUserLocalService(UserLocalService userLocalService) {
		super.setUserLocalService(userLocalService);
	}

}