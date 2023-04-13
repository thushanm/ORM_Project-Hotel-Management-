package lk.ijse.hotel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class RoomDTO {
    String roomID;
    String type;
    String key_money;
    int qty;
    @ToString.Exclude
    List<ReservationDTO> reservationDTOList;

    public RoomDTO(String roomID, String type, String key_money, int qty) {
        this.roomID = roomID;
        this.type = type;
        this.key_money = key_money;
        this.qty = qty;
    }


}
