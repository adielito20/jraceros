package utp.edu.pe.jracero.servlet.trabajador;

import utp.edu.pe.jracero.dao.TrabajadorDao;
import utp.edu.pe.jracero.model.Trabajador;
import utp.edu.pe.jracero.model.enums.Rol;
import utp.edu.pe.jracero.service.Auth;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utp.edu.pe.jracero.util.ErrorLog;

import java.io.IOException;

@WebServlet(name = "addWorker", urlPatterns = {"/addWorker"})
public class AddWorkerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String nombre = req.getParameter("nombre");
        String apellido = req.getParameter("apellido");
        String dni = req.getParameter("dni");
        Rol rol = Rol.valueOf(req.getParameter("rol"));
        String telefono = req.getParameter("telefono");
        String correo = req.getParameter("correo");
        String contraseña = req.getParameter("pwd");
        TrabajadorDao trabajadorDao;

        try {
            trabajadorDao = new TrabajadorDao();
            trabajadorDao.createTrabajador(new Trabajador(nombre, apellido, dni, rol, telefono, correo, contraseña));
            trabajadorDao.close();

            ErrorLog.log("Trabajador creado con éxito", ErrorLog.Level.INFO);
            resp.sendRedirect(req.getContextPath() + "/workers");
        } catch (Exception e) {
            ErrorLog.log(e.getMessage(), ErrorLog.Level.ERROR);
            req.setAttribute("msg", e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
