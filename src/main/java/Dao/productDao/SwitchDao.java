package Dao.productDao;

import Dao.DBConnect;
import interfaces.DatabaseDao;
import org.hibernate.Session;
import org.hibernate.query.Query;
import product.ComputerSwitch;
import product.Product;

import java.util.List;

public class SwitchDao implements DatabaseDao {
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
        ComputerSwitch computerSwitch = (ComputerSwitch) entity;
        Session session = DBConnect.getSession();
        session.beginTransaction();
        Query query = session.createQuery("UPDATE ComputerSwitch set switchName=: name, socket=:socket where id=:id");
        query.setParameter("name", computerSwitch.getSwitchName());
        query.setParameter("socket",computerSwitch.getSocket());
        query.setParameter("id",computerSwitch.getId());
        query.executeUpdate();
        session.getTransaction().commit();
        DBConnect.closeSession();

    }

    @Override
    public void delete(int i) {
        Session session = DBConnect.getSession();
        session.beginTransaction();
        Query query = session.createQuery("delete from ComputerSwitch where id= :id");
        query.setParameter("id",i).executeUpdate();
        session.getTransaction().commit();
        DBConnect.closeSession();
    }

    @Override
    public Object findByYd(int id) {
        Session session = DBConnect.getSession();
        session.beginTransaction();
        Query query = session.createQuery(" from ComputerSwitch where id=:id");
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
    public List<ComputerSwitch> getList() {
        Session session = DBConnect.getSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM ComputerSwitch ");
        session.getTransaction().commit();
        List<ComputerSwitch> list = query.list();
        DBConnect.closeSession();
        return list;
    }

    public void saveproductOnSocket(Product product, ComputerSwitch computerSwitch){
        Session session = DBConnect.getSession();
        session.beginTransaction();
        Query query = session.createQuery("UPDATE ComputerSwitch set productOnSocket=:product where id=:id");
        query.setParameter("product", product);
        query.setParameter("id", computerSwitch.getId());
        query.executeUpdate();
        session.getTransaction().commit();
        DBConnect.closeSession();
    }
}
