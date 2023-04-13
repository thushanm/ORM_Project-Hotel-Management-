package lk.ijse.hotel.tm;

import javafx.scene.layout.HBox;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class StudentTM {
    String studentID;
    String name;
    String address;
    String tel;
    Date dob;
    String gender;
    HBox btnBox;
    @ToString.Exclude
    List<ReservationTM> reservationTMList;

}
