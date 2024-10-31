package utp.edu.pe.jracero.servlet.proveedor;

import utp.edu.pe.jracero.dao.ProveedorDao;
import utp.edu.pe.jracero.model.Proveedor;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utp.edu.pe.jracero.util.ErrorLog;

import javax.naming.NamingException;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "addProvider", urlPatterns = {"/addProvider"})
public class AddProviderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String nombre_empresa = req.getParameter("empresa");
        String telefono = req.getParameter("telefono");
        String correo = req.getParameter("correo");
        String ruc = req.getParameter("ruc");
        ProveedorDao proveedorDao;

        try {
            proveedorDao = new ProveedorDao();
            proveedorDao.createProveedor(new Proveedor(nombre_empresa, telefono, correo, ruc));
            System.out.println(new Proveedor(nombre_empresa, telefono, correo, ruc));
            proveedorDao.close();

            ErrorLog.log("Proveedor creado con éxito", ErrorLog.Level.INFO);
            resp.sendRedirect(req.getContextPath() + "/providers");
        } catch (SQLException | NamingException e) {
            ErrorLog.log(e.getMessage(), ErrorLog.Level.ERROR);
            req.setAttribute("msg", e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
