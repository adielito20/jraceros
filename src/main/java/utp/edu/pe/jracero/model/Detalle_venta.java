package utp.edu.pe.jracero.model;

public class Detalle_venta {
    private int id_venta;
    private int id_producto;
    private int cantidad;
    private double precio_unitario;
    private double sub_total;
    private Producto producto;
    private Venta venta;

    public Detalle_venta() {
    }

    public Detalle_venta(int id_venta, int id_producto, int cantidad, double precio_unitario, double sub_total) {
        this.id_venta = id_venta;
        this.id_producto = id_producto;
        this.cantidad = cantidad;
        this.precio_unitario = precio_unitario;
        this.sub_total = sub_total;
    }

    @Override
    public String toString() {
        return "Detalle_venta{" +
                "id_venta=" + id_venta +
                ", id_producto=" + id_producto +
                ", cantidad=" + cantidad +
                ", precio_unitario=" + precio_unitario +
                ", sub_total=" + sub_total +
                '}';
    }

    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(double precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public double getSub_total() {
        return sub_total;
    }

    public void setSub_total(double sub_total) {
        this.sub_total = sub_total;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }
}
