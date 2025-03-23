package secretaria_transito.project.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "registration")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "registration_id")
    private Long registrationId;

    @Column(name = "license_plate")
    private String licensePlate;

    @Column(name = "brand")
    private String brand;

    @Column(name = "registration_date")
    private Date registrationDate;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

}
