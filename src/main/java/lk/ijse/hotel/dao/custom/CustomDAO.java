package lk.ijse.hotel.dao.custom;

import lk.ijse.hotel.dao.SuperDAO;
import lk.ijse.hotel.entity.Custom;
import org.hibernate.Session;

import java.util.List;

public interface CustomDAO extends SuperDAO {
   List<Custom> getAllReservationDetails(Session session);

}
