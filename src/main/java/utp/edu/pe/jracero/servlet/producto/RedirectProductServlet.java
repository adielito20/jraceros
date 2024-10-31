package utp.edu.pe.jracero.servlet.producto;

import utp.edu.pe.jracero.dao.CategoriaDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utp.edu.pe.jracero.dao.ProductoDao;
import utp.edu.pe.jracero.model.Producto;
import utp.edu.pe.jracero.util.ErrorLog;

import java.io.IOException;

@WebServlet(name = "redirectProduct", urlPatterns = {"/redirectProduct"})
public class RedirectProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        int id_producto = Integer.parseInt(req.getParameter("id_producto"));
        ProductoDao productoDao;
        CategoriaDao categoriaDao;
        Producto producto;

        try {
            productoDao = new ProductoDao();
            categoriaDao = new CategoriaDao();
            producto = productoDao.getProductoById(id_producto);
            productoDao.close();
            req.setAttribute("producto", producto);
            req.setAttribute("categorias", categoriaDao.getCategorias());
            categoriaDao.close();

            ErrorLog.log("Producto actualizado con éxito", ErrorLog.Level.INFO);
            req.getRequestDispatcher("update-producto.jsp").forward(req, resp);
        } catch (Exception e) {
            ErrorLog.log(e.getMessage(), ErrorLog.Level.ERROR);
            req.setAttribute("msg", e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
