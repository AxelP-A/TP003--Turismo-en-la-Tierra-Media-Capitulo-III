<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="partials/head.jsp"></jsp:include>

<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="assets/stylesheets/styles.css">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"
	integrity="sha384-DyZ88mC6Up2uqS4h/KRgHuoeGwBcD4Ng9SiP4dIRy0EXTlnuz47vAwmeGwVChigm"
	crossorigin="anonymous">
<title>Inicio de Sesion</title>
</head>
<body>
	<header>
		<div class="contenedor">
			<p><a href="index.html"> <img src="assets/img/tolkien-plano.jpg" alt="logo"></a></p>
			<div class="enlaces-contenedor">
				<a href="giftshop.html">Tienda</a>
				<a href="">Comunidad</a> 
				<a href="">Acerca de</a> 
				<a href="">Soporte</a>
			</div>
			<a href="inicioSesion.html">Iniciar sesión</a>
		</div>
	</header>

	<main>
		<div class="background-seccionUno">

			<div class="formulario-inicio-sesion">
				<h1>Inicia Sesión</h1>
				
				<c:if test="${flash != null}">
				<div class="alert alert-danger">
					<p>
						<c:out value="${flash}" />
					</p>
				</div>
			</c:if>
							
				<form action="login" method="post">

					<p>
						<label for="username"><b>Nombre de usuario</b></label>
						<input type="text" placeholder="Ingrese su usuario" name="username"
							required>
					</p>
					<p>
						<label for="password"><b>Contraseña</b></label>
						<input type="password" placeholder="Ingrese su contraseña"
							name="password" required>
					</p>
						<input type="checkbox" checked="checked"> Recuerdame
						<p> 
					</p>
					<button type="submit">Iniciar sesión</button>
					<!-- <input type="number"
					name="dni" placeholder="DNI"> <input type="email"
					name="email" placeholder="Email"> <select name="paises">
					<option value="1">Argentina</option>
					<option value="2">Colombia</option>
					<option value="3">Uruguay</option> </select> 
				 <input type="submit" value="Iniciar Sesion">-->
				</form>
			</div>
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
			<p><i class="far fa-copyright"></i> 2021 - Todos los derechos reservados</p>
		</div>
	</footer>

</body>
</html>