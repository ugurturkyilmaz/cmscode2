window.${namespace}showVersionDetailsDialog = function (saveURL) {
	Liferay.componentReady(
		'${namespace}DocumentLibraryCheckinModal'
	).then(function(documentLibraryCheckinModal) {
		documentLibraryCheckinModal.open(function(versionIncrease, changeLog) {
			var portletURL = saveURL;

			if (versionIncrease) {
				portletURL += '&${namespace}versionIncrease=' + encodeURIComponent(versionIncrease);
			}

			if (changeLog) {
				portletURL += '&${namespace}changeLog=' + encodeURIComponent(changeLog);
			}

			portletURL += '&${namespace}updateVersionDetails=true'

			window.location.href = portletURL;
		});
	})
};