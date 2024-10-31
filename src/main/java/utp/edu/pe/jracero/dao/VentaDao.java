package utp.edu.pe.jracero.dao;

import utp.edu.pe.jracero.model.Venta;
import utp.edu.pe.jracero.model.enums.Metodo_pago;
import utp.edu.pe.jracero.model.enums.Tipo_comprobante;
import utp.edu.pe.jracero.util.AppConfig;
import utp.edu.pe.jracero.util.DataAccessMariaDB;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VentaDao {
    private final Connection cnn;

    public VentaDao() throws SQLException, NamingException {
         this.cnn = DataAccessMariaDB.getConnection(DataAccessMariaDB.TipoDA.DATASOURCE, AppConfig.getDatasource());
    }

    public void close() throws SQLException {
        if (this.cnn != null) DataAccessMariaDB.closeConnection(this.cnn);
    }

    public void createVenta(Venta venta) throws SQLException {
        String query = "INSERT INTO venta (id_cliente, id_trabajador, metodo_pago, fecha, tipo_comprobante, igv, total) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = cnn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, venta.getId_cliente());
            ps.setInt(2, venta.getId_trabajador());
            ps.setString(3, venta.getMetodo_pago().toString());
            ps.setTimestamp(4, Timestamp.valueOf(venta.getFecha()));
            ps.setString(5, venta.getTipo_comprobante().toString());
            ps.setDouble(6, venta.getIgv());
            ps.setDouble(7, venta.getTotal());
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                venta.setId_venta(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateVenta(Venta venta) throws SQLException {
        String query = "UPDATE venta SET id_cliente = ?, id_trabajador = ?, metodo_pago = ?, fecha = ?, tipo_comprobante = ?, igv = ?, total = ? WHERE id_venta = ?";
        try (PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setInt(1, venta.getId_cliente());
            ps.setInt(2, venta.getId_trabajador());
            ps.setString(3, venta.getMetodo_pago().toString());
            ps.setTimestamp(4, Timestamp.valueOf(venta.getFecha()));
            ps.setString(5, venta.getTipo_comprobante().toString());
            ps.setDouble(6, venta.getIgv());
            ps.setDouble(7, venta.getTotal());
            ps.setInt(8, venta.getId_venta());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteVenta(int id_venta) throws SQLException {
        String query = "DELETE FROM venta WHERE id_venta = ?";
        try (PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setInt(1, id_venta);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Venta getVentaById(int id_venta) throws SQLException {
        String query = "SELECT * FROM venta WHERE id_venta = ?";
        Venta venta = null;
        try (PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setInt(1, id_venta);
            ResultSet rs = ps.executeQuery();
            ClienteDao clienteDao = new ClienteDao();
            TrabajadorDao trabajadorDao = new TrabajadorDao();
            if (rs.next()) {
                venta = new Venta();
                venta.setId_venta(rs.getInt("id_venta"));
                venta.setId_cliente(rs.getInt("id_cliente"));
                venta.setId_trabajador(rs.getInt("id_trabajador"));
                venta.setMetodo_pago(Metodo_pago.valueOf(rs.getString("metodo_pago")));
                venta.setFecha(rs.getTimestamp("fecha").toLocalDateTime());
                venta.setTipo_comprobante(Tipo_comprobante.valueOf(rs.getString("tipo_comprobante")));
                venta.setIgv(rs.getDouble("igv"));
                venta.setTotal(rs.getDouble("total"));
                venta.setCliente(clienteDao.getClienteById(rs.getInt("id_cliente")));
                venta.setTrabajador(trabajadorDao.getTrabajadorById(rs.getInt("id_trabajador")));
            }
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        }
        return venta;
    }

    public List<Venta> getVentas() throws SQLException {
        String query = "SELECT * FROM venta";
        List<Venta> ventas = new ArrayList<>();
        try (PreparedStatement ps = cnn.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            ClienteDao clienteDao = new ClienteDao();
            TrabajadorDao trabajadorDao = new TrabajadorDao();
            while (rs.next()) {
                Venta venta = new Venta();
                venta.setId_venta(rs.getInt("id_venta"));
                venta.setId_cliente(rs.getInt("id_cliente"));
                venta.setId_trabajador(rs.getInt("id_trabajador"));
                venta.setMetodo_pago(Metodo_pago.valueOf(rs.getString("metodo_pago")));
                venta.setFecha(rs.getTimestamp("fecha").toLocalDateTime());
                venta.setTipo_comprobante(Tipo_comprobante.valueOf(rs.getString("tipo_comprobante")));
                venta.setIgv(rs.getDouble("igv"));
                venta.setTotal(rs.getDouble("total"));
                venta.setCliente(clienteDao.getClienteById(rs.getInt("id_cliente")));
                venta.setTrabajador(trabajadorDao.getTrabajadorById(rs.getInt("id_trabajador")));
                ventas.add(venta);
            }
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        }
        return ventas;
    }

    public int getLasId() throws SQLException {
        String query = "SELECT MAX(id_venta) FROM venta";
        int id = 0;
        try (PreparedStatement ps = cnn.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

}
