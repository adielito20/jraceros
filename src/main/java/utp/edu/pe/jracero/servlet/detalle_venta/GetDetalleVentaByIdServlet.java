package utp.edu.pe.jracero.servlet.detalle_venta;

import utp.edu.pe.jracero.dao.Detalle_ventaDao;
import utp.edu.pe.jracero.model.Detalle_venta;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utp.edu.pe.jracero.util.ErrorLog;

import javax.naming.NamingException;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "getDetalleVentaById", urlPatterns = {"/getDetalleVentaById"})
public class GetDetalleVentaByIdServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int venta_id = Integer.parseInt(req.getParameter("venta_id"));
        Detalle_ventaDao detalleVentaDao;
        List<Detalle_venta> detalleVentas;

        try {
            detalleVentaDao = new Detalle_ventaDao();
            detalleVentas = detalleVentaDao.getDetallesVentaByVenta(venta_id);
            detalleVentaDao.close();
            req.setAttribute("detalleVentas", detalleVentas);

            ErrorLog.log("Detalle de venta obtenidos con éxito", ErrorLog.Level.INFO);
            req.getRequestDispatcher("detalleVenta.jsp").forward(req, resp);
        } catch (Exception e) {
            ErrorLog.log(e.getMessage(), ErrorLog.Level.ERROR);
            req.setAttribute("msg", e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
