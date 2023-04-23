package com.supermercado.dao;

import com.supermercado.persona.Cliente;
import com.supermercado.persona.Persona;

import java.util.List;

import com.supermercado.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class PersonaDAO {

    private Session session;
    public PersonaDAO() {
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();

    }

    public boolean obtenerClientePorDni(String dni) {
        return false;
    }

    public void guardar(Persona nuevaPersona) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(nuevaPersona);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }

    public Persona buscarPorDni(String dni) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Persona> query = session.createQuery("from Persona where dni = :dni", Persona.class);
            query.setParameter("dni", dni);
            List<Persona> personas = query.list();
            if (personas.size() > 0) {
                return personas.get(0);
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
