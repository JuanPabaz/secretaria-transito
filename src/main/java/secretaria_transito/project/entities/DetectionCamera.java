package secretaria_transito.project.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "detection_camera")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DetectionCamera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "camera_id")
    private Integer cameraId;

    @Column(name = "location")
    private String location;

    @OneToMany(targetEntity = TrafficTicket.class, fetch = FetchType.LAZY, mappedBy = "detectionCamera")
    private List<TrafficTicket> trafficTickets;
}
