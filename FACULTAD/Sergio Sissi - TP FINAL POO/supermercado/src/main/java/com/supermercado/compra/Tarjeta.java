package com.supermercado.compra;

import com.google.protobuf.ExperimentalApi;

import javax.persistence.*;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "tarjeta")
@PrimaryKeyJoinColumn(name = "id_pago", referencedColumnName = "id")
public class Tarjeta extends Pago {
    @Column(name = "cuotas")
    private Integer cuotas;

    @Column(name = "recargo")
    private Double recargo;

    @Column(name = "tipo_tarjeta")
    private String tipoTarjeta;

    // constructor, getters y setters


    public Tarjeta() {
    }

    public Tarjeta(Double montoPagado, Date fechaPago, Integer cuotas, Double recargo, String tipoTarjeta) {
        super(montoPagado, fechaPago);
        this.cuotas = cuotas;
        this.recargo = recargo;
        this.tipoTarjeta = tipoTarjeta;
    }

    public Integer getCuotas() {
        return cuotas;
    }

    public void setCuotas(Integer cuotas) {
        this.cuotas = cuotas;
    }

    public Double getRecargo() {
        return recargo;
    }

    public void setRecargo(Double recargo) {
        this.recargo = recargo;
    }

    public String getTipoTarjeta() {
        return tipoTarjeta;
    }

    public void setTipoTarjeta(String tipoTarjeta) {
        this.tipoTarjeta = tipoTarjeta;
    }
}


