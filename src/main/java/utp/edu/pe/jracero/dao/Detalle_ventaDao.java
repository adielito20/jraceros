package utp.edu.pe.jracero.dao;

import utp.edu.pe.jracero.model.Detalle_venta;
import utp.edu.pe.jracero.util.AppConfig;
import utp.edu.pe.jracero.util.DataAccessMariaDB;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Detalle_ventaDao {
    private final Connection cnn;

    public Detalle_ventaDao() throws SQLException, NamingException {
        this.cnn = DataAccessMariaDB.getConnection(DataAccessMariaDB.TipoDA.DATASOURCE, AppConfig.getDatasource());
    }

    public void close() throws SQLException {
        if (this.cnn != null) DataAccessMariaDB.closeConnection(this.cnn);
    }

    public void createDetalleVenta(Detalle_venta detalleVenta) throws SQLException {
        String query = "INSERT INTO detalle_venta (id_venta, id_producto, cantidad, precio_unitario, sub_total) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setInt(1, detalleVenta.getId_venta());
            ps.setInt(2, detalleVenta.getId_producto());
            ps.setInt(3, detalleVenta.getCantidad());
            ps.setDouble(4, detalleVenta.getPrecio_unitario());
            ps.setDouble(5, detalleVenta.getSub_total());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateDetalleVenta(Detalle_venta detalleVenta) throws SQLException {
        String query = "UPDATE detalle_venta SET cantidad = ?, precio_unitario = ?, sub_total = ? WHERE id_venta = ? AND id_producto = ?";
        try (PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setInt(1, detalleVenta.getCantidad());
            ps.setDouble(2, detalleVenta.getPrecio_unitario());
            ps.setDouble(3, detalleVenta.getSub_total());
            ps.setInt(4, detalleVenta.getId_venta());
            ps.setInt(5, detalleVenta.getId_producto());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDetalleVenta(int id_venta, int id_producto) throws SQLException {
        String query = "DELETE FROM detalle_venta WHERE id_venta = ? AND id_producto = ?";
        try (PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setInt(1, id_venta);
            ps.setInt(2, id_producto);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Detalle_venta getDetalleVenta(int id_venta, int id_producto) throws SQLException {
        String query = "SELECT * FROM detalle_venta WHERE id_venta = ? AND id_producto = ?";
        Detalle_venta detalleVenta = null;
        try (PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setInt(1, id_venta);
            ps.setInt(2, id_producto);
            ProductoDao productoDao = new ProductoDao();
            VentaDao ventaDao = new VentaDao();
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                detalleVenta = new Detalle_venta();
                detalleVenta.setId_venta(rs.getInt("id_venta"));
                detalleVenta.setId_producto(rs.getInt("id_producto"));
                detalleVenta.setCantidad(rs.getInt("cantidad"));
                detalleVenta.setPrecio_unitario(rs.getDouble("precio_unitario"));
                detalleVenta.setSub_total(rs.getDouble("sub_total"));
                detalleVenta.setProducto(productoDao.getProductoById(rs.getInt("id_producto")));
                detalleVenta.setVenta(ventaDao.getVentaById(rs.getInt("id_venta")));
            }
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        }
        return detalleVenta;
    }

    public List<Detalle_venta> getDetallesVentaByVenta(int id_venta) throws SQLException {
        String query = "SELECT * FROM detalle_venta WHERE id_venta = ?";
        List<Detalle_venta> detalles = new ArrayList<>();
        try (PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setInt(1, id_venta);
            ProductoDao productoDao = new ProductoDao();
            VentaDao ventaDao = new VentaDao();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Detalle_venta detalleVenta = new Detalle_venta();
                detalleVenta.setId_venta(rs.getInt("id_venta"));
                detalleVenta.setId_producto(rs.getInt("id_producto"));
                detalleVenta.setCantidad(rs.getInt("cantidad"));
                detalleVenta.setPrecio_unitario(rs.getDouble("precio_unitario"));
                detalleVenta.setSub_total(rs.getDouble("sub_total"));
                detalleVenta.setProducto(productoDao.getProductoById(rs.getInt("id_producto")));
                detalleVenta.setVenta(ventaDao.getVentaById(rs.getInt("id_venta")));
                detalles.add(detalleVenta);
            }
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        }
        return detalles;
    }

    public void deleteDetalleVentaByVenta (int id_venta) throws SQLException {
        String query = "DELETE FROM detalle_venta WHERE id_venta = ?";
        try (PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setInt(1, id_venta);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
