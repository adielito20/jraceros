package utp.edu.pe.jracero.servlet.trabajador;

import utp.edu.pe.jracero.dao.ProveedorDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utp.edu.pe.jracero.dao.TrabajadorDao;
import utp.edu.pe.jracero.util.ErrorLog;

import java.io.IOException;

@WebServlet(name = "updateWorkerRedirect", urlPatterns = {"/updateWorkerRedirect"})
public class UpdateWorkerRedirectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        int id_trabajador = Integer.parseInt(req.getParameter("id_trabajador"));
        TrabajadorDao trabajadorDao;

        try {
            trabajadorDao = new TrabajadorDao();
            req.setAttribute("trabajador", trabajadorDao.getTrabajadorById(id_trabajador));

            trabajadorDao.close();
            ErrorLog.log("Trabajador actualizado con éxito", ErrorLog.Level.INFO);
            req.getRequestDispatcher("update-trabajador.jsp").forward(req, resp);
        } catch (Exception e) {
            ErrorLog.log(e.getMessage(), ErrorLog.Level.ERROR);
            req.setAttribute("msg", e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
