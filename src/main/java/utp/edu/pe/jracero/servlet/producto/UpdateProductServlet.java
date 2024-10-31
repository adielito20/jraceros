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
import utp.edu.pe.jracero.util.TextUTP;
import utp.edu.pe.jracero.util.UTPBinary;

import java.io.IOException;

@WebServlet(name = "updateProduct", urlPatterns = {"/updateProduct"})
@MultipartConfig
public class UpdateProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        int id_producto = Integer.parseInt(req.getParameter("id_producto"));
        String nombre = req.getParameter("nombre");
        int id_categoria = Integer.parseInt(req.getParameter("categoria"));
        Tipo_producto tipo = (Tipo_producto.valueOf(req.getParameter("tipo")));
        String descripcion = req.getParameter("descripcion");
        double precio = Double.parseDouble(req.getParameter("precio"));
        Part filePart = req.getPart("image");
        ProductoDao productoDao;

        try {
            productoDao = new ProductoDao();
            productoDao.updateProducto(new Producto(id_producto, nombre, tipo, descripcion, precio, id_categoria));
            productoDao.close();

            byte[] fileContent = filePart.getInputStream().readAllBytes();
            TextUTP.clear("/tmp/" + nombre);
            UTPBinary.echobin(fileContent, "/tmp/" + nombre);

            ErrorLog.log("Producto actualizado con éxito", ErrorLog.Level.INFO);
            resp.sendRedirect(req.getContextPath() + "/products");
        } catch (Exception e) {
            ErrorLog.log(e.getMessage(), ErrorLog.Level.ERROR);
            req.setAttribute("msg", e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
