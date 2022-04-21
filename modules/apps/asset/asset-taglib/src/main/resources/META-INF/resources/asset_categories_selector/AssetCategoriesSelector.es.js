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

import PropTypes from 'prop-types';
import React from 'react';

import AssetVocabularyCategoriesSelector from './AssetVocabularyCategoriesSelector.es';

function AssetCategoriesSelector({
	eventName,
	groupIds,
	id,
	inputName,
	onVocabulariesChange,
	portletURL,
	useFallbackInput,
	vocabularies = [],
}) {
	return (
		<div id={id}>
			{vocabularies.map((vocabulary, index) => {
				const label = vocabulary.group
					? `${vocabulary.title} (${vocabulary.group})`
					: vocabulary.title;
				const vocabularyInputName = inputName + vocabulary.id;

				return (
					<AssetVocabularyCategoriesSelector
						eventName={eventName}
						groupIds={groupIds}
						id={`namespace_assetCategoriesSelector_${vocabulary.id}`}
						inputName={vocabularyInputName}
						key={vocabulary.id}
						label={label}
						onSelectedItemsChange={(selectedItems) => {
							const newVocabulary = {
								...vocabulary,
								selectedItems,
							};

							onVocabulariesChange([
								...vocabularies.slice(0, index),
								newVocabulary,
								...vocabularies.slice(index + 1),
							]);
						}}
						portletURL={portletURL}
						required={vocabulary.required}
						selectedItems={vocabulary.selectedItems}
						singleSelect={vocabulary.singleSelect}
						sourceItemsVocabularyIds={[vocabulary.id]}
						useFallbackInput={useFallbackInput}
					/>
				);
			})}
		</div>
	);
}

AssetCategoriesSelector.propTypes = {
	eventName: PropTypes.string.isRequired,
	groupIds: PropTypes.array.isRequired,
	id: PropTypes.string.isRequired,
	inputName: PropTypes.string.isRequired,
	label: PropTypes.string,
	onVocabulariesChange: PropTypes.func.isRequired,
	portletURL: PropTypes.string.isRequired,
	useFallbackInput: PropTypes.bool,
};

export default AssetCategoriesSelector;
