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

export type TName = {
	[key: string]: string;
};

export type TObjectLayout = {
	defaultObjectLayout: boolean;
	name: TName;
	objectLayoutTabs: TObjectLayoutTab[];
};

export type TObjectLayoutTab = {
	name: TName;
	objectLayoutBoxes: TObjectLayoutBox[];
	objectRelationshipId: number;
	priority: number;
};

export type TObjectLayoutBox = {
	collapsable: boolean;
	name: TName;
	objectLayoutRows: TObjectLayoutRow[];
	priority: number;
};

export type TObjectLayoutRow = {
	objectLayoutColumns: TObjectLayoutColumn[];
	priority: number;
};

export type TObjectLayoutColumn = {
	objectFieldId: number;
	priority: number;
	size: number;
};

export type TObjectField = {
	DBType: string;
	businessType: string;
	id: number;
	indexed: boolean;
	indexedAsKeyword: boolean;
	indexedLanguageId: string;
	inLayout?: boolean; // eslint-disable-line @typescript-eslint/member-ordering
	label: TName;
	listTypeDefinitionId: boolean;
	name: string;
	required: boolean;
};

export type TObjectRelationship = {
	id: number;
	inLayout?: boolean;
	label: TName;
	name: string;
	objectDefinitionId1: number;
	objectDefinitionId2: number;
	type: string;
};
