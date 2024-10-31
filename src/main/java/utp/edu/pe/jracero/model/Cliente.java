package utp.edu.pe.jracero.model;

import utp.edu.pe.jracero.model.enums.Tipo_documento;

public class Cliente {
    private int id_cliente;
    private String nombre;
    private String apellido;
    private String telefono;
    private String correo;
    private Tipo_documento tipo_documento;
    private String numero_documento;

    public Cliente() {
    }

    public Cliente(String nombre, String apellido, String telefono, String correo, Tipo_documento tipo_documento, String numero_documento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.correo = correo;
        this.tipo_documento = tipo_documento;
        this.numero_documento = numero_documento;
    }

    public Cliente(int id_cliente, String nombre, String apellido, String telefono, String correo, Tipo_documento tipo_documento, String numero_documento) {
        this.id_cliente = id_cliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.correo = correo;
        this.tipo_documento = tipo_documento;
        this.numero_documento = numero_documento;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id_cliente=" + id_cliente +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", telefono='" + telefono + '\'' +
                ", correo='" + correo + '\'' +
                ", tipo_documento=" + tipo_documento +
                ", numero_documento='" + numero_documento + '\'' +
                '}';
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Tipo_documento getTipo_documento() {
        return tipo_documento;
    }

    public void setTipo_documento(Tipo_documento tipo_documento) {
        this.tipo_documento = tipo_documento;
    }

    public String getNumero_documento() {
        return numero_documento;
    }

    public void setNumero_documento(String numero_documento) {
        this.numero_documento = numero_documento;
    }
}