package Dao.policemanDao;

import Dao.DBConnect;
import interfaces.DatabaseDao;
import org.hibernate.Session;
import org.hibernate.query.Query;
import policeman.Worker;

import java.util.List;

public class WorkerDao implements DatabaseDao {
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
        Worker worker = (Worker) entity;
        Session session = DBConnect.getSession();
        session.beginTransaction();
        Query query = session.createQuery("UPDATE Worker  set name=:name, surrname=:surrname, pesel=:pesel, ewidential=:ewidential where id=:id");
        query.setParameter("name", worker.getName());
        query.setParameter("surrname",worker.getSurrname());
        query.setParameter("pesel", worker.getPesel());
        query.setParameter("ewidential", worker.getEwidential());
        query.setParameter("id",worker.getId());
        query.executeUpdate();
        session.getTransaction().commit();
        DBConnect.closeSession();
    }

    @Override
    public void delete(int i) {
        Session session = DBConnect.getSession();
        session.beginTransaction();
        Query query = session.createQuery("delete from Worker where id= :id");
        query.setParameter("id",i).executeUpdate();
        session.getTransaction().commit();
        DBConnect.closeSession();
    }

    @Override
    public Object findByYd(int id) {
        Session session = DBConnect.getSession();
        session.beginTransaction();
        Query query = session.createQuery(" from Worker where id=:id");
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
    public List<Worker> getList() {
        Session session = DBConnect.getSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Worker worker WHERE worker.isActiv=true");
        session.getTransaction().commit();
        List<Worker> list = query.list();
        DBConnect.closeSession();
        return list;
    }

    public List<Worker> getListInActiv() {
        Session session = DBConnect.getSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Worker worker WHERE worker.isActiv=false");
        session.getTransaction().commit();
        List<Worker> list = query.list();
        DBConnect.closeSession();
        return list;
    }
}
