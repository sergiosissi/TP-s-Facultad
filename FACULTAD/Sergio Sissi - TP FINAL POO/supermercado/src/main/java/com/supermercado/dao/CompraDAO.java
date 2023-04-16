package com.supermercado.dao;

import com.supermercado.compra.Compra;
import com.supermercado.persona.Cliente;
import com.supermercado.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CompraDAO {

    private Session session;


    public CompraDAO() {
        this.session =  HibernateUtil.getSessionFactory().openSession();
    }


    public void guardar(Compra compra) {
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
