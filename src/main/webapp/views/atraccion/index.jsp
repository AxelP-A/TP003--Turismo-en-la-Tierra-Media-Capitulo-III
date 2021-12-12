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
<title>Atracciones y promociones</title>

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
	window.addEventListener('DOMContentLoaded', function() {
		$('.dataTable').DataTable({
			"order" : [ [ 2, "asc" ], [ 5, "desc" ] ]
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
				<a href="../index.jsp">Inicio</a> <a href="">Comunidad</a> <a href="">Acerca
					de</a> <a href="">Soporte</a>
			</div>
			<c:out value="${user.nombre}" />
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
				<h1>Estas son las atracciones de la Tierra Media</h1>
			</div>
			<p></p>

			<c:if test="${user.isAdmin()}">
				<div class="mb-3">
					<a href="/Tp003-TurismoEnLaTierraMedia/atraccion/create.do"
						class="btn btn-primary" role="button"> <i
						class="bi bi-plus-lg"></i> Nueva Atracción
					</a>
				</div>
			</c:if>
			<table class="dataTable table-promocion" style="width: 70%">
				<thead>
					<tr>
						<th>Atraccion</th>
						<th>Costo</th>
						<th>Duracion</th>
						<th>Cupo</th>
						<th>Tipo</th>
						<th>Acciones</th>
					</tr>
				</thead>
				<tbody>

					<c:choose>
						<c:when test="${user.admin}">

							<c:forEach items="${atraccion}" var="atraccion">
								<tr>
									<td><strong><c:out value="${atraccion.nombre}"></c:out></strong>
										<p><c:out value="${atraccion.getDescripcion()}"></c:out></p></td>
									<td><c:out value="${atraccion.getCostoDeVisita()}"></c:out></td>
									<td><c:out value="${atraccion.tiempoNecesario}"></c:out></td>
									<td><c:out value="${atraccion.getCupo()}"></c:out></td>
									<td><c:out value="${atraccion.getTipo()}"></c:out></td>
									

									<c:choose>
										<c:when test="${!atraccion.esPromocion()}">
											<td><a href="#${atraccion.getId()}-atraccion"><i
													class="fas fa-angle-double-right"></i></a></td>
										</c:when>
										<c:otherwise>
											<td class="open-promo"><a href="#${atraccion.getId()}-promocion"><i
													class="fas fa-angle-double-right"></i></a></td>
										</c:otherwise>
									</c:choose>

									<td><a
										href="/Tp003-TurismoEnLaTierraMedia/atraccion/edit.do?id=${atraccion.id}">
											<i class="fas fa-pen-square"></i>
									</a> <a
										href="/Tp003-TurismoEnLaTierraMedia/atraccion/delete.do?id=${atraccion.id}"><i
											class="fas fa-trash-alt"></i></a></td>
							</c:forEach>

						</c:when>
						<c:otherwise>
							<c:forEach items="${atraccion}" var="atraccion">

								<c:if test="${atraccion.estaHabilitada()}">
									<tr>
										<td><strong><c:out value="${atraccion.nombre}"></c:out></strong>
											<p><c:out value="${atraccion.getDescripcion()}"></c:out></p></td>
										<td><c:out value="${atraccion.costoDeVisita}"></c:out></td>
										<td><c:out value="${atraccion.tiempoNecesario}"></c:out></td>
										<td><c:out value="${atraccion.getCupo()}"></c:out></td>
										<td><c:out value="${atraccion.getTipo()}"></c:out></td>
										

										<td><c:choose>
												<c:when
													test="${user.tieneDinero(atraccion) && user.tieneTiempo(atraccion) && atraccion.comprobarCupo() && buscarEnLista.noCompro(atraccion, atraccionesAceptadas)}">
													<a
														href="/Tp003-TurismoEnLaTierraMedia/atraccion/buy.do?id=${atraccion.id}&ep=${atraccion.esPromocion()}"
														class="btn btn-success rounded" role="button">Comprar</a>
												</c:when>
												<c:otherwise>
													<a href="#" class="btn btn-secondary rounded disabled"
														role="button">No se puede comprar</a>
												</c:otherwise>
											</c:choose></td>

										<c:choose>
											<c:when test="${!atraccion.esPromocion()}">
												<td><a href="#${atraccion.getId()}-atraccion"><i
														class="fas fa-angle-double-right"></i></a></td>
											</c:when>
											<c:otherwise>
												<td class="open-promo"><a href="#${atraccion.getId()}-promocion"><i
														class="fas fa-angle-double-right"></i></a></td>
											</c:otherwise>
										</c:choose>
									</tr>
								</c:if>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
		

		
	 	
		<div class="container-all" id="1-atraccion">
			<div class="popup">
				<div class="img uno"></div>
				<div class="container-text">
					<h2>Moria</h2>
					<p>Fue fundada por Durin I el Inmortal en los albores de la
						Primera Edad en las cuevas que daban a Azanulbizar. Se encontraban
						en el centro de las montañas Nubladas, bajo los picos Caradhras,
						monte Nuboso y Cuerno de Plata.</p>
					<span><i class="fas fa-hourglass-start"></i> Tiempo:</span> <span><i
						class="fas fa-users"></i> Cupo:</span> <span><i
						class="fas fa-dollar-sign"></i> Precio:</span>
				</div>
				<a href="#" class="btn-close-popup">X</a>
			</div>
		</div>
		
		<div class="container-all" id="2-atraccion">
			<div class="popup">
				<div class="img dos"></div>
				<div class="container-text">
					<h2>Minas Tirith</h2>
					<p>En la Tercera Edad, se llamó Minas Tirith a la ciudad
						capital del reino de Gondor. Originalmente se le conoció como
						Minas Anor (Torre del Sol, en sindarin), adoptando el nuevo nombre
						tras el cambio de capital, tras la decadencia de la antigua
						capital Osgiliath</p>
					<span><i class="fas fa-hourglass-start"></i> Tiempo:</span> <span><i
						class="fas fa-users"></i> Cupo:</span> <span><i
						class="fas fa-dollar-sign"></i> Precio:</span>
				</div>
				<a href="#" class="btn-close-popup">X</a>
			</div>
		</div>
		<div class="container-all" id="3-atraccion">
			<div class="popup">
				<div class="img tres"></div>
				<div class="container-text">
					<h2>La Comarca</h2>
					<p>La Comarca (The Shire en inglés) es el nombre que recibe la
						región de la Tierra Media en la que viven los Hobbits, una raza
						emparentada con los Hombres que se asentó en esta zona a mediados
						de la Tercera Edad</p>
					<span><i class="fas fa-hourglass-start"></i> Tiempo:</span> <span><i
						class="fas fa-users"></i> Cupo:</span> <span><i
						class="fas fa-dollar-sign"></i> Precio:</span>
				</div>
				<a href="#" class="btn-close-popup">X</a>
			</div>
		</div>
		<div class="container-all" id="4-atraccion">
			<div class="popup">
				<div class="img cuatro"></div>
				<div class="container-text">
					<h2>Mordor</h2>
					<p>En esta región principalmente volcánica, situada al este de
						Gondor, Sauron forjó el Anillo Único en las fraguas del Monte del
						Destino, hacia el año 1600 SE, con el objetivo de dominar por
						completo a los pueblos de la Tierra Media. Miles de años después,
						Mordor se convirtió en el escenario de las últimas aventuras de
						los miembros de la Comunidad del Anillo en su afán por destruir el
						Anillo Único, durante la Guerra del Anillo.3​</p>
					<span><i class="fas fa-hourglass-start"></i> Tiempo:</span> <span><i
						class="fas fa-users"></i> Cupo:</span> <span><i
						class="fas fa-dollar-sign"></i> Precio:</span>
				</div>
				<a href="#" class="btn-close-popup">X</a>
			</div>
		</div>
		<div class="container-all" id="5-atraccion">
			<div class="popup">
				<div class="img cinco"></div>
				<div class="container-text">
					<h2>Abismo de Helm</h2>
					<p>La Batalla del Abismo de Helm es una batalla pertenciente a
						la Guerra del Anillo en la que se enfrentaron las fuerzas de Rohan
						y las de Isengard. Participaron también varios miembros de la
						Comunidad del Anillo y los ucornos.</p>
					<span><i class="fas fa-hourglass-start"></i> Tiempo:</span> <span><i
						class="fas fa-users"></i> Cupo:</span> <span><i
						class="fas fa-dollar-sign"></i> Precio:</span>
				</div>
				<a href="#" class="btn-close-popup">X</a>
			</div>
		</div>
		<div class="container-all" id="6-atraccion">
			<div class="popup">
				<div class="img seis"></div>
				<div class="container-text">
					<h2>Lothlorien</h2>
					<p>En el universo imaginario de J. R. R. Tolkien y en la novela
						El Señor de los Anillos, Lothlórien es un reino élfico de la
						Tierra Media, ubicado al este de las Montañas Nubladas y más allá
						de las puertas de Moria. En Aman existen unos jardines llamados
						Lórien, destinados al reposo y la curación de las almas.</p>
					<span><i class="fas fa-hourglass-start"></i> Tiempo:</span> <span><i
						class="fas fa-users"></i> Cupo:</span> <span><i
						class="fas fa-dollar-sign"></i> Precio:</span>
				</div>
				<a href="#" class="btn-close-popup">X</a>
			</div>
		</div>
		<div class="container-all" id="7-atraccion">
			<div class="popup">
				<div class="img siete"></div>
				<div class="container-text">
					<h2>Erebor</h2>
					<p>Erebor, también llamada la Montaña Solitaria, es un lugar
						ficticio perteneciente al legendarium del escritor J. R. R.
						Tolkien y que aparece en su novela El hobbit. Se trata de una
						elevación aislada situada al noreste de Rhovanion, que fue
						arrebatada por un dragón, llamado Smaug, al rey Thrór de los
						enanos.</p>
					<span><i class="fas fa-hourglass-start"></i> Tiempo:</span> <span><i
						class="fas fa-users"></i> Cupo:</span> <span><i
						class="fas fa-dollar-sign"></i> Precio:</span>
				</div>
				<a href="#" class="btn-close-popup">X</a>
			</div>
		</div>
		<div class="container-all" id="8-atraccion">
			<div class="popup">
				<div class="img ocho"></div>
				<div class="container-text">
					<h2>Bosque Negro</h2>
					<p>El Bosque Negro (Mirkwood en inglés), también llamado
						Taur-nu-Fuin o Taur-e-Ndadelos, fue un enorme bosque situado en
						Rhovanion, al nordeste de la Tierra Media. Conocido anteriormente
						como el Gran Bosque Verde, desde la construcción de la fortaleza
						de Dol Guldur por parte de Sauron, una gran sombra se cernió sobre
						el bosque, pasando a llamarse Bosque Negro.</p>
					<span><i class="fas fa-hourglass-start"></i> Tiempo:</span> <span><i
						class="fas fa-users"></i> Cupo:</span> <span><i
						class="fas fa-dollar-sign"></i> Precio:</span>
				</div>
				<a href="#" class="btn-close-popup">X</a>
			</div>
		</div>
		
		
		
		<div class="container-all" id="1-promocion">
			<div class="popup-promo">
				<div class="img nueve"></div>
				<div class="container-text">
					<h2>Promo Aventura</h2>
					<span>Atraccion 1: </span> <span>Atraccion 2: </span> <span><i
						class="fas fa-hourglass-start"></i> Tiempo:</span> <span><i
						class="fas fa-users"></i> Cupo:</span> <span><i
						class="fas fa-dollar-sign"></i> Precio:</span>
				</div>
				<a href="#" class="btn-close-popup">X</a>
			</div>
		</div>
		<div class="container-all" id="2-promocion">
			<div class="popup-promo">
				<div class="img once"></div>
				<div class="container-text">
					<h2>Promo Desgustación</h2>
					<span>Atraccion 1: </span> <span>Atraccion 2: </span> <span><i
						class="fas fa-hourglass-start"></i> Tiempo:</span> <span><i
						class="fas fa-users"></i> Cupo:</span> <span><i
						class="fas fa-dollar-sign"></i> Precio:</span>
				</div>
				<a href="#" class="btn-close-popup">X</a>
			</div>
		</div>
		<div class="container-all" id="3-promocion">
			<div class="popup-promo">
				<div class="img diez"></div>
				<div class="container-text">
					<h2>Promo Paisaje</h2>
					<span>Atraccion 1: </span> <span>Atraccion 2: </span> <span><i
						class="fas fa-hourglass-start"></i> Tiempo:</span> <span><i
						class="fas fa-users"></i> Cupo:</span> <span><i
						class="fas fa-dollar-sign"></i> Precio:</span>
				</div>
				<a href="#" class="btn-close-popup">X</a>
			</div>
		</div>
		<div class="container-all" id="4-promocion">
			<div class="popup-promo">
				<div class="img doce"></div>
				<div class="container-text">
					<h2>Promo Aventura 2</h2>
					<span>Atraccion 1: </span> <span>Atraccion 2: </span> <span><i
						class="fas fa-hourglass-start"></i> Tiempo:</span> <span><i
						class="fas fa-users"></i> Cupo:</span> <span><i
						class="fas fa-dollar-sign"></i> Precio:</span>
				</div>
				<a href="#" class="btn-close-popup">X</a>
			</div>
		</div>
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