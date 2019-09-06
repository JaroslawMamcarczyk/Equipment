package Dao.productDao;

import Dao.DBConnect;
import building.Room;
import interfaces.DatabaseDao;
import org.hibernate.Session;
import org.hibernate.query.Query;
import product.Product;

import java.util.List;

public class ProductDao implements DatabaseDao {
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

    public void updatePosition(Product product){
        Session session = DBConnect.getSession();
        session.beginTransaction();
        Query query = session.createQuery("UPDATE Product set positionX=: posX, PositionY=:posY where id=:id");
        query.setParameter("posX", product.getPositionX());
        query.setParameter("posY",product.getPositionY());
        query.setParameter("id",product.getProductId());
        query.executeUpdate();
        session.getTransaction().commit();
        DBConnect.closeSession();
    }

    public void updateBuilding(Product product, Room room){
        Session session = DBConnect.getSession();
        session.beginTransaction();
        Query query = session.createQuery("UPDATE Product set positionX=: posX, PositionY=:posY, roomNumber=:room, department=:department  where id=:id");
        query.setParameter("posX", product.getPositionX());
        query.setParameter("posY",product.getPositionY());
        query.setParameter("room",room);
        query.setParameter("department", room.getDepartment());
        query.setParameter("id",product.getProductId());
        query.executeUpdate();
        session.getTransaction().commit();
        DBConnect.closeSession();
    }

    @Override
    public void delete(int i) {
        Session session = DBConnect.getSession();
        session.beginTransaction();
        Query query = session.createQuery("delete from Product where id= :id");
        query.setParameter("id",i).executeUpdate();
        session.getTransaction().commit();
        DBConnect.closeSession();
    }

    @Override
    public Object findByYd(int id) {
        Session session = DBConnect.getSession();
        session.beginTransaction();
        Query query = session.createQuery(" from Product where id=:id");
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
    public List<Product> getList() {
        Session session = DBConnect.getSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Product ");
        session.getTransaction().commit();
        List<Product> list = query.list();
        DBConnect.closeSession();
        return list;
    }

    public List<Product> getListFromRoom(Room room){
        Session session = DBConnect.getSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Product p where p.roomNumber =:room");
        query.setParameter("room",room);
        List<Product> list = query.list();
        DBConnect.closeSession();
        return list;
    }
public List<Product> getListWhenRoomIsNull(){
    Session session = DBConnect.getSession();
    session.beginTransaction();
    Query query = session.createQuery("FROM Product p where p.roomNumber =null");
    List<Product> list = query.list();
    DBConnect.closeSession();
    return list;
}
}
