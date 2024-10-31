package utp.edu.pe.jracero.servlet.movimiento_inventario;

import utp.edu.pe.jracero.dao.InventarioDao;
import utp.edu.pe.jracero.dao.Movimiento_inventarioDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utp.edu.pe.jracero.model.Movimiento_inventario;
import utp.edu.pe.jracero.model.enums.Tipo_movimiento;
import utp.edu.pe.jracero.util.ErrorLog;

import java.io.IOException;

@WebServlet(name = "deleteInventoryMoves", urlPatterns = {"/deleteInventoryMoves"})
public class DeleteInventoryMovesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id_movimiento_inventario = Integer.parseInt(req.getParameter("id_movimiento_inventario"));
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
                if (movimiento.getTipo_movimiento() == Tipo_movimiento.INGRESO) {
                    if (movimiento.getCantidad() > inventarioDao.getStockByProducto(movimiento.getId_producto())) {
                        req.setAttribute("msg", "No hay suficiente stock en el producto");
                        ErrorLog.log("Movimiento de inventario no eliminado por falta de stock", ErrorLog.Level.WARN);
                        movimientoInventarioDao.close();
                        inventarioDao.close();
                        req.getRequestDispatcher("error.jsp").forward(req, resp);
                    } else {
                        movimientoInventarioDao.deleteMovimientoInventario(id_movimiento_inventario);
                        inventarioDao.updateStockByProducto(movimiento.getId_producto(), inventarioDao.getStockByProducto(movimiento.getId_producto()) - movimiento.getCantidad());
                        ErrorLog.log("Movimiento de inventario eliminado con éxito", ErrorLog.Level.INFO);
                        movimientoInventarioDao.close();
                        inventarioDao.close();
                        req.getRequestDispatcher(req.getContextPath() + "/inventoryMoves").forward(req, resp);
                    }
                } else {
                    movimientoInventarioDao.deleteMovimientoInventario(id_movimiento_inventario);
                    inventarioDao.updateStockByProducto(movimiento.getId_producto(), inventarioDao.getStockByProducto(movimiento.getId_producto()) + movimiento.getCantidad());
                    ErrorLog.log("Movimiento de inventario eliminado con éxito", ErrorLog.Level.INFO);
                    movimientoInventarioDao.close();
                    inventarioDao.close();
                    req.getRequestDispatcher(req.getContextPath() + "/inventoryMoves").forward(req, resp);
                }
            }
            movimientoInventarioDao.close();
            inventarioDao.close();
        } catch (Exception e) {
            ErrorLog.log(e.getMessage(), ErrorLog.Level.ERROR);
            req.setAttribute("msg", e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
