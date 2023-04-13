package lk.ijse.hotel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
public class Room implements SuperEntity {
    public Room(String roomID, String type, String key_money, int qty) {
        this.roomID = roomID;
        this.type = type;
        this.key_money = key_money;
        this.qty = qty;
    }

    @Id
    String roomID;
    String type;
    String key_money;
    int qty;
    @ToString.Exclude
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "room",orphanRemoval = true,cascade = {CascadeType.ALL})
    List<Reservation> reservationList;

}
