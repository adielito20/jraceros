package utp.edu.pe.jracero.dao;

import utp.edu.pe.jracero.model.Producto;
import utp.edu.pe.jracero.model.enums.Tipo_producto;
import utp.edu.pe.jracero.util.AppConfig;
import utp.edu.pe.jracero.util.DataAccessMariaDB;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDao {
    private final Connection cnn;

    public ProductoDao() throws SQLException, NamingException {
        this.cnn = DataAccessMariaDB.getConnection(DataAccessMariaDB.TipoDA.DATASOURCE, AppConfig.getDatasource());
    }

    public void close() throws SQLException {
        if (this.cnn != null) DataAccessMariaDB.closeConnection(this.cnn);
    }

    public void createProducto(Producto producto) throws SQLException {
        String query = "INSERT INTO producto (nombre, tipo, descripcion, precio, id_categoria) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getTipo().toString());
            ps.setString(3, producto.getDescripcion());
            ps.setDouble(4, producto.getPrecio());
            ps.setInt(5, producto.getId_categoria());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateProducto(Producto producto) throws SQLException {
        String query = "UPDATE producto SET nombre = ?, tipo = ?, descripcion = ?, precio = ?, id_categoria = ? WHERE id_producto = ?";
        try (PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getTipo().toString());
            ps.setString(3, producto.getDescripcion());
            ps.setDouble(4, producto.getPrecio());
            ps.setInt(5, producto.getId_categoria());
            ps.setInt(6, producto.getId_producto());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProducto(int id_producto) throws SQLException {
        String query = "DELETE FROM producto WHERE id_producto = ?";
        try (PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setInt(1, id_producto);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Producto getProductoById(int id_producto) throws SQLException {
        String query = "SELECT * FROM producto WHERE id_producto = ?";
        Producto producto = null;
        try (PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setInt(1, id_producto);
            ResultSet rs = ps.executeQuery();
            CategoriaDao categoriaDao = new CategoriaDao();
            if (rs.next()) {
                producto = new Producto();
                producto.setId_producto(rs.getInt("id_producto"));
                producto.setNombre(rs.getString("nombre"));
                producto.setTipo(Tipo_producto.valueOf(rs.getString("tipo")));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setId_categoria((rs.getInt("id_categoria")));
                producto.setCategoria(categoriaDao.getCategoriaById(rs.getInt("id_categoria")));
            }
            categoriaDao.close();
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        }
        return producto;
    }

    public List<Producto> getProductos() throws SQLException {
        String query = "SELECT * FROM producto";
        List<Producto> productos = new ArrayList<>();
        try (PreparedStatement ps = cnn.prepareStatement(query)) {
            CategoriaDao categoriaDao = new CategoriaDao();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setId_producto(rs.getInt("id_producto"));
                producto.setNombre(rs.getString("nombre"));
                producto.setTipo(Tipo_producto.valueOf(rs.getString("tipo")));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setId_categoria((rs.getInt("id_categoria")));
                producto.setCategoria(categoriaDao.getCategoriaById(rs.getInt("id_categoria")));
                productos.add(producto);
            }
            categoriaDao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
        return productos;
    }
}
