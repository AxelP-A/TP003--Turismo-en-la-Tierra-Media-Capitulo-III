<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="modal-body">
	<div class="mb-3">
		<label for="name" class="col-form-label">Nombre promoción:</label> <input
			class="form-control" type="text" id="name" name="name" required
			value="${promocion.getNombre()}">
	</div>
	<div class="mb-3">
		<label for="cost"
			class='col-form-label ${promocion.errors.get("calculoDeCosto") != null ? "is-invalid" : "" }'>Coeficiente
			promocion:</label> <input class="form-control" type="number" id="cost"
			name="cost" required value="${promocion.getCalculoDeCosto()}"></input>
		<div class="invalid-feedback">
			<c:out value='${promocion.errors.get("calculoDeCosto")}'></c:out>
		</div>
	</div>
	<!--  <div class="mb-3">
		<label for="duration"
			class='col-form-label ${promocion.getErrors().get("tiempoNecesario") != null ? "is-invalid" : "" }'>Tiempo
			Necesario:</label> <input class="form-control" type="number" id="duration"
			name="duration" required value="${promocion.tiempoNecesario}" placeholder="Disabled input" disabled></input>
		<div class="invalid-feedback">
			<c:out value='${promocion.errors.get("tiempoNecesario")}'></c:out>
		</div>
	</div>-->
	<!--  <div class="mb-3">
		<label for="capacity"
			class='col-form-label ${promocion.errors.get("cupoDePersonas") != null ? "is-invalid" : "" }'>Cupo:</label>
		<input class="form-control" type="number" id="capacity"
			name="capacity" required value="${promocion.getCupo()}" placeholder="Disabled input" disabled></input>
		<div class="invalid-feedback">
			<c:out value='${promocion.errors.get("cupoDePersonas")}'></c:out>
		</div>
	</div>
	<!--  <div class="mb-3">
		<label for="tipo"
			class='col-form-label ${promocion.errors.get("tipo") != null ? "is-invalid" : "" }'>Tipo
			de promoción:</label> <input class="form-control" type="text" id="tipo"
			name="tipo" required value="${promocion.getTipo()}"></input>
		<div class="invalid-feedback">
			<c:out value='${promocion.errors.get("tipo")}'></c:out>
		</div>
	</div>-->
	<div class="mb-3">
		<label for="sel1" class="form-label">Select list (select one):</label> 
		<select	class="form-select" id="sel1" name="sellist1">
			<option selected value="AXB">AxB</option>
			<option value="ABSOLUTA">ABSOLUTA</option>
			<option value="PORCENTUAL">PORCENTUAL</option>
		</select>
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

	<jsp:useBean id="buscarEnLista" class="services.BuscarEnListaService" />
	<div>
		<label for="sel2" class="form-label">Mutiple select list (hold
			shift to select more than one):</label> <select multiple class="form-select"
			id="sel2" name="sellist2">
			<c:forEach items="${atracciones}" var="atraccion">
				<c:choose>
					<c:when
						test="${!buscarEnLista.noCompro(atraccion, promocion.getAtraccionesIncluidas())}">
						<option <c:out value="selected"></c:out>
							value="${atraccion.getId()}"><c:out
								value="${atraccion.getNombre()}"></c:out> (
							<c:out value="${atraccion.getTipo()}"></c:out>)
						</option>
					</c:when>
					<c:otherwise>
						<option value="${atraccion.getId()}"><c:out
								value="${atraccion.getNombre()}"></c:out> (
							<c:out value="${atraccion.getTipo()}"></c:out>)


						</option>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</select>
	</div>
</div>
<div>
	<button type="submit" class="btn btn-primary">Guardar</button>
	<a onclick="window.history.back();" class="btn btn-secondary"
		role="button">Cancelar</a>
</div>
