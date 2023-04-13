package lk.ijse.hotel.bo.util;


import lk.ijse.hotel.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private SessionFactory sessionFactory;
    private FactoryConfiguration(){
        Properties properties=new Properties();
        try {
            properties.load(ClassLoader.getSystemClassLoader().getResourceAsStream("Hibernate.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Configuration configuration=new Configuration().
                addAnnotatedClass(User.class).
                addAnnotatedClass(Room.class).
                addAnnotatedClass(Student.class).addAnnotatedClass(Reservation.class);
        configuration.addProperties(properties);
        sessionFactory=configuration.buildSessionFactory();
    }
    public static FactoryConfiguration getInstance(){
        return factoryConfiguration==null ? factoryConfiguration=new FactoryConfiguration():factoryConfiguration;
    }
    public Session getSession(){
     return sessionFactory.openSession();
    }

}
