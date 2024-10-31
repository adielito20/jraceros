package utp.edu.pe.jracero.servlet.movimiento_inventario;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utp.edu.pe.jracero.dao.Movimiento_inventarioDao;
import utp.edu.pe.jracero.model.Movimiento_inventario;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import utp.edu.pe.jracero.util.ErrorLog;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "inventoryMoves", urlPatterns = {"/inventoryMoves"})
public class InventoryMovesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Movimiento_inventario> movimientos;
        Movimiento_inventarioDao movimientoInventarioDao;

        try {
            movimientoInventarioDao = new Movimiento_inventarioDao();
            movimientos = movimientoInventarioDao.getMovimientosInventario();
            movimientoInventarioDao.close();
            req.setAttribute("movimientos", movimientos);
            req.getRequestDispatcher("movimiento.jsp").forward(req, resp);
        } catch (Exception e) {
            ErrorLog.log(e.getMessage(), ErrorLog.Level.ERROR);
            req.setAttribute("msg", e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
