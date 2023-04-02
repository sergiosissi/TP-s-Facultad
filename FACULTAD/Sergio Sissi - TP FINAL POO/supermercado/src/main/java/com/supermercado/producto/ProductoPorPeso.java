package com.supermercado.producto;
import javax.persistence.*;

@Entity
public class ProductoPorPeso extends ProductoSimple {

    @Column(name = "peso")
    private double peso;
    public ProductoPorPeso() {}

    public ProductoPorPeso(String nombre, double precioPorKilo, int stock, double peso, String departamento) {
        super(nombre, precioPorKilo ,stock, departamento);
        this.peso = peso;
    }


    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    //public double calcularPrecio(){
      //  return super.getPrecio() * peso;
   // }

    @Override public double getPrecio(){
        return super.getPrecio() * this.getPeso();
    }


}

