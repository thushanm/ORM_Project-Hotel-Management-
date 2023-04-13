package lk.ijse.hotel.bo.custom;

import lk.ijse.hotel.bo.SuperBO;
import lk.ijse.hotel.dto.UserDTO;
import org.hibernate.Session;

import java.util.List;

public interface UserBO extends SuperBO {
    UserDTO saveUser(UserDTO userDTO);
    UserDTO updateUser(UserDTO userDTO);
    boolean deleteUser(String id);
    List<UserDTO> getAllUser();
    UserDTO searchUser(String id);
    String generateIDUser();
}
