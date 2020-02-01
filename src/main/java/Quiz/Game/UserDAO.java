package Quiz.Game;

import Quiz.entity.User;
import org.hibernate.Session;

public class UserDAO {

    public void insert(User newUser, String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        newUser.setName(name);
        session.beginTransaction();
        session.persist(newUser);
        session.flush();
        session.close();
    }

    public User select(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        User user = session.find(User.class, id);
        session.flush();
        session.close();
        return user;

    }

    public User update(User userToUpdate) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        userToUpdate = (User) session.merge(userToUpdate);
        session.flush();
        session.close();
        return userToUpdate;
    }

    public void delete(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        User userToDelete = new User();
        userToDelete.setId(id);
        session.delete(session.contains(userToDelete) ? userToDelete : session.merge(userToDelete));
        session.flush();
        session.close();
    }
}


