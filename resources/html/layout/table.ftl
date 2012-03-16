<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" href="screen.css" type="text/css" />
	<link type="text/css" rel="stylesheet" href="sql/shCore.css" />
	<link type="text/css" rel="stylesheet" href="sql/shThemeDefault.css" />
	<script type="text/javascript" src="sql/shCore.js"></script>
	<script type="text/javascript" src="sql/shBrushSql.js"></script>
	<script type="text/javascript" src="mootools.js"></script>
	<script type="text/javascript" src="doc.js"></script>
	<script type="text/javascript">
	SyntaxHighlighter.all();
	window.addEvent("domready", function() { addToC("toc"); });
	</script>
		
	<title>table</title>
</head>
<body id="table">

<ul class="path">
	<li class="database"><a href="main.html">${Database.name}</a></li>
	<#if Database.supportingSchemas>
		<li class="schema">${Table.schema.name}</li>
	</#if>
	<li class="table">${Table.name}</li>
</ul>

<h1>${Table.name}</h1>
<div id="toc"></div>
<#if Table.comment?has_content>
	<p class="comment">
		${Table.comment}
	</p>
</#if>

<h2 class="toc">Columns</h2>
<table cellspacing="0" cellpadding="0" border="0" id="columns">
	<tr>
		<th class="name">Name</th>
		<th class="datatype">Type</th>
		<th class="datalength">Length</th>
		<th class="nullable">Nullable</th>
		<th class="constr">Indizes</th>
		<th class="extras">Extras</th>
		<th class="fkeys">Relations</th>
	</tr>
<#list Table.columns as column>
	<tr class="column">
		<td class="name row${column_index%2}">${column.name}<a name="column_${column.name}"></a></td>
		<td class="datatype row${column_index%2}">${column.dataType}</td>
		<td class="datalength row${column_index%2}<#if column.dataLength gt 0> n/a</#if>">
			<#if column.dataLength gt 0>
				${column.dataLength}
			<#else>
				-
			</#if>
		</td>
		<td class="nullable row${column_index%2}"><#if column.nullable>Yes<#else>No</#if></td>
		<td class="constr row${column_index%2}">
			<#if column.primaryKeyColumn>
			<a href="#index_${Table.primaryKey.name}" title="${Table.primaryKey.name}">PK</a>
			</#if>
			<#if column.constraints?has_content>
				<#list column.constraints as constraint>
					<#if constraint.class.name == "dbdoc.reflect.Unique">
						<a href="#index_${constraint.name}" title="${constraint.name}">C</a>
					</#if>
				</#list>
			</#if>
			<#if column.indexes?has_content>
				<#list column.indexes as index>
					<a href="#index_${index.name}" title="${index.name}">I</a>
				</#list>
			</#if>
		</td>
		<td class="extras row${column_index%2}">
			<#if column.sequence>
			Sequence
			</#if>
		</td>
		<td class="fkeys row${column_index%2}"<#if column.comment?has_content> rowspan="2"</#if>>
			<#if column.referencing>
				<ul class="refs-out">
				<#list column.referencedColumns as refCol>
					<#if refCol_index gt 1>
						<li><a href="#referencedtables">...</a></li>
						<#break/>
					<#else>
						<li><a href="table.${refCol.table.name}.html">${refCol.table.name}</a>.<a href="table.${refCol.table.name}.html#column_${refCol.name}">${refCol.name}</a></li>
					</#if>
				</#list>
				</ul>
			</#if>
			<#if column.referenced>
				<ul class="refs-in">
				<#list column.referencingColumns as refCol>
					<#if refCol_index gt 1>
						<li><a href="#referencingtables">...</a></li>
						<#break/>
					<#else>
						<li><a href="table.${refCol.table.name}.html">${refCol.table.name}</a>.<a href="table.${refCol.table.name}.html#column_${refCol.name}">${refCol.name}</a></li>
					</#if>
				</#list>
				</ul>
			</#if>
		</td>
	</tr>
	<#if column.comment?has_content>
		<tr class="comment">
			<td colspan="6" class="comment row${column_index%2}">${column.comment!""}</td>
		</tr>
	</#if>
</#list>
</table>

<#if Table.constraints?has_content || Table.indexes?has_content>
	<h2 class="toc">Indizes</h2>
	<table id="indices">
	<#if Table.primaryKey?has_content>
		<tr>
			<th class="pkey">
				<a name="index_${Table.primaryKey.name}"></a>
				${Table.primaryKey.name}&nbsp;(primary key)
			</th>
			<td>
				<#list Table.primaryKey.columns as column>
					<a href="#column_${column.name}">${column.name}</a><#if column_has_next>, </#if>
				</#list>
			</td>
		</tr>
	</#if>
	
	<#if Table.constraints?has_content && ((Table.primaryKey?has_content && Table.constraints?size gt 1) || !Table.primaryKey?has_content)>
		<#list Table.constraints as constraint>
			<#if constraint.class.name == "dbdoc.reflect.Unique">
				<tr>
					<th class="constr">
						<a name="index_${constraint.name}"></a>
						${constraint.name}&nbsp;(unique)
					</th>
					<td>
						<#list constraint.columns as column>
							<a href="#column_${column.name}">${column.name}</a><#if column_has_next>, </#if>
						</#list>
					</td>
				</tr>
			</#if>
		</#list>
	</#if>
	
	<#if Table.indexes?has_content>
		<#list Table.indexes as index>
			<tr>
				<th class="index">
					<a name="index_${index.name}"></a>
					${index.name}&nbsp;(Index)
				</th>
				<td>
					<#list index.columns as column>
						<a href="#column_${column.name}">${column.name}</a><#if column_has_next>, </#if>
					</#list>
				</td>
			</tr>
		</#list>
	</#if>
	</table>
</#if>

<#if Table.foreignKeysOut?has_content || Table.foreignKeysIn?has_content>
	<h2 class="toc">Relations</h2>
	<#if Table.foreignKeysOut?has_content>
	<h3 class="toc">Referenced tables</h3>
	<ul>
		<#list Table.foreignKeysOut as foreignKey>
			<li>
				<#list foreignKey.columns as column>
					<a href="#column_${column.name}">${column.name}</a><#if column_has_next>, </#if>
				</#list>
				<img src="right.png" title="referenced" />
				<#list foreignKey.foreignColumns as column>
					<a href="table.${column.table.name}.html">${column.table.name}</a>.<a href="table.${column.table.name}.html#column_${column.name}">${column.name}</a><#if column_has_next>, </#if>
				</#list>
			</li>
		</#list>
	</ul>
	</#if>
	
	<#if Table.foreignKeysIn?has_content>
	<h3 class="toc">Referencing tables</h3>
	<ul>
		<#list Table.foreignKeysIn as foreignKey>
			<li>
				<#list foreignKey.foreignColumns as column>
					<a href="#column_${column.name}">${column.name}</a><#if column_has_next>, </#if>
				</#list>
				<img src="left.png" title="referenced" />
				<#list foreignKey.columns as column>
					<a href="table.${column.table.name}.html">${column.table.name}</a>.<a href="table.${column.table.name}.html#column_${column.name}">${column.name}</a><#if column_has_next>, </#if>
				</#list>
			</li>
		</#list>
	</ul>
	</#if>
</#if>

<#if Table.triggers?has_content>
	<h2 class="toc">Triggers</h2>
	<#list Table.triggers as trigger>
		<h3>${trigger.name}</h3>
		<p class="trigger">
			(<strong><#if trigger.timing == "BEFORE">before<#else>after</#if></strong> ${trigger.statementType} queries)
		</p>
		<pre class="brush:sql">${trigger.body}</pre>
	</#list>
</#if>	
</body>
</html>