package Dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class DBConnect {
    private static SessionFactory sessionFactory;

    public static Session getSession(){
        StandardServiceRegistry sr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(sr).getMetadataBuilder().build();
        sessionFactory=metadata.getSessionFactoryBuilder().build();
        return sessionFactory.openSession();
    }

    public static void closeSession(){
        sessionFactory.close();
    }
}
