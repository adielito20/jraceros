package utp.edu.pe.jracero.model;

import utp.edu.pe.jracero.model.enums.Metodo_pago;
import utp.edu.pe.jracero.model.enums.Tipo_comprobante;

import java.time.LocalDateTime;

public class Venta {
    private int id_venta;
    private int id_cliente;
    private int id_trabajador;
    private Metodo_pago metodo_pago;
    private LocalDateTime fecha;
    private Tipo_comprobante tipo_comprobante;
    private double igv;
    private double total;
    private Cliente cliente;
    private Trabajador trabajador;

    public Venta() {
    }

    public Venta(int id_venta, int id_cliente, int id_trabajador, Metodo_pago metodo_pago, LocalDateTime fecha, Tipo_comprobante tipo_comprobante, double igv, double total) {
        this.id_venta = id_venta;
        this.id_cliente = id_cliente;
        this.id_trabajador = id_trabajador;
        this.metodo_pago = metodo_pago;
        this.fecha = fecha;
        this.tipo_comprobante = tipo_comprobante;
        this.igv = igv;
        this.total = total;
    }

    public Venta(int id_cliente, int id_trabajador, Metodo_pago metodo_pago, LocalDateTime fecha, Tipo_comprobante tipo_comprobante, double igv, double total) {
        this.id_cliente = id_cliente;
        this.id_trabajador = id_trabajador;
        this.metodo_pago = metodo_pago;
        this.fecha = fecha;
        this.tipo_comprobante = tipo_comprobante;
        this.igv = igv;
        this.total = total;
    }

    @Override
    public String toString() {
        return "Venta{" +
                "id_venta=" + id_venta +
                ", id_cliente=" + id_cliente +
                ", id_trabajador=" + id_trabajador +
                ", metodo_pago=" + metodo_pago +
                ", fecha=" + fecha +
                ", tipo_comprobante=" + tipo_comprobante +
                ", igv=" + igv +
                ", total=" + total +
                '}';
    }

    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_trabajador() {
        return id_trabajador;
    }

    public void setId_trabajador(int id_trabajador) {
        this.id_trabajador = id_trabajador;
    }

    public Metodo_pago getMetodo_pago() {
        return metodo_pago;
    }

    public void setMetodo_pago(Metodo_pago metodo_pago) {
        this.metodo_pago = metodo_pago;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Tipo_comprobante getTipo_comprobante() {
        return tipo_comprobante;
    }

    public void setTipo_comprobante(Tipo_comprobante tipo_comprobante) {
        this.tipo_comprobante = tipo_comprobante;
    }

    public double getIgv() {
        return igv;
    }

    public void setIgv(double igv) {
        this.igv = igv;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Trabajador getTrabajador() {
        return trabajador;
    }

    public void setTrabajador(Trabajador trabajador) {
        this.trabajador = trabajador;
    }
}
