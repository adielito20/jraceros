package utp.edu.pe.jracero.servlet.proveedor;

import utp.edu.pe.jracero.dao.ProveedorDao;

import javax.naming.NamingException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utp.edu.pe.jracero.util.ErrorLog;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "redirectProvider", urlPatterns = {"/redirectProvider"})
public class RedirectProviderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id_proveedor = Integer.parseInt(req.getParameter("id_proveedor"));
        ProveedorDao proveedorDao;

        try {
            proveedorDao = new ProveedorDao();
            req.setAttribute("proveedor", proveedorDao.getProveedorById(id_proveedor));
            proveedorDao.close();

            ErrorLog.log("Proveedor redireccionado con éxito", ErrorLog.Level.INFO);
            req.getRequestDispatcher("update-proveedor.jsp").forward(req, resp);
        } catch (SQLException | NamingException e) {
            ErrorLog.log(e.getMessage(), ErrorLog.Level.ERROR);
            req.setAttribute("msg", e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
