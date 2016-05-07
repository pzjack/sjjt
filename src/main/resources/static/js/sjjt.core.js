/**
 * 
 */
function showDialog(url) {
	var fatherBody = $(window.top.document.body);
	var id = 'pages';
	var dialog = $('#' + id);
	if (dialog.length == 0) {
		dialog = $('<div class="modal fade" role="dialog" id="' + id + '"/>');
		dialog.appendTo(fatherBody);
	}
	$(fatherBody).css({
		"overflow" : "hidden"
	});
	dialog.load(url, function() {
		dialog.modal({
			backdrop : false
		});
	});
	fatherBody
			.append('<div id="backdropId" class="modal-backdrop fade in"></div>');
	fatherBody.find("#pages").on("hidden.bs.modal", function(e) {
		$(this).remove();
		$(fatherBody).css({
			"overflow-y" : "auto"
		});
		fatherBody.find("#backdropId").remove();
	});
