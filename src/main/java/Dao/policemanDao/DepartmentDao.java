package Dao.policemanDao;

import Dao.DBConnect;
import interfaces.DatabaseDao;
import org.hibernate.Session;
import org.hibernate.query.Query;
import policeman.Department;

import java.util.List;

public class DepartmentDao implements DatabaseDao {
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
    Department department = (Department) entity;
        Session session = DBConnect.getSession();
        session.beginTransaction();
        Query query = session.createQuery("UPDATE Department  set departmentName=: name, departmentShort=:short where id=:id");
        query.setParameter("name", department.getDepartmentName());
        query.setParameter("short", department.getDepartmentShort());
        query.setParameter("id",department.getId());
        query.executeUpdate();
        session.getTransaction().commit();
        DBConnect.closeSession();
    }

    @Override
    public void delete(int i) {
        Session session = DBConnect.getSession();
        session.beginTransaction();
        Query query = session.createQuery("delete from Department where id= :id");
        query.setParameter("id",i).executeUpdate();
        session.getTransaction().commit();
        DBConnect.closeSession();
    }

    @Override
    public Object findByYd(int id) {
        Session session = DBConnect.getSession();
        session.beginTransaction();
        Query query = session.createQuery(" from Department where id=:id");
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
    public List<Department> getList() {
        Session session = DBConnect.getSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Department ");
        session.getTransaction().commit();
        List<Department> list = query.list();
        DBConnect.closeSession();
        return list;
    }

}
