package com.supermercado.dao;

import com.supermercado.compra.Pago;
import com.supermercado.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PagoDAO {

    private static Session session;


    public PagoDAO() {
        this.session =  HibernateUtil.getSessionFactory().openSession();
    }


    public static void guardar(Pago pago) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(pago);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }

}
