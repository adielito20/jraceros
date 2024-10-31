<%@ page import="utp.edu.pe.jracero.model.Categoria" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="utp.edu.pe.jracero.model.enums.Tipo_producto" %>
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
%>
<jsp:include page="component/header.jsp"></jsp:include>
<jsp:include page="component/sidebar.jsp"></jsp:include>
<jsp:include page="component/navbar.jsp"></jsp:include>
<div class="container-fluid">
    <div class="card">
        <div class="card-body">
            <h5 class="card-title fw-semibold mb-4">Registrar nuevo producto</h5>
            <form method="post" action="<%= request.getContextPath() %>/addProduct" enctype="multipart/form-data">
                <div class="mb-3">
                    <label for="nombre" class="form-label">Ingresa el nombre</label>
                    <input type="text" class="form-control" id="nombre" name="nombre" required>
                </div>
                <div class="mb-3">
                    <label for="categoria" class="form-label">Selecciona la categoría</label>
                    <select name="categoria" class="form-control" id="categoria">
                        <% if (!categorias.isEmpty()) { %>
                        <% for (Categoria categoria : categorias) { %>
                        <option value="<%= categoria.getId_categoria() %>"><%= categoria.getNombre() %></option>
                        <% } %> <% } else { %>
                        <option disabled selected>Debes registrar una categoria antes de registrar un producto</option>
                        <% } %>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="tipo" class="form-label">Selecciona el tipo</label>
                    <select name="tipo" class="form-control" id="tipo" required>
                        <% for (Tipo_producto tipo : Tipo_producto.values()) { %>
                        <option value="<%= tipo %>"><%= tipo %></option>
                        <% } %>
                    </select>
                </div>

                <div class="mb-3">
                    <label for="descripcion" class="form-label">Ingresa la descripción</label>
                    <input type="text" class="form-control" id="descripcion" name="descripcion" required>
                </div>
                <div class="mb-3">
                    <label for="precio" class="form-label">Ingresa el precio</label>
                    <input type="text" class="form-control" id="precio" name="precio"
                           aria-describedby="emailHelp" required>
                </div>
                <div class="mb-3">
                    <label for="image" class="form-label">Ingresa la imagen</label>
                    <input type="file" id="image" name="image" required>
                </div>
                <button type="submit" class="btn btn-primary">Registrar</button>
            </form>
            <a href="<%= request.getContextPath() %>/products"><button type="submit" class="btn btn-danger" style="margin-top: 20px; float: right;">Cancelar</button></a>
        </div>
    </div>
</div>
<jsp:include page="component/footer.jsp"></jsp:include>