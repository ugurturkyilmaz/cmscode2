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

import Component from 'metal-component';
import Soy from 'metal-soy';

import template from './MiniumActionsMenu.soy';

class MiniumActionsMenu extends Component {
	_handleToggle(event) {
		const actions = this.refs.actions;
		const row = event.target.closest('tr');
		const width = actions.getBoundingClientRect().width;
		row.style.setProperty('--translate-space', `${width - 10}px`);
		row.classList.toggle('is-active');
	}
}

Soy.register(MiniumActionsMenu, template);

export {MiniumActionsMenu};
export default MiniumActionsMenu;
