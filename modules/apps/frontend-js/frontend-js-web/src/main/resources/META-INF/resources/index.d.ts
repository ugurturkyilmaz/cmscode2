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

/* Debounces function execution. */
export function debounce(fn: () => void, delay: number): () => void;

/* Cancels the scheduled debounced function. */
export function cancelDebounce(debounced: () => void): void;

/**
 * Listens to the specified event on the given DOM element, but only calls the
 * given callback listener when it's triggered by elements that match the
 * given selector or target element.
 */
export function delegate(
	element: Element,
	eventName: string,
	selector: string,
	callback: (event: any) => void
): {dispose: () => void};

export class AOP {

	/**
	 * Constructor for AOP class.
	 */
	constructor(object: object, fnName: string);

	/**
	 * Array of listeners that will invoke after the displaced function.
	 */
	after_: string[];

	/**
	 * Array of listeners that will invoke before the displaced function.
	 */
	before_: string[];

	/**
	 * The name of the displaced function.
	 */
	fnName_: string;

	/**
	 * The displaced function.
	 */
	fn_: () => void;

	/**
	 * The object hosting the method to displace.
	 */
	obj_: object;

	/**
	 * Creates handle for detaching listener from displaced function.
	 */
	createHandle(fn: () => void, before: boolean): object;

	/**
	 * Detaches listener from displaced function.
	 */
	detach_(fn: () => void, before: boolean): void;

	exec(...args: any[]): any;

	/**
	 * Registers an AOP listener.
	 */
	register(fn: () => void, before: boolean): EventHandle;

	/**
	 * Executes the supplied method after the specified function.
	 */
	static after(fn: () => void, object: object, fnName: string): EventHandle;

	/**
	 * Return an alterReturn object when you want to change the result returned
	 * from the core method to the caller.
	 */
	static alterReturn(value: any): {type: string; value: any};

	/**
	 * Executes the supplied method before the specified function.
	 */
	static before(fn: () => void, object: object, fnName: string): EventHandle;

	/**
	 * Return a halt object when you want to terminate the execution
	 * of all subsequent subscribers as well as the wrapped method
	 * if it has not executed yet.
	 */
	static halt(value: any): {type: string; value: any};

	/**
	 * Executes the supplied method before or after the specified function.
	 */
	static inject(
		before: boolean,
		fn: () => void,
		object: object,
		fnName: string
	): EventHandle;

	/**
	 * Returns object which instructs `exec` method to modify the return
	 * value or prevent default behavior of wrapped function.
	 */
	static modify_(type: string, value: any): {type: string; value: any};

	/**
	 * Return a prevent object when you want to prevent the wrapped function
	 * from executing, but want the remaining listeners to execute.
	 */
	static prevent(): {type: string; value: any};
}

export class AutoSize {
	constructor(inputElement: HTMLElement);

	createTemplate(computedStyle: {
		fontFamily: string;
		fontSize: string;
		fontStyle: string;
		fontWeight: string;
		letterSpacing: string;
		lineHeight: string;
		textTransform: string;
	}): HTMLPreElement;

	handleInput(event: string): void;
}

export class Disposable {

	/**
	 * Disposes of this instance's object references. Calls `disposeInternal`.
	 */
	dispose(): void;

	/**
	 * Checks if this instance has already been disposed.
	 */
	isDisposed(): boolean;
}

export class EventEmitter {

	/**
	 * Adds a listener to the end of the listeners array for the specified events.
	 */
	addListener(
		event: string | string[],
		listener: () => void,
		defaultListener: boolean
	): EventHandle;
}

export class EventHandle {

	/**
	 * EventHandle constructor
	 */
	constructor(emitter: EventEmitter, event: string, listener: () => void);

	disposeInternal(): void;

	/**
	 * Removes the listener subscription from the emitter.
	 */
	removeListener(): void;
}

/**
 * EventHandler utility. It's useful for easily removing a group of
 * listeners from different EventEmitter instances.
 */
export class EventHandler {

	/**
	 * Adds a listener to the end of the listeners array for the specified events.
	 */
	addListener(
		event: string | string[],
		listener: () => void,
		defaultListener: boolean
	): EventHandle;

	/**
	 * Disposes of this instance's object references.
	 */
	disposeInternal(): void;

	/**
	 * Execute each of the listeners in order with the supplied arguments.
	 */
	emit(event: string, ...opt_args: any[]): boolean;

	/**
	 * Gets the configuration option which determines if an event facade should
	 * be sent as a param of listeners when emitting events. If set to true, the
	 * facade will be passed as the first argument of the listener.
	 */
	getShouldUseFacade(): boolean;

	/**
	 * Returns an array of listeners for the specified event.
	 */
	listeners(event: string): any[];

	/**
	 * Adds a listener that will be invoked a fixed number of times for the
	 * events. After each event is triggered the specified amount of times, the
	 * listener is removed for it.
	 */
	many(
		event: string | string[],
		amount: number,
		listener: () => void
	): EventHandle;

