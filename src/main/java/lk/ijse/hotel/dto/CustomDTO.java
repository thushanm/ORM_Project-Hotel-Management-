package lk.ijse.hotel.dto;

import lk.ijse.hotel.entity.Room;
import lk.ijse.hotel.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class CustomDTO {
    String resID;
    Date date;
    Student student;
    Room room;
}
