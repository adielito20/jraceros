package utp.edu.pe.jracero.servlet.trabajador;

import utp.edu.pe.jracero.dao.TrabajadorDao;

import javax.naming.NamingException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utp.edu.pe.jracero.util.ErrorLog;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "deleteWorker", urlPatterns = {"/deleteWorker"})
public class DeleteWorkerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id_trabajador = Integer.parseInt(req.getParameter("id_trabajador"));
        try {
            TrabajadorDao trabajadorDao = new TrabajadorDao();
            trabajadorDao.deleteTrabajador(id_trabajador);
            trabajadorDao.close();

            ErrorLog.log("Trabajador eliminado con éxito", ErrorLog.Level.INFO);
            resp.sendRedirect(req.getContextPath() + "/workers");
        } catch (SQLException | NamingException e) {
            ErrorLog.log(e.getMessage(), ErrorLog.Level.ERROR);
            req.setAttribute("msg", e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
