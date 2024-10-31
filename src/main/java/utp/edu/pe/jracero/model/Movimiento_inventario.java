package utp.edu.pe.jracero.model;

import utp.edu.pe.jracero.model.enums.Tipo_movimiento;

import java.time.LocalDateTime;

public class Movimiento_inventario {
    private int id_movimiento_inventario;
    private int id_producto;
    private int id_trabajador;
    private int id_proveedor;
    private int cantidad;
    private Tipo_movimiento tipo_movimiento;
    private LocalDateTime fecha;
    private Producto producto;
    private Trabajador trabajador;
    private Proveedor proveedor;

    public Movimiento_inventario() {
    }

    public Movimiento_inventario(int id_producto, int id_trabajador, int id_proveedor, int cantidad, Tipo_movimiento tipo_movimiento, LocalDateTime fecha) {
        this.id_producto = id_producto;
        this.id_trabajador = id_trabajador;
        this.id_proveedor = id_proveedor;
        this.cantidad = cantidad;
        this.tipo_movimiento = tipo_movimiento;
        this.fecha = fecha;
    }

    public Movimiento_inventario(int id_movimiento_inventario, int id_producto, int id_trabajador, int id_proveedor, int cantidad, Tipo_movimiento tipo_movimiento, LocalDateTime fecha) {
        this.id_movimiento_inventario = id_movimiento_inventario;
        this.id_producto = id_producto;
        this.id_trabajador = id_trabajador;
        this.id_proveedor = id_proveedor;
        this.cantidad = cantidad;
        this.tipo_movimiento = tipo_movimiento;
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Movimiento_inventario{" +
                "id_movimiento_inventario=" + id_movimiento_inventario +
                ", id_producto=" + id_producto +
                ", id_trabajador=" + id_trabajador +
                ", id_proveedor=" + id_proveedor +
                ", cantidad=" + cantidad +
                ", tipo_movimiento=" + tipo_movimiento +
                ", fecha=" + fecha +
                '}';
    }

    public int getId_movimiento_inventario() {
        return id_movimiento_inventario;
    }

    public void setId_movimiento_inventario(int id_movimiento_inventario) {
        this.id_movimiento_inventario = id_movimiento_inventario;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getId_trabajador() {
        return id_trabajador;
    }

    public void setId_trabajador(int id_trabajador) {
        this.id_trabajador = id_trabajador;
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Tipo_movimiento getTipo_movimiento() {
        return tipo_movimiento;
    }

    public void setTipo_movimiento(Tipo_movimiento tipo_movimiento) {
        this.tipo_movimiento = tipo_movimiento;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Trabajador getTrabajador() {
        return trabajador;
    }

    public void setTrabajador(Trabajador trabajador) {
        this.trabajador = trabajador;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
}
