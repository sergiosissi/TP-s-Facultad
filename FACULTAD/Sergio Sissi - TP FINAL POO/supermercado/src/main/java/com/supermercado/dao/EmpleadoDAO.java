package com.supermercado.dao;

import com.supermercado.persona.Empleado;
import com.supermercado.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class EmpleadoDAO {

    private Session session;

    public EmpleadoDAO() {
        this.session =  HibernateUtil.getSessionFactory().openSession();
    }


    public void guardar(Empleado empleado) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(empleado);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }

    }
}
