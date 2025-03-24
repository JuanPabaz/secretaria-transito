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

        TrafficTicketResponseDTO trafficTicketResponseDTO = new TrafficTicketResponseDTO();
        trafficTicketResponseDTO.setTrafficTicketId(trafficTicket.getTrafficTicketId());
        trafficTicketResponseDTO.setDate(trafficTicket.getDate());
        trafficTicketResponseDTO.setTrafficAgentId(
                (trafficTicket.getTrafficAgent() != null && trafficTicket.getTrafficAgent().getTrafficAgentId() != null)
                        ? trafficTicket.getTrafficAgent().getTrafficAgentId()
                        : 0L
        );
        trafficTicketResponseDTO.setDescription(trafficTicket.getDescription());
        trafficTicketResponseDTO.setVehicleId(trafficTicket.getVehicle().getVehicleId());
        trafficTicketResponseDTO.setDetectionCameraId(
                (trafficTicket.getDetectionCamera() != null && trafficTicket.getDetectionCamera().getCameraId() != null)
                        ? trafficTicket.getDetectionCamera().getCameraId()
                        : 0
        );
        trafficTicketResponseDTO.setPrice(trafficTicket.getPrice());

        return trafficTicketResponseDTO;
    }

    public List<TrafficTicketResponseDTO> mapTrafficTicketList(List<TrafficTicket> trafficTicketList) {
        return trafficTicketList.stream()
                .map(this::mapTrafficTicket)
                .toList();
    }
}
