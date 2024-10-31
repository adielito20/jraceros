package utp.edu.pe.jracero.model;

public class Proveedor {
    private int id_proveedor;
    private String nombre_empresa;
    private String telefono;
    private String correo;
    private String ruc;

    public Proveedor() {
    }

    public Proveedor(int id_proveedor, String nombre_empresa, String telefono, String correo, String ruc) {
        this.id_proveedor = id_proveedor;
        this.nombre_empresa = nombre_empresa;
        this.telefono = telefono;
        this.correo = correo;
        this.ruc = ruc;
    }

    public Proveedor(String nombre_empresa, String telefono, String correo, String ruc) {
        this.nombre_empresa = nombre_empresa;
        this.telefono = telefono;
        this.correo = correo;
        this.ruc = ruc;
    }

    @Override
    public String toString() {
        return "Proveedor{" +
                "id_proveedor=" + id_proveedor +
                ", nombre_empresa='" + nombre_empresa + '\'' +
                ", telefono='" + telefono + '\'' +
                ", correo='" + correo + '\'' +
                ", ruc='" + ruc + '\'' +
                '}';
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public String getNombre_empresa() {
        return nombre_empresa;
    }

    public void setNombre_empresa(String nombre_empresa) {
        this.nombre_empresa = nombre_empresa;
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

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }
}
