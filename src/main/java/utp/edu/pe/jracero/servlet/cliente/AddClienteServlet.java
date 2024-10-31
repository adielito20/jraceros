package utp.edu.pe.jracero.servlet.cliente;

import utp.edu.pe.jracero.dao.ClienteDao;
import utp.edu.pe.jracero.model.Cliente;

import javax.naming.NamingException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utp.edu.pe.jracero.model.enums.Tipo_documento;
import utp.edu.pe.jracero.util.ErrorLog;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "addCliente", urlPatterns = {"/addCliente"})
public class AddClienteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String nombre = req.getParameter("nombre");
        String apellido = req.getParameter("apellido");
        String telefono = req.getParameter("telefono");
        String correo = req.getParameter("correo");
        Tipo_documento tipo_documento = Tipo_documento.valueOf(req.getParameter("tipo_documento"));
        String numero_documento = req.getParameter("numero_documento");
        ClienteDao clienteDao;

        try {
            clienteDao = new ClienteDao();
            clienteDao.createCliente(new Cliente(nombre, apellido, telefono, correo, tipo_documento, numero_documento));
            clienteDao.close();

            ErrorLog.log("Cliente creado con éxito", ErrorLog.Level.INFO);
            resp.sendRedirect(req.getContextPath() + "/clients");
        } catch (SQLException | NamingException e) {
            ErrorLog.log(e.getMessage(), ErrorLog.Level.ERROR);
            req.setAttribute("msg", e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
