package utp.edu.pe.jracero.servlet.trabajador;

import utp.edu.pe.jracero.dao.TrabajadorDao;
import utp.edu.pe.jracero.model.Trabajador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utp.edu.pe.jracero.util.ErrorLog;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "workers", urlPatterns = {"/workers"})
public class WorkersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TrabajadorDao trabajadorDao;
        List<Trabajador> trabajadores;

        try {
            trabajadorDao = new TrabajadorDao();
            trabajadores = trabajadorDao.getTrabajadores();
            trabajadorDao.close();
            req.setAttribute("trabajadores", trabajadores);

            ErrorLog.log("Trabajadores obtenidos con éxito", ErrorLog.Level.INFO);
            req.getRequestDispatcher("trabajadores.jsp").forward(req, resp);
        } catch (Exception e) {
            ErrorLog.log(e.getMessage(), ErrorLog.Level.ERROR);
            req.setAttribute("msg", e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
