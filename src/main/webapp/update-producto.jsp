<%@ page import="utp.edu.pe.jracero.model.Categoria" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="utp.edu.pe.jracero.model.Producto" %>
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
  List<Categoria> categorias;
  if (request.getAttribute("categorias") != null) {
    categorias = (List<Categoria>) request.getAttribute("categorias");
  } else {
    categorias = new ArrayList<>();
  }

  Producto producto;
  if (request.getAttribute("producto") != null) {
    producto = (Producto) request.getAttribute("producto");
  } else {
    producto = new Producto();
  }
%>
<jsp:include page="component/header.jsp"></jsp:include>
<jsp:include page="component/sidebar.jsp"></jsp:include>
<jsp:include page="component/navbar.jsp"></jsp:include>
<div class="container-fluid">
  <div class="card">
    <div class="card-body">
      <h5 class="card-title fw-semibold mb-4"><%= (producto.getId_producto() > 0) ? "Actualizar producto" : "Registrar nuevo producto" %></h5>
      <form method="post" action="<%= request.getContextPath() %>/<%= (producto.getId_producto() > 0) ? "updateProduct" : "addProduct" %>" enctype="multipart/form-data">
        <input type="hidden" name="id_producto" value="<%= producto.getId_producto() %>">
        <div class="mb-3">
          <label for="nombre" class="form-label">Ingresa el nombre</label>
          <input type="text" class="form-control" id="nombre" name="nombre" value="<%= producto.getNombre() %>" required>
        </div>
        <div class="mb-3">
          <label for="categoria" class="form-label">Selecciona la categoría</label>
          <select name="categoria" class="form-control" id="categoria">
            <% for (Categoria categoria : categorias) { %>
            <option value="<%= categoria.getId_categoria() %>" <%= (producto.getId_categoria() == categoria.getId_categoria()) ? "selected" : "" %>><%= categoria.getNombre() %></option>
            <% } %>
          </select>
        </div>
        <div class="mb-3">
          <label for="tipo" class="form-label">Ingresa el tipo</label>
          <input type="text" class="form-control" id="tipo" name="tipo" value="<%= producto.getTipo() != null ? producto.getTipo().toString() : "" %>" required>
        </div>
        <div class="mb-3">
          <label for="descripcion" class="form-label">Ingresa la descripción</label>
          <input type="text" class="form-control" id="descripcion" name="descripcion" value="<%= producto.getDescripcion() %>" required>
        </div>
        <div class="mb-3">
          <label for="precio" class="form-label">Ingresa el precio</label>
          <input type="text" class="form-control" id="precio" name="precio" value="<%= producto.getPrecio() %>" required>
        </div>
        <div class="mb-3">
          <label for="image" class="form-label">Ingresa la imagen</label>
          <input type="file" id="image" name="image" <%= (producto.getId_producto() > 0) ? "" : "required" %>>
        </div>
        <button type="submit" class="btn btn-primary"><%= (producto.getId_producto() > 0) ? "Actualizar" : "Registrar" %></button>
      </form>
      <a href="<%= request.getContextPath() %>/products"><button type="button" class="btn btn-danger" style="margin-top: 20px; float: right;">Cancelar</button></a>
    </div>
  </div>
</div>
<jsp:include page="component/footer.jsp"></jsp:include>
