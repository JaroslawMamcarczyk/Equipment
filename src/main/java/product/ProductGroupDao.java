package product;

import Dao.DBConnect;
import building.Room;
import interfaces.DatabaseDao;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class ProductGroupDao implements DatabaseDao {

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

    }

    @Override
    public void delete(int id) {
        Session session = DBConnect.getSession();
        session.beginTransaction();
        Query query = session.createQuery("delete from ProductGroup where id= :id");
        query.setParameter("id",id).executeUpdate();
        session.getTransaction().commit();
        DBConnect.closeSession();
    }

    @Override
    public Object findByYd(int id) {
        Session session = DBConnect.getSession();
        session.beginTransaction();
        Query query = session.createQuery(" from ProductGroup  where id=:id");
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
    public List getList() {
        Session session = DBConnect.getSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM ProductGroup ");
        session.getTransaction().commit();
        List<Room> list = query.list();
        DBConnect.closeSession();
        return list;
    }
}
