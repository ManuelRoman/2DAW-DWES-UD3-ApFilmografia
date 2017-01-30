<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="filmografia.beans.*" %>
<%@ page isErrorPage="true" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Mensajes de error</title>
<style>
	div{
	position: absolute;
	left: 30%;
	}
</style>
</head>
<body>
	<div>
	<% if (request.getAttribute("error") != null) {
		BeanError error = (BeanError) request.getAttribute("error");
		out.println(error.toString());
		} else {
			String pagOrigenError = request.getParameter("pagOrigen");%>
			<p>Ha ocurrido un error en la página: <%=pagOrigenError%></p>
			<p>Descripción de la excepción: <%=exception.toString()%></p>		
	<%}%> 
		<form action="consulta.jsp" method="get">
			<input type="submit" value="volver">
		</form>
	</div>
</body>
</html>