<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="filmografia.beans.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page errorPage="WEB-INF/errores.jsp?pagOrigen=consulta.jsp"%>

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
	<jsp:useBean id="listaDirectoresConsultados" scope="session" class="filmografia.beans.ListaDirectores" />
	<% if (session.isNew()) {
		session.setAttribute("listaDirectoresConsultados", listaDirectoresConsultados);
		%><c:set var="directores" scope="session"/><%
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