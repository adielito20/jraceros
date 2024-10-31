package utp.edu.pe.jracero.servlet.venta;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utp.edu.pe.jracero.dao.Detalle_ventaDao;
import utp.edu.pe.jracero.dao.InventarioDao;
import utp.edu.pe.jracero.dao.VentaDao;
import utp.edu.pe.jracero.model.Detalle_venta;
import utp.edu.pe.jracero.model.Venta;
import utp.edu.pe.jracero.util.ErrorLog;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "deleteSale", urlPatterns = {"/deleteSale"})
public class DeleteSaleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id_venta = Integer.parseInt(req.getParameter("id_venta"));
        VentaDao ventaDao;
        InventarioDao inventarioDao;
        Detalle_ventaDao detalleVentaDao;
        List<Detalle_venta> detalleVentaList;

        try {
            ventaDao = new VentaDao();
            inventarioDao = new InventarioDao();
            detalleVentaDao = new Detalle_ventaDao();
            detalleVentaList = detalleVentaDao.getDetallesVentaByVenta(id_venta);
            for (Detalle_venta detalleVenta : detalleVentaList) {
                inventarioDao.updateStockByProducto(detalleVenta.getId_producto(), detalleVenta.getCantidad());
            }
            detalleVentaDao.deleteDetalleVentaByVenta(id_venta);
            ventaDao.deleteVenta(id_venta);
            detalleVentaDao.close();
            ventaDao.close();

            ErrorLog.log("Venta eliminada correctamente", ErrorLog.Level.INFO);
            req.getRequestDispatcher(req.getContextPath() + "sales").forward(req, resp);
        } catch (Exception e) {
            ErrorLog.log(e.getMessage(), ErrorLog.Level.ERROR);
            req.setAttribute("msg", e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
