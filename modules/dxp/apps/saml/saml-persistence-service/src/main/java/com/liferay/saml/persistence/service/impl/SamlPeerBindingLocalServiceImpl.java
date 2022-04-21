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

package com.liferay.saml.persistence.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.saml.persistence.model.SamlPeerBinding;
import com.liferay.saml.persistence.service.base.SamlPeerBindingLocalServiceBaseImpl;

import org.osgi.service.component.annotations.Component;

/**
 * @author Mika Koivisto
 */
@Component(
	property = "model.class.name=com.liferay.saml.persistence.model.SamlPeerBinding",
	service = AopService.class
)
public class SamlPeerBindingLocalServiceImpl
	extends SamlPeerBindingLocalServiceBaseImpl {

	@Override
	public SamlPeerBinding addSamlPeerBinding(
			long userId, String samlNameIdFormat,
			String samlNameIdNameQualifier, String samlNameIdSpNameQualifier,
			String samlNameIdSpProvidedId, String samlNameIdValue,
			String samlPeerEntityId)
		throws PortalException {

		User user = userLocalService.getUserById(userId);

		SamlPeerBinding samlPeerBinding = samlPeerBindingPersistence.create(
			counterLocalService.increment(SamlPeerBinding.class.getName()));

		samlPeerBinding.setCompanyId(user.getCompanyId());
		samlPeerBinding.setUserId(user.getUserId());
		samlPeerBinding.setUserName(user.getFullName());
		samlPeerBinding.setSamlNameIdFormat(samlNameIdFormat);
		samlPeerBinding.setSamlNameIdSpNameQualifier(samlNameIdSpNameQualifier);
		samlPeerBinding.setSamlNameIdSpProvidedId(samlNameIdSpProvidedId);
		samlPeerBinding.setSamlNameIdValue(samlNameIdValue);
		samlPeerBinding.setSamlPeerEntityId(samlPeerEntityId);

		return samlPeerBindingPersistence.update(samlPeerBinding);
	}

	@Override
	public SamlPeerBinding fetchSamlPeerBinding(
		long companyId, String samlNameIdFormat, String samlNameIdNameQualifier,
		String samlNameIdValue, String samlSpEntityId) {

		return samlPeerBindingPersistence.fetchByC_D_SNIF_SNINQ_SNIV_SPEI_First(
			companyId, false, samlNameIdFormat, samlNameIdNameQualifier,
			samlNameIdValue, samlSpEntityId, null);
	}

}