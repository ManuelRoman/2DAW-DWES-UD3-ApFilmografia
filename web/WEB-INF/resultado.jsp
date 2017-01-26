<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="filmografia.beans.*, java.util.*"%>
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
	<div>
	<% ListaPeliculas listaPeliculas = (ListaPeliculas) request.getAttribute("listaPeliculas"); 
	ListaDirectores listaDirectores = (ListaDirectores) session.getAttribute("listaDirectores");
	listaDirectores.add((String)request.getAttribute("director")); %>
	<table>
  		<tr>
    		<th>Título película</th>
    		<th>Fecha película</th>
  		</tr>
  		<%
  		Pelicula pelicula;
        ListIterator iterador = listaPeliculas.listIterator();
        while (iterador.hasNext()) {
            pelicula = (Pelicula) iterador.next();
        %>
  		<tr>
    		<td><%=pelicula.getTitulo() %></td>
    		<td><%=pelicula.getFecha() %></td>
    	</tr>
    	<%} %>
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