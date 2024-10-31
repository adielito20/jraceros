package utp.edu.pe.jracero.servlet.producto;

import utp.edu.pe.jracero.dao.ProductoDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utp.edu.pe.jracero.util.ErrorLog;

import java.io.IOException;

@WebServlet(name = "deleteProduct", urlPatterns = {"/deleteProduct"})
public class DeleteProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id_producto = Integer.parseInt(req.getParameter("id_producto"));
        ProductoDao productoDao;

        try {
            productoDao = new ProductoDao();
            productoDao.deleteProducto(id_producto);
            productoDao.close();

            ErrorLog.log("Producto eliminado con éxito", ErrorLog.Level.INFO);
            resp.sendRedirect(req.getContextPath() + "/products");
        } catch (Exception e) {
            ErrorLog.log(e.getMessage(), ErrorLog.Level.ERROR);
            req.setAttribute("msg", e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
