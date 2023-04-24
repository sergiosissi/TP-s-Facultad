package com.supermercado.dao;

import com.supermercado.persona.Cliente;
import com.supermercado.persona.Empleado;
import com.supermercado.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

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

    public Empleado getClienteByLegajo(String legajo) {

        Empleado empleado = null;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query<Empleado> query = session.createQuery("FROM Empleado WHERE legajo = :legajo");
            query.setParameter("legajo", legajo);
            empleado = query.uniqueResult();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        return empleado;

    }
}
