package com.supermercado.dao;

import com.supermercado.persona.Cliente;
import com.supermercado.productos.Producto;
import com.supermercado.util.*;
import org.hibernate.Session;
import org.hibernate.Transaction;


import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    private static Session session;

    public ProductoDAO() {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    public static List<Producto> getAll() {
        List<Producto> productos = session.createQuery("FROM Producto").list();
        if (productos == null) {
            productos = new ArrayList<>();
        }
        return productos;

    }

    public static List<Producto> getAllCombos() {
        List<Producto> productos = session.createQuery("FROM Producto WHERE departamento = 'Combos' ").list();
        if (productos == null) {
            productos = new ArrayList<>();
        }
        return productos;

    }

    public static Producto getProductoById(int id) {
        Transaction tx = null;
        Producto producto = null;
        try {
            tx = session.beginTransaction();
            producto = (Producto) session.createQuery("FROM Producto WHERE id = :id")
                    .setParameter("id", id)
                    .uniqueResult();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        return producto;
    }

    public static List<Producto> getProductosByProductoCompuestoId(int id) {
        Transaction tx = null;
        List<Producto> productos = null;
        try {
            tx = session.beginTransaction();
            productos = session.createQuery("SELECT pcp.producto FROM ProductoCompuestoProducto pcp WHERE pcp.productoCompuesto.id = :id")
                    .setParameter("id", id)
                    .list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        return productos;
    }


    public static boolean existeProducto(String nombre) {
        Transaction tx = null;
        boolean existe = false;
        try {
            tx = session.beginTransaction();
            // Utiliza una consulta de Hibernate para verificar si existe un producto con el nombre proporcionado
            Producto producto = (Producto) session.createQuery("FROM Producto WHERE nombre = :nombre")
                    .setParameter("nombre", nombre)
                    .uniqueResult();
            existe = producto != null; // Si el producto no es nulo, significa que existe en la base de datos
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        return existe;
    }


    public static void guardar(Producto producto) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(producto);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
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

