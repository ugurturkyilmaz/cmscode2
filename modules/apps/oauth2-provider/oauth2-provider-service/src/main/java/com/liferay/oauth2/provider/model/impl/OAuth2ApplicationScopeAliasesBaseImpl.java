/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.oauth2.provider.model.impl;

import com.liferay.oauth2.provider.model.OAuth2ApplicationScopeAliases;
import com.liferay.oauth2.provider.service.OAuth2ApplicationScopeAliasesLocalServiceUtil;

/**
 * The extended model base implementation for the OAuth2ApplicationScopeAliases service. Represents a row in the &quot;OAuth2ApplicationScopeAliases&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link OAuth2ApplicationScopeAliasesImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OAuth2ApplicationScopeAliasesImpl
 * @see OAuth2ApplicationScopeAliases
 * @generated
 */
public abstract class OAuth2ApplicationScopeAliasesBaseImpl
	extends OAuth2ApplicationScopeAliasesModelImpl
	implements OAuth2ApplicationScopeAliases {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a o auth2 application scope aliases model instance should use the <code>OAuth2ApplicationScopeAliases</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			OAuth2ApplicationScopeAliasesLocalServiceUtil.
				addOAuth2ApplicationScopeAliases(this);
		}
		else {
			OAuth2ApplicationScopeAliasesLocalServiceUtil.
				updateOAuth2ApplicationScopeAliases(this);
		}
	}

}