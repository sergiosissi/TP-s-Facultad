package com.supermercado.dao;

import com.supermercado.compra.CompraProducto;
import com.supermercado.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CompraProductoDAO {

    private static Session session;


    public CompraProductoDAO() {
        this.session =  HibernateUtil.getSessionFactory().openSession();
    }

    public void guardar(CompraProducto compraProducto) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(compraProducto);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }

    }

    public static List<CompraProducto> getComprasProductosByCompraId(Long compraId) {
        List<CompraProducto> comprasProductos = null;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            comprasProductos = session.createQuery("FROM CompraProducto WHERE compra.id = :compraId")
                    .setParameter("compraId", compraId)
                    .list();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        }
        return comprasProductos;
    }


    public void eliminarCompraProductoByProductoId(int productoId) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            // Se utiliza una consulta de Hibernate para eliminar los registros de la tabla producto_compuesto_producto
            session.createQuery("DELETE FROM CompraProducto WHERE producto.id = :productoId")
                    .setParameter("productoId", productoId)
                    .executeUpdate();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }

}
