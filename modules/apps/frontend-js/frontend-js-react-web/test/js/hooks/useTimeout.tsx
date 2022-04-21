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

/* eslint-disable @liferay/use-state-naming-pattern */

import {cleanup, fireEvent, render} from '@testing-library/react';
import React from 'react';

import useTimeout from '../../../src/main/resources/META-INF/resources/js/hooks/useTimeout';

const {useState} = React;

const INTERVAL = 100;

type Callback = ReturnType<typeof jest.fn>;
type Delay = ReturnType<typeof useTimeout>;

const Component = ({
	callback,
	onDelay,
	onRender,
}: {
	callback?: Callback;
	onDelay?: (callback: () => void) => void;
	onRender?: (delay: Delay) => void;
}) => {
	const delay = useTimeout();

	if (onRender) {
		onRender(delay);
	}

	const [, forceUpdate] = useState(0);

	const invoke = () => {
		if (callback) {
			const cancel = delay(callback, INTERVAL);

			if (onDelay) {
				onDelay(cancel);
			}
		}
	};

	return (
		<>
			<button onClick={invoke}>Invoke</button>
			<button onClick={() => forceUpdate((count) => count + 1)}>
				Update
			</button>
		</>
	);
};

describe('useTimeout()', () => {
	beforeEach(() => jest.useFakeTimers());

	afterEach(cleanup);

	it('runs a function after a delay', () => {
		const fn = jest.fn();

		const {getByText} = render(<Component callback={fn} />);

		fireEvent.click(getByText('Invoke'));

		expect(fn).not.toHaveBeenCalled();

		jest.runAllTimers();

		expect(fn).toHaveBeenCalled();
	});

	it('returns a function that cancels the timeout', () => {
		const fn = jest.fn();

		let cancel: () => void;

		const onDelay = (callback: () => void): void => {
			cancel = callback;
		};

		const {getByText} = render(
			<Component callback={fn} onDelay={onDelay} />
		);

		fireEvent.click(getByText('Invoke'));

		expect(fn).not.toHaveBeenCalled();

		cancel!();

		jest.runAllTimers();

		expect(fn).not.toHaveBeenCalled();
	});

	it('does not run if unmounted', () => {
		const fn = jest.fn();

		const {getByText, unmount} = render(<Component callback={fn} />);

		fireEvent.click(getByText('Invoke'));

		unmount();

		jest.runAllTimers();

		expect(fn).not.toHaveBeenCalled();
	});

	it('does not run if unmounted and then remounted', () => {
		const fn = jest.fn();

		const {getByText, unmount} = render(<Component callback={fn} />);

		fireEvent.click(getByText('Invoke'));

		unmount();

		render(<Component callback={fn} />);

		jest.runAllTimers();

		expect(fn).not.toHaveBeenCalled();
	});

	it('preserves the identity of the delay function', () => {
		const functions: Array<Delay> = [];

		const {getByText} = render(
			<Component onRender={(delay) => functions.push(delay)} />
		);

		expect(functions.length).toBe(1);

		fireEvent.click(getByText('Update'));

		expect(functions.length).toBe(2);

		expect(functions[0]).toBe(functions[1]);
	});
});
