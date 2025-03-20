package secretaria_transito.project.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "traffic_ticker")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TrafficTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "traffic_ticket_id")
    private Long trafficTicketId;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @Column(name = "date")
    private Date date;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "traffic_agent_id")
    private TrafficAgent trafficAgent;

    @ManyToOne
    @JoinColumn(name = "detection_camera_id")
    private DetectionCamera detectionCamera;

}
