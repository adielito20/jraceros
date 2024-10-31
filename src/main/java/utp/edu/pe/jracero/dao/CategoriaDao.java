package utp.edu.pe.jracero.dao;

import utp.edu.pe.jracero.model.Categoria;
import utp.edu.pe.jracero.util.AppConfig;
import utp.edu.pe.jracero.util.DataAccessMariaDB;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDao {
    private final Connection cnn;

    public CategoriaDao() throws SQLException, NamingException {
        this.cnn = DataAccessMariaDB.getConnection(DataAccessMariaDB.TipoDA.DATASOURCE, AppConfig.getDatasource());
    }

    public void close() throws SQLException {
        if (this.cnn != null) DataAccessMariaDB.closeConnection(this.cnn);
    }

    public void createCategoria(Categoria categoria) throws SQLException {
        String query = "INSERT INTO categoria (id_categoria, nombre) VALUES (?, ?)";
        try (PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setInt(1, categoria.getId_categoria());
            ps.setString(2, categoria.getNombre());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCategoria(Categoria categoria) throws SQLException {
        String query = "UPDATE categoria SET nombre = ? WHERE id_categoria = ?";
        try (PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setString(1, categoria.getNombre());
            ps.setInt(2, categoria.getId_categoria());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCategoria(int id_categoria) throws SQLException {
        String query = "DELETE FROM categoria WHERE id_categoria = ?";
        try (PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setInt(1, id_categoria);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Categoria getCategoriaById(int id_categoria) throws SQLException {
        String query = "SELECT * FROM categoria WHERE id_categoria = ?";
        Categoria categoria = null;
        try (PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setInt(1, id_categoria);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                categoria = new Categoria();
                categoria.setId_categoria(rs.getInt("id_categoria"));
                categoria.setNombre(rs.getString("nombre"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoria;
    }

    public List<Categoria> getCategorias() throws SQLException {
        String query = "SELECT * FROM categoria";
        List<Categoria> categorias = new ArrayList<>();
        try (PreparedStatement ps = cnn.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setId_categoria(rs.getInt("id_categoria"));
                categoria.setNombre(rs.getString("nombre"));
                categorias.add(categoria);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categorias;
    }
}
