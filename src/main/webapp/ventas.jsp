<%@ page import="utp.edu.pe.jracero.model.Venta" %>
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
<% List<Venta> ventas;
    if (request.getAttribute("ventas") != null) {
        ventas = (List<Venta>) request.getAttribute("ventas");
    } else {
        ventas = new ArrayList<>();
    }
%>
<jsp:include page="component/header.jsp"></jsp:include>
<jsp:include page="component/sidebar.jsp"></jsp:include>
<jsp:include page="component/navbar.jsp"></jsp:include>
<div class="container-fluid">
    <div class="card">
        <div class="card-body">
            <h5 class="card-title fw-semibold mb-4">Ventas</h5>
            <% if (!ventas.isEmpty()) { %>
            <div class="table-responsive">
                <table class="table" style="text-align: center;">
                    <thead>
                    <tr>
                        <th>Id</th>
                        <th>Cliente</th>
                        <th>Trabajador</th>
                        <th>Método de pago</th>
                        <th>Fecha</th>
                        <th>IGV</th>
                        <th>Total</th>
                        <th>Detalles</th>
                    </tr>
                    </thead>
                    <tbody>
                    <% for (Venta venta : ventas) { %>
                    <tr>
                        <td><%= venta.getId_venta() %></td>
                        <td><%= venta.getCliente().getNombre() + " " + venta.getCliente().getApellido() %></td>
                        <td><%= venta.getTrabajador().getNombre() + " " + venta.getTrabajador().getApellido() %></td>
                        <td><%= venta.getMetodo_pago() %></td>
                        <td><%= venta.getFecha() %></td>
                        <td><%= venta.getIgv() %></td>
                        <td><%= venta.getTotal() %></td>
                        <td>
                            <a href="<%= request.getContextPath() %>/detalleVenta?venta_id=<%= venta.getId_venta() %>"><button type="button" class="btn btn-warning m-1"
                                               style="justify-self: center; margin-bottom: 5px; align-self: center; font-size: 16px;">Detalle</button></a>
                        </td>
                    </tr>
                    <% } %>
                    </tbody>
                </table>
            </div>
            <% } else { %>
            <div class="alert alert-warning" role="alert">
                No hay ventas registradas
            </div>
            <% } %>
        </div>
    </div>
</div>
<jsp:include page="component/footer.jsp"></jsp:include>