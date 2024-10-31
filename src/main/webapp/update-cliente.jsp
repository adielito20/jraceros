<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="utp.edu.pe.jracero.model.Cliente" %>
<%@ page import="utp.edu.pe.jracero.model.enums.Tipo_documento" %>
<%
    // Verificar la sesión
    HttpSession ses = request.getSession(false);
    if (ses == null || ses.getAttribute("admin") == null) {
        response.sendRedirect("index.jsp");
        return;
    }

    Cliente cliente;
    if (request.getAttribute("cliente") != null) {
        cliente = (Cliente) request.getAttribute("cliente");
    } else {
        cliente = new Cliente();
    }
%>
<jsp:include page="component/header.jsp"></jsp:include>
<jsp:include page="component/sidebar.jsp"></jsp:include>
<jsp:include page="component/navbar.jsp"></jsp:include>
<div class="container-fluid">
    <div class="card">
        <div class="card-body">
            <h5 class="card-title fw-semibold mb-4">Actualizar cliente</h5>
            <form method="post" action="<%= request.getContextPath() %>/updateCliente">
                <input type="hidden" name="id_cliente" value="<%= cliente.getId_cliente() %>">
                <div class="mb-3">
                    <label for="nombre" class="form-label">Nombre</label>
                    <input type="text" class="form-control" id="nombre" name="nombre" value="<%= cliente.getNombre() %>" required>
                </div>
                <div class="mb-3">
                    <label for="apellido" class="form-label">Apellido</label>
                    <input type="text" class="form-control" id="apellido" name="apellido" value="<%= cliente.getApellido() %>" required>
                </div>
                <div class="mb-3">
                    <label for="telefono" class="form-label">Teléfono</label>
                    <input type="tel" class="form-control" id="telefono" name="telefono" value="<%= cliente.getTelefono() %>" required>
                </div>
                <div class="mb-3">
                    <label for="correo" class="form-label">Correo Electrónico</label>
                    <input type="email" class="form-control" id="correo" name="correo" value="<%= cliente.getCorreo() %>" required>
                </div>
                <div class="mb-3">
                    <label for="tipoDocumento" class="form-label">Tipo de Documento</label>
                    <select name="tipo_documento" class="form-control" id="tipoDocumento" required>
                        <% for (Tipo_documento tipo : Tipo_documento.values()) { %>
                        <option value="<%= tipo.name() %>" <%= (cliente.getTipo_documento() != null && cliente.getTipo_documento().name().equals(tipo.name())) ? "selected" : "" %>><%= tipo.name() %></option>
                        <% } %>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="numeroDocumento" class="form-label">Número de Documento</label>
                    <input type="text" class="form-control" id="numeroDocumento" name="numero_documento" value="<%= cliente.getNumero_documento() %>" required>
                </div>
                <button type="submit" class="btn btn-primary">Actualizar</button>
            </form>
            <a href="<%= request.getContextPath() %>/clientes.jsp"><button type="button" class="btn btn-danger" style="margin-top: 20px; float: right;">Cancelar</button></a>
        </div>
    </div>
</div>
<jsp:include page="component/footer.jsp"></jsp:include>
