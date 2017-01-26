<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="filmografia.beans.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Directores Consultados</title>
</head>
<body>
	<%ListaDirectores listaDirectores = (ListaDirectores) session.getAttribute("listaDirectores"); %>
	<table>
  		<tr>
    		<th>Directores</th>
  		</tr>
  		<%
  		String director = null;
  		ListIterator iterador = listaDirectores.listIterator();
        while (iterador.hasNext()) {
            director = (String) iterador.next();
        %>
  		<tr>
    		<td><%=director %></td>
    	</tr>
    	<%} %>
	</table>
	<form action="consulta.jsp" method="post">
		<input type="submit" value="Nueva Consulta">
	</form>
	<%session.invalidate(); %>
</body>
</html>