<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Usuarios</title>
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
			</div>
			<div class="enlaces-contenedor">
				<a href="../index.jsp">Inicio</a> <a href="">Comunidad</a> <a
					href="">Acerca de</a> <a href="">Soporte</a>
			</div>
	</header>

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
				<h1>Usuarios</h1>
			</div>
			<p></p>
			<c:if test="${user.isAdmin()}">
				<div class="mb-3">
					<a href="/Tp003-TurismoEnLaTierraMedia/usuarios/create.do" class="btn btn-primary"
						role="button"> <i class="bi bi-plus-lg"></i> Nuevo Usuario
					</a>
				</div>
			</c:if>
			<table class="dataTable table-promocion" style="width: 99%">
				<thead>
					<tr>
						<th>Nombre</th>
						<th>Presupuesto</th>
						<th>Tiempo</th>
						<th>Preferencia</th>
						<th>Rol</th>
						<th>Estado</th>
						<th>Itinerario</th>
						<th>Acciones</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${listaUsuarios}" var="usuario">
						<tr>
							<td><strong><c:out value="${usuario.getNombre()}"></c:out></strong></td>
							<td><c:out value="${usuario.getPresupuesto()}"></c:out></td>
							<td><c:out value="${usuario.getTiempoDisponible()}"></c:out></td>
							<td><c:out value="${usuario.getAtraccionPreferida()}"></c:out></td>
							<td><c:choose>
									<c:when test="${usuario.isAdmin()}">
									Admin
								</c:when>
									<c:otherwise>
									Normal
								</c:otherwise>
								</c:choose></td>
							<td><c:choose>
									<c:when test="${usuario.estaActivo()}">
									Habilitado
								</c:when>
									<c:otherwise>
									Eliminado
								</c:otherwise>
								</c:choose></td>
							
							<td class="open-promo"><a href="#${usuario.getId()}">
								<i class="fas fa-angle-double-right"></i></a>
							</td>
							
							<td><c:choose>
									<c:when
										test="${user.isAdmin() && (!usuario.isAdmin() || usuario.getId() == user.getId())}">
										<a href="/Tp003-TurismoEnLaTierraMedia/usuarios/edit.do?id=${usuario.getId()}">
											<i class="fas fa-pen-square"> </i></a>
										<a href="/Tp003-TurismoEnLaTierraMedia/usuarios/delete.do?id=${usuario.getId()}">
											<i class="fas fa-trash-alt"></i></a>
									</c:when>
									<c:otherwise>
										<a href="#" class="btn btn-secondary rounded disabled"
											type="button" role="button">No puede modificar</a>
									</c:otherwise>
								</c:choose></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		
		<c:forEach items="${listaUsuarios}" var="usuario">
		 <div class="container-all" id="${usuario.id}">
           <div class="popup">
               <div class="img uno"></div>
               <div class="container-text">
                   <h2>Itinerario: </h2>          
                   <br><br>
                   
                   <table class="dataTable table-promocion" style="width: 80%">
	                 	<thead>
							<tr>
								<th>Listado</th>
								<th>Precio</th>
								<th>Duracion</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach items="${itinerario}" var="itinerarioVar">
						<tr>
							<td>
								<strong><c:out
									value="${itinerarioVar.getNombre()}">
								</c:out></strong> 
								<c:if test="${itinerarioVar.esPromocion()}">
									<p> Que está compuesta de las atracciones: <br>
										<c:forEach items="${itinerarioVar.getAtraccionesIncluidas()}" var="atraccion">
											<c:out value="${atraccion.getNombre()}"></c:out><br>
										</c:forEach>
									</p>
								</c:if>
							</td>
							<td><c:out value="${itinerarioVar.getCostoDeVisita()}"></c:out></td>
							<td><c:out value="${itinerarioVar.getTiempoNecesario()}"></c:out></td>
						</tr>	
						</c:forEach>
						</tbody>
                   </table>
               </div>
               <a href="#" class="btn-close-popup">X</a>
           </div>
       </div>
     </c:forEach>
</main>

</body>
</html>