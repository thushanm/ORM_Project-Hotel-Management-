package lk.ijse.hotel.tm;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class RoomTM {
    String roomID;
    String type;
    String key_money;
    int qty;
    HBox btnBox;


    public RoomTM(String roomID, String type, String key_money, int qty) {
        this.roomID = roomID;
        this.type = type;
        this.key_money = key_money;
        this.qty = qty;
    }

    @ToString.Exclude
    List<ReservationTM> reservationTMList;




}
