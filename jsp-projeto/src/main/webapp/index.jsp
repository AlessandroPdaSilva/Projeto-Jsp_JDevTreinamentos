<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Projeto JSP</title>
</head>

<body>
	
	<h1> Formulario </h1>
	
	<form action="<%= request.getContextPath() %>/ServletLogin" method="POST">
		 
		 
		<label> Login:</label>
		<input name="login" type="text" ><br>
	
	
		<label>Senha:</label>
		<input name="senha" type="password"><br>
	
		<input type="submit" value="enviar"> 
		
	</form>
	${msgErro}
	
</body>
</html>