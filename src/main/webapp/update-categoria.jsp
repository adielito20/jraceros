<%@ page import="utp.edu.pe.jracero.model.Categoria" %>
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
  Categoria categoria;
    if (request.getAttribute("categoria") != null) {
        categoria = (Categoria) request.getAttribute("categoria");
    } else {
        categoria = new Categoria();
    }
%>
<jsp:include page="component/header.jsp"></jsp:include>
<jsp:include page="component/sidebar.jsp"></jsp:include>
<jsp:include page="component/navbar.jsp"></jsp:include>
<div class="container-fluid">
  <div class="card">
    <div class="card-body">
      <h5 class="card-title fw-semibold mb-4">Actualizar categoria</h5>
      <form method="post" action="<%= request.getContextPath() %>/updateCategory">
        <input type="hidden" name="id_categoria" value="<%= categoria.getId_categoria() %>">
        <div class="mb-3">
          <label for="nombre" class="form-label">Nombre</label>
          <input type="text" class="form-control" id="nombre" name="nombre" value="<%= categoria.getNombre() %>" required>
        </div>
        <button type="submit" class="btn btn-primary">Actualizar</button>
      </form>
      <a href="<%= request.getContextPath() %>/categories"><button type="button" class="btn btn-danger"
                                                                   style="margin-top: 20px; float: right;">Cancelar</button></a>
    </div>
  </div>
</div>
<jsp:include page="component/footer.jsp"></jsp:include>
