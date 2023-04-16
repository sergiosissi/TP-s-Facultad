package com.supermercado.dao;

import com.supermercado.productos.Producto;
import com.supermercado.productos.ProductoCompuesto;
import com.supermercado.productos.ProductoCompuestoProducto;
import com.supermercado.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import java.util.List;




public class ProductoCompuestoProductoDAO {


    private static Session session;

    public ProductoCompuestoProductoDAO() {
        session = HibernateUtil.getSessionFactory().openSession();
    }
    public static void guardar(ProductoCompuesto productoCompuesto, List<Producto> productos) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            for (Producto producto : productos) {
                ProductoCompuestoProducto productoCompuestoProducto = new ProductoCompuestoProducto();
                productoCompuestoProducto.setProductoCompuesto(productoCompuesto);
                productoCompuestoProducto.setProducto(producto);
                session.save(productoCompuestoProducto);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }
}
