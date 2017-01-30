<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="filmografia.beans.*, javax.sql.*"%>
<%@ page errorPage="errores.jsp?pagOrigen=listaTodosDirectores.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lista de todos los directores</title>
<style>
	body{
		text-align: center;
	}
	div{
		position: absolute;
		left: 28%;
	}
</style>
</head>
<body>
	<% ServletContext sc = request.getSession().getServletContext();
	DataSource dataSource = (DataSource)sc.getAttribute("dataSource"); %>
	
	<c:if test="${empty directores}">
		<sql:setDataSource dataSource="<%=dataSource %>"></sql:setDataSource>
		<sql:query var="directores">
    		SELECT DISTINCT director FROM peliculas;
		</sql:query>
	</c:if>
	<div>
	<p>No existe ese director en la base de datos, seleccione uno de los existentes</p>	
	<form action="/filmografia/consultar" method="post">
		<label for="director">Nombre director:</label> 
		<select name="director" id="director">
			<c:forEach var="nombre" items="${directores.rows}">
				<option value="<c:out value="${nombre.director}"/>"><c:out value="${nombre.director}"/></option>
    		</c:forEach>
		</select><br>
		<input type="submit" value="Consultar">
	</form>
	</div>
</body>
</html>