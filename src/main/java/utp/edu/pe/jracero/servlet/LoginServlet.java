package utp.edu.pe.jracero.servlet;

import utp.edu.pe.jracero.model.Trabajador;
import utp.edu.pe.jracero.service.Auth;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.naming.NamingException;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "login", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String correo = req.getParameter("correo");
        String pwd = req.getParameter("pwd");
        try {
            Trabajador trabajador = Auth.isValidTrabajador(correo, pwd);
            if (trabajador != null) {
                req.getSession().setAttribute("admin", trabajador);
                resp.sendRedirect("registro-venta.jsp");
            } else {
                resp.sendRedirect("index.jsp");
            }
        } catch (SQLException | NamingException e) {
            req.setAttribute("message", e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
