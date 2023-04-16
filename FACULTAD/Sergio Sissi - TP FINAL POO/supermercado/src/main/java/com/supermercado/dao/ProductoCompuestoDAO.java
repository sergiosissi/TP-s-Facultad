package com.supermercado.dao;

import com.supermercado.productos.ProductoCompuesto;
import com.supermercado.productos.Producto;
import com.supermercado.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class ProductoCompuestoDAO {

    private static Session session;

    public ProductoCompuestoDAO() {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    public List<ProductoCompuesto> getAll() {
        String sql = "SELECT * FROM producto_compuesto";
        NativeQuery<ProductoCompuesto> query = session.createNativeQuery(sql, ProductoCompuesto.class);
        List<ProductoCompuesto> productosCompuestos = query.getResultList();
        session.close();
        return productosCompuestos;
    }

    public static void guardar(ProductoCompuesto productoCompuesto) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(productoCompuesto);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }

}
