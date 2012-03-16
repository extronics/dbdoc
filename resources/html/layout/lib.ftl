<#macro strlimit str limit=300>
	<#assign cleaned = str?replace("</?[a-zA-Z0-9]+>|\n|\r", "", "r") />
	<#if cleaned?length gt limit>
		${cleaned?substring(0, limit-3)} ...
	<#else>
		${cleaned}
	</#if>
</#macro>