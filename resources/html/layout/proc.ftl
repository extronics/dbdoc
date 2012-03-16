<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" href="screen.css" type="text/css" />
	<link type="text/css" rel="stylesheet" href="sql/shCore.css" />
	<link type="text/css" rel="stylesheet" href="sql/shThemeDefault.css" />
	<script type="text/javascript" src="sql/shCore.js"></script>
	<script type="text/javascript" src="sql/shBrushSql.js"></script>
	<script type="text/javascript">
	SyntaxHighlighter.all();
	</script>
	<title>proc</title>
</head>
<body id="proc">

<a name="top"></a>
<ul class="path">
	<li class="database"><a href="main.html">${Database.name}</a></li>
	<#if Database.supportingSchemas>
		<li class="schema">${Procedure.schema.name}</li>
	</#if>
	<li class="procedure">${Procedure.name}</li>
</ul>

<h1>${Procedure.name}</h1>
<#if Procedure.comment?has_content>
	<p class="comment">
		${Procedure.comment}
	</p>
</#if>

<pre class="brush:sql">${Procedure.body!""}</pre>

</body>
</html>