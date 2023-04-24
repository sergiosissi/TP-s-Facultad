package com.supermercado.dao;

import com.supermercado.compra.Compra;
import com.supermercado.compra.CompraProducto;
import com.supermercado.persona.Cliente;
import com.supermercado.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CompraDAO {

    private static Session session;


    public CompraDAO() {
        this.session =  HibernateUtil.getSessionFactory().openSession();
    }


    public static List<Compra> getComprasByClienteId(Long clienteId) {
        List<Compra> compras = null;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            compras =  session.createQuery("SELECT DISTINCT c FROM Compra c JOIN FETCH c.productos cp WHERE c.cliente.id = :clienteId ORDER BY c.fechaCompra DESC")
                                .setParameter("clienteId", clienteId)
                    .list();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        }
        return compras;
    }


    public static void guardar(Compra compra) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(compra);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }

    }

}
