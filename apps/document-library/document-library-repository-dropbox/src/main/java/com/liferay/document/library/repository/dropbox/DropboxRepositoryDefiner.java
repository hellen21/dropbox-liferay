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

import com.liferay.document.library.repository.dropbox.constant.DropboxRepositoryConstants;
import com.liferay.portal.kernel.repository.RepositoryConfiguration;
import com.liferay.portal.kernel.repository.RepositoryConfigurationBuilder;
import com.liferay.portal.kernel.repository.RepositoryFactory;
import com.liferay.portal.kernel.repository.capabilities.PortalCapabilityLocator;
import com.liferay.portal.kernel.repository.registry.RepositoryDefiner;
import com.liferay.portal.kernel.repository.registry.RepositoryFactoryRegistry;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Adolfo P??rez
 */
@Component(immediate = true, service = RepositoryDefiner.class)
public class DropboxRepositoryDefiner extends BaseDropboxRepositoryDefiner {

	public DropboxRepositoryDefiner() {
		RepositoryConfigurationBuilder repositoryConfigurationBuilder =
			new RepositoryConfigurationBuilder(
				getResourceBundleLoader(),
				//add inputs to repository
				DropboxRepositoryConstants.DROPBOX_REPOSITORY_TOKEN_PARAMETER
			//	DropboxRepositoryConstants.CMIS_ATOMPUB_URL_PARAMETER
				);

		_repositoryConfiguration = repositoryConfigurationBuilder.build();
	}

	@Override
	public String getClassName() {
		return DropboxRepository.class.getName();
	}

	@Override
	public RepositoryConfiguration getRepositoryConfiguration() {
		return _repositoryConfiguration;
	}

	@Override
	public boolean isExternalRepository() {
		return true;
	}

	@Override
	public void registerRepositoryFactory(
		RepositoryFactoryRegistry repositoryFactoryRegistry) {

		repositoryFactoryRegistry.setRepositoryFactory(_repositoryFactory);
	}

	protected PortalCapabilityLocator getPortalCapabilityLocator() {
		return _portalCapabilityLocator;
	}

	@Reference(
		target = "(repository.target.class.name=" + DropboxRepositoryConstants.DROPBOX_REPOSITORY_CLASSNAME + ")",
		unbind = "-"
	)
	protected void setRepositoryFactory(RepositoryFactory repositoryFactory) {
		_repositoryFactory = repositoryFactory;
	}

	@Reference
	private PortalCapabilityLocator _portalCapabilityLocator;

	private final RepositoryConfiguration _repositoryConfiguration;
	private RepositoryFactory _repositoryFactory;

}