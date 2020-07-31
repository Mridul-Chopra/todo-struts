<html>
	<head>
		<title> Todos App </title>
	</head>
	<body>
		<h1> ${message} </h1>
		<@todos todos = todosList/>
	</body>
</html>


<#-- Macros -->

<#macro todos todos>
	<ul>
		<li>
			<#list todos as todo>
				${todo.todoId}
				${todo.userId}
				${todo.todo}
				${todo.done?c}
			</#list>
		</li>
	</ul>
</#macro>