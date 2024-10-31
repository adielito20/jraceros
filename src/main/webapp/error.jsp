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
      <h5 class="card-title fw-semibold mb-4">Error</h5>
      <c:if test="${not empty requestScope.msg}">
        <div class="alert alert-danger" role="alert">
            ${requestScope.msg}
        </div>
      </c:if>
    </div>
  </div>
</div>
<jsp:include page="component/footer.jsp"></jsp:include>