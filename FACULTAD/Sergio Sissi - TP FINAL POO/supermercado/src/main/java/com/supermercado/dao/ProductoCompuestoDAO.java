package com.supermercado.dao;

import com.supermercado.productos.ProductoCompuesto;
import com.supermercado.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class ProductoCompuestoDAO {

    private Session session;

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
}
