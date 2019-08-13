package Dao.productDao;

import Dao.DBConnect;
import interfaces.DatabaseDao;
import org.hibernate.Session;
import org.hibernate.query.Query;
import product.Product;

import java.util.List;

public class ProductDao implements DatabaseDao {
    @Override
    public void save(Object entity) {
        Session session = DBConnect.getSession();
        session.beginTransaction();
        session.persist(entity);
        session.getTransaction().commit();
        DBConnect.closeSession();
    }

    @Override
    public void update(Object entity) {
//        ComputerSwitch computerSwitch = (ComputerSwitch) entity;
//        Session session = DBConnect.getSession();
//        session.beginTransaction();
//        Query query = session.createQuery("UPDATE ComputerSwitch set switchName=: name, socket=:socket where id=:id");
//        query.setParameter("name", computerSwitch.getSwitchName());
//        query.setParameter("socket",computerSwitch.getSocket());
//        query.setParameter("id",computerSwitch.getId());
//        query.executeUpdate();
//        session.getTransaction().commit();
//        DBConnect.closeSession();

    }

    @Override
    public void delete(int i) {
        Session session = DBConnect.getSession();
        session.beginTransaction();
        Query query = session.createQuery("delete from Product where id= :id");
        query.setParameter("id",i).executeUpdate();
        session.getTransaction().commit();
        DBConnect.closeSession();
    }

    @Override
    public Object findByYd(int id) {
        Session session = DBConnect.getSession();
        session.beginTransaction();
        Query query = session.createQuery(" from Product where id=:id");
        query.setParameter("id",id).executeUpdate();
        session.getTransaction().commit();
        DBConnect.closeSession();
        return query.list().get(0);
    }

    @Override
    public Object findByString(String string) {
        return null;
    }

    @Override
    public List<Product> getList() {
        Session session = DBConnect.getSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Product ");
        session.getTransaction().commit();
        List<Product> list = query.list();
        DBConnect.closeSession();
        return list;
    }

}
