package com.supermercado.compra;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "pago")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "montoPagado")
    private Double montoPagado;
    @Column(name = "fechaPago")
    private Date fechaPago;

    public Pago() {
    }

    public Pago(Double montoPagado, Date fechaPago) {
        this.montoPagado = montoPagado;
        this.fechaPago = fechaPago;
    }

    // getters y setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getMontoPagado() {
        return montoPagado;
    }

    public void setMontoPagado(Double montoPagado) {
        this.montoPagado = montoPagado;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }
}

