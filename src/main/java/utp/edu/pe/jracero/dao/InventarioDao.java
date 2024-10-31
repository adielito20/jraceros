package utp.edu.pe.jracero.dao;

import utp.edu.pe.jracero.model.Inventario;
import utp.edu.pe.jracero.util.AppConfig;
import utp.edu.pe.jracero.util.DataAccessMariaDB;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventarioDao {
    private final Connection cnn;

    public InventarioDao() throws SQLException, NamingException {
        this.cnn = DataAccessMariaDB.getConnection(DataAccessMariaDB.TipoDA.DATASOURCE, AppConfig.getDatasource());
    }

    public void close() throws SQLException {
        if (this.cnn != null) DataAccessMariaDB.closeConnection(this.cnn);
    }

    public void createInventario(Inventario inventario) throws SQLException {
        String query = "INSERT INTO inventario (id_producto, stock) VALUES (?, ?)";
        try (PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setInt(1, inventario.getId_producto());
            ps.setInt(2, inventario.getStock());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateInventario(Inventario inventario) throws SQLException {
        String query = "UPDATE inventario SET id_producto = ?, stock = ? WHERE id_inventario = ?";
        try (PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setInt(1, inventario.getId_producto());
            ps.setInt(2, inventario.getStock());
            ps.setInt(3, inventario.getId_inventario());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteInventario(int id_inventario) throws SQLException {
        String query = "DELETE FROM inventario WHERE id_inventario = ?";
        try (PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setInt(1, id_inventario);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Inventario getInventarioById(int id_inventario) throws SQLException {
        String query = "SELECT * FROM inventario WHERE id_inventario = ?";
        Inventario inventario = null;
        try (PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setInt(1, id_inventario);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                inventario = new Inventario();
                inventario.setId_producto(rs.getInt("id_producto"));
                inventario.setStock(rs.getInt("stock"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inventario;
    }

    public List<Inventario> getInventarios() throws SQLException {
        String query = "SELECT * FROM inventario";
        List<Inventario> inventarios = new ArrayList<>();
        try (PreparedStatement ps = cnn.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            ProductoDao productoDao = new ProductoDao();
            while (rs.next()) {
                Inventario inventario = new Inventario();
                inventario.setId_producto(rs.getInt("id_producto"));
                inventario.setStock(rs.getInt("stock"));
                inventario.setProducto(productoDao.getProductoById(inventario.getId_producto()));
                inventarios.add(inventario);
            }
            productoDao.close();
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        }
        return inventarios;
    }

    public int getStockByProducto(int id_producto) throws SQLException {
        String query = "SELECT stock FROM inventario WHERE id_producto = ?";
        int stock = 0;
        try (PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setInt(1, id_producto);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                stock = rs.getInt("stock");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stock;
    }

    public void updateStockByProducto(int id_producto, int stock) throws SQLException {
        String query = "UPDATE inventario SET stock = ? WHERE id_producto = ?";
        try (PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setInt(1, stock);
            ps.setInt(2, id_producto);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
