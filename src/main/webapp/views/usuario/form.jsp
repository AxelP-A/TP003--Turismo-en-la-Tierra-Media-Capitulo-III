<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="modal-body">
    <div class="mb-3">
        <label for="nombre" class="col-form-label">Nombre:</label> <input
            class="form-control" type="text" id="nombre" name="nombre" required
            value="${usuario.getNombre()}">
    </div>
    <div class="mb-3">
        <label for="presupuesto"
            class='col-form-label ${usuario.getErrors().get("presupuesto") != null ? "is-invalid" : "" }'>Presupuesto:</label>
        <input class="form-control" type="number" id="presupuesto" name="presupuesto"
            required value="${usuario.presupuesto}"></input>
        <div class="invalid-feedback">
            <c:out value='${usuario.errors.get("presupuesto")}'></c:out>
        </div>
    </div>
    <div class="mb-3">
        <label for="tiempoDisponible"
            class='col-form-label ${usuario.getErrors().get("tiempoDisponible") != null ? "is-invalid" : "" }'>Tiempo Disponible:</label>
        <input class="form-control" type="number" id="tiempoDisponible"
            name="tiempoDisponible" required value="${usuario.tiempoDisponible}"></input>
        <div class="invalid-feedback">
            <c:out value='${usuario.errors.get("tiempoDisponible")}'></c:out>
        </div>
    </div>
<div class="mb-3">
        <label for="preferida"
            class='col-form-label ${usuario.errors.get("preferida") != null ? "is-invalid" : "" }'>Tipo de atracción preferida:</label>
        <input class="form-control" type="text" id="preferida"
            name="preferida" required value="${usuario.getAtraccionPreferida()}"></input>
        <div class="invalid-feedback">
            <c:out value='${usuario.errors.get("preferida")}'></c:out>
        </div>
</div>
    <div class="mb-3">
        <label for="password"
            class='col-form-label ${usuario.errors.get("password") != null ? "is-invalid" : "" }'>Password:</label>
        <input class="form-control" type="text" id="password" name="password" required
            value=""></input>
        <div class="invalid-feedback">
            <c:out value='${usuario.errors.get("password")}'></c:out>
        </div>
    </div>
    <div class="mb-3">
     <div class="ui checkbox">
  <input type="checkbox" name="admin">
  <label>Cuenta con permisos de administración</label>
</div>
     

    
<div>
    <button type="submit" class="btn btn-primary">Guardar</button>
    <a onclick="window.history.back();" class="btn btn-secondary"
        role="button">Cancelar</a>
</div>
</div>