package utp.edu.pe.jracero.servlet.proveedor;

import utp.edu.pe.jracero.dao.ProveedorDao;
import utp.edu.pe.jracero.model.Proveedor;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utp.edu.pe.jracero.util.ErrorLog;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "providers", urlPatterns = {"/providers"})
public class ProvidersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProveedorDao proveedorDao;
        List<Proveedor> proveedores;

        try {
            proveedorDao = new ProveedorDao();
            proveedores = proveedorDao.getProveedores();
            proveedorDao.close();
            req.setAttribute("proveedores", proveedores);

            ErrorLog.log("Proveedores obtenidos con éxito", ErrorLog.Level.INFO);
            req.getRequestDispatcher( "proveedores.jsp").forward(req, resp);
        } catch (Exception e) {
            ErrorLog.log(e.getMessage(), ErrorLog.Level.ERROR);
            req.setAttribute("msg", e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
