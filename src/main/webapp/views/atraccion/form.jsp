<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="modal-body">
	<div class="mb-3">
		<label for="name" class="col-form-label">Nombre:</label> <input
			class="form-control" type="text" id="name" name="name" required
			value="${atraccion.getNombre()}">
	</div>
	<div class="mb-3">
		<label for="cost"
			class='col-form-label ${atraccion.errors.get("costoDeVisita") != null ? "is-invalid" : "" }'>Costo:</label>
		<input class="form-control" type="number" id="cost" name="cost"
			required value="${atraccion.costoDeVisita}"></input>
		<div class="invalid-feedback">
			<c:out value='${atraccion.errors.get("costoDeVisita")}'></c:out>
		</div>
	</div>
	<div class="mb-3">
		<label for="duration"
			class='col-form-label ${atraccion.getErrors().get("tiempoNecesario") != null ? "is-invalid" : "" }'>Tiempo Necesario:</label>
		<input class="form-control" type="number" id="duration"
			name="duration" required value="${atraccion.tiempoNecesario}"></input>
		<div class="invalid-feedback">
			<c:out value='${atraccion.errors.get("tiempoNecesario")}'></c:out>
		</div>
	</div>
	<div class="mb-3">
		<label for="capacity"
			class='col-form-label ${atraccion.errors.get("cupoDePersonas") != null ? "is-invalid" : "" }'>Cupo:</label>
		<input class="form-control" type="number" id="capacity"
			name="capacity" required value="${atraccion.getCupo()}"></input>
		<div class="invalid-feedback">
			<c:out value='${atraccion.errors.get("cupoDePersonas")}'></c:out>
		</div>
	</div>
	<div class="mb-3">
		<label for="tipo"
			class='col-form-label ${atraccion.errors.get("tipo") != null ? "is-invalid" : "" }'>Tipo:</label>
		<input class="form-control" type="text" id="tipo" name="tipo" required
			value="${atraccion.getTipo()}"></input>
		<div class="invalid-feedback">
			<c:out value='${atraccion.errors.get("tipo")}'></c:out>
		</div>
	</div>
	<div class="mb-3">
		<label for="descripcion"
			class='col-form-label ${atraccion.errors.get("descripcion") != null ? "is-invalid" : "" }'>Descripcion</label>
		<input class="form-control" type="text" id="descripcion" name="descripcion" required
			value="${atraccion.getDescripcion()}"></input>
		<div class="invalid-feedback">
			<c:out value='${atraccion.errors.get("descripcion")}'></c:out>
		</div>
	</div>
</div>
<div>
	<button type="submit" class="btn btn-primary">Guardar</button>
	<a onclick="window.history.back();" class="btn btn-secondary"
		role="button">Cancelar</a>
</div>
