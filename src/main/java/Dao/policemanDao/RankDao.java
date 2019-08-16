package Dao.policemanDao;

import Dao.DBConnect;
import interfaces.DatabaseDao;
import org.hibernate.Session;
import org.hibernate.query.Query;
import policeman.Rank;

import java.util.List;

public class RankDao implements DatabaseDao {
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
        Query query = session.createQuery("delete from Rank where id= :id");
        query.setParameter("id",i).executeUpdate();
        session.getTransaction().commit();
        DBConnect.closeSession();
    }

    @Override
    public Object findByYd(int id) {
        Session session = DBConnect.getSession();
        session.beginTransaction();
        Query query = session.createQuery(" from Rank where id=:id");
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
    public List<Rank> getList() {
        Session session = DBConnect.getSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Department ");
        session.getTransaction().commit();
        List<Rank> list = query.list();
        DBConnect.closeSession();
        return list;
    }

}
