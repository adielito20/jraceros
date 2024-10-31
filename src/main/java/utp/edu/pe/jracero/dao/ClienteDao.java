package utp.edu.pe.jracero.dao;

import utp.edu.pe.jracero.model.Cliente;
import utp.edu.pe.jracero.model.enums.Tipo_documento;
import utp.edu.pe.jracero.util.AppConfig;
import utp.edu.pe.jracero.util.DataAccessMariaDB;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDao {
    private final Connection cnn;

    public ClienteDao() throws SQLException, NamingException {
        this.cnn = DataAccessMariaDB.getConnection(DataAccessMariaDB.TipoDA.DATASOURCE, AppConfig.getDatasource());
    }

    public void close() throws SQLException {
        if (this.cnn != null) {
            cnn.close();
        }
    }

    // Ejemplo corregido de createCliente
    public void createCliente(Cliente cliente) throws SQLException {
        String query = "INSERT INTO cliente (nombre, apellido, telefono, correo, tipo_documento, numero_documento) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setString(1, cliente.getNombre()); // Parámetro 1
            ps.setString(2, cliente.getApellido()); // Parámetro 2
            ps.setString(3, cliente.getTelefono()); // Parámetro 3
            ps.setString(4, cliente.getCorreo()); // Parámetro 4
            ps.setString(5, cliente.getTipo_documento().toString()); // Parámetro 5
            ps.setString(6, cliente.getNumero_documento()); // Parámetro 6
            ps.executeUpdate();
        }
    }

    public void updateCliente(Cliente cliente) throws SQLException {
        String query = "UPDATE cliente SET nombre = ?, apellido = ?, telefono = ?, correo = ?, tipo_documento = ?, numero_documento = ? WHERE id_cliente = ?";
        try (PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setString(3, cliente.getTelefono());
            ps.setString(4, cliente.getCorreo());
            ps.setString(5, cliente.getTipo_documento().toString());
            ps.setString(6, cliente.getNumero_documento());
            ps.setInt(7, cliente.getId_cliente());
            ps.executeUpdate();
        }
    }

    public void deleteCliente(int id_cliente) throws SQLException {
        String query = "DELETE FROM cliente WHERE id_cliente = ?";
        try (PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setInt(1, id_cliente);
            ps.executeUpdate();
        }
    }
    public Cliente getClienteById(int id_cliente) throws SQLException {
        String query = "SELECT * FROM cliente WHERE id_cliente = ?";
        Cliente cliente = null;
        try (PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setInt(1, id_cliente); // Configura el parámetro aquí
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    cliente = new Cliente();
                    cliente.setId_cliente(rs.getInt("id_cliente"));
                    cliente.setNombre(rs.getString("nombre"));
                    cliente.setApellido(rs.getString("apellido"));
                    cliente.setTelefono(rs.getString("telefono"));
                    cliente.setCorreo(rs.getString("correo"));
                    cliente.setTipo_documento(Tipo_documento.valueOf(rs.getString("tipo_documento")));
                    cliente.setNumero_documento(rs.getString("numero_documento"));
                }
            }
        }
        return cliente;
    }

    public List<Cliente> getClientes() throws SQLException {
        String query = "SELECT * FROM cliente";
        List<Cliente> clientes = new ArrayList<>();
        try (PreparedStatement ps = cnn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId_cliente(rs.getInt("id_cliente"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setCorreo(rs.getString("correo"));
                cliente.setTipo_documento(Tipo_documento.valueOf(rs.getString("tipo_documento")));
                cliente.setNumero_documento(rs.getString("numero_documento"));
                clientes.add(cliente);
            }
        }
        return clientes;
    }
}
