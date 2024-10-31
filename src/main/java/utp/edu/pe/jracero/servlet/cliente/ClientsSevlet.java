package utp.edu.pe.jracero.servlet.cliente;

import utp.edu.pe.jracero.dao.ClienteDao;
import utp.edu.pe.jracero.model.Cliente;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utp.edu.pe.jracero.util.ErrorLog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "clients", urlPatterns = {"/clients"})
public class ClientsSevlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Cliente> clientes;
        ClienteDao clienteDao;

        try {
            clienteDao = new ClienteDao();
            clientes = clienteDao.getClientes();
            clienteDao.close();
            req.setAttribute("clientes", clientes);

            ErrorLog.log("Clientes obtenidos con éxito", ErrorLog.Level.INFO);
            req.getRequestDispatcher("clientes.jsp").forward(req, resp);
        } catch (Exception e) {
            ErrorLog.log(e.getMessage(), ErrorLog.Level.ERROR);
            req.setAttribute("msg", e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
