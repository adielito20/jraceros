package utp.edu.pe.jracero.servlet.cliente;

import utp.edu.pe.jracero.dao.ClienteDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "deleteClient", urlPatterns = {"/deleteClient"})
public class DeleteClientServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id_cliente = Integer.parseInt(req.getParameter("id_cliente"));
        ClienteDao clienteDao;

        try {
            clienteDao = new ClienteDao();
            clienteDao.deleteCliente(id_cliente);
            clienteDao.close();
            resp.sendRedirect(req.getContextPath() + "/clients");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