	/**
	 * Removes a listener for the specified events.
	 * Caution: changes array indices in the listener array behind the listener.
	 */
	off(event: string | string[], listener: () => void): EventEmitter;

	/**
	 * Adds a listener to the end of the listeners array for the specified events.
	 */
	on(events: string | string[], listener: () => void): EventHandle;

	/**
	 * Adds handler that gets triggered when an event is listened to on this
	 * instance.
	 */
	onListener(handler: () => void): void;

	/**
	 * Adds a one time listener for the events. This listener is invoked only the
	 * next time each event is fired, after which it is removed.
	 */
	once(event: string | string[], listener: () => void): EventHandle;

	/**
	 * Removes all listeners, or those of the specified events. It's not a good
	 * idea to remove listeners that were added elsewhere in the code,
	 * especially when it's on an emitter that you didn't create.
	 */
	removeAllListeners(event?: string | string[]): EventEmitter;

	/**
	 * Removes a listener for the specified events.
	 * Caution: changes array indices in the listener array behind the listener.
	 */
	removeListener(
		events: string | string[],
		listener: () => void
	): EventEmitter;

	/**
	 * Sets the configuration option which determines if an event facade should
	 * be sent as a param of listeners when emitting events. If set to true, the
	 * facade will be passed as the first argument of the listener.
	 */
	setShouldUseFacade(shouldUseFacade: boolean): EventEmitter;
}

/**
 * Decodes the update strings.
 * The update string is a JSON object containing the entire page state.
 * This decoder returns an object containing the portlet data for portlets whose
 * state has changed as compared to the current page state.
 */
export function decodeUpdateString(
	pageRenderState: object,
	updateString: string
): object;

/**
 * Function to extract data from form and encode
 * it as an 'application/x-www-form-urlencoded' string.
 */
export function encodeFormAsString(
	portletId: string,
	form: HTMLFormElement
): string;

/**
 * Generates the required options for an action URL request
 * according to the portletId, action URL and optional form element.
 */
export function generateActionUrl(
	portletId: string,
	url: string,
	form: HTMLFormElement
): object;

/**
 * Helper for generating portlet mode & window state strings for the URL.
 */
export function generatePortletModeAndWindowStateString(
	pageRenderState: object,
	portletId: string
): string;

/**
 * Gets the updated public parameters for the given portlet ID and new render state.
 * Returns an object whose properties are the group indexes of the
 * updated public parameters. The values are the new public parameter values.
 */
export function getUpdatedPublicRenderParameters(
	pageRenderState: object,
	portletId: string,
	state: RenderState
): object;

/**
 * Returns a URL of the specified type.
 */
export function getUrl(
	pageRenderState: object,
	type: string,
	portletId: string,
	parameters: object,
	cache: string,
	resourceId: string
): Promise<string>;

/**
 * Used by the portlet hub methods to check the number and types of the
 * arguments.
 */
export function validateArguments(
	args: string[],
	min: number,
	max: number,
	types: string[]
): void;

/**
 * Validates an HTMLFormElement
 */
export function validateForm(form: HTMLFormElement): void;

/**
 * Verifies that the input parameters are in valid format.
 *
 * Parameters must be an object containing parameter names. It may also
 * contain no property names which represents the case of having no
 * parameters.
 *
 * If properties are present, each property must refer to an array of string
 * values. The array length must be at least 1, because each parameter must
 * have a value. However, a value of 'null' may appear in any array entry.
 *
 * To represent a <code>null</code> value, the property value must equal [null].
 */
export function validateParameters(parameters: object): void;

/**
 * Validates the specificed portletId against the list
 * of current portlet in the pageRenderState.
 */
export function validatePortletId(
	portletId: string,
	pageRenderState: object
): boolean;

/**
 * Verifies that the input parameters are in valid format, that the portlet
 * mode and window state values are allowed for the portlet.
 */
export function validateState(state: RenderState, portletData: object): void;

export class RenderState {
	constructor(state: any);

	/**
	 * Clone returns a copy of this RenderState instance.
	 */
	clone(): RenderState;

	/**
	 * Set the properties of a RenderState instance based on another RenderState
	 */
	from(renderState: RenderState): void;

	/**
	 * Returns the portletMode for this RenderState.
	 */
	getPortletMode(): string;

	/**
	 * Returns the string parameter value for the given name.
	 * If name designates a multi-valued parameter this function returns
	 * the first value in the values array. If the parameter is undefined
	 * this function returns the optional default parameter <code>defaultValue</code>.
	 */
	getValue(name: string, defaultValue?: string): string;

	/**
	 * Gets the string array parameter value for the given <code>name</code>.
	 * If the parameter for the given name is undefined, this function
	 * returns the optional default value array <code>def</code>.
	 */
	getValues(name: string, defaultValue?: string[]): string[];

	/**
	 * Returns the windowState for this RenderState.
	 */
	getWindowState(): string;

	/**
	 * Removes the parameter with the given name.
	 */
	remove(name: string): void;

