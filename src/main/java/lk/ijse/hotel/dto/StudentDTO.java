package lk.ijse.hotel.dto;

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

public class StudentDTO {
    String studentID;
    String name;
    String address;
    String tel;
    Date dob;
    String gender;
    @ToString.Exclude
    List<ReservationDTO> reservationDTOList;

    public StudentDTO(String studentID, String name, String address, String tel, Date dob, String gender) {
        this.studentID = studentID;
        this.name = name;
        this.address = address;
        this.tel = tel;
        this.dob = dob;
        this.gender = gender;
    }


}
