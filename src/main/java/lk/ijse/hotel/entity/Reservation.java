package lk.ijse.hotel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
public class Reservation implements SuperEntity {
    @Id
    String resID;
    Date date;
    String status;
    @ManyToOne
    @JoinColumn(name = "studentID")
    Student student;
    @ManyToOne
    @JoinColumn(name = "roomID")
    Room room;
}
