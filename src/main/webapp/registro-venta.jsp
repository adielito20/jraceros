<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
      <h5 class="card-title fw-semibold mb-4">Nueva venta</h5>

      <!-- Formulario de búsqueda -->
      <form class="d-flex align-items-center" action="search" method="post">
        <input type="text" class="form-control" placeholder="Buscar producto..." style="max-width: 250px; border-radius: 8px 0px 0px 8px;">
        <button type="submit" class="btn btn-primary ml-2 d-flex align-items-center justify-content-center" style="padding: 9px; border-radius: 0px 8px 8px 0px;">
          <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="size-6" style="width: 18px;">
            <path stroke-linecap="round" stroke-linejoin="round" d="m21 21-5.197-5.197m0 0A7.5 7.5 0 1 0 5.196 5.196a7.5 7.5 0 0 0 10.607 10.607Z" />
          </svg>
        </button>
      </form>

      <!-- Sección de Productos y Carrito -->
      <div class="container-productos-carrito">
        <!-- Productos -->
        <div class="productos">
          <h2>Productos</h2>
          <div class="producto-item">
            <p><strong>Producto:</strong> Lámina de metal</p>
            <p><strong>Precio:</strong> S/40</p>
            <div class="cantidad-control">
              <button onclick="decreaseQuantity()">-</button>
              <input type="text" id="quantity" value="1" readonly>
              <button onclick="increaseQuantity()">+</button>
            </div>
            <button class="agregar-btn" onclick="addToCart()">Agregar</button>
          </div>
        </div>

        <!-- Carrito -->
        <div class="carrito">
          <h2>Carrito</h2>
          <div class="carrito-item" id="cart-item">
            <p><strong>Producto:</strong> Lámina de metal</p>
            <p><strong>Cantidad:</strong> <span id="cart-quantity">1</span></p>
            <p><strong>Subtotal:</strong> S/ <span id="cart-subtotal">40</span></p>
          </div>
          <div class="carrito-resumen">
            <p><strong>Total:</strong> S/ <span id="cart-total">40</span></p>
          </div>
        </div>
      </div>
      <button class="btn btn-dark" style="margin-top: 20px; width: 100%;">Continuar compra</button>
    </div>
  </div>
</div>
<jsp:include page="component/footer.jsp"></jsp:include>