# Turismo en la Tierra Media

Esta aplicación web tiene como objetivo demostrar el uso de las herramientas vistas a lo largo del curso, coordinadas e interactuando para brindar parte de la funcionalidad solicitada.

Puede utilizarse como base, o como referencia para continuar el desarrollo.

## Cómo hacerlo funcionar

1. Editar el archivo `/turismo/src/main/resources/env.properties` y colocar la ruta correcta del archivo de la base de datos.
2. Verificar la versión de JRE con la que se está compilando y construyendo.
3. Atender los problemas identificados por Eclipse en la pestaña "Problems".

## Mejoras sugeridas

1. El archivo de la base de datos NO debería estar en el proyecto. Sin embargo, se adjunta para tener uno de referencia.
2. Realizar cambios necesarios en el filtro del login para que redireccione al login si tratamos de acceder al mismo desde una ubicación no válida.
3. Generar una query y las variables necesarias para poder acceder desde todas las vistas (que utilicen esa información) a las atracciones incluidas en las promociones.
4. Mensaje de bienvenida personalizado al usuario en función de si compró anteriormente o no en nuestra tienda.