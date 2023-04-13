package lk.ijse.hotel.bo.custom;

import lk.ijse.hotel.bo.SuperBO;
import lk.ijse.hotel.dto.ReservationDTO;
import org.hibernate.Session;

import java.util.List;

public interface ReservationBO extends SuperBO {
    ReservationDTO saveReservation( ReservationDTO reservationDTO);
    ReservationDTO updateReservation(ReservationDTO reservationDTO);
    boolean deleteReservation(ReservationDTO reservationDTO);
    List<ReservationDTO> getAllReservation();
    ReservationDTO searchReservation(String id);
    String generateIDReservation();
}
