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
//import com.liferay.document.library.repository.cmis.CMISRepositoryHandler;
//import com.liferay.document.library.repository.cmis.configuration.CMISRepositoryConfiguration;
//import com.liferay.document.library.repository.cmis.search.BaseCmisSearchQueryBuilder;
//import com.liferay.document.library.repository.cmis.search.CMISSearchQueryBuilder;
import com.liferay.documet.library.repository.dropbox.search.DropboxRepositoryHandler;
import com.liferay.exportimport.kernel.lar.ExportImportThreadLocal;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.lock.LockManager;
import com.liferay.portal.kernel.repository.BaseRepository;
import com.liferay.portal.kernel.repository.LocalRepository;
import com.liferay.portal.kernel.repository.Repository;
import com.liferay.portal.kernel.repository.RepositoryFactory;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.RepositoryEntryLocalService;
import com.liferay.portal.kernel.service.RepositoryLocalService;
import com.liferay.portal.kernel.service.UserLocalService;

/**
 * @author Adolfo P??rez
 */
public abstract class BaseDropboxRepositoryFactory<T extends DropboxRepositoryHandler>
	implements RepositoryFactory {

	@Override
	public LocalRepository createLocalRepository(long repositoryId)
		throws PortalException {
		//return null;

		try (ContextClassLoaderSetter contextClassLoaderSetter =
				new ContextClassLoaderSetter(
					BaseDropboxRepositoryFactory.class.getClassLoader())) {

			BaseRepository baseRepository = createBaseRepository(repositoryId);

			return baseRepository.getLocalRepository();
		}
	}

	@Override
	public Repository createRepository(long repositoryId)
		throws PortalException {
		//	return null;
//poner get de dropbox
		try (ContextClassLoaderSetter contextClassLoaderSetter =
				new ContextClassLoaderSetter(
					BaseDropboxRepositoryFactory.class.getClassLoader())) {

			return new RepositoryProxyBean(
				createBaseRepository(repositoryId),
				BaseDropboxRepositoryFactory.class.getClassLoader());
		}
	}

	protected abstract T createBaseRepository();

	protected BaseRepository createBaseRepository(long repositoryId)
		throws PortalException {

		T baseRepository = createBaseRepository();

		com.liferay.portal.kernel.model.Repository repository =
			_repositoryLocalService.getRepository(repositoryId);

//		CMISRepository cmisRepository = new CMISRepository(
//			_cmisRepositoryConfiguration, baseRepository,
//			_cmisSearchQueryBuilder, _cmisSessionCache, _lockManager);
//
//		baseRepository.setCmisRepository(cmisRepository);
//
//		setupRepository(repositoryId, repository, cmisRepository);

		setupRepository(repositoryId, repository, baseRepository);

		
		//revisar init
		if (!ExportImportThreadLocal.isImportInProcess()) {
			baseRepository.initRepository();
		}

		return baseRepository;
	}

	protected void setAssetEntryLocalService(
		AssetEntryLocalService assetEntryLocalService) {

		_assetEntryLocalService = assetEntryLocalService;
	}

	protected void setDropboxRepositoryConfiguration(
		DropboxRepositoryConfiguration repositoryConfiguration) {

		_dropboxRepositoryConfiguration = repositoryConfiguration;
	}

//	protected void setCMISSessionCache(CMISSessionCache cmisSessionCache) {
//		_cmisSessionCache = cmisSessionCache;
//	}

	protected void setCompanyLocalService(
		CompanyLocalService companyLocalService) {

		_companyLocalService = companyLocalService;
	}

	protected void setDLAppHelperLocalService(
		DLAppHelperLocalService dlAppHelperLocalService) {

		_dlAppHelperLocalService = dlAppHelperLocalService;
	}

	protected void setDLFolderLocalService(
		DLFolderLocalService dlFolderLocalService) {

		_dlFolderLocalService = dlFolderLocalService;
	}

	protected void setLockManager(LockManager lockManager) {
		_lockManager = lockManager;
	}

	protected void setRepositoryEntryLocalService(
		RepositoryEntryLocalService repositoryEntryLocalService) {

		_repositoryEntryLocalService = repositoryEntryLocalService;
	}

	protected void setRepositoryLocalService(
		RepositoryLocalService repositoryLocalService) {

		_repositoryLocalService = repositoryLocalService;
	}

	protected void setupRepository(
		long repositoryId,
		com.liferay.portal.kernel.model.Repository repository,
		BaseRepository baseRepository) {

		baseRepository.setAssetEntryLocalService(_assetEntryLocalService);
		baseRepository.setCompanyId(repository.getCompanyId());
		baseRepository.setCompanyLocalService(_companyLocalService);
		baseRepository.setDLAppHelperLocalService(_dlAppHelperLocalService);
		baseRepository.setDLFolderLocalService(_dlFolderLocalService);
		baseRepository.setGroupId(repository.getGroupId());
		baseRepository.setRepositoryEntryLocalService(
			_repositoryEntryLocalService);
		baseRepository.setRepositoryId(repositoryId);
		baseRepository.setTypeSettingsProperties(
			repository.getTypeSettingsProperties());
		baseRepository.setUserLocalService(_userLocalService);
	}

	protected void setUserLocalService(UserLocalService userLocalService) {
		_userLocalService = userLocalService;
	}

	private AssetEntryLocalService _assetEntryLocalService;
	private DropboxRepositoryConfiguration _dropboxRepositoryConfiguration;
//	private final CMISSearchQueryBuilder _cmisSearchQueryBuilder =
//		new BaseCmisSearchQueryBuilder();
//	private CMISSessionCache _cmisSessionCache;
	private CompanyLocalService _companyLocalService;
	private DLAppHelperLocalService _dlAppHelperLocalService;
	private DLFolderLocalService _dlFolderLocalService;
	private LockManager _lockManager;
	private RepositoryEntryLocalService _repositoryEntryLocalService;
	private RepositoryLocalService _repositoryLocalService;
	private UserLocalService _userLocalService;

}