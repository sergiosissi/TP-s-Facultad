package com.supermercado.dao;

import com.supermercado.persona.Cliente;
import com.supermercado.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class ClienteDAO {
    private Session session;

    public ClienteDAO() {
        this.session =  HibernateUtil.getSessionFactory().openSession();
    }

    public void guardar(Cliente cliente) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(cliente);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }

    }

    public Cliente getClienteById(Long id) {
        Cliente cliente = null;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query<Cliente> query = session.createQuery("FROM Cliente WHERE id = :id");
            query.setParameter("id", id);
            cliente = query.uniqueResult();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        return cliente;
    }

    public Cliente getClienteByDNI(String dni) {
        Cliente cliente = null;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query<Cliente> query = session.createQuery("FROM Cliente WHERE dni = :dni");
            query.setParameter("dni", dni);
            cliente = query.uniqueResult();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        return cliente;
    }

}

