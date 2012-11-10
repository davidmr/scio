(function($){
	$(document).ready(function(){
		$("#buttonSciosByTag").click(function(ev){
			ev.preventDefault();
			searchByTag();
		});
	});
	
	function searchByTag() {
		var elemTag = $("#textScioByTag")
		var elemPanelShow = $("#panelSciosByTag");
		var elemContentShow = $("#contentSciosByTag");
		var elemPanelHide = $("#panelSciosFeatured");

		var urlSearch = $("#urlSearchByTag").val();
		$.get(urlSearch, {tag: elemTag.val()}, function(data) {
			elemContentShow.empty();
			elemContentShow.append(data);
			elemPanelShow.show();
			
			elemPanelHide.hide();
		});
	}
})(jQuery);