package com.supermercado.compra;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "efectivo")
@PrimaryKeyJoinColumn(name = "id_pago", referencedColumnName = "id")
public class Efectivo extends Pago {
    @Column(name = "descuento")
    private Double descuento;

    // constructor, getters y setters


    public Efectivo() {
    }

    public Efectivo(Double montoPagado, Date fechaPago, Double descuento) {
        super(montoPagado, fechaPago);
        this.descuento = descuento;
    }

    public Double getDescuento() {
        return descuento;
    }

    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }
}
