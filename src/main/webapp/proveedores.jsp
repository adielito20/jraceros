<%@ page import="utp.edu.pe.jracero.model.Proveedor" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
  // Verificar la sesión
  HttpSession ses = request.getSession(false);
  if (ses == null || ses.getAttribute("admin") == null) {
    response.sendRedirect("index.jsp");
    return;
  }
%>
<%
  List<Proveedor> proveedores;
  if (request.getAttribute("proveedores") != null) {
    proveedores = (List<Proveedor>) request.getAttribute("proveedores");
  } else {
    proveedores = new ArrayList<>();
  }
%>
<jsp:include page="component/header.jsp"></jsp:include>
<jsp:include page="component/sidebar.jsp"></jsp:include>
<jsp:include page="component/navbar.jsp"></jsp:include>
<div class="container-fluid">
  <div class="card">
    <div class="card-body">
      <div
              style="display: flex; justify-content: space-between; align-items: center; align-content: center; padding: 10px 20px;">
        <h5 style="font-weight: 600; font-size: 18px;">Proveedores</h5>
        <a href="add-proveedor.jsp"> <button class="btn btn-success m-1">Registrar nuevo proveedor</button></a>
      </div>
      <% if (!proveedores.isEmpty()) { %>
      <div class="table-responsive">
        <table class="table" style="text-align: center;">
          <thead>
          <tr>
            <th>Id</th>
            <th>Empresa</th>
            <th>Teléfono</th>
            <th>Correo</th>
            <th>RUC</th>
            <th>Acción</th>
          </tr>
          </thead>
          <tbody>
          <% for (Proveedor proveedor : proveedores) { %>
            <tr>
              <td><%= proveedor.getId_proveedor() %></td>
              <td><%= proveedor.getNombre_empresa() %></td>
              <td><%= proveedor.getTelefono() %></td>
              <td><%= proveedor.getCorreo() %></td>
              <td><%= proveedor.getRuc() %></td>
              <td class="icons" style="display: flex; justify-content: center; align-items: center; align-content: center;">
                <a href="<%= request.getContextPath() %>/redirectProvider?id_proveedor=<%= proveedor.getId_proveedor() %>">
                  <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="icon" style="width: 21px; margin: 2px;">
                    <path stroke-linecap="round" stroke-linejoin="round" d="m16.862 4.487 1.687-1.688a1.875 1.875 0 1 1 2.652 2.652L10.582 16.07a4.5 4.5 0 0 1-1.897 1.13L6 18l.8-2.685a4.5 4.5 0 0 1 1.13-1.897l8.932-8.931Zm0 0L19.5 7.125M18 14v4.75A2.25 2.25 0 0 1 15.75 21H5.25A2.25 2.25 0 0 1 3 18.75V8.25A2.25 2.25 0 0 1 5.25 6H10" />
                  </svg>
                </a>
                <a href=<%= request.getContextPath() %>//deleteProvider?id_proveedor=<%= proveedor.getId_proveedor() %>>
                  <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="icon" style="width: 21px; margin: 2px;">
                    <path stroke-linecap="round" stroke-linejoin="round" d="M6 18L18 6M6 6l12 12" />
                  </svg>
                </a>
              </td>
            </tr>
            <% } %>
          </tr>
          </tbody>
        </table>
      </div>
        <% } else { %>
        <div class="alert alert-warning" role="alert">
          No hay proveedores registrados
        </div>
        <% } %>
    </div>
  </div>
</div>
<jsp:include page="component/footer.jsp"></jsp:include>