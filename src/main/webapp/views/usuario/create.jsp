
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Creación de usuarios</title>
<jsp:include page="/partials/head.jsp"></jsp:include>
</head>
<body>
	<main class="container">

		<c:if test="${usuario != null && !usuario.isValid()}">
			<div class="alert alert-danger">
				<p>Se encontraron errores al crear el usuario.</p>
			</div>
		</c:if>

		<form action="/Tp003-TurismoEnLaTierraMedia/usuario/create.do" method="post">
			<jsp:include page="/views/usuario/form.jsp"></jsp:include>
		</form>
	</main>
</body>
</html>