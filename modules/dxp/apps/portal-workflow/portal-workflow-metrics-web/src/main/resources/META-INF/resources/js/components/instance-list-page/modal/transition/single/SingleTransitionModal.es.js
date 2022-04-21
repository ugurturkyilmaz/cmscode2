/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 */

import ClayAlert from '@clayui/alert';
import ClayButton from '@clayui/button';
import {ClayInput} from '@clayui/form';
import ClayModal, {useModal} from '@clayui/modal';
import React, {useCallback, useContext, useEffect, useState} from 'react';

import {useToaster} from '../../../../../shared/components/toaster/hooks/useToaster.es';
import {useFetch} from '../../../../../shared/hooks/useFetch.es';
import {usePost} from '../../../../../shared/hooks/usePost.es';
import {InstanceListContext} from '../../../InstanceListPageProvider.es';
import {ModalContext} from '../../ModalProvider.es';

export default function SingleTransitionModal() {
	const [comment, setComment] = useState('');
	const {
		closeModal,
		setSingleTransition,
		singleTransition,
		visibleModal,
	} = useContext(ModalContext);
	const {selectedInstance, setSelectedItems} = useContext(
		InstanceListContext
	);
	const {title, transitionName} = singleTransition;
	const [errorToast, setErrorToast] = useState(false);

	const toaster = useToaster();

	const {data, fetchData} = useFetch({
		admin: true,
		params: {completed: false, page: 1, pageSize: 1},
		url: `/workflow-instances/${selectedInstance?.id}/workflow-tasks`,
	});

	const onCloseModal = (refetch) => {
		closeModal(refetch);
		setSelectedItems([]);
		setSingleTransition({
			title: '',
			transitionName: '',
		});
	};

	const {observer, onClose} = useModal({
		onClose: onCloseModal,
	});

	useEffect(() => {
		if (selectedInstance?.id && visibleModal === 'singleTransition') {
			fetchData();
		}
		// eslint-disable-next-line react-hooks/exhaustive-deps
	}, [visibleModal]);

	const taskId = data?.items?.[0].id;

	const {postData} = usePost({
		admin: true,
		body: {comment, transitionName, workflowTaskId: taskId},
		callback: () => {
			toaster.success(
				Liferay.Language.get(
					'the-selected-step-has-transitioned-successfully'
				)
			);

			onCloseModal(true);
		},
		url: `/workflow-tasks/${taskId}/change-transition`,
	});

	const handleDone = useCallback(() => {
		setErrorToast(false);
		postData().catch(() => {
			setErrorToast(
				`${Liferay.Language.get(
					'your-request-has-failed'
				)} ${Liferay.Language.get('select-done-to-retry')}`
			);
		});

		// eslint-disable-next-line react-hooks/exhaustive-deps
	}, [toaster]);

	return (
		<>
			{visibleModal === 'singleTransition' && (
				<ClayModal observer={observer} size="md">
					<ClayModal.Header>{title}</ClayModal.Header>

					{errorToast && (
						<ClayAlert
							className="mb-0"
							displayType="danger"
							title={Liferay.Language.get('error')}
						>
							{errorToast}
						</ClayAlert>
					)}

					<ClayModal.Body>
						<label htmlFor="commentTextArea">
							{Liferay.Language.get('comment')}
						</label>

						<ClayInput
							component="textarea"
							id="commentTextArea"
							onChange={({target}) => setComment(target.value)}
							placeholder={Liferay.Language.get('comment')}
							type="text"
						/>
					</ClayModal.Body>

					<ClayModal.Footer
						last={
							<>
								<ClayButton
									className="mr-3"
									displayType="secondary"
									onClick={onClose}
								>
									{Liferay.Language.get('cancel')}
								</ClayButton>

								<ClayButton onClick={handleDone}>
									{Liferay.Language.get('done')}
								</ClayButton>
							</>
						}
					/>
				</ClayModal>
			)}
		</>
	);
}
