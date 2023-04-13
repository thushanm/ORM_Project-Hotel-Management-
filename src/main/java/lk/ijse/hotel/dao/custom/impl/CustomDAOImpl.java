package lk.ijse.hotel.dao.custom.impl;

import lk.ijse.hotel.dao.custom.CustomDAO;
import lk.ijse.hotel.entity.Custom;
import org.hibernate.Session;

import java.util.List;

public class CustomDAOImpl implements CustomDAO {

    @Override
    public List<Custom> getAllReservationDetails(Session session) {
            session.createQuery("");
            return null;
    }
}
