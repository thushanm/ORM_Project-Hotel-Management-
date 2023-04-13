package lk.ijse.hotel.bo.custom;

import lk.ijse.hotel.bo.SuperBO;
import lk.ijse.hotel.dto.CustomDTO;
import lk.ijse.hotel.entity.Custom;
import org.hibernate.Session;

import java.util.List;

public interface CustomBO extends SuperBO {
    List<CustomDTO> getAllReservationDetails();
}
