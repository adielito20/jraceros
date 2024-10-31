package utp.edu.pe.jracero.servlet.movimiento_inventario;

import utp.edu.pe.jracero.dao.InventarioDao;
import utp.edu.pe.jracero.dao.Movimiento_inventarioDao;
import utp.edu.pe.jracero.model.Movimiento_inventario;
import utp.edu.pe.jracero.model.enums.Tipo_movimiento;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utp.edu.pe.jracero.util.ErrorLog;

import javax.naming.NamingException;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

@WebServlet(name = "addInventoryMoves", urlPatterns = {"/addInventoryMoves"})
public class AddInventoryMoveServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Obtener y validar los parámetros
        String productoParam = req.getParameter("producto");
        String trabajadorParam = req.getParameter("trabajador");
        String proveedorParam = req.getParameter("proveedor");
        String cantidadParam = req.getParameter("cantidad");
        String tipoMovimientoParam = req.getParameter("tipo_movimiento");

        if (productoParam == null || trabajadorParam == null || proveedorParam == null || cantidadParam == null || tipoMovimientoParam == null) {
            req.setAttribute("msg", "Por favor, complete todos los campos antes de enviar el formulario.");
            req.getRequestDispatcher("error.jsp").forward(req, resp);
            return;
        }

        try {
            // Convertir los parámetros a los tipos adecuados
            int id_producto = Integer.parseInt(productoParam);
            int id_trabajador = Integer.parseInt(trabajadorParam);
            int id_proveedor = Integer.parseInt(proveedorParam);
            int cantidad = Integer.parseInt(cantidadParam);
            Tipo_movimiento tipo_movimiento = Tipo_movimiento.valueOf(tipoMovimientoParam);

            Movimiento_inventarioDao movimientoInventarioDao = new Movimiento_inventarioDao();
            InventarioDao inventarioDao = new InventarioDao();

            if (tipo_movimiento == Tipo_movimiento.INGRESO) {
                movimientoInventarioDao.createMovimientoInventario(new Movimiento_inventario(id_producto, id_trabajador, id_proveedor, cantidad, tipo_movimiento, LocalDateTime.now()));
                inventarioDao.updateStockByProducto(id_producto, inventarioDao.getStockByProducto(id_producto) + cantidad);
            } else {
                if (inventarioDao.getStockByProducto(id_producto) < cantidad) {
                    req.setAttribute("msg", "No hay suficiente stock en el producto.");
                    movimientoInventarioDao.close();
                    inventarioDao.close();
                    req.getRequestDispatcher("error.jsp").forward(req, resp);
                    return;
                } else {
                    movimientoInventarioDao.createMovimientoInventario(new Movimiento_inventario(id_producto, id_trabajador, id_proveedor, cantidad, tipo_movimiento, LocalDateTime.now()));
                    inventarioDao.updateStockByProducto(id_producto, inventarioDao.getStockByProducto(id_producto) - cantidad);
                }
            }

            movimientoInventarioDao.close();
            inventarioDao.close();
            req.getRequestDispatcher(req.getContextPath() + "/inventoryMoves").forward(req, resp);
        } catch (NumberFormatException | SQLException | NamingException e) {
            ErrorLog.log(e.getMessage(), ErrorLog.Level.ERROR);
            req.setAttribute("msg", e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
