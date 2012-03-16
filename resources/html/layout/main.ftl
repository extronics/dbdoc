<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="screen.css" type="text/css" />
<title>main</title>
</head>
<body id="main">

<h1>${Database.name}</h1>
<#if Database.comment?has_content>
	<p class="comment">
	${Database.comment}
	</p>
</#if>
<#macro displaySchema schema decoration>
	<#if decoration>
	<h2>${schema.name}</h2>
	</#if>
	
	<h3>Tables</h3>
	<table>
	<#list schema.tables as table>
		<tr>
			<th><a href="table.${table.name}.html" target="main">${table.name}</a></th>
			<td><@strlimit table.comment!"" /></td>
		</tr>
	</#list>
	</table>
	
	<#if schema.procedures?has_content>
		<h3>Procedures</h3>
		<table>
		<#list schema.procedures as procedure>
			<tr>
				<th><a href="procedure.${procedure.name}.html" target="main">${procedure.name}</a></th>
				<td><@strlimit procedure.comment!"" /></td>
			</tr>
		</#list>
		</table>
	</#if>
</#macro>

<#if Database.supportingSchemas>
	<#list Database.schemas as schema>
		<@displaySchema schema true />
	</#list>
<#else>
	<@displaySchema Database.schemas[0] false />
</#if>
</body>
</html>