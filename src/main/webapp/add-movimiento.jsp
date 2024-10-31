<%@ page import="utp.edu.pe.jracero.model.Producto" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="utp.edu.pe.jracero.model.Proveedor" %>
<%@ page import="utp.edu.pe.jracero.model.enums.Tipo_movimiento" %>
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
    List<Producto> productos;
    if (request.getAttribute("productos") != null) {
        productos = (List<Producto>) request.getAttribute("productos");
    } else {
        productos = new ArrayList<>();
    }

    List<Proveedor> proveedores;
    if (request.getAttribute("proveedores") != null) {
        proveedores = (List<Proveedor>) request.getAttribute("proveedores");
    } else {
        proveedores = new ArrayList<>();
    }

    List<Tipo_movimiento> tipos_movimiento = List.of(Tipo_movimiento.values());
%>
<jsp:include page="component/header.jsp"></jsp:include>
<jsp:include page="component/sidebar.jsp"></jsp:include>
<jsp:include page="component/navbar.jsp"></jsp:include>
<div class="container-fluid">
    <div class="card">
        <div class="card-body">
            <h5 class="card-title fw-semibold mb-4">Registrar nuevo movimiento de inventario</h5>
            <form method="post" action="<%= request.getContextPath() %>/addInventoryMoves">
                <div class="mb-3">
                    <label for="producto" class="form-label">Producto</label>
                    <select name="producto" class="form-control" id="producto" required>
                        <% if (!productos.isEmpty()) { %>
                        <% for (Producto producto : productos) { %>
                        <option value="<%= producto.getId_producto() %>"><%= producto.getNombre() %></option>
                        <% } %>
                        <% } else { %>
                        <option disabled selected>Debes registrar un producto antes de registrar un movimiento</option>
                        <% } %>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="proveedor" class="form-label">Proveedor</label>
                    <select name="proveedor" class="form-control" id="proveedor" required>
                        <% if (!proveedores.isEmpty()) { %>
                        <% for (Proveedor proveedor : proveedores) { %>
                        <option value="<%= proveedor.getId_proveedor() %>"><%= proveedor.getNombre_empresa() %></option>
                        <% } %>
                        <% } else { %>
                        <option disabled selected>Debes registrar un proveedor antes de registrar un movimiento</option>
                        <% } %>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="cantidad" class="form-label">Cantidad</label>
                    <input type="number" class="form-control" id="cantidad" name="cantidad" required>
                </div>
                <div class="mb-3">
                    <label for="tipo_movimiento" class="form-label">Tipo de Movimiento</label>
                    <select name="tipo_movimiento" class="form-control" id="tipo_movimiento" required>
                        <% for (Tipo_movimiento tipo_movimiento : tipos_movimiento) { %>
                        <option value="<%= tipo_movimiento.name() %>"><%= tipo_movimiento.name() %></option>
                        <% } %>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">Registrar</button>
            </form>
            <a href="movimiento.jsp"><button type="button" class="btn btn-danger" style="margin-top: 20px; float: right;">Cancelar</button></a>
        </div>
    </div>
</div>
<jsp:include page="component/footer.jsp"></jsp:include>