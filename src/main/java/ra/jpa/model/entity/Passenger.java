package ra.jpa.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Passenger {
    @Id
    @Column(name="passenger_id")
    private String id;
    @Column(name="passenger_full_name")
    private String name;
    @Column(name="passenger_email")
    private String email;
    @Column(name="passenger_phone")
    private String phone;
    @Column(name="passenger_bod")
    private LocalDate birthDay;
    @Column(name="passenger_gender")
    @Enumerated(EnumType.STRING)
    private PassengerGender gender;

}
