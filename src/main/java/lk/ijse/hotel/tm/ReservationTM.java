package lk.ijse.hotel.tm;

import javafx.scene.layout.HBox;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class ReservationTM {

    String resID;
    Date date;
    String status;
    String studentID;
    String studentName;
    String roomID;
    String roomType;
    String key_money;
    HBox btnBox;
    public ReservationTM(String resID, Date date, String status, String studentID, String studentName, String roomID, String roomType, String key_money) {
        this.resID = resID;
        this.date = date;
        this.status = status;
        this.studentID = studentID;
        this.studentName = studentName;
        this.roomID = roomID;
        this.roomType = roomType;
        this.key_money = key_money;
    }


}
