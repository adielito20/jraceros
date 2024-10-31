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

@WebServlet(name = "updateProvider", urlPatterns = {"/updateProvider"})
public class UpdateProviderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            req.setCharacterEncoding("UTF-8");
            int id_proveedor = Integer.parseInt(req.getParameter("id_proveedor"));
            String nombre_empresa = req.getParameter("empresa");
            String telefono = req.getParameter("telefono");
            String correo = req.getParameter("correo");
            String ruc = req.getParameter("ruc");

            try {
                ProveedorDao proveedorDao = new ProveedorDao();
                Proveedor proveedorActualizado = new Proveedor(id_proveedor, nombre_empresa, telefono, correo, ruc);
                proveedorDao.updateProveedor(proveedorActualizado);
                proveedorDao.close();
                ErrorLog.log("Proveedor actualizado con éxito", ErrorLog.Level.INFO);
                resp.sendRedirect(req.getContextPath() + "/providers");
            } catch (Exception e) {
                ErrorLog.log(e.getMessage(), ErrorLog.Level.ERROR);
                req.setAttribute("msg", e.getMessage());
                req.getRequestDispatcher("error.jsp").forward(req, resp);
            }
    }
}
