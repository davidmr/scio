(function($){
	$(document).ready(function(){
		$("#buttonSciosByTag").click(function(ev){
			ev.preventDefault();
			searchByTag('panelSciosFeatured');
		});
		
		$("#buttonMySciosByTag").click(function(ev){
			ev.preventDefault();
			searchByTag('panelSciosMine');
		});
	});
	
	function searchByTag(panelSciosHide) {
		var elemTag = $("#textScioByTag")
		var elemPanelShow = $("#panelSciosByTag");
		var elemContentShow = $("#contentSciosByTag");
		var elemPanelHide = $("#" + panelSciosHide);

		var urlSearch = $("#urlSearchByTag").val();
		$.get(urlSearch, {tag: elemTag.val()}, function(data) {
			elemContentShow.empty();
			elemContentShow.append(data);
			elemPanelShow.show();
			
			elemPanelHide.hide();
		});
	}
})(jQuery);