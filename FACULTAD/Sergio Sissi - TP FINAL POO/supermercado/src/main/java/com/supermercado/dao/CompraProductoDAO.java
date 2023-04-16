package com.supermercado.dao;

import com.supermercado.compra.Compra;
import com.supermercado.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CompraProductoDAO {

    private Session session;


    public CompraProductoDAO() {
        this.session =  HibernateUtil.getSessionFactory().openSession();
    }

    public void guardar(Compra.CompraProducto compraProducto) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(compraProducto);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }

    }

}
