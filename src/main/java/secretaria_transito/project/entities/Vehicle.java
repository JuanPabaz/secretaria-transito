package secretaria_transito.project.entities;

import jakarta.persistence.*;
import lombok.*;
import secretaria_transito.project.enums.VehicleType;

import java.util.List;

@Entity
@Table(name = "vehicle")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicle_id")
    private Long vehicleId;

    @Column(name = "license_plate")
    private String licensePlate;

    @Column(name = "brand")
    private String brand;

    @Column(name = "vehicle_type")
    private VehicleType vehicleType;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(targetEntity = TrafficTicket.class, fetch = FetchType.LAZY, mappedBy = "vehicle")
    private List<TrafficTicket> trafficTickets;

}
