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

package com.liferay.talend.wizard;

import com.liferay.talend.properties.connection.LiferayConnectionProperties;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.talend.components.api.properties.ComponentProperties;
import org.talend.components.api.wizard.AbstractComponentWizardDefintion;
import org.talend.components.api.wizard.ComponentWizard;
import org.talend.components.api.wizard.WizardImageType;
import org.talend.daikon.definition.DefinitionImageType;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
@SuppressWarnings("deprecation")
public class LiferaySchemaWizardDefinition
	extends AbstractComponentWizardDefintion {

	public static final String COMPONENT_WIZARD_NAME = "LiferaySchemaWizard";

	@Override
	public ComponentWizard createWizard(
		ComponentProperties componentProperties, String location) {

		if (componentProperties != null) {
			if (!Objects.equals("connection", componentProperties.getName())) {
				if (_logger.isDebugEnabled()) {
					_logger.debug(
						"Adjust component properties name to `connection`");
				}

				componentProperties.setName("connection");
			}

			return new LiferaySchemaWizard(this, componentProperties, location);
		}

		if (_logger.isInfoEnabled()) {
			_logger.info("Create wizard with new component properties");
		}

		LiferayConnectionProperties liferayConnectionProperties =
			new LiferayConnectionProperties("connection");

		liferayConnectionProperties.init();

		return new LiferaySchemaWizard(
			this, liferayConnectionProperties, location);
	}

	@Override
	public ComponentWizard createWizard(String location) {
		return createWizard(null, location);
	}

	@Override
	public String getIconKey() {
		return null;
	}

	@Override
	public String getImagePath(DefinitionImageType definitionImageType) {
		if (definitionImageType == DefinitionImageType.TREE_ICON_16X16) {
			return "table.gif";
		}
		else if (definitionImageType ==
					DefinitionImageType.WIZARD_BANNER_75X66) {

			return "LiferayWizard_banner_75x66.png";
		}

		return null;
	}

	@Override
	public String getName() {
		return COMPONENT_WIZARD_NAME;
	}

	/**
	 * @see    org.talend.components.api.wizard.ComponentWizardDefinition#getPngImagePath(
	 *         WizardImageType)
	 * @review
	 */
	@Override
	public String getPngImagePath(WizardImageType wizardImageType) {
		if (wizardImageType == WizardImageType.TREE_ICON_16X16) {
			return getImagePath(DefinitionImageType.TREE_ICON_16X16);
		}
		else if (wizardImageType == WizardImageType.WIZARD_BANNER_75X66) {
			return getImagePath(DefinitionImageType.WIZARD_BANNER_75X66);
		}

		return null;
	}

	@Override
	public boolean supportsProperties(
		Class<? extends ComponentProperties> propertiesClass) {

		return propertiesClass.isAssignableFrom(
			LiferayConnectionProperties.class);
	}

	private static final Logger _logger = LoggerFactory.getLogger(
		LiferaySchemaWizardDefinition.class);

}