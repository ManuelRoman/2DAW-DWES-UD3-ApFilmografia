<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="filmografia.beans.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page errorPage="errores.jsp?pagOrigen=resultado.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lista de películas</title>
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
	<jsp:useBean id="listaPeliculas" scope="request" class="filmografia.beans.ListaPeliculas" />
	<div>
	<% listaPeliculas = (ListaPeliculas) request.getAttribute("listaPeliculas"); 
	ListaDirectores listaDirectoresConsultados = (ListaDirectores) session.getAttribute("listaDirectoresConsultados");
	listaDirectoresConsultados.add((String)request.getAttribute("director")); %>
	<table>
  		<tr>
    		<th>Título película</th>
    		<th>Fecha película</th>
  		</tr>
        <c:forEach var="pelicula" items="${listaPeliculas}">
  		<tr>
    		<td><c:out value="${pelicula.getTitulo()}"/></td>
    		<td><c:out value="${pelicula.getFecha()}"/></td>
    	</tr>
    	</c:forEach>
	</table>
	<form action="consulta.jsp" method="post">
		<input type="hidden" name="accion" value="nuevaConsulta" />
		<input type="submit" value="Nueva Consulta">
	</form>
	<form action="/filmografia/consultar" method="post">
		<input type="hidden" name="accion" value="finalizar" />
		<input type="submit" value="Finalizar">
	</form>
	</div>
</body>
</html>