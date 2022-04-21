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

/**
 * Disposable utility. When inherited provides the `dispose` function to its
 * subclass, which is responsible for disposing of any object references
 * when an instance won't be used anymore. Subclasses should override
 * `disposeInternal` to implement any specific disposing logic.
 * @constructor
 */
class Disposable {

	/**
	 * Disposable constructor
	 */
	constructor() {

		/**
		 * Flag indicating if this instance has already been disposed.
		 * @type {boolean}
		 * @protected
		 */
		this._disposed = false;
	}

	/**
	 * Disposes of this instance's object references. Calls `disposeInternal`.
	 */
	dispose() {
		if (!this._disposed) {
			this.disposeInternal();
			this._disposed = true;
		}
	}

	/**
	 * Subclasses should override this method to implement any specific
	 * disposing logic (like clearing references and calling `dispose` on other
	 * disposables).
	 */
	disposeInternal() {}

	/**
	 * Checks if this instance has already been disposed.
	 * @return {boolean}
	 */
	isDisposed() {
		return this._disposed;
	}
}

export default Disposable;
