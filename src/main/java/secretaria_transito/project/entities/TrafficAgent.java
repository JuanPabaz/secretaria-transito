package secretaria_transito.project.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "traffic_agent")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TrafficAgent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "traffic_agent_id")
    private Long trafficAgentId;

    @Column(name = "name")
    private String name;

    @Column(name = "idNumber")
    private String idNumber;

    @OneToMany(targetEntity = TrafficTicket.class, fetch = FetchType.LAZY, mappedBy = "trafficAgent")
    private List<TrafficTicket> trafficTickets;

}
