package utp.edu.pe.jracero.servlet.movimiento_inventario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utp.edu.pe.jracero.dao.ClienteDao;
import utp.edu.pe.jracero.dao.ProductoDao;
import utp.edu.pe.jracero.dao.ProveedorDao;
import utp.edu.pe.jracero.dao.TrabajadorDao;
import utp.edu.pe.jracero.model.Cliente;
import utp.edu.pe.jracero.model.Producto;
import utp.edu.pe.jracero.model.Proveedor;
import utp.edu.pe.jracero.model.Trabajador;
import utp.edu.pe.jracero.util.ErrorLog;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "addInventoryMoveRedirect", urlPatterns = {"/addInventoryMoveRedirect"})
public class AddInventoryMoveRedirectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ClienteDao clienteDao;
        TrabajadorDao trabajadorDao;
        ProveedorDao proveedorDao;
        ProductoDao productoDao;
        List<Cliente> clientes;
        List<Trabajador> trabajadores;
        List<Proveedor> proveedores;
        List<Producto> productos;

        try {
            clienteDao = new ClienteDao();
            trabajadorDao = new TrabajadorDao();
            proveedorDao = new ProveedorDao();
            productoDao = new ProductoDao();
            clientes = clienteDao.getClientes();
            trabajadores = trabajadorDao.getTrabajadores();
            proveedores = proveedorDao.getProveedores();
            productos = productoDao.getProductos();
            req.setAttribute("clientes", clientes);
            req.setAttribute("trabajadores", trabajadores);
            req.setAttribute("proveedores", proveedores);
            req.setAttribute("productos", productos);

            clienteDao.close();
            trabajadorDao.close();
            proveedorDao.close();
            productoDao.close();
            ErrorLog.log("Movimiento de inventario redireccionado con éxito", ErrorLog.Level.INFO);
            req.getRequestDispatcher("add-movimiento.jsp").forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("msg", e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
