package Dao.buildingDao;

import Dao.DBConnect;
import building.Building;
import interfaces.DatabaseDao;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;


public class BuildingDao implements DatabaseDao {
    @Override
    public void save(Object entity) {
        Session session = DBConnect.getSession();
        session.beginTransaction();
        session.save(entity);
        session.getTransaction().commit();
        DBConnect.closeSession();
    }

    @Override
    public void update(Object entity) {
        Building buildingObject = (Building) entity;
        Session session = DBConnect.getSession();
        session.beginTransaction();
        Query query = session.createQuery("update Building set name=:name where id= :id");
        query.setParameter("name", buildingObject.getName());
        query.setParameter("id", buildingObject.getId());
        query.executeUpdate();
        session.getTransaction().commit();
        DBConnect.closeSession();
    }

    @Override
    public void delete(int id) {
        Session session = DBConnect.getSession();
        session.beginTransaction();
        Query query = session.createQuery("delete from Building where id= :id");
        query.setParameter("id", id).executeUpdate();
        session.getTransaction().commit();
        DBConnect.closeSession();
    }

    @Override
    public Object findByYd(int id) {
        Session session = DBConnect.getSession();
        session.beginTransaction();
        Query query = session.createQuery("from Building where id=:id");
        query.setParameter("id", id).executeUpdate();
        session.getTransaction().commit();
        DBConnect.closeSession();
        return query.list().get(0);
    }

    @Override
    public Object findByString(String string) {
        return null;
    }

    @Override
    public List<Building> getList() {
        Session session = DBConnect.getSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Building ");
        session.getTransaction().commit();
        List<Building> list = query.list();
        DBConnect.closeSession();
        return list;
    }
}
