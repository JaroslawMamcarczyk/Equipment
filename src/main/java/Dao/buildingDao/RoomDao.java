package Dao.buildingDao;

import Dao.DBConnect;
import building.Building;
import building.Room;
import interfaces.DatabaseDao;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.List;

public class RoomDao implements DatabaseDao {
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
    public void delete(int i) {
        Session session = DBConnect.getSession();
        session.beginTransaction();
        Query query = session.createQuery("delete from Room where id= :id");
        query.setParameter("id",i).executeUpdate();
        session.getTransaction().commit();
        DBConnect.closeSession();
    }

    @Override
    public Object findByYd(int id) {
        Session session = DBConnect.getSession();
        session.beginTransaction();
        Query query = session.createQuery(" from Room where id=:id");
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
    public List<Room> getList() {
        Session session = DBConnect.getSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Room ");
        session.getTransaction().commit();
        List list = query.list();
        DBConnect.closeSession();
        return list;
    }


    public void changePositionX(int x,int id){
        Session session = DBConnect.getSession();
        session.beginTransaction();
        Query query = session.createQuery("update Room set positionX=:positionX WHERE id=:id");
        query.setParameter("positionX",x);
        query.setParameter("id",id);
        query.executeUpdate();
        session.getTransaction().commit();
        DBConnect.closeSession();
    }

    public void changePositionY(int x,int id){
        Session session = DBConnect.getSession();
        session.beginTransaction();
        Query query = session.createQuery("update Room set positionY=:positionX WHERE id=:id");
        query.setParameter("positionX",x);
        query.setParameter("id",id);
        query.executeUpdate();
        session.getTransaction().commit();
        DBConnect.closeSession();
    }

    public List<Room> getListRoomFromBuilding(Building building, Room.Floor floor) {
        Session session = DBConnect.getSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Room room where room.building=:building and room.floor=:floor");
        query.setParameter("building",building);
        query.setParameter("floor",floor );
        session.getTransaction().commit();
        List<Room> list = query.list();
        DBConnect.closeSession();
        return list;
    }
}
