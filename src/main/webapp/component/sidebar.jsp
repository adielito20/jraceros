<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<nav class="sidebar-nav scroll-sidebar" data-simplebar="">
  <ul id="sidebarnav">
    <li class="nav-small-cap">
      <i class="ti ti-dots nav-small-cap-icon fs-4"></i>
      <span class="hide-menu">Ventas</span>
    </li>
    <li class="sidebar-item">
      <a class="sidebar-link" href="<%= request.getContextPath() %>/newSale" aria-expanded="false">
                                <span>
                                    <i class="ti ti-basket"></i>
                                </span>
        <span class="hide-menu">Nueva venta</span>
      </a>
    </li>
    <li class="sidebar-item">
      <a class="sidebar-link" href="<%= request.getContextPath() %>/sales" aria-expanded="false">
                                <span>
                                    <i class="ti ti-clipboard-data"></i>
                                </span>
        <span class="hide-menu">Listado</span>
      </a>
    </li>
    <li class="nav-small-cap">
      <i class="ti ti-dots nav-small-cap-icon fs-4"></i>
      <span class="hide-menu">Administrar</span>
    </li>
    <li class="sidebar-item">
      <a class="sidebar-link" href="<%= request.getContextPath() %>/products" aria-expanded="false">
                                <span>
                                    <i class="ti ti-building-factory-2"></i>
                                </span>
        <span class="hide-menu">Productos</span>
      </a>
    </li>
    <li class="sidebar-item">
      <a class="sidebar-link" href="<%= request.getContextPath() %>/providers" aria-expanded="false">
                                <span>
                                    <i class="ti ti-address-book"></i>
                                </span>
        <span class="hide-menu">Proveedores</span>
      </a>
    </li>
    <li class="sidebar-item">
      <a class="sidebar-link" href="<%= request.getContextPath() %>/clients" aria-expanded="false">
                                <span>
                                    <i class="ti ti-users"></i>
                                </span>
        <span class="hide-menu">Clientes</span>
      </a>
    </li>
    <li class="sidebar-item">
      <a class="sidebar-link" href="<%= request.getContextPath() %>/workers" aria-expanded="false">
                                <span>
                                    <i class="ti ti-sitemap"></i>
                                </span>
        <span class="hide-menu">Trabajadores</span>
      </a>
    </li>
    <li class="nav-small-cap">
      <i class="ti ti-dots nav-small-cap-icon fs-4"></i>
      <span class="hide-menu">Configuración</span>
    </li>
    <li class="sidebar-item">
      <a class="sidebar-link" href="<%= request.getContextPath() %>/categories" aria-expanded="false">
                                <span>
                                    <i class="ti ti-coin"></i>
                                </span>
        <span class="hide-menu">Categorias</span>
      </a>
    </li>
    <li class="nav-small-cap">
      <i class="ti ti-dots nav-small-cap-icon fs-4"></i>
      <span class="hide-menu">Inventario</span>
    </li>
    <li class="sidebar-item">
      <a class="sidebar-link" href="<%= request.getContextPath() %>/inventory" aria-expanded="false">
                                <span>
                                    <i class="ti ti-forklift"></i>
                                </span>
        <span class="hide-menu">Inventario</span>
      </a>
    </li>
    <li class="sidebar-item">
      <a class="sidebar-link" href="<%= request.getContextPath() %>/inventoryMoves" aria-expanded="false">
                                <span>
                                    <i class="ti ti-home-move"></i>
                                </span>
        <span class="hide-menu">Movimiento</span>
      </a>
    </li>
  </ul>
</nav>
<!-- End Sidebar navigation -->
</div>
<!-- End Sidebar scroll-->
</aside>