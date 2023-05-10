<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>PolyFactures - Connexion Administrateur</title>
</head>

<body>
	
	Connexion - Portail Administrateur
	
	<form method=post action="/connexionAdmin">
		Login : <input type="text" name=loginAdmin value=""/>
		Mot de Passe :<input type="password" name=passwordAdmin value=""/>
		<input type=submit value="Connexion" />
	</form>
	
</body>

</html>