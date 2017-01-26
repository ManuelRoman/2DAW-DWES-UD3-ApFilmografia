<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="filmografia.beans.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lista de películas</title>
</head>
<body>
	<% ListaPeliculas listaPeliculas = (ListaPeliculas) request.getAttribute("listaPeliculas"); %>
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
</body>
</html>