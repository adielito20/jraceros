package utp.edu.pe.jracero.servlet.venta;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utp.edu.pe.jracero.dao.VentaDao;
import utp.edu.pe.jracero.model.Venta;
import utp.edu.pe.jracero.util.ErrorLog;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "sales", urlPatterns = {"/sales"})
public class SalesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Venta> ventas;
        VentaDao ventaDao;

        try {
            ventaDao = new VentaDao();
            ventas = ventaDao.getVentas();
            req.setAttribute("ventas", ventas);

            ErrorLog.log("Ventas obtenidas con éxito", ErrorLog.Level.INFO);
            req.getRequestDispatcher("ventas.jsp").forward(req, resp);
        } catch (Exception e) {
            ErrorLog.log(e.getMessage(), ErrorLog.Level.ERROR);
            req.setAttribute("msg", e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
