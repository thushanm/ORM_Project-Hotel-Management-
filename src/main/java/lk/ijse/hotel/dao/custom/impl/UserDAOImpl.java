package lk.ijse.hotel.dao.custom.impl;

import lk.ijse.hotel.dao.SuperDAO;
import lk.ijse.hotel.dao.custom.UserDAO;
import lk.ijse.hotel.entity.User;
import org.hibernate.Session;

import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public User save(Session session, User entity) {
        session.save(entity);
        return  entity;
    }

    @Override
    public User update(Session session, User entity) {
        session.update(entity);
        return  entity;
    }

    @Override
    public boolean delete(Session session, String id) {
        final User user = session.get(User.class, id);
        session.delete(user);
        return user!=null;
    }

    @Override
    public List<User> getAll(Session session) {
      return (List<User>) session.createQuery("FROM User").getResultList();

    }

    @Override
    public User search(Session session, String id) {
        return session.get(User.class, id);
    }

    @Override
    public String generateID(Session session) {
        final List<User> list = session.createQuery("FROM User ORDER BY userID DESC").list();
        return list.size()<=0 ? null:list.get(0).getUserID();
    }
}
