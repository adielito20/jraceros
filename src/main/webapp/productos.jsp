<%@ page import="utp.edu.pe.jracero.model.Producto" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  // Verificar la sesión
  HttpSession ses = request.getSession(false);
  if (ses == null || ses.getAttribute("admin") == null) {
    response.sendRedirect("index.jsp");
    return;
  }
%>
<% List<Producto> productos = (List<Producto>) request.getAttribute("productos"); %>
<jsp:include page="component/header.jsp"></jsp:include>
<jsp:include page="component/sidebar.jsp"></jsp:include>
<jsp:include page="component/navbar.jsp"></jsp:include>
<div class="container-fluid">
  <div class="card">
    <div class="card-body">
      <div
              style="display: flex; justify-content: space-between; align-items: center; align-content: center; padding: 10px 20px;">
        <h5 style="font-weight: 600; font-size: 18px;">Productos</h5>
        <a href="<%= request.getContextPath() %>/addProductRedirect"> <button class="btn btn-success m-1">Registrar nuevo producto</button>
        </a>
      </div>
      <div class="table-responsive">
        <% if (productos != null && !productos.isEmpty()) { %>
        <table class="table table-striped table-hover">
          <thead>
            <tr>
              <th>ID</th>
              <th>Nombre</th>
              <th>Categoria</th>
              <th>Tipo</th>
              <th>Descripcion</th>
              <th>Precio</th>
              <th>imagen</th>
              <th>Acción</th>
            </tr>
          </thead>
          <tbody>
            <% for (Producto producto : productos) { %>
            <tr>
                <td><%= producto.getId_producto() %></td>
              <td><%= producto.getNombre() %></td>
              <td><%= producto.getCategoria().getNombre() %></td>
              <td><%= producto.getTipo() %></td>
              <td><%= producto.getDescripcion() %></td>
              <td><%= producto.getPrecio() %></td>
              <td><img src="image?img=<%= producto.getNombre() %>" alt="<%= producto.getNombre() %>" width="90px"></td>
              <td class="icons" style="display: flex; justify-content: center; align-items: center; align-content: center;">
                <a href="<%= request.getContextPath() %>/redirectProduct?id_producto=<%= producto.getId_producto() %>">
                  <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="icon" style="width: 21px; margin: 2px;">
                    <path stroke-linecap="round" stroke-linejoin="round" d="m16.862 4.487 1.687-1.688a1.875 1.875 0 1 1 2.652 2.652L10.582 16.07a4.5 4.5 0 0 1-1.897 1.13L6 18l.8-2.685a4.5 4.5 0 0 1 1.13-1.897l8.932-8.931Zm0 0L19.5 7.125M18 14v4.75A2.25 2.25 0 0 1 15.75 21H5.25A2.25 2.25 0 0 1 3 18.75V8.25A2.25 2.25 0 0 1 5.25 6H10" />
                  </svg>
                </a>
                <a href=<%= request.getContextPath() %>/deleteProduct?id_producto=<%= producto.getId_producto() %>>
                  <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="icon" style="width: 21px; margin: 2px;">
                    <path stroke-linecap="round" stroke-linejoin="round" d="m14.74 9-.346 9m-4.788 0L9.26 9m9.968-3.21c.342.052.682.107 1.022.166m-1.022-.165L18.16 19.673a2.25 2.25 0 0 1-2.244 2.077H8.084a2.25 2.25 0 0 1-2.244-2.077L4.772 5.79m14.456 0a48.108 48.108 0 0 0-3.478-.397m-12 .562c.34-.059.68-.114 1.022-.165m0 0a48.11 48.11 0 0 1 3.478-.397m7.5 0v-.916c0-1.18-.91-2.164-2.09-2.201a51.964 51.964 0 0 0-3.32 0c-1.18.037-2.09 1.022-2.09 2.201v.916m7.5 0a48.667 48.667 0 0 0-7.5 0" />
                  </svg>
                </a>
              </td>
            </tr>
            <% } %>
          </tbody>
          <% } else { %>
          <div class="alert alert-danger" role="alert">
            No hay productos registrados
          </div>
            <% } %>

      </div>
    </div>
  </div>
</div>
<jsp:include page="component/footer.jsp"></jsp:include>