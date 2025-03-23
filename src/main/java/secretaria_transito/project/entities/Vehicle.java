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

    @Column(name = "vehicle_type")
    private VehicleType vehicleType;

    @OneToOne
    @JoinColumn(name = "registration_id")
    private Registration registration;

    @OneToMany(targetEntity = TrafficTicket.class, fetch = FetchType.LAZY, mappedBy = "vehicle")
    private List<TrafficTicket> trafficTickets;

}
