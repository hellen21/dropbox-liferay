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

package com.liferay.document.library.repository.dropbox.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Adolfo P??rez
 */
@ExtendedObjectClassDefinition(category = "file-storage")
@Meta.OCD(
	id = "com.liferay.document.library.repository.cmis.configuration.CMISRepositoryConfiguration",
	localization = "content/Language",
	name = "cmis-repository-configuration-name"
)
public interface DropboxRepositoryConfiguration {

	@Meta.AD(
		deflt = "1", description = "delete-depth-description",
		name = "delete-depth-name", required = false
	)
	public int deleteDepth();

}