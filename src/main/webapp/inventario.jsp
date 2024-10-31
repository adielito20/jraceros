<%@ page import="utp.edu.pe.jracero.model.Inventario" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
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
  List<Inventario> inventarios;
    if (request.getAttribute("inventarios") != null) {
        inventarios = (List<Inventario>) request.getAttribute("inventarios");
    } else {
        inventarios = new ArrayList<>();
    }
%>
<jsp:include page="component/header.jsp"></jsp:include>
<jsp:include page="component/sidebar.jsp"></jsp:include>
<jsp:include page="component/navbar.jsp"></jsp:include>
<div class="container-fluid">
  <div class="card">
    <div class="card-body">
      <h5 class="card-title fw-semibold mb-4">Inventario</h5>
      <% if (!inventarios.isEmpty()) { %>
      <div class="table-responsive">
        <table class="table" style="text-align: center;">
          <thead>
          <tr>
            <th>Producto</th>
            <th>Stock</th>
          </tr>
          </thead>
          <tbody>
          <% for (Inventario inventario : inventarios) { %>
            <tr>
                <td><%= inventario.getProducto().getNombre() %></td>
                <td><%= inventario.getStock() %></td>
            </tr>
            <% } %>
          </tbody>
        </table>
      </div>
        <% } else { %>
        <div class="alert alert-warning" role="alert">
          No hay registros disponibles en el inventario.
        </div>
        <% } %>
    </div>
  </div>
</div>
<jsp:include page="component/footer.jsp"></jsp:include>