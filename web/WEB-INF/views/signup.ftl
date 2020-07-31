<html>
	<head>
		<title>Sign up </title>
	</head>
	
	<body>
		<form method='POST' action='/CrudStruts/signup'>
			email : 
			<input type = 'text' id='email' name='email'> <br>
			username:
			<input type = 'text' id = 'username' name='username'><br>
			password : 
			<input type='text' id='password' name='password'><br>
			<input type = 'submit'>Submit</input>
			
			${errorMessage}
			${successMessage}
		</form>
	</body>
</html>