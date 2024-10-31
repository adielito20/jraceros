package utp.edu.pe.jracero.servlet.carrito;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "cart", urlPatterns = {"/cart"})
public class CartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        HttpSession session = req.getSession();
        Map<Integer, Integer> car = (Map<Integer, Integer>) session.getAttribute("car");

        if (car == null) {
            car = new HashMap<>();
        }

        if ("add".equals(action)) {
            int productId = Integer.parseInt(req.getParameter("id_producto"));
            int quantity = Integer.parseInt(req.getParameter("quantity"));
            car.put(productId, car.getOrDefault(productId, 0) + quantity);
            resp.sendRedirect(req.getContextPath() + "/newVenta");
        } else if ("remove".equals(action)) {
            int productId = Integer.parseInt(req.getParameter("id_producto"));
            car.remove(productId);
            resp.sendRedirect(req.getContextPath() + "/newVenta");
        } else if ("modify".equals(action)) {
            int productId = Integer.parseInt(req.getParameter("id_producto"));
            int quantity = Integer.parseInt(req.getParameter("quantity"));
            if (quantity > 0) {
                car.put(productId, quantity);
            } else {
                car.remove(productId);
            }
            resp.sendRedirect(req.getContextPath() + "/newVenta");
        }
        session.setAttribute("car", car);
    }
}
