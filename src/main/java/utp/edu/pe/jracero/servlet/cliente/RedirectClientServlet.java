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

@WebServlet(name = "redirectClient", urlPatterns = {"/redirectClient"})
public class RedirectClientServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id_cliente = Integer.parseInt(req.getParameter("id_cliente"));
        Cliente cliente;
        ClienteDao clienteDao;

        try {
            clienteDao = new ClienteDao();
            cliente = clienteDao.getClienteById(id_cliente);
            clienteDao.close();
            req.setAttribute("cliente", cliente);

            ErrorLog.log("Cliente redireccionado con éxito", ErrorLog.Level.INFO);
            req.getRequestDispatcher("update-cliente.jsp").forward(req, resp);
        } catch (Exception e) {
            ErrorLog.log(e.getMessage(), ErrorLog.Level.ERROR);
            req.setAttribute("msg", e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
