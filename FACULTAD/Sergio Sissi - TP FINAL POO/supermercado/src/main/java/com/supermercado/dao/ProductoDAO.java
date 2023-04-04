package com.supermercado.dao;

import com.supermercado.productos.Producto;
import com.supermercado.util.HibernateUtil;
import org.hibernate.Session;


import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    private static Session session;

    public ProductoDAO() {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    public static List<Producto> getAll() {
       // List<Producto> productos = session.createQuery("FROM Producto").list();
        List<Producto> productos = null;
        if (productos == null) {
            productos = new ArrayList<>();
        }
        return productos;

    }

    public static Producto getById(Long id) {
        return session.get(Producto.class, id);
    }

    public void save(Producto producto) {
        session.beginTransaction();
        session.save(producto);
        session.getTransaction().commit();
    }

    public static void update(Producto producto) {
        session.beginTransaction();
        session.update(producto);
        session.getTransaction().commit();
    }

    public void delete(Producto producto) {
        session.beginTransaction();
        session.delete(producto);
        session.getTransaction().commit();
    }
}

