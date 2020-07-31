<html>
	<head>
		<title>Todos Login</title>
	</head>
	<body>
		<form method = 'POST' action='/CrudStruts/login'>
		
		Username :	<input type = 'text'  id="username" name="username"/>
		Password :	<input type = 'text' id="password" name="password"/>
		<input type = 'submit'>Submit</input>
			${errorMessage}
		</form>
	</body>

</html>