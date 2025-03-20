package secretaria_transito.project.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import secretaria_transito.project.dto.TrafficTicketResponseDTO;
import secretaria_transito.project.services.TrafficTicketService;

import java.util.List;

@RestController
@RequestMapping("/traffic-ticket")
public class TrafficTicketController {

    private final TrafficTicketService trafficTicketService;

    public TrafficTicketController(TrafficTicketService trafficTicketService) {
        this.trafficTicketService = trafficTicketService;
    }

    @GetMapping("/{idVehicle}")
    public List<TrafficTicketResponseDTO> getTrafficTicket(@PathVariable Long idVehicle) {
        return trafficTicketService.getTrafficTicketByVehicleId(idVehicle);
    }

}
