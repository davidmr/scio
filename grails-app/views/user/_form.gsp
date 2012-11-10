<div class="control-group fieldcontain ${hasErrors(bean: userInstance, field: 'fullname', 'error')} required">
	<label class="control-label" for="fullname">
		Fullname <span class="required-indicator">*</span>
	</label>
	<div class="controls">
		<g:textField name="fullname" required="" value="${userInstance?.fullname}"/>
	</div>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: userInstance, field: 'username', 'error')} required">
	<label class="control-label" for="username">
		Username <span class="required-indicator">*</span>
	</label>
	<div class="controls">
		<g:textField name="username" required="" value="${userInstance?.username}"/>
	</div>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: userInstance, field: 'password', 'error')} required">
	<label class="control-label" for="password">
		Password <span class="required-indicator">*</span>
	</label>
	<div class="controls">
		<g:passwordField name="password" required="" value=""/>
	</div>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: userInstance, field: 'password', 'error')} required">
	<label class="control-label" for="password">
		Repeat Password <span class="required-indicator">*</span>
	</label>
	<div class="controls">
		<g:passwordField name="passwordRepeat" required="" value=""/>
	</div>
</div>

