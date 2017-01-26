<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="filmografia.beans.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Consulta de películas</title>
<style>
	body{
		text-align: center;
	}
	div{
		position: absolute;
		left: 35%;
	}
</style>
</head>
<body>
	<% ListaDirectores listaDirectores = null;
	if (session.isNew()) {
		listaDirectores = new ListaDirectores();
		session.setAttribute("listaDirectores", listaDirectores);
	}%>
	<p>Introduzca un nombre para consultar el listado de películas</p>
	<div>	
	<form action="/filmografia/consultar" method="post">
		<label for="director">Nombre director:</label><input id="director" type="text" name="director" maxlength="128" required="required"><br>
		<input type="submit" value="Consultar">
	</form>
	</div>
</body>
</html>