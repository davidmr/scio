modules = {
	bbcode {
		dependsOn 'jquery'
		resource url : 'markitup/jquery.markitup.js'
		resource url : 'markitup/sets/bbcode/set.js'
		resource url : 'markitup/skins/markitup/style.css'
		resource url : 'markitup/sets/bbcode/style.css'
	}
	
	core {
		dependsOn 'bootstrap'
		resource url : 'css/main.css'
		resource url : 'css/mybootstrap.css'
		resource url : 'css/forms.css'
		resource url : 'images/owl.png'
	}
	
	searchScio {
		dependsOn 'jquery'
		resource url : 'js/searchScio.js'
	}
	
	editScio {
		dependsOn 'jquery'
		resource url : 'js/editScio.js'
	}
	
}