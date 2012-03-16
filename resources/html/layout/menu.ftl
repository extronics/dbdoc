<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="screen.css" type="text/css" />
<title>menu</title>
</head>
<body id="menu">

<h1><a href="main.html" target="main">${Database.name}</a></h1>
<#macro displaySchema schema decoration>
	<#if decoration>
	<ul>
		<li class="schema">${schema.name}</li>
		<li>
	</#if>
	
	<h2>Tables</h2>
	<ul>
	<#list schema.tables as table>
		<li class="table"><a href="table.${table.name}.html" target="main">${table.name}</a></li>	
	</#list>
	</ul>
	
	<#if schema.procedures?has_content>
		<h2>Procedures</h2>
		<ul>
		<#list schema.procedures as procedure>
			<li class="procedure"><a href="procedure.${procedure.name}.html" target="main">${procedure.name}</a></li>
		</#list>
		</ul>
	</#if>
	
	<#if decoration>
		</li>
	</ul>
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