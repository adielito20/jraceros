package utp.edu.pe.jracero.servlet.movimiento_inventario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utp.edu.pe.jracero.dao.Movimiento_inventarioDao;
import utp.edu.pe.jracero.util.ErrorLog;

import java.io.IOException;

@WebServlet(name = "redirectInvtoryMove", urlPatterns = {"/redirectInvtoryMove"})
public class RedirectInvtoryMoveServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id_movimiento = Integer.parseInt(req.getParameter("id_movimiento"));
        Movimiento_inventarioDao movimiento_inventarioDao;

        try {
            movimiento_inventarioDao = new Movimiento_inventarioDao();
            req.setAttribute("movimiento", movimiento_inventarioDao.getMovimientoInventarioById(id_movimiento));
            movimiento_inventarioDao.close();

            ErrorLog.log("Movimiento redireccionado con éxito", ErrorLog.Level.INFO);
            req.getRequestDispatcher("update-movimiento-inventario.jsp").forward(req, resp);
        } catch (Exception e) {
            ErrorLog.log(e.getMessage(), ErrorLog.Level.ERROR);
            req.setAttribute("msg", e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
