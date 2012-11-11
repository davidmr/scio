<ul class="nav nav-list">
	<g:each in="${monthMap}" var="month" >
		<li><a href="#" data-month="${month.key}" id="linkMySciosByMonth">${month.key}</a></li>
	</g:each>
</ul>

<g:hiddenField name="urlSearchByMonth" id="urlSearchByMonth" value="${createLink(controller: 'search', action: 'searchMineByMonth')}"/>