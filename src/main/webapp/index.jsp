<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="partials/head.jsp"></jsp:include>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css"
	href="assets/stylesheets/styles.css">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"
	integrity="sha384-DyZ88mC6Up2uqS4h/KRgHuoeGwBcD4Ng9SiP4dIRy0EXTlnuz47vAwmeGwVChigm"
	crossorigin="anonymous">
<title>Turismo en la tierra media - Grupo 404</title>
</head>
<body>
	<header>
		<div class="contenedor">
			<p>
				<a href="index.jsp"> <img src="assets/img/tolkien-plano.jpg"
					alt="logo"></a>
			</p>
			<div class="enlaces-contenedor">
				<a href="">Acerca de</a> <a href="">Soporte</a>
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
	<!--   <jsp:include page="partials/nav.jsp"></jsp:include> -->
	<main>
		<div class="background-seccionUno">
			<section class="seccion-uno">
				<h1>
					Bienvenido a Turismo en la Tierra Media,
					<c:out value="${user.nombre}" />
					!
				</h1>
				<div class="barra">
					<a href="views/giftshop.html">Giftshop</a> <a
						href="views/galeria.html">Galeria de Fotos</a> <a
						href="atraccion/index.do">Tienda</a>
					<!--   <a href="views/atraccion/index.jsp">Tienda</a>-->
					<a href="">Comentarios</a> <a href="">Contacto</a>
					<c:if test="${!user.isAdmin()}">
						<a href="itinerario/index.do">Itinerario</a>
					</c:if>
					<c:if test="${user.isAdmin()}">
						<a href="usuario/index.do">Usuarios</a>
					</c:if>
				</div>
				<h2>Atracciones en la Tierra Media</h2>
				<ul class="galeria">
					<li><a href="#img1"><img
							src="assets/img/AbismoDeHelm/abismodehelm.jpg"></a></li>
					<li><a href="#img2"><img
							src="assets/img/BosqueNegro/bosquenegro.jpg"></a></li>
					<li><a href="#img3"><img
							src="assets/img/Erebor/erebor.jpg"></a></li>
					<li><a href="#img4"><img
							src="assets/img/LaComarca/lacomarcacasaBilbo.jpeg"></a></li>
					<li><a href="#img5"><img
							src="assets/img/Lothlorien/lothlorien.jpg"></a></li>
					<li><a href="#img6"><img
							src="assets/img/MinasTirith/minastirith.jpg"></a></li>
				</ul>
			</section>
		</div>
		<div class="modal" id="img1">
			<h3>Abismo De Helm</h3>
			<div class="imagen">
				<a href="#img6">&#60;</a> <a href="#img2"><img
					src="assets/img/AbismoDeHelm/abismodehelm.jpg"></a> <a
					href="#img2">></a>
			</div>
			<a class="cerrar" href=""><i class="fas fa-times-circle"></i></a>
		</div>
		<div class="modal" id="img2">
			<h3>Bosque Negro</h3>
			<div class="imagen">
				<a href="#img1">&#60;</a> <a href="#img3"><img
					src="assets/img/BosqueNegro/bosquenegro.jpg"></a> <a href="#img3">></a>
			</div>
			<a class="cerrar" href=""><i class="fas fa-times-circle"></i></a>
		</div>
		<div class="modal" id="img3">
			<h3>Erebor</h3>
			<div class="imagen">
				<a href="#img2">&#60;</a> <a href="#img4"><img
					src="assets/img/Erebor/erebor.jpg"></a> <a href="#img4">></a>
			</div>
			<a class="cerrar" href=""><i class="fas fa-times-circle"></i></a>
		</div>
		<div class="modal" id="img4">
			<h3>La Comarcar</h3>
			<div class="imagen">
				<a href="#img3">&#60;</a> <a href="#img5"><img
					src="assets/img/LaComarca/lacomarcacasaBilbo.jpeg"></a> <a
					href="#img5">></a>
			</div>
			<a class="cerrar" href=""><i class="fas fa-times-circle"></i></a>
		</div>
		<div class="modal" id="img5">
			<h3>Lothlorien</h3>
			<div class="imagen">
				<a href="#img4">&#60;</a> <a href="#img6"><img
					src="assets/img/Lothlorien/lothlorien.jpg"></a> <a href="#img6">></a>
			</div>
			<a class="cerrar" href=""><i class="fas fa-times-circle"></i></a>
		</div>
		<div class="modal" id="img6">
			<h3>Minas Tirith</h3>
			<div class="imagen">
				<a href="#img5">&#60;</a> <a href="#img1"><img
					src="assets/img/MinasTirith/minastirith.jpg"></a> <a href="#img1">></a>
			</div>
			<a class="cerrar" href=""><i class="fas fa-times-circle"></i></a>
		</div>

		<div class="background-seccionDos">
			<section class="seccion-dos">
				<h3>Sobre Nosotros</h3>
				<p>
					El parque tematico Tierra Media fue creado por el arquitecto
					Federico Gaggiosanvarg en el año 1996, a 20 kilometros al sur de la
					ciudad de Tandil, provincia de Buenos Aires, inspirado en los
					famosos libros de J.R.R. Tolkien, "El Señor de los Anillos" y "El
					hobbit".<br> Desde pequeño Gaggiosanvarg soñaba con recrear
					esos lugares tan mágicos que describía Tolkien en sus libros, por
					lo cual, despues de convertirse en un arquitecto de fama mundial,
					centro parte de su fortuna en crear su soñado parque temático. Le
					llevó aproximadamente 4 años construir el parque, con ayuda de
					inversores japoneses y estadounidenses pudo terminar el parque que
					hoy es tan importante por su belleza y atraccion turistica para la
					ciudad de Tandil y la Argentina.<br> Su dimension actual es de
					12800 hectáreas, cuenta con ocho atracciones principales: Moria,
					Minas Tirith, La Comarca, Mordor, Abismo de Helm, Lothlorien,
					Erebor y Bosque Negro. Cada una de las atracciones posee las
					caracteristicas mas importantes descritas por Tolkien.
					Gaggiosanvarg superviso hasta el mas minimo detalle durante la
					construccion del parque, valiendose de experimentados escultores,
					ingenieros, artistas plásticos, constructores y arquitectos para
					dar vida a su visión, que dio como resultado a uno de los parques
					temáticos mas famosos e importantes del mundo. <br> Dia a dia
					recibe visitantes de todo el globo; con una afluencia promedio de
					35000 personas diarias, lo cual es de suma importancia para la
					economia regional y nacional. Dentro del parque se puede disfrutar
					de paseos a caballo,cuatriciclo,bicicleta o trekking por paisajes
					fantasticos como Minas Tirith con sus imponentes construcciones y
					vistas; actividades como canopy, tirolesa o salto bungee desde los
					imponentes arboles de Bosque Negro, revivir el terror de la mirada
					del ojo de Sauron durante la "caminata del martirio de Frodo" hacia
					el Monte del Destino o el grito escalofriante de los Nazgul volando
					al lado de los parapentes y globos aerostaticos en Mordor,
					disfrutar del exclusivo menu en los restaurants y tabernas de La
					Comarca mientras se observa el show diario de fuegos artificiales,o
					los siete hoteles spa cuatro estrellas construidos sobre los
					arboles en Lothlorien, con todas las comodidades que puede ofrecer
					un hotel de alta categoria. <br> Tierra Media es una vision
					cumplida de un soñador, que ha logrado los mas altos estandares;
					por la calidad de sus servicios, por su exclusivo diseño, por la
					seguridad de sus atracciones, y por lo mas importante...porque nos
					siguen eligiendo nuestros visitantes a diario!
				</p>
				<h3>Promociones 2021</h3>
			</section>
		</div>
		<div class="background-seccionTres">
			<section class="seccion-tres">
				<article>
					<div class="background promoAbsoluta">
						<i class="fas fa-heart"></i>
					</div>
					<img src="assets/img/MinasTirith/minastirith4.jpg" alt="promo1">
					<h3>Promo Absoluta</h3>
					<p>Lorem ipsum dolor sit amet consectetur, adipisicing elit.
						Corrupti, quae deserunt. Enim quis praesentium fugiat voluptate
						labore illum eos officiis?.</p>
					<h4>Incluye</h4>
					<ul>
						<li>item 1</li>
						<li>item 2</li>
						<li>item 3</li>
					</ul>
					<button>Comprar</button>
				</article>
				<article>
					<div class="background promoPorcentual">
						<i class="fas fa-heart"></i>
					</div>
					<img class="imgProfileDos"
						src="assets/img/AbismoDeHelm/abismodehelm3.jpg" alt="promo2">
					<h3>Promo Porcentual</h3>
					<p>Lorem ipsum dolor sit amet consectetur, adipisicing elit.
						Corrupti, quae deserunt. Enim quis praesentium fugiat voluptate
						labore illum eos officiis?.</p>
					<h4>Incluye</h4>
					<ul>
						<li>item 1</li>
						<li>item 2</li>
						<li>item 3</li>
					</ul>
					<button>Comprar</button>
				</article>
				<article>
					<div class="background promoAXB">
						<i class="fas fa-heart"></i>
					</div>
					<img class="imgProfileDos" src="assets/img/anillopaisaje.jpg"
						alt="promoaxb">
					<h3>Promo AxB</h3>
					<p>Lorem ipsum dolor sit amet consectetur, adipisicing elit.
						Corrupti, quae deserunt. Enim quis praesentium fugiat voluptate
						labore illum eos officiis?.</p>
					<h4>Incluye</h4>
					<ul>
						<li>item 1</li>
						<li>item 2</li>
						<li>item 3</li>
					</ul>
					<button>Comprar</button>
				</article>
			</section>
		</div>
	</main>
	<footer>
		<div class="contenedor">
			<img src="assets/img/tolkien-plano.jpg" alt="logo">
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
</body>

</html>