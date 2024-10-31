package utp.edu.pe.jracero.dao;

import utp.edu.pe.jracero.model.Proveedor;
import utp.edu.pe.jracero.util.AppConfig;
import utp.edu.pe.jracero.util.DataAccessMariaDB;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProveedorDao {
    private final Connection cnn;

    public ProveedorDao() throws SQLException, NamingException {
        this.cnn = DataAccessMariaDB.getConnection(DataAccessMariaDB.TipoDA.DATASOURCE, AppConfig.getDatasource());
    }

    public void close() throws SQLException {
        if (this.cnn != null) {
            DataAccessMariaDB.closeConnection(this.cnn);
        }
    }

    public void createProveedor(Proveedor proveedor) throws SQLException {
        String query = "INSERT INTO proveedor (nombre_empresa, telefono, correo, ruc) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setString(1, proveedor.getNombre_empresa());
            ps.setString(2, proveedor.getTelefono());
            ps.setString(3, proveedor.getCorreo());
            ps.setString(4, proveedor.getRuc());
            ps.executeUpdate();
        }
    }

    public void updateProveedor(Proveedor proveedor) throws SQLException {
        String query = "UPDATE proveedor SET nombre_empresa = ?, telefono = ?, correo = ?, ruc = ? WHERE id_proveedor = ?";
        try (PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setString(1, proveedor.getNombre_empresa());
            ps.setString(2, proveedor.getTelefono());
            ps.setString(3, proveedor.getCorreo());
            ps.setString(4, proveedor.getRuc());
            ps.setInt(5, proveedor.getId_proveedor());
            ps.executeUpdate();
        }
    }

    public void deleteProveedor(int id_proveedor) throws SQLException {
        String query = "DELETE FROM proveedor WHERE id_proveedor = ?";
        try (PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setInt(1, id_proveedor);
            ps.executeUpdate();
        }
    }

    public Proveedor getProveedorById(int id_proveedor) throws SQLException {
        String query = "SELECT * FROM proveedor WHERE id_proveedor = ?";
        Proveedor proveedor = null;
        try (PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setInt(1, id_proveedor);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    proveedor = new Proveedor();
                    proveedor.setId_proveedor(rs.getInt("id_proveedor"));
                    proveedor.setNombre_empresa(rs.getString("nombre_empresa"));
                    proveedor.setTelefono(rs.getString("telefono"));
                    proveedor.setCorreo(rs.getString("correo"));
                    proveedor.setRuc(rs.getString("ruc"));
                }
            }
        }
        return proveedor;
    }

    public List<Proveedor> getProveedores() throws SQLException {
        String query = "SELECT * FROM proveedor";
        List<Proveedor> proveedores = new ArrayList<>();
        try (PreparedStatement ps = cnn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Proveedor proveedor = new Proveedor();
                proveedor.setId_proveedor(rs.getInt("id_proveedor"));
                proveedor.setNombre_empresa(rs.getString("nombre_empresa"));
                proveedor.setTelefono(rs.getString("telefono"));
                proveedor.setCorreo(rs.getString("correo"));
                proveedor.setRuc(rs.getString("ruc"));
                proveedores.add(proveedor);
            }
        }
        return proveedores;
    }
}