<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>PolyFactures - Connexion Client</title>
</head>

<body>

	Connexion - Portail Client
	
	<form method=post action="/connexionClient">
		Login : <input type="text" name=loginClient value=""/>
		Mot de Passe :<input type="password" name=passwordClient value=""/>
		<input type=submit value="Connexion" />
	</form>
	
</body>

</html>