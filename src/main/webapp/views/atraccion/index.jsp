<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/partials/head.jsp"></jsp:include>
</head>
<body>
	<jsp:useBean id="buscarEnLista" class="services.BuscarEnListaService" />
	<jsp:include page="/partials/nav.jsp"></jsp:include>

	<main class="container">

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
			<h1>Estas son las atracciones de la Tierra Media</h1>
		</div>

		<c:if test="${user.isAdmin()}">
			<div class="mb-3">
				<a href="/Tp003-TurismoEnLaTierraMedia/atraccion/create.do"
					class="btn btn-primary" role="button"> <i class="bi bi-plus-lg"></i>
					Nueva Atracción
				</a>
			</div>
		</c:if>
		<table class="table table-stripped table-hover">
			<thead>
				<tr>
					<th>Atraccion</th>
					<th>Costo</th>
					<th>Duracion</th>
					<th>Cupo</th>

					<th>Tipo</th>

					<th>Descripción</th>

					<th>Acciones</th>
				</tr>
			</thead>
			<tbody>
				
				<c:choose>
					<c:when test="${user.admin}">

						<c:forEach items="${atraccion}" var="atraccion">
							<tr>
								<td><strong><c:out value="${atraccion.nombre}"></c:out></strong>
									<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
										Cras pretium eros urna. Sed quis erat congue, bibendum tortor
										malesuada, iaculis diam. Ut ut imperdiet sapien.</p></td>
								<td><c:out value="${atraccion.getCostoDeVisita()}"></c:out></td>
								<td><c:out value="${atraccion.tiempoNecesario}"></c:out></td>
								<td><c:out value="${atraccion.getCupo()}"></c:out></td>

								<td><a
									href="/Tp003-TurismoEnLaTierraMedia/atraccion/edit.do?id=${atraccion.id}"
									class="btn btn-light rounded-0" role="button"><i
										class="bi bi-pencil-fill"></i></a> <a
									href="/Tp003-TurismoEnLaTierraMedia/atraccion/delete.do?id=${atraccion.id}"
									class="btn btn-danger rounded" role="button"><i
										class="bi bi-x-circle-fill"></i></a>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<c:forEach items="${atraccion}" var="atraccion">
						
							<c:if test="${atraccion.estaHabilitada()}">
							<tr>
								<td><strong><c:out value="${atraccion.nombre}"></c:out></strong>
									<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
										Cras pretium eros urna. Sed quis erat congue, bibendum tortor
										malesuada, iaculis diam. Ut ut imperdiet sapien.</p></td>
								<td><c:out value="${atraccion.costoDeVisita}"></c:out></td>
								<td><c:out value="${atraccion.tiempoNecesario}"></c:out></td>
								<td><c:out value="${atraccion.getCupo()}"></c:out></td>

								<td><c:choose>
										<c:when
											test="${user.tieneDinero(atraccion) && user.tieneTiempo(atraccion) && atraccion.comprobarCupo() && buscarEnLista.noCompro(atraccion, atraccionesAceptadas)}">
											<a
												href="/Tp003-TurismoEnLaTierraMedia/atraccion/buy.do?id=${atraccion.id} ep=${atraccion.esPromocion()}"
												class="btn btn-success rounded" role="button">Comprar</a>
										</c:when>
										<c:otherwise>
											<a href="#" class="btn btn-secondary rounded disabled"
												role="button">No se puede comprar</a>
										</c:otherwise>
									</c:choose></td>
							</tr>
							</c:if>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>

	</main>

</body>
</html>