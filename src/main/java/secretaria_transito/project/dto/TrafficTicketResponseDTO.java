package secretaria_transito.project.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import secretaria_transito.project.entities.Vehicle;

import java.util.Date;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class TrafficTicketResponseDTO {

    private Long trafficTicketId;
    private Vehicle vehicle;
    private Date date;
    private String description;
    private Long trafficAgentId;
    private Integer detectionCameraId;
}
