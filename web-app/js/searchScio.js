(function($){
	$(document).ready(function(){
		$("#buttonSciosByTag").click(function(ev){
			ev.preventDefault();
			var elemCriteria = $("#textScioByTag");
			searchByTag(elemCriteria.val(), 'urlSearchByTag', 'panelSciosFeatured');
		});
		
		$("#buttonMySciosByTag").click(function(ev){
			ev.preventDefault();
			var elemCriteria = $("#textScioByTag");
			searchByTag(elemCriteria.val(), 'urlSearchByTag', 'panelSciosMine');
		});
		
		$("#linkMySciosByMonth").click(function(ev){
			ev.preventDefault();
			var criteria = $(this).attr("data-month");
			searchByTag(criteria, 'urlSearchByMonth', 'panelSciosMine');
		});
		
	});
	
	function searchByTag(criteria, urlSearch, panelSciosHide) {
		var elemPanelShow = $("#panelSciosByTag");
		var elemContentShow = $("#contentSciosByTag");
		
		var elemPanelError = $("#panelNoScios");
		var elemPanelHide = $("#" + panelSciosHide);

		var url = $("#" + urlSearch).val();
		$.get(url, {criteria: criteria}, function(data) {
			if (data) {
				elemContentShow.empty();
				elemContentShow.append(data);
				elemPanelShow.show();
				elemPanelError.hide();
			} else {
				elemPanelError.show();
				elemPanelShow.hide()
			}
			elemPanelHide.hide();
		});
	}
})(jQuery);