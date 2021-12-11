<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="modal-body">
	<div class="mb-3">
		<label for="name" class="col-form-label">Nombre:</label> <input
			type="text" class="form-control" id="name" name="name"
			required value="${atraccion.nombre}">
	</div>
	<div class="mb-3">
		<label for="cost"
			class='col-form-label ${atraccion.errors.get("costoDeVisita") != null ? "is-invalid" : "" }'>Costo:</label>
		<input class="form-control" type="number" id="cost" name="cost"
			required value="${atraccion.CostoDeVisita}"></input>
		<div class="invalid-feedback">
			<c:out value='${atraccion.errors.get("costoDeVisita")}'></c:out>
		</div>
	</div>
	<div class="mb-3">
		<label for="duration"
			class='col-form-label ${atraccion.errors.get("duration") != null ? "is-invalid" : "" }'>Duration:</label>
		<input class="form-control" type="number" id="duration" name="duration"
			required value="${atraccion.duration}"></input>
		<div class="invalid-feedback">
			<c:out value='${atraccion.errors.get("duration")}'></c:out>
		</div>
	</div>
	<div class="mb-3">
		<label for="capacity"
			class='col-form-label ${atraccion.errors.get("capacity") != null ? "is-invalid" : "" }'>Capacity:</label>
		<input class="form-control" type="number" id="capacity" name="capacity"
			required value="${atraccion.capacity}"></input>
		<div class="invalid-feedback">
			<c:out value='${atraccion.errors.get("capacity")}'></c:out>
		</div>
	</div>
	<div class="mb-3">
		<label for="tipo"
			class='col-form-label ${atraccion.errors.get("tipo") != null ? "is-invalid" : "" }'>Tipo:</label>
		<input class="form-control" type="text" id="tipo" name="tipo"
			required value="${atraccion.tipo}"></input>
		<div class="invalid-feedback">
			<c:out value='${atraccion.errors.get("tipo")}'></c:out>
		</div>
	</div>
</div>
<div>
	<button type="submit" class="btn btn-primary">Guardar</button>
	<a onclick="window.history.back();" class="btn btn-secondary"
		role="button">Cancelar</a>
</div>
