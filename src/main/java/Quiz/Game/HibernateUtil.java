package Quiz.Game;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory(){
        return new Configuration().configure().buildSessionFactory();
    }

    static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
}
