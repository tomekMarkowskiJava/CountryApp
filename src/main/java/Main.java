import Quiz.Game.HibernateUtil;
import Quiz.entity.User;
import org.hibernate.Session;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        User user = new User("Tomek");
        session.persist(user);
        session.flush();
        session.close();
        //Quiz quiz = new Quiz();

        //quiz.start();
    }
}
