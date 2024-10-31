<%@ page import="utp.edu.pe.jracero.model.Trabajador" %>
<%@ page import="java.util.List" %>
<%@ page import="utp.edu.pe.jracero.model.enums.Rol" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  // Verificar la sesión
  HttpSession ses = request.getSession(false);
  if (ses == null || ses.getAttribute("admin") == null) {
    response.sendRedirect("index.jsp");
    return;
  }
%>
<%
  Trabajador trabajador;
  if (request.getAttribute("trabajador") != null) {
    trabajador = (Trabajador) request.getAttribute("trabajador");
  } else {
    trabajador = new Trabajador();
  }

  List<Rol> rols = List.of(Rol.values());
%>
<jsp:include page="component/header.jsp"></jsp:include>
<jsp:include page="component/sidebar.jsp"></jsp:include>
<jsp:include page="component/navbar.jsp"></jsp:include>
<div class="container-fluid">
  <div class="card">
    <div class="card-body">
      <h5 class="card-title fw-semibold mb-4">Actualizar trabajador</h5>
      <form method="post" action="<%=request.getContextPath() %>/updateWorker">
        <input type="hidden" name="id_trabajador" value="<%= trabajador.getId_trabajador() %>">
        <div class="mb-3">
          <label for="nombre" class="form-label">Nombre</label>
          <input type="text" class="form-control" id="nombre" name="nombre" value="<%= trabajador.getNombre() %>" required>
        </div>
        <div class="mb-3">
          <label for="apellido" class="form-label">Apellido</label>
          <input type="text" class="form-control" id="apellido" name="apellido" value="<%= trabajador.getApellido() %>" required>
        </div>
        <div class="mb-3">
          <label for="dni" class="form-label">DNI</label>
          <input type="text" class="form-control" id="dni" name="dni" value="<%= trabajador.getDni() %>" required>
        </div>
        <div class="mb-3">
          <label for="rol" class="form-label">Rol</label>
          <select name="rol" class="form-control" id="rol">
            <% for (Rol rol : rols) { %>
            <option value="<%= rol.name() %>" <%= trabajador.getRol().equals(rol.name()) ? "selected" : "" %>><%= rol.name() %></option>
            <% } %>
          </select>
        </div>
        <div class="mb-3">
          <label for="telefono" class="form-label">Teléfono</label>
          <input type="text" class="form-control" id="telefono" name="telefono" value="<%= trabajador.getTelefono() %>" required>
        </div>
        <div class="mb-3">
          <label for="correo" class="form-label">Correo Electrónico</label>
          <input type="email" class="form-control" id="correo" name="correo" value="<%= trabajador.getCorreo() %>" required>
        </div>
        <div class="mb-3">
          <label for="pwd" class="form-label">Contraseña</label>
          <input type="password" class="form-control" id="pwd" name="pwd" required>
        </div>
        <button type="submit" class="btn btn-primary">Actualizar</button>
      </form>
      <a href=<%=request.getContextPath() %>/workers><button type="button" class="btn btn-danger"
                                         style="margin-top: 20px; float: right;">Cancelar</button></a>
    </div>
  </div>
</div>
<jsp:include page="component/footer.jsp"></jsp:include>