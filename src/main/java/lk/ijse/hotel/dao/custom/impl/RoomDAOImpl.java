package lk.ijse.hotel.dao.custom.impl;

import lk.ijse.hotel.dao.custom.RoomDAO;
import lk.ijse.hotel.entity.Room;
import org.hibernate.Session;

import java.util.List;

public class RoomDAOImpl implements RoomDAO {
    @Override
    public Room save(Session session, Room entity) {
        session.save(entity);
        return entity;
    }

    @Override
    public Room update(Session session, Room entity) {
        session.update(entity);
        return entity;
    }

    @Override
    public boolean delete(Session session, String id) {
        final Room room = session.get(Room.class, id);
        session.delete(room);
        return room!=null;
    }

    @Override
    public List<Room> getAll(Session session) {
        return (List<Room>) session.createQuery("FROM Room").getResultList();
    }

    @Override
    public Room search(Session session, String id) {
        return session.get(Room.class,id);
    }

    @Override
    public String generateID(Session session) {
final List<Room> list = session.createQuery("FROM Room ORDER BY roomID DESC").list();
        return list.size()==0 ? null:list.get(0).getRoomID();
    }
}
