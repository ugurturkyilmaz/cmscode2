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

import Disposable from './Disposable';

/**
 * EventHandle utility. Holds information about an event subscription, and
 * allows removing them easily.
 * EventHandle is a Disposable, but it's important to note that the
 * EventEmitter that created it is not the one responsible for disposing it.
 * That responsibility is for the code that holds a reference to it.
 * @extends {Disposable}
 */
class EventHandle extends Disposable {

	/**
	 * EventHandle constructor
	 * @param {!EventEmitter} emitter Emitter the event was subscribed to.
	 * @param {string} event The name of the event that was subscribed to.
	 * @param {!Function} listener The listener subscribed to the event.
	 */
	constructor(emitter, event, listener) {
		super();

		/**
		 * The EventEmitter instance that the event was subscribed to.
		 * @type {EventEmitter}
		 * @protected
		 */
		this._emitter = emitter;

		/**
		 * The name of the event that was subscribed to.
		 * @type {string}
		 * @protected
		 */
		this._event = event;

		/**
		 * The listener subscribed to the event.
		 * @type {Function}
		 * @protected
		 */
		this._listener = listener;
	}

	/**
	 * Disposes of this instance's object references.
	 * @override
	 */
	disposeInternal() {
		this.removeListener();
		this._emitter = null;
		this._listener = null;
	}

	/**
	 * Removes the listener subscription from the emitter.
	 */
	removeListener() {
		if (!this._emitter.isDisposed()) {
			this._emitter.removeListener(this._event, this._listener);
		}
	}
}

export default EventHandle;
