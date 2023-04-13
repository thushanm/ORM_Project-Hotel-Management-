package lk.ijse.hotel.dao;

import lk.ijse.hotel.entity.SuperEntity;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;

public interface CrudDAO<T extends SuperEntity,ID extends Serializable> extends SuperDAO{
    T save(Session session,T entity);
    T update(Session session,T entity);
    boolean delete(Session session,ID id);
    List<T> getAll(Session session);
    T search(Session session,ID id);
    ID generateID(Session session);
}
