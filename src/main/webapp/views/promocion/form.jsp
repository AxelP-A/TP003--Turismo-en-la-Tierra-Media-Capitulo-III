<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="modal-body">
	<div class="mb-3">
		<label for="name" class="col-form-label">Nombre promoción:</label> <input
			class="form-control" type="text" id="name" name="name" required
			value="${promocion.getNombre()}">
	</div>
	<div class="mb-3">
		<label for="cost"
			class='col-form-label ${promocion.errors.get("costoDeVisita") != null ? "is-invalid" : "" }'>Costo:</label>
		<input class="form-control" type="number" id="cost" name="cost"
			required value="${promocion.costoDeVisita}"></input>
		<div class="invalid-feedback">
			<c:out value='${promocion.errors.get("costoDeVisita")}'></c:out>
		</div>
	</div>
	<div class="mb-3">
		<label for="duration"
			class='col-form-label ${promocion.getErrors().get("tiempoNecesario") != null ? "is-invalid" : "" }'>Tiempo
			Necesario:</label> <input class="form-control" type="number" id="duration"
			name="duration" required value="${promocion.tiempoNecesario}"></input>
		<div class="invalid-feedback">
			<c:out value='${promocion.errors.get("tiempoNecesario")}'></c:out>
		</div>
	</div>
	<div class="mb-3">
		<label for="capacity"
			class='col-form-label ${promocion.errors.get("cupoDePersonas") != null ? "is-invalid" : "" }'>Cupo:</label>
		<input class="form-control" type="number" id="capacity"
			name="capacity" required value="${promocion.getCupo()}"></input>
		<div class="invalid-feedback">
			<c:out value='${promocion.errors.get("cupoDePersonas")}'></c:out>
		</div>
	</div>
	<div class="mb-3">
		<label for="tipo"
			class='col-form-label ${promocion.errors.get("tipo") != null ? "is-invalid" : "" }'>Tipo
			de promoción:</label> <input class="form-control" type="text" id="tipo"
			name="tipo" required value="${promocion.getTipo()}"></input>
		<div class="invalid-feedback">
			<c:out value='${promocion.errors.get("tipo")}'></c:out>
		</div>
	</div>
	<div class="mb-3">
		<label for="descripcion"
			class='col-form-label ${promocion.errors.get("descripcion") != null ? "is-invalid" : "" }'>Descripcion</label>
		<input class="form-control" type="text" id="descripcion"
			name="descripcion" required value="${promocion.getDescripcion()}"></input>
		<div class="invalid-feedback">
			<c:out value='${promocion.errors.get("descripcion")}'></c:out>
		</div>
	</div>

	<div class="container mt-3">
		<h4>Atracciones incluidas</h4>
		<label for="atracciones"
			class='col-form-label ${promocion.errors.get("atracciones") != null ? "is-invalid" : "" }'>Atracciones
			Incluidas</label>
		<table class="table table-bordered table-sm">
			<thead>
				<tr>
					<th>Atraccion</th>
					<th>Accion</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${promocion.getAtraccionesIncluidas()}"
					var="atraccion">
					<tr>
						<td><c:out value="${atraccion.getNombre()}"></c:out></td>
						<td><a
							href="/Tp003-TurismoEnLaTierraMedia/promocion/edit.do?id=${attraction.id}"
							class="btn btn-light rounded-0" role="button"><i
								class="bi bi-x-circle-fill"></i></a></td>

					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
<div>
	<button type="submit" class="btn btn-primary">Guardar</button>
	<a onclick="window.history.back();" class="btn btn-secondary"
		role="button">Cancelar</a>
</div>
