package com.supermercado.dao;

import com.supermercado.persona.Cliente;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ClienteDAO {
    private Session session;

    public ClienteDAO() {
        this.session = session;
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

}

