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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.repository.DocumentRepository;
import com.liferay.portal.kernel.repository.capabilities.PortalCapabilityLocator;
import com.liferay.portal.kernel.repository.capabilities.ProcessorCapability;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.repository.registry.BaseRepositoryDefiner;
import com.liferay.portal.kernel.repository.registry.CapabilityRegistry;
import com.liferay.portal.kernel.util.CacheResourceBundleLoader;
import com.liferay.portal.kernel.util.ClassResourceBundleLoader;
import com.liferay.portal.kernel.util.ResourceBundleLoader;
import com.liferay.portal.kernel.util.ResourceBundleUtil;

import java.util.Locale;
import java.util.ResourceBundle;

//import org.apache.chemistry.opencmis.client.api.Document;

/**
 * @author Adolfo P??rez
 */
public abstract class BaseDropboxRepositoryDefiner extends BaseRepositoryDefiner {

	@Override
	public String getRepositoryTypeLabel(Locale locale) {
		ResourceBundle resourceBundle =
			_resourceBundleLoader.loadResourceBundle(locale);

		return ResourceBundleUtil.getString(
			resourceBundle, _MODEL_RESOURCE_NAME_PREFIX + getClassName());
	}

	@Override
	public void registerCapabilities(
		CapabilityRegistry<DocumentRepository> capabilityRegistry) {

		DocumentRepository documentRepository = capabilityRegistry.getTarget();

		PortalCapabilityLocator portalCapabilityLocator =
			getPortalCapabilityLocator();

		capabilityRegistry.addSupportedCapability(
			ProcessorCapability.class,
			new RefreshingProcessorCapability(
				portalCapabilityLocator.getProcessorCapability(
					documentRepository,
					ProcessorCapability.ResourceGenerationStrategy.REUSE)));
	}

	protected abstract PortalCapabilityLocator getPortalCapabilityLocator();

	protected ResourceBundleLoader getResourceBundleLoader() {
		return _resourceBundleLoader;
	}

	private static final String _MODEL_RESOURCE_NAME_PREFIX = "model.resource.";

	private final ResourceBundleLoader _resourceBundleLoader =
		new CacheResourceBundleLoader(
			new ClassResourceBundleLoader(
				"content.Language", BaseDropboxRepositoryDefiner.class));

	private static class RefreshingProcessorCapability
		implements ProcessorCapability {

		public RefreshingProcessorCapability(
			ProcessorCapability processorCapability) {

			_processorCapability = processorCapability;
		}

		@Override
		public void cleanUp(FileEntry fileEntry) throws PortalException {
			_refresh(fileEntry);

			_processorCapability.cleanUp(fileEntry);
		}

		@Override
		public void cleanUp(FileVersion fileVersion) throws PortalException {
			_refresh(fileVersion);

			_processorCapability.cleanUp(fileVersion);
		}

		@Override
		public void copy(FileEntry fileEntry, FileVersion fileVersion)
			throws PortalException {

			_refresh(fileEntry);
			_refresh(fileVersion);

			_processorCapability.copy(fileEntry, fileVersion);
		}

		@Override
		public void generateNew(FileEntry fileEntry) throws PortalException {
			_refresh(fileEntry);

			_processorCapability.generateNew(fileEntry);
		}

		private void _refresh(FileEntry fileEntry) {
//			Document document = (Document)fileEntry.getModel();
//
//			document.refresh();
		}

		private void _refresh(FileVersion fileVersion) throws PortalException {
//			Document document = (Document)fileVersion.getModel();
//
//			document.refresh();
//
//			_refresh(fileVersion.getFileEntry());
		}

		private final ProcessorCapability _processorCapability;

	}

}