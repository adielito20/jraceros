package utp.edu.pe.jracero.servlet.venta;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utp.edu.pe.jracero.dao.Detalle_ventaDao;
import utp.edu.pe.jracero.dao.InventarioDao;
import utp.edu.pe.jracero.dao.ProductoDao;
import utp.edu.pe.jracero.dao.VentaDao;
import utp.edu.pe.jracero.model.Detalle_venta;
import utp.edu.pe.jracero.model.Producto;
import utp.edu.pe.jracero.model.Trabajador;
import utp.edu.pe.jracero.model.Venta;
import utp.edu.pe.jracero.model.enums.Metodo_pago;
import utp.edu.pe.jracero.model.enums.Tipo_comprobante;
import utp.edu.pe.jracero.util.ErrorLog;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@WebServlet(name = "newVenta", urlPatterns = {"/newVenta"})
public class NewSaleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id_cliente = Integer.parseInt(req.getParameter("cliente"));
        Metodo_pago metodoPago = Metodo_pago.valueOf(req.getParameter("metodo_pago"));
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String fechaHoraActual = LocalDateTime.now().format(fmt);
        LocalDateTime fecha = LocalDateTime.parse(fechaHoraActual);
        HttpSession session = req.getSession();
        Trabajador trabajador = (Trabajador) session.getAttribute("admin");
        Tipo_comprobante tipoComprobante = Tipo_comprobante.valueOf(req.getParameter("tipo_comprobante"));
        Map<Integer, Integer> car = (Map<Integer, Integer>) session.getAttribute("car");
        VentaDao ventaDao;
        InventarioDao inventarioDao;
        ProductoDao productoDao;
        Detalle_ventaDao detalleVentaDao;
        double total = Float.parseFloat(req.getParameter("total"));

        try {
            ventaDao = new VentaDao();
            inventarioDao = new InventarioDao();
            detalleVentaDao = new Detalle_ventaDao();
            productoDao = new ProductoDao();

            if (car != null && !car.isEmpty()) {
                ventaDao.createVenta(new Venta(id_cliente, trabajador.getId_trabajador(), metodoPago, fecha, tipoComprobante, total * 0.18, total));
                int id_venta = ventaDao.getLasId();
                for (Map.Entry<Integer, Integer> entry : car.entrySet()) {
                    Producto producto = productoDao.getProductoById(entry.getKey());
                    Integer quantity = entry.getValue();
                    detalleVentaDao.createDetalleVenta(new Detalle_venta(id_venta, producto.getId_producto(), quantity, producto.getPrecio(), producto.getPrecio() * quantity));

                    inventarioDao.updateStockByProducto(producto.getId_producto(), inventarioDao.getStockByProducto(producto.getId_producto() + quantity));

                    ErrorLog.log("Venta registrada correctamente", ErrorLog.Level.INFO);
                    req.getRequestDispatcher(req.getContextPath() + "/sales").forward(req, resp);
                }
            } else {
                ErrorLog.log("No hay productos en el carrito", ErrorLog.Level.ERROR);
                req.setAttribute("msg", "No hay productos en el carrito");
                req.getRequestDispatcher("error.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            ErrorLog.log(e.getMessage(), ErrorLog.Level.ERROR);
            req.setAttribute("msg", e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
