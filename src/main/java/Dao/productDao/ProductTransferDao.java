package Dao.productDao;

import Dao.DBConnect;
import interfaces.DatabaseDao;
import org.hibernate.Session;
import org.hibernate.query.Query;
import product.ProductTransfer;

import java.time.Year;
import java.util.List;

public class ProductTransferDao implements DatabaseDao {
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
        Query query = session.createQuery("delete from ProductTransfer where id= :id");
        query.setParameter("id",i).executeUpdate();
        session.getTransaction().commit();
        DBConnect.closeSession();
    }

    @Override
    public Object findByYd(int id) {
        Session session = DBConnect.getSession();
        session.beginTransaction();
        Query query = session.createQuery(" from ProductTransfer where id=:id");
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
    public List<ProductTransfer> getList() {
        Session session = DBConnect.getSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM ProductTransfer ");
        session.getTransaction().commit();
        List<ProductTransfer> list = query.list();
        DBConnect.closeSession();
        return list;
    }

    public List<ProductTransfer> getListFromYear(Year year) {
        Session session = DBConnect.getSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM ProductTransfer productTransfer where productTransfer.year=:year ");
        query.setParameter("year", year);
        session.getTransaction().commit();
        List<ProductTransfer> list = query.list();
        DBConnect.closeSession();
        return list;
    }
}
