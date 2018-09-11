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

import com.liferay.document.library.repository.dropbox.search.DropboxRepositoryHandler;
import com.liferay.document.library.repository.dropbox.search.Session;
//import com.liferay.document.library.repository.cmis.CMISRepositoryHandler;
//import com.liferay.document.library.repository.cmis.Session;
//import com.liferay.document.library.repository.cmis.internal.constants.CMISRepositoryConstants;
import com.liferay.portal.kernel.exception.InvalidRepositoryException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;

import java.util.HashMap;
import java.util.Map;

//import org.apache.chemistry.opencmis.commons.SessionParameter;
//import org.apache.chemistry.opencmis.commons.enums.BindingType;

/**
 * @author Alexander Chow
 */
public class DropboxRepository extends DropboxRepositoryHandler {

	//registro del repositorio a crear
	@Override
	public Session getSession() throws PortalException {
		Map<String, String> parameters = new HashMap<>();

		//change binding type
//		parameters.put(
//			SessionParameter.ATOMPUB_URL,
//			getTypeSettingsValue(
//				DropboxRepositoryConstants.CMIS_ATOMPUB_URL_PARAMETER));
//		parameters.put(
//			SessionParameter.BINDING_TYPE, BindingType.ATOMPUB.value());
//		parameters.put(SessionParameter.COMPRESSION, Boolean.TRUE.toString());
//
//		Locale locale = LocaleUtil.getSiteDefault();
//
//		parameters.put(
//			SessionParameter.LOCALE_ISO3166_COUNTRY, locale.getCountry());
//		parameters.put(
//			SessionParameter.LOCALE_ISO639_LANGUAGE, locale.getLanguage());

		String login = getLogin();
		String password = null;

		if (Validator.isNotNull(login)) {
			password = PrincipalThreadLocal.getPassword();
		}
//		else {
//			login = _DL_REPOSITORY_GUEST_USERNAME;
//			password = _DL_REPOSITORY_GUEST_PASSWORD;
//		}

//		parameters.put(SessionParameter.PASSWORD, password);
//		parameters.put(SessionParameter.USER, login);

		Thread thread = Thread.currentThread();

		ClassLoader contextClassLoader = thread.getContextClassLoader();

		Class<?> clazz = getClass();

		ClassLoader classLoader = clazz.getClassLoader();

		thread.setContextClassLoader(classLoader);

		try {
//			CMISRepositoryUtil.checkRepository(
//				getRepositoryId(), parameters, getTypeSettingsProperties(),
//				CMISRepositoryConstants.CMIS_ATOMPUB_REPOSITORY_ID_PARAMETER);
//
//			return CMISRepositoryUtil.createSession(parameters);
		}
		finally {
			thread.setContextClassLoader(contextClassLoader);
		}
		return null;
	}

	protected String getTypeSettingsValue(String typeSettingsKey)
		throws InvalidRepositoryException {

		UnicodeProperties typeSettingsProperties = getTypeSettingsProperties();
		return typeSettingsKey;

//		return CMISRepositoryUtil.getTypeSettingsValue(
//			typeSettingsProperties, typeSettingsKey);
	}

//	private static final String _DL_REPOSITORY_GUEST_PASSWORD =
//		GetterUtil.getString(
//			PropsUtil.get(PropsKeys.DL_REPOSITORY_GUEST_PASSWORD));
//
//	private static final String _DL_REPOSITORY_GUEST_USERNAME =
//		GetterUtil.getString(
//			PropsUtil.get(PropsKeys.DL_REPOSITORY_GUEST_USERNAME));

}