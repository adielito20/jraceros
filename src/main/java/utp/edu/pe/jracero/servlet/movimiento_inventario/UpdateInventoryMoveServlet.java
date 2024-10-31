package utp.edu.pe.jracero.servlet.movimiento_inventario;

import utp.edu.pe.jracero.dao.InventarioDao;
import utp.edu.pe.jracero.dao.Movimiento_inventarioDao;
import utp.edu.pe.jracero.model.Movimiento_inventario;
import utp.edu.pe.jracero.model.enums.Tipo_movimiento;
import utp.edu.pe.jracero.util.ErrorLog;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "updateInventoryMove", urlPatterns = {"/updateInventoryMove"})
public class UpdateInventoryMoveServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id_movimiento_inventario = Integer.parseInt(req.getParameter("id_movimiento_inventario"));
        int nuevaCantidad = Integer.parseInt(req.getParameter("cantidad"));
        Tipo_movimiento nuevoTipoMovimiento = Tipo_movimiento.valueOf(req.getParameter("tipo_movimiento"));
        Movimiento_inventarioDao movimientoInventarioDao;
        InventarioDao inventarioDao;
        Movimiento_inventario movimiento;

        try {
            movimientoInventarioDao = new Movimiento_inventarioDao();
            inventarioDao = new InventarioDao();
            movimiento = movimientoInventarioDao.getMovimientoInventarioById(id_movimiento_inventario);

            if (movimiento == null) {
                ErrorLog.log("Movimiento de inventario no encontrado", ErrorLog.Level.WARN);
                req.setAttribute("msg", "Movimiento de inventario no encontrado");
                req.getRequestDispatcher("error.jsp").forward(req, resp);
            } else {
                int stockActual = inventarioDao.getStockByProducto(movimiento.getId_producto());
                if (movimiento.getTipo_movimiento() == Tipo_movimiento.INGRESO) {
                    stockActual -= movimiento.getCantidad();
                } else {
                    stockActual += movimiento.getCantidad();
                }
                if (nuevoTipoMovimiento == Tipo_movimiento.INGRESO) {
                    stockActual += nuevaCantidad;
                } else {
                    if (nuevaCantidad > stockActual) {
                        req.setAttribute("msg", "No hay suficiente stock para realizar la salida.");
                        ErrorLog.log("No se pudo actualizar el movimiento de inventario por falta de stock", ErrorLog.Level.WARN);
                        movimientoInventarioDao.close();
                        inventarioDao.close();
                        req.getRequestDispatcher("error.jsp").forward(req, resp);
                        return;
                    }
                    stockActual -= nuevaCantidad;
                }

                movimiento.setCantidad(nuevaCantidad);
                movimiento.setTipo_movimiento(nuevoTipoMovimiento);
                movimientoInventarioDao.updateMovimientoInventario(movimiento);
                inventarioDao.updateStockByProducto(movimiento.getId_producto(), stockActual);

                ErrorLog.log("Movimiento de inventario actualizado con éxito", ErrorLog.Level.INFO);
                movimientoInventarioDao.close();
                inventarioDao.close();
                req.getRequestDispatcher(req.getContextPath() + "/inventoryMoves").forward(req, resp);
            }
        } catch (Exception e) {
            ErrorLog.log(e.getMessage(), ErrorLog.Level.ERROR);
            req.setAttribute("msg", e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