	/**
	 * Sets the portletMode to the specified value.
	 */
	setPortletMode(portletMode: string): void;

	/**
	 * Sets a parameter with a given name and value.
	 * The value may be a string or an array.
	 */
	setValue(name: string, value: string | string[]): void;

	/**
	 * Sets the windowState to the specified value.
	 */
	setWindowState(windowState: string): void;
}

/**
 * Minimizes portlet
 */
export function minimizePortlet(
	portletSelector: string,
	trigger: HTMLElement,
	options?: object
): void;

export class PortletInit {
	constructor(portletId: string);

	/**
	 * Initiates a portlet action using the specified action parameters and
	 * element arguments.
	 */
	action(...args: any[]): Promise<void>;

	/**
	 * Adds a listener function for specified event type.
	 */
	addEventListener(type: string, handler: () => void): object;

	/**
	 * Returns a promise for a resource URL with parameters set appropriately
	 * for the page state according to the resource parameters, cacheability
	 * option, and resource ID provided.
	 */
	createResourceUrl(
		parameters: object,
		cache: string,
		resourceId: string
	): Promise<string>;

	/**
	 * Dispatches a client event.
	 */
	dispatchClientEvent(type: string, payload: any): number;

	/**
	 * Tests whether a blocking operation is in progress.
	 */
	isInProgress(): boolean;

	/**
	 * Creates and returns a new PortletParameters object.
	 */
	newParameters(optParameters?: object): object;

	/**
	 * Creates and returns a new RenderState object.
	 */
	newState(optState?: RenderState): RenderState;

	/**
	 * Removes a previously added listener function designated by the handle.
	 * The handle must be the same object previously returned by the
	 * addEventListener function.
	 */
	removeEventListener(handle: any): void;

	/**
	 * Sets the render state, which consists of the public and private render
	 * parameters, the portlet mode, and the window state.
	 */

	setRenderState(state: RenderState): void;

	/**
	 * Starts partial action processing and returns a promise for the result.
	 */
	startPartialAction(actionParameters: object): Promise<void>;
}

/**
 * Registers a portlet client with the portlet hub.
 */
export function register(portletId: string): Promise<void>;

/**
 * Aligns the element with the best region around alignElement. The best
 * region is defined by clockwise rotation starting from the specified
 * `position`. The element is always aligned in the middle of alignElement
 * axis.
 */
export function align(
	element: HTMLElement,
	alignElement: HTMLElement,
	position: number,
	autoBestAlign: boolean
): string;

interface Region {
	bottom: number;
	height: number;
	left: number;
	right: number;
	top: number;
	width: number;
}

/**
 * Returns the best region to align element with alignElement. This is similar
 * to `suggestAlignBestRegion`, but it only returns the region information,
 * while `suggestAlignBestRegion` also returns the chosen position.
 */
export function getAlignBestRegion(
	element: HTMLElement,
	alignElement: HTMLElement,
	position: number
): {
	position: number;
	region: Region;
};

/**
 * Returns the region to align element with alignElement. The element is
 * always aligned in the middle of alignElement axis.
 */
export function getAlignRegion(
	element: HTMLElement,
	alignElement: HTMLElement,
	position: number
): Region;

/**
 * Looks for the best region for aligning the given element. The best
 * region is defined by clockwise rotation starting from the specified
 * `position`. The element is always aligned in the middle of alignElement
 * axis.
 */
export function suggestAlignBestRegion(
	element: HTMLElement,
	alignElement: HTMLElement,
	position: number
): {
	position: number;
	region: Region;
};

/**
 * Adds compatibility for YUI events, re-emitting events according to YUI naming
 * and adding the capability of adding targets to bubble events to them.
 */
export class CompatibilityEventProxy {
	constructor(config: object, element: HTMLElement);

	/**
	 * Registers another event target as a bubble target.
	 */
	addTarget(target: object): void;
}

/**
 * Appends list item elements to dropdown menus with inline-scrollers on scroll
 * events to improve page loading performance.
 */
export class DynamicInlineScroll {
	attached(): void;

	created(): void;

	detached(): void;
}

export class DynamicSelect {
	constructor(array: object[]);
}

export class PortletBase {

	/**
	 * Returns a Node List containing all the matching element nodes within the
	 * subtrees of the root object, in tree order. If there are no matching
	 * nodes, the method returns an empty Node List.
	 */
	all(selector: string, root?: string | HTMLElement): HTMLElement[];

	/**
	 * Appends the portlet's namespace to the given string or object properties.
	 */
	ns(object: object | string): object | string;

	/**
	 * Returns the first matching Element node within the subtrees of the
	 * root object. If there is no matching Element, the method returns null.
	 */
	one(selectors: string, root?: string | HTMLElement): HTMLElement | null;
}

/**
 * Throttle implementation that fires on the leading and trailing edges.
 * If multiple calls come in during the throttle interval, the last call's
 * arguments and context are used, replacing those of any previously pending
 * calls.
 */
export default function throttle(fn: () => void, interval: number): () => void;
