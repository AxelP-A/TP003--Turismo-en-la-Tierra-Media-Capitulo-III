<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="modal-body">
    <div class="mb-3">
        <label for="nombre" class="col-form-label">Nombre:</label> <input
            class="form-control" type="text" id="nombre" name="nombre" required
            value="${usuario.getNombre()}">
    </div>
    <div class="mb-3">
        <label for="presupuesto"
            class='col-form-label '>Presupuesto:</label>
        <input class="form-control" type="number" id="presupuesto" name="presupuesto"
            required value="${usuario.presupuesto}"></input>
        <div class="invalid-feedback">
           
        </div>
    </div>
    <div class="mb-3">
        <label for="tiempoDisponible"
            class='col-form-label '>Tiempo Disponible:</label>
        <input class="form-control" type="number" id="tiempoDisponible"
            name="duration" required value="${usuario.tiempoDisponible}"></input>
        <div class="invalid-feedback">
         
        </div>
    </div>
<div class="mb-3">
        <label for="preferida"
            class='col-form-label '>Preferida:</label>
        <input class="form-control" type="text" id="preferida"
            name="preferida" required value="${usuario.getAtraccionPreferida()}"></input>
        <div class="invalid-feedback">
          
        </div>
    </div>
    <div class="mb-3">
        <label for="password"
            class='col-form-label '>Password:</label>
        <input class="form-control" type="password" id="password" name="password" required
            value="${usuario.getPassword()}"></input>
        <div class="invalid-feedback">
             
        </div>
    </div>
    <div class="mb-3">
        <label for="admin"
            class='col-form-label '>Admin:</label>
        <input class="form-control" type="text" id="descripcion" name="admin" required
            value="${usuario.isAdmin()}"></input>
        <div class="invalid-feedback">
           
        </div>
    </div>

<div>
    <button type="submit" class="btn btn-primary">Guardar</button>
    <a onclick="window.history.back();" class="btn btn-secondary"
        role="button">Cancelar</a>
</div>
</div>