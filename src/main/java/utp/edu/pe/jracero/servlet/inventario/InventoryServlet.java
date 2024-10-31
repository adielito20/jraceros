package utp.edu.pe.jracero.servlet.inventario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utp.edu.pe.jracero.dao.InventarioDao;
import utp.edu.pe.jracero.model.Inventario;
import utp.edu.pe.jracero.util.ErrorLog;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "inventory", urlPatterns = {"/inventory"})
public class InventoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        InventarioDao inventarioDao;
        List<Inventario> inventarios;

        try {
            inventarioDao = new InventarioDao();
            inventarios = inventarioDao.getInventarios();
            inventarioDao.close();
            req.setAttribute("inventarios", inventarios);

            ErrorLog.log("Inventarios cargados con éxito", ErrorLog.Level.INFO);
            req.getRequestDispatcher("inventario.jsp").forward(req, resp);
        } catch (Exception e) {
            ErrorLog.log(e.getMessage(), ErrorLog.Level.ERROR);
            req.setAttribute("msg", e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
