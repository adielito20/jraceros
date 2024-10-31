package utp.edu.pe.jracero.servlet.producto;

import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.Part;
import utp.edu.pe.jracero.dao.ProductoDao;
import utp.edu.pe.jracero.model.Producto;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utp.edu.pe.jracero.model.enums.Tipo_producto;
import utp.edu.pe.jracero.util.ErrorLog;
import utp.edu.pe.jracero.util.UTPBinary;

import java.io.IOException;

@WebServlet(name = "addProduct", urlPatterns = {"/addProduct"})
@MultipartConfig
public class AddProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String nombre = req.getParameter("nombre");
        int id_categoria = Integer.parseInt(req.getParameter("categoria"));
        Tipo_producto tipo = Tipo_producto.valueOf(req.getParameter("tipo"));
        String descripcion = req.getParameter("descripcion");
        double precio = Double.parseDouble(req.getParameter("precio"));
        Part filePart = req.getPart("image");
        ProductoDao productoDao;

        try {
            productoDao = new ProductoDao();
            Producto nuevoProducto = new Producto(nombre, tipo, descripcion, precio, id_categoria);
            productoDao.createProducto(nuevoProducto);
            productoDao.close();

            if (filePart != null && filePart.getSize() > 0) {
                byte[] fileContent = filePart.getInputStream().readAllBytes();
                UTPBinary.echobin(fileContent, "/tmp/" + nombre);
            }

            ErrorLog.log("Producto creado con éxito", ErrorLog.Level.INFO);
            resp.sendRedirect(req.getContextPath() + "/products");
        } catch (Exception e) {
            ErrorLog.log(e.getMessage(), ErrorLog.Level.ERROR);
            req.setAttribute("msg", e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
