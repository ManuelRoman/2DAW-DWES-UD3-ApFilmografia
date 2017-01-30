<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="filmografia.beans.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page errorPage="errores.jsp?pagOrigen=consultasRealizadas.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Directores Consultados</title>
<style>
	body{
		text-align: center;
	}
	div{
		position: absolute;
		left: 40%;
	}
</style>
</head>
<body>
	<jsp:useBean id="listaDirectoresConsultados" scope="session" class="filmografia.beans.ListaDirectores" />
	<% listaDirectoresConsultados = (ListaDirectores) session.getAttribute("listaDirectoresConsultados"); %>
	<div>
	<table>
  		<tr>
    		<th>Directores</th>
  		</tr>
  		<c:forEach var="director" items="${listaDirectoresConsultados}">
  		<tr>
    		<td><c:out value="${director}"/></td>
    	</tr>
    	</c:forEach>
	</table>
	<form action="consulta.jsp" method="post">
		<input type="submit" value="Nueva Consulta">
	</form>
	</div>
	<%session.invalidate(); %>
</body>
</html>