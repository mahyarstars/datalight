<%@ page import="com.acn.mydata.Record" %>



<div class="fieldcontain ${hasErrors(bean: recordInstance, field: 'country', 'error')} required">
	<label for="country">
		<g:message code="record.country.label" default="Country" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="country" name="country.id" from="${com.acn.mydata.Country.list()}" optionKey="id" required="" value="${recordInstance?.country?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: recordInstance, field: 'county', 'error')} required">
	<label for="county">
		<g:message code="record.county.label" default="County" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="county" name="county.id" from="${com.acn.mydata.County.list()}" optionKey="id" required="" value="${recordInstance?.county?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: recordInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="record.description.label" default="Description" />
		
	</label>
	<g:textField name="description" value="${recordInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: recordInstance, field: 'isShared', 'error')} ">
	<label for="isShared">
		<g:message code="record.isShared.label" default="Is Shared" />
		
	</label>
	<g:checkBox name="isShared" value="${recordInstance?.isShared}" />
</div>

<div class="fieldcontain ${hasErrors(bean: recordInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="record.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${recordInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: recordInstance, field: 'state', 'error')} required">
	<label for="state">
		<g:message code="record.state.label" default="State" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="state" name="state.id" from="${com.acn.mydata.State.list()}" optionKey="id" required="" value="${recordInstance?.state?.id}" class="many-to-one"/>
</div>

