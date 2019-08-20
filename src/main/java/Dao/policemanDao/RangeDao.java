package Dao.policemanDao;

import Dao.DBConnect;
import interfaces.DatabaseDao;
import org.hibernate.Session;
import org.hibernate.query.Query;
import policeman.Range;

import java.util.List;

public class RangeDao implements DatabaseDao {
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
        Range range = (Range) entity;
        Session session = DBConnect.getSession();
        session.beginTransaction();
        Query query = session.createQuery("UPDATE Range  set rangeName=:range where id=:id");
        query.setParameter("range",range.getRangeName());
        query.setParameter("id",range.getId());
        query.executeUpdate();
        session.getTransaction().commit();
        DBConnect.closeSession();
    }

    @Override
    public void delete(int i) {
        Session session = DBConnect.getSession();
        session.beginTransaction();
        Query query = session.createQuery("delete from Range where id= :id");
        query.setParameter("id",i).executeUpdate();
        session.getTransaction().commit();
        DBConnect.closeSession();
    }

    @Override
    public Object findByYd(int id) {
        Session session = DBConnect.getSession();
        session.beginTransaction();
        Query query = session.createQuery(" from Range where id=:id");
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
    public List<Range> getList() {
        Session session = DBConnect.getSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Range");
        session.getTransaction().commit();
        List<Range> list = query.list();
        DBConnect.closeSession();
        return list;
    }
}
