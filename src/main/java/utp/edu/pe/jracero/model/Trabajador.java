package utp.edu.pe.jracero.model;

import utp.edu.pe.jracero.model.enums.Rol;

public class Trabajador {
    private int id_trabajador;
    private String nombre;
    private String apellido;
    private String dni;
    private Rol rol;
    private String telefono;
    private String correo;
    private String contraseña;

    public Trabajador() {
    }

    public Trabajador(String nombre, String apellido, String dni, Rol rol, String telefono, String correo, String contraseña) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.rol = rol;
        this.telefono = telefono;
        this.correo = correo;
        this.contraseña = contraseña;
    }

    public Trabajador(int id_trabajador, String nombre, String apellido, String dni, Rol rol, String telefono, String correo, String contraseña) {
        this.id_trabajador = id_trabajador;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.rol = rol;
        this.telefono = telefono;
        this.correo = correo;
        this.contraseña = contraseña;
    }

    @Override
    public String toString() {
        return "Trabajador{" +
                "id_trabajador=" + id_trabajador +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni='" + dni + '\'' +
                ", rol=" + rol +
                ", telefono='" + telefono + '\'' +
                ", correo='" + correo + '\'' +
                ", contraseña='" + contraseña + '\'' +
                '}';
    }

    public int getId_trabajador() {
        return id_trabajador;
    }

    public void setId_trabajador(int id_trabajador) {
        this.id_trabajador = id_trabajador;
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

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
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

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}
