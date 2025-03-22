package secretaria_transito.project.maps;

import org.springframework.stereotype.Component;
import secretaria_transito.project.dto.TrafficTicketResponseDTO;
import secretaria_transito.project.entities.TrafficTicket;

import java.util.List;

@Component
public class TrafficTicketMapper {

    public TrafficTicketResponseDTO mapTrafficTicket(TrafficTicket trafficTicket) {
        if (trafficTicket == null) {
            return null;
        }
        return TrafficTicketResponseDTO.builder()
                .trafficTicketId(trafficTicket.getTrafficTicketId())
                .date(trafficTicket.getDate())
                .trafficAgentId(trafficTicket.getTrafficAgent().getTrafficAgentId())
                .detectionCameraId(trafficTicket.getDetectionCamera().getCameraId())
                .price(trafficTicket.getPrice())
                .description(trafficTicket.getDescription())
                .build();
    }

    public List<TrafficTicketResponseDTO> mapTrafficTicketList(List<TrafficTicket> trafficTicketList) {
        return trafficTicketList.stream()
                .map(this::mapTrafficTicket)
                .toList();
    }
}
