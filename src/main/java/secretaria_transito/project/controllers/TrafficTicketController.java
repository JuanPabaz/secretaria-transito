package secretaria_transito.project.controllers;

import org.springframework.web.bind.annotation.*;
import secretaria_transito.project.dto.TrafficTicketResponseDTO;
import secretaria_transito.project.services.TrafficTicketService;

import java.util.List;
import java.util.Map;

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

    @GetMapping("/report")
    public Map<String, String> generateTrafficTicketInvoice(@RequestParam Long idVehicle,
                                                            @RequestParam Long trafficTicketId,
                                                            @RequestParam Integer userId) {
        return trafficTicketService.generateInvoicePdf(userId, idVehicle, trafficTicketId);
    }

}
