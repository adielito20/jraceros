package utp.edu.pe.jracero.model;

import utp.edu.pe.jracero.model.enums.Tipo_producto;

public class Producto {
    private int id_producto;
    private int id_categoria;
    private double precio;
    private Tipo_producto tipo;
    private String nombre;
    private String descripcion;
    private Categoria categoria;

    public Producto() {
    }

    public Producto(String nombre, Tipo_producto tipo, String descripcion, double precio, int id_categoria) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.id_categoria = id_categoria;
    }

    public Producto(int id_producto, String nombre, Tipo_producto tipo, String descripcion, double precio, int id_categoria) {
        this.id_producto = id_producto;
        this.nombre = nombre;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.id_categoria = id_categoria;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id_producto=" + id_producto +
                ", nombre='" + nombre + '\'' +
                ", tipo='" + tipo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                ", id_categoria=" + id_categoria +
                '}';
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Tipo_producto getTipo() {
        return tipo;
    }

    public void setTipo(Tipo_producto tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
