package lk.ijse.hotel.bo.custom;

import lk.ijse.hotel.bo.SuperBO;
import lk.ijse.hotel.dto.RoomDTO;
import org.hibernate.Session;

import java.util.List;

public interface RoomBO extends SuperBO {
    RoomDTO saveRoom(RoomDTO roomDTO);
    RoomDTO updateRoom(RoomDTO roomDTO);
    boolean deleteRoom(String id);
    List<RoomDTO> getAllRoom();
    RoomDTO searchRoom(String id);
    String generateIDRoom();
}
