<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
  // Verificar la sesión
  HttpSession ses = request.getSession(false);
  if (ses == null || ses.getAttribute("admin") == null) {
    response.sendRedirect("index.jsp");
    return;
  }
%>
<jsp:include page="component/header.jsp"></jsp:include>
<jsp:include page="component/sidebar.jsp"></jsp:include>
<jsp:include page="component/navbar.jsp"></jsp:include>
<div class="container-fluid">
  <div class="card">
    <div class="card-body">
      <h5 class="card-title fw-semibold mb-4">Registrar nuevo proveedor</h5>
      <form method="post" action="<%= request.getContextPath() %>/addProvider">
        <div class="mb-3">
          <label for="empresa" class="form-label">Nombre de la Empresa</label>
          <input type="text" class="form-control" id="empresa" name="empresa" required>
        </div>
        <div class="mb-3">
          <label for="telefono" class="form-label">Teléfono</label>
          <input type="text" class="form-control" id="telefono" name="telefono" required>
        </div>
        <div class="mb-3">
          <label for="correo" class="form-label">Correo Electrónico</label>
          <input type="email" class="form-control" id="correo" name="correo" required>
        </div>
        <div class="mb-3">
          <label for="ruc" class="form-label">RUC</label>
          <input type="text" class="form-control" id="ruc" name="ruc" required>
        </div>
        <button type="submit" class="btn btn-primary">Registrar</button>
      </form>
      <a href="<%= request.getContextPath() %>/providers"><button type="button" class="btn btn-danger"
                                         style="margin-top: 20px; float: right;">Cancelar</button></a>
    </div>
  </div>
</div>
<jsp:include page="component/footer.jsp"></jsp:include>