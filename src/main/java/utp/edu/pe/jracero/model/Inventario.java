package utp.edu.pe.jracero.model;

public class Inventario {
    private final int id_inventario = 1;
    private int id_producto;
    private int stock;
    private Producto producto;

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Inventario() {
    }

    public Inventario(int id_producto, int stock) {
        this.id_producto = id_producto;
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Inventario{" +
                "id_inventario=" + id_inventario +
                ", id_producto=" + id_producto +
                ", stock=" + stock +
                '}';
    }

    public int getId_inventario() {
        return id_inventario;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
