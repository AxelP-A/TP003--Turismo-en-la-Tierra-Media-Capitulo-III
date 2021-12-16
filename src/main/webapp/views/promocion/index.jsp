<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<!--<jsp:include page="/partials/head.jsp"></jsp:include>-->
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Promociones</title>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"
	integrity="sha384-DyZ88mC6Up2uqS4h/KRgHuoeGwBcD4Ng9SiP4dIRy0EXTlnuz47vAwmeGwVChigm"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/v/dt/jq-3.6.0/dt-1.11.3/af-2.3.7/b-2.0.1/b-colvis-2.0.1/b-print-2.0.1/fh-3.2.0/kt-2.6.4/r-2.2.9/sb-1.3.0/sp-1.4.0/sl-1.3.3/datatables.min.css" />
<link rel="stylesheet" href="../assets/stylesheets/dataTables.css">
<link rel="stylesheet" href="../assets/stylesheets/styles.css">
<script defer type="text/javascript"
	src="https://cdn.datatables.net/v/dt/jq-3.6.0/dt-1.11.3/af-2.3.7/b-2.0.1/b-colvis-2.0.1/b-print-2.0.1/fh-3.2.0/kt-2.6.4/r-2.2.9/sb-1.3.0/sp-1.4.0/sl-1.3.3/datatables.min.js"></script>
<script type="text/javascript">
	window
			.addEventListener(
					'DOMContentLoaded',
					function() {
						$('.dataTable')
								.DataTable(
										{
											"order" : [],

											language : {
												url : "https://cdn.datatables.net/plug-ins/1.10.19/i18n/Spanish.json"
											}
										});
					});
</script>
</head>
<body>
	<header>
		<div class="contenedor">
			<p>
				<a href="../index.jsp"> <img
					src="../assets/img/tolkien-plano.jpg" alt="logo"></a>
			</p>
			<div class="enlaces-contenedor">
				<a href="../index.jsp">Inicio</a> <a href="">Comunidad</a> <a
					href="">Acerca de</a> <a href="">Soporte</a>
			</div>
				<div class="dropdown">
				<c:out value="${user.nombre}"></c:out>
				<span><i class="fas fa-caret-down"></i></span>
				<ul class="dropdown-content">
					<li><i class="fas fa-donate"></i>
					<c:out value="${user.getPresupuesto()}"></c:out></li>
					<li><i class="fas fa-hourglass-half"></i>
					<c:out value="${user.getTiempoDisponible()}"></c:out></li>
					<li><a href="/Tp003-TurismoEnLaTierraMedia/logout"> <i
							class="fas fa-sign-out-alt">Logout</i></a>
				</ul>
			</div>
		</div>
	</header>
	<jsp:useBean id="buscarEnLista" class="services.BuscarEnListaService" />
	<!--<jsp:include page="/partials/nav.jsp"></jsp:include> -->
	<main class="mainSugeribles">
		<div class="background-seccionUno">
			<c:if test="${flash != null}">
				<div class="alert alert-danger">
					<p>
						<c:out value="${flash}" />
						<c:if test="${errors != null}">
							<ul>
								<c:forEach items="${errors}" var="entry">
									<li><c:out value="${entry.getValue()}"></c:out></li>
								</c:forEach>
							</ul>
						</c:if>
					</p>
				</div>
			</c:if>
			<div class="bg-light p-4 mb-3 rounded">
				<h1>Estas son las promociones de Tierra Media</h1>
			</div>
			<p></p>
			<c:if test="${user.isAdmin()}">
				<div class="mb-3">
					<a href="/Tp003-TurismoEnLaTierraMedia/promocion/create.do"
						class="btn btn-primary" type="button" role="button"> <i
						class="bi bi-plus-lg"></i> Nueva Promocion
					</a>
				</div>
			</c:if>
			<table class="dataTable table-promocion" style="width: 99%">
				<thead>
					<tr>
						<th>Promocion</th>
						<th>Precio</th>
						<th>Duracion</th>
						<th>Cupo</th>
						<th>Tipo</th>
						<!--<c:if test="${!user.isAdmin()}">
                        <th>Comprar</th>
                        </c:if>-->
						<th>Ver</th>
						<th>Acciones</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${promociones}" var="promocion">

						<tr>
							<td><strong><c:out value="${promocion.nombre}"></c:out></strong>
								<p>
									<c:out value="${promocion.getDescripcion()}"></c:out>
								</p></td>
							<td><c:out value="${promocion.costoDeVisita}"></c:out></td>
							<td><c:out value="${promocion.tiempoNecesario}"></c:out></td>
							<td><c:out value="${promocion.getCupo()}"></c:out></td>
							<td><c:out value="${promocion.getTipo()}"></c:out></td>
							<td class="open-promo"><a
								href="#${promocion.getId()}-promocion"><i
									class="fas fa-angle-double-right"></i></a></td>
							<td>
							<a
								href="/Tp003-TurismoEnLaTierraMedia/promocion/edit.do?id=${promocion.id}">
									<i class="fas fa-pen-square"></i>
							</a> 
										<c:choose>
										<c:when
										test="${promocion.estaHabilitada()}">
										<a
											href="/Tp003-TurismoEnLaTierraMedia/promocion/delete.do?id=${promocion.getId()}">
											<i class="fas fa-trash-alt"></i>
										</a>
										</c:when>
										<c:otherwise>
										<a href="/Tp003-TurismoEnLaTierraMedia/promocion/habilitar.do?id=${promocion.getId()}">
										<i class="fas fa-trash-restore-alt"></i></a>
										</c:otherwise>
										</c:choose>
									</td>
									
						</tr>

					</c:forEach>
				</tbody>
			</table>
		</div>
		<c:forEach items="${promociones}" var="promocion">
					<div class="container-all" id="${promocion.id}-promocion">
						<div class="popup">
							<div class="img uno"></div>
							<div class="container-text">
								<h2>
									<c:out value="${promocion.nombre}"></c:out>
								</h2>
								<p>
									<c:out value="${promocion.getDescripcion()}"></c:out>
								</p>
								<span><i class="fas fa-hourglass-start"></i> Tiempo:<c:out
										value="${promocion.tiempoNecesario}"></c:out></span> <span><i
									class="fas fa-users"></i> Cupo:<c:out
										value="${promocion.getCupo()}"></c:out></span> <span><i
									class="fas fa-dollar-sign"></i> Precio:<c:out
										value="${promocion.costoDeVisita}"></c:out></span>
							</div>
							<a href="#" class="btn-close-popup">X</a>
						</div>
					</div>
		</c:forEach>
		<!--CIERRE POPUP-->
		<footer>
			<div class="contenedor">
				<img src="../assets/img/tolkien-plano.jpg" alt="logo">
				<div class="enlaces-footer">
					<a href=""><i class="fas fa-wifi"></i></a> <a href=""><i
						class="fab fa-twitter"></i></a> <a href=""><i
						class="fab fa-instagram"></i></a> <a href=""><i
						class="fab fa-facebook"></i></a> <a href=""><i
						class="fab fa-linkedin"></i></a>
				</div>
				<p>
					<i class="far fa-copyright"></i> 2021 - Todos los derechos
					reservados
				</p>
			</div>
		</footer>
	</main>
</body>
</html>