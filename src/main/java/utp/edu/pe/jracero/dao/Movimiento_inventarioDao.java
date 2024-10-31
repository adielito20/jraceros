package utp.edu.pe.jracero.dao;

import utp.edu.pe.jracero.model.Movimiento_inventario;
import utp.edu.pe.jracero.model.enums.Tipo_movimiento;
import utp.edu.pe.jracero.util.AppConfig;
import utp.edu.pe.jracero.util.DataAccessMariaDB;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Movimiento_inventarioDao {
    private final Connection cnn;

    public Movimiento_inventarioDao() throws SQLException, NamingException {
        this.cnn = DataAccessMariaDB.getConnection(DataAccessMariaDB.TipoDA.DATASOURCE, AppConfig.getDatasource());
    }

    public void close() throws SQLException {
        if (this.cnn != null) DataAccessMariaDB.closeConnection(this.cnn);
    }

    public void createMovimientoInventario(Movimiento_inventario movimiento) throws SQLException {
        String query = "INSERT INTO movimiento_inventario (id_producto, id_trabajador, id_proveedor, cantidad, tipo_movimiento, fecha) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setInt(1, movimiento.getId_producto());
            ps.setInt(2, movimiento.getId_trabajador());
            ps.setInt(3, movimiento.getId_proveedor());
            ps.setInt(4, movimiento.getCantidad());
            ps.setString(5, movimiento.getTipo_movimiento().name());
            ps.setTimestamp(6, Timestamp.valueOf(movimiento.getFecha()));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateMovimientoInventario(Movimiento_inventario movimiento) throws SQLException {
        String query = "UPDATE movimiento_inventario SET id_producto = ?, id_trabajador = ?, id_proveedor = ?, cantidad = ?, tipo_movimiento = ?, fecha = ? WHERE id_movimiento_inventario = ?";
        try (PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setInt(1, movimiento.getId_producto());
            ps.setInt(2, movimiento.getId_trabajador());
            ps.setInt(3, movimiento.getId_proveedor());
            ps.setInt(4, movimiento.getCantidad());
            ps.setString(5, movimiento.getTipo_movimiento().name());
            ps.setTimestamp(6, Timestamp.valueOf(movimiento.getFecha()));
            ps.setInt(7, movimiento.getId_movimiento_inventario());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteMovimientoInventario(int id_movimiento_inventario) throws SQLException {
        String query = "DELETE FROM movimiento_inventario WHERE id_movimiento_inventario = ?";
        try (PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setInt(1, id_movimiento_inventario);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Movimiento_inventario getMovimientoInventarioById(int id_movimiento_inventario) throws SQLException {
        String query = "SELECT * FROM movimiento_inventario WHERE id_movimiento_inventario = ?";
        Movimiento_inventario movimiento = null;
        try (PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setInt(1, id_movimiento_inventario);
            ProductoDao productoDao = new ProductoDao();
            TrabajadorDao trabajadorDao = new TrabajadorDao();
            ProveedorDao proveedorDao = new ProveedorDao();
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                movimiento = new Movimiento_inventario();
                movimiento.setId_movimiento_inventario(rs.getInt("id_movimiento_inventario"));
                movimiento.setId_producto(rs.getInt("id_producto"));
                movimiento.setId_trabajador(rs.getInt("id_trabajador"));
                movimiento.setId_proveedor(rs.getInt("id_proveedor"));
                movimiento.setCantidad(rs.getInt("cantidad"));
                movimiento.setTipo_movimiento(Tipo_movimiento.valueOf(rs.getString("tipo_movimiento")));
                movimiento.setFecha(rs.getTimestamp("fecha").toLocalDateTime());
                movimiento.setProducto(productoDao.getProductoById(rs.getInt("id_producto")));
                movimiento.setTrabajador(trabajadorDao.getTrabajadorById(rs.getInt("id_trabajador")));
                movimiento.setProveedor(proveedorDao.getProveedorById(rs.getInt("id_proveedor")));
            }
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        }
        return movimiento;
    }

    public List<Movimiento_inventario> getMovimientosInventario() throws SQLException {
        String query = "SELECT * FROM movimiento_inventario";
        List<Movimiento_inventario> movimientos = new ArrayList<>();
        try (PreparedStatement ps = cnn.prepareStatement(query)) {
            ProductoDao productoDao = new ProductoDao();
            TrabajadorDao trabajadorDao = new TrabajadorDao();
            ProveedorDao proveedorDao = new ProveedorDao();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Movimiento_inventario movimiento = new Movimiento_inventario();
                movimiento.setId_movimiento_inventario(rs.getInt("id_movimiento_inventario"));
                movimiento.setId_producto(rs.getInt("id_producto"));
                movimiento.setId_trabajador(rs.getInt("id_trabajador"));
                movimiento.setId_proveedor(rs.getInt("id_proveedor"));
                movimiento.setCantidad(rs.getInt("cantidad"));
                movimiento.setTipo_movimiento(Tipo_movimiento.valueOf(rs.getString("tipo_movimiento")));
                movimiento.setFecha(rs.getTimestamp("fecha").toLocalDateTime());
                movimiento.setProducto(productoDao.getProductoById(rs.getInt("id_producto")));
                movimiento.setTrabajador(trabajadorDao.getTrabajadorById(rs.getInt("id_trabajador")));
                movimiento.setProveedor(proveedorDao.getProveedorById(rs.getInt("id_proveedor")));
                movimientos.add(movimiento);
            }
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        }
        return movimientos;
    }
}
