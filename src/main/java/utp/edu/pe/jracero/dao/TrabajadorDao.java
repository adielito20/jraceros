package utp.edu.pe.jracero.dao;

import utp.edu.pe.jracero.model.Trabajador;
import utp.edu.pe.jracero.model.enums.Rol;
import utp.edu.pe.jracero.service.Auth;
import utp.edu.pe.jracero.util.AppConfig;
import utp.edu.pe.jracero.util.DataAccessMariaDB;

import javax.naming.NamingException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrabajadorDao {
    private final Connection cnn;

    public TrabajadorDao() throws SQLException, NamingException {
        this.cnn = DataAccessMariaDB.getConnection(DataAccessMariaDB.TipoDA.DATASOURCE, AppConfig.getDatasource());
    }

    public void close() throws SQLException {
        if (this.cnn != null) DataAccessMariaDB.closeConnection(this.cnn);
    }

    public void createTrabajador(Trabajador trabajador) throws SQLException {
        String query = "INSERT INTO trabajador (nombre, apellido, dni, rol, telefono, correo, contraseña) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setString(1, trabajador.getNombre());
            ps.setString(2, trabajador.getApellido());
            ps.setString(3, trabajador.getDni());
            ps.setString(4, trabajador.getRol().toString());
            ps.setString(5, trabajador.getTelefono());
            ps.setString(6, trabajador.getCorreo());
            ps.setString(7, Auth.md5(trabajador.getContraseña()));
            ps.executeUpdate();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public void updateTrabajador(Trabajador trabajador) throws SQLException {
        String query = "UPDATE trabajador SET nombre = ?, apellido, dni = ?, rol = ?, telefono = ?, correo = ?, contraseña = ? WHERE id_trabajador = ?";
        try (PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setString(1, trabajador.getNombre());
            ps.setString(2, trabajador.getApellido());
            ps.setString(3, trabajador.getDni());
            ps.setString(4, trabajador.getRol().toString());
            ps.setString(5, trabajador.getTelefono());
            ps.setString(6, trabajador.getCorreo());
            ps.setString(7, Auth.md5(trabajador.getContraseña()));
            ps.setInt(8, trabajador.getId_trabajador());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteTrabajador(int id_trabajador) throws SQLException {
        String query = "DELETE FROM trabajador WHERE id_trabajador = ?";
        try (PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setInt(1, id_trabajador);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Trabajador getTrabajadorById(int id_trabajador) throws SQLException {
        String query = "SELECT * FROM trabajador WHERE id_trabajador = ?";
        Trabajador trabajador = null;
        try (PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setInt(1, id_trabajador);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                trabajador = new Trabajador();
                trabajador.setId_trabajador(rs.getInt("id_trabajador"));
                trabajador.setNombre(rs.getString("nombre"));
                trabajador.setApellido(rs.getString("apellido"));
                trabajador.setDni(rs.getString("dni"));
                trabajador.setRol(Rol.valueOf(rs.getString("rol")));
                trabajador.setTelefono(rs.getString("telefono"));
                trabajador.setCorreo(rs.getString("correo"));
                trabajador.setContraseña(rs.getString("contraseña"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trabajador;
    }

    public List<Trabajador> getTrabajadores() throws SQLException {
        String query = "SELECT * FROM trabajador";
        List<Trabajador> trabajadores = new ArrayList<>();
        try (PreparedStatement ps = cnn.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Trabajador trabajador = new Trabajador();
                trabajador.setId_trabajador(rs.getInt("id_trabajador"));
                trabajador.setNombre(rs.getString("nombre"));
                trabajador.setApellido(rs.getString("apellido"));
                trabajador.setDni(rs.getString("dni"));
                trabajador.setRol(Rol.valueOf(rs.getString("rol")));
                trabajador.setTelefono(rs.getString("telefono"));
                trabajador.setCorreo(rs.getString("correo"));
                trabajador.setContraseña(rs.getString("contraseña"));
                trabajadores.add(trabajador);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trabajadores;
    }
}
