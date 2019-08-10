package worksToDo;

import interfaces.DatabaseDao;
import org.hibernate.Session;
import Dao.DBConnect;
import org.hibernate.query.Query;

import java.util.List;

public class WorksDao implements DatabaseDao {

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
        Query query = session.createQuery("delete from Works where id= :id");
        query.setParameter("id",id).executeUpdate();
        session.getTransaction().commit();
        DBConnect.closeSession();
    }

    @Override
    public Object findByYd(int id) {
        Session session = DBConnect.getSession();
        session.beginTransaction();
        Query query = session.createQuery(" from Works where id=:id");
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
        Query query = session.createQuery("FROM Works ");
        session.getTransaction().commit();
        List<Works> list = query.list();
        DBConnect.closeSession();
        return list;
    }

    public void setWorkDoing(int id){
        Session session = DBConnect.getSession();
        session.beginTransaction();
        Query query = session.createQuery("update Works SET isDone=true where id=:id");
        query.setParameter("id",id).executeUpdate();
        session.getTransaction().commit();
        DBConnect.closeSession();
    }
}
