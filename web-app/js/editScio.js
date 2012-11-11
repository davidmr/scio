(function($) {
	$(document).ready(function() {
		var bbcodesettings = mySettings;
		bbcodesettings.previewParserPath = $("#bbcodepreview").val();
		$("#scio-edit").markItUp(mySettings);
		$("#tags").typeahead({
			source : findTags,
			updater : function(item) {
				var tagsField = $("#tagsField");
				var tagsFeedback = $("#tagsFeedback");
				var old = tagsField.val();
				var tags = old + ' ' + item;
				tagsField.val(tags);
				tagsFeedback.text(tags);
				return null;
			}
		});
	});

	function findTags(query, process) {
		var url = $("#findTagsUrl").val();
		$.get(url, {
			q : query
		}, function(data) {
			process(data.options);
		});
	}
})(jQuery);