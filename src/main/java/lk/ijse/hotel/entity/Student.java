package lk.ijse.hotel.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
public class Student implements SuperEntity {
    @Id
    String studentID;
    String name;
    String address;
    String tel;
    Date dob;
    String gender;
    @ToString.Exclude
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "student", targetEntity = Reservation.class, cascade = CascadeType.ALL)
    List<Reservation> reservationList;

}
