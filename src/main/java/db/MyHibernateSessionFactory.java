package db;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MyHibernateSessionFactory {
    private static SessionFactory sessionFactory;
    private MyHibernateSessionFactory(){
    }
    public static SessionFactory getSessionFactory() {
        if(sessionFactory==null){
            sessionFactory = new Configuration().
                    configure("hibernate.cfg.xml").
                    buildSessionFactory();
        }
        return sessionFactory;
    }
}

