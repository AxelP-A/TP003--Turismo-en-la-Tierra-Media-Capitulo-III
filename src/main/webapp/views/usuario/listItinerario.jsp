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
<title>Itinerario</title>
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
				<a href="../index.jsp">Inicio</a> <a href="">Acerca de</a> <a
					href="">Soporte</a>
			</div>
			<div class="dropdown">
				<c:out value="${user.nombre}"></c:out>
				<span><i class="fas fa-caret-down"></i></span>
				<ul class="dropdown-content">
					<li><i class="fas fa-donate"></i> <c:out
							value="${user.getPresupuesto()}"></c:out></li>
					<li><i class="fas fa-hourglass-half"></i> <c:out
							value="${user.getTiempoDisponible()}"></c:out></li>
					<li><a href="/Tp003-TurismoEnLaTierraMedia/logout"> <i
							class="fas fa-sign-out-alt">Logout</i></a>
				</ul>
			</div>
		</div>
	</header>


	<main class="mainSugeribles">
		<div class="background-seccionUno">

			<div class="bg-light p-4 mb-3 rounded">
				<h1>
					Este es el itinerario de
					<c:out value="${usuario.getNombre()}" />
				</h1>

			</div>
			<p></p>

			<table class="dataTable table-promocion" style="width: 99%">
				<thead>
					<tr>
						<th>Listado</th>
						<th>Precio</th>
						<th>Duracion</th>
						<th>Ver</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${itinerario}" var="itinerarioVar">
						<tr>
							<td><strong><c:out
										value="${itinerarioVar.getNombre()}"></c:out></strong> <c:if
									test="${itinerarioVar.esPromocion()}">

									<p>
										Que está compuesta de las atracciones: <br>
										<c:forEach items="${itinerarioVar.getAtraccionesIncluidas()}"
											var="atraccion">
											<c:out value="${atraccion.getNombre()}"></c:out>
											<br>
										</c:forEach>
									</p>
								</c:if></td>
							<td><c:out value="${itinerarioVar.getCostoDeVisita()}"></c:out></td>
							<td><c:out value="${itinerarioVar.getTiempoNecesario()}"></c:out></td>
							<c:choose>
								<c:when test="${!itinerarioVar.esPromocion()}">
									<td><a href="#${itinerarioVar.getId()}-promocion"><i
											class="fas fa-angle-double-right"></i></a></td>
								</c:when>
								<c:otherwise>
									<td class="open-promo"><a
										href="#${itinerarioVar.getId()}-atraccion"><i
											class="fas fa-angle-double-right"></i></a></td>
								</c:otherwise>
							</c:choose>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<a href="/Tp003-TurismoEnLaTierraMedia/usuario/index.do"><i class="fas fa-arrow-left fa-5x" Style="color: #4cff94;" ></i></a>
		</div>
		<c:forEach items="${itinerario}" var="itinerarioVar">
			<c:choose>
				<c:when test="${!itinerarioVar.esPromocion()}">
					<div class="container-all" id="${itinerarioVar.id}-promocion">
						<div class="popup">
							<div class="img uno"></div>
							<div class="container-text">
								<h2>
									<c:out value="${itinerarioVar.nombre}"></c:out>
								</h2>
								<p>
									<c:out value="${itinerarioVar.getDescripcion()}"></c:out>
								</p>
								<span><i class="fas fa-hourglass-start"></i> Tiempo:<c:out
										value="${itinerarioVar.tiempoNecesario}"></c:out></span> <span><i
									class="fas fa-dollar-sign"></i> Precio: <c:out
										value="${itinerarioVar.costoDeVisita}"></c:out></span>
							</div>
							<a href="#" class="btn-close-popup">X</a>
						</div>
					</div>
				</c:when>
				<c:otherwise>
					<div class="container-all" id="${itinerarioVar.getId()}-atraccion">
						<div class="popup">
							<div class="img uno"></div>
							<div class="container-text">
								<h2>
									<c:out value="${itinerarioVar.getNombre()}"></c:out>
								</h2>
								<p>
									<c:out value="${itinerarioVar.getDescripcion()}"></c:out>
								</p>
								<span><i class="fas fa-hourglass-start"></i> Tiempo:<c:out
										value="${itinerarioVar.tiempoNecesario}"></c:out></span> <span><i
									class="fas fa-dollar-sign"></i> Precio:<c:out
										value="${itinerarioVar.costoDeVisita}"></c:out></span>
							</div>
							<a href="#" class="btn-close-popup">X</a>
						</div>
					</div>
				</c:otherwise>
			</c:choose>
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