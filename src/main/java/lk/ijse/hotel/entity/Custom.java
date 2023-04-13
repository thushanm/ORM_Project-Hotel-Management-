package lk.ijse.hotel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Custom implements SuperEntity{
    String resID;
    Date date;
    Student student;
    Room room;
}
