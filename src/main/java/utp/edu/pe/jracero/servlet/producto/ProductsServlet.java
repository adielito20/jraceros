package utp.edu.pe.jracero.servlet.producto;

import utp.edu.pe.jracero.dao.ProductoDao;
import utp.edu.pe.jracero.model.Producto;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utp.edu.pe.jracero.util.ErrorLog;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "products", urlPatterns = {"/products"})
public class ProductsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductoDao productoDao;
        List<Producto> productos;

        try {
            productoDao = new ProductoDao();
            productos = productoDao.getProductos();
            productoDao.close();
            req.setAttribute("productos", productos);
            productoDao.close();

            ErrorLog.log("Productos obtenidos con éxito", ErrorLog.Level.INFO);
            req.getRequestDispatcher("productos.jsp").forward(req, resp);
        } catch (Exception e) {
            ErrorLog.log(e.getMessage(), ErrorLog.Level.ERROR);
            req.setAttribute("msg", e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
