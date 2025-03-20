package secretaria_transito.project.services;

import org.springframework.stereotype.Service;
import secretaria_transito.project.dto.TrafficTicketResponseDTO;
import secretaria_transito.project.entities.Vehicle;
import secretaria_transito.project.exceptions.BadCreateRequest;
import secretaria_transito.project.maps.TrafficTicketMapper;
import secretaria_transito.project.repositories.TrafficTicketRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TrafficTicketService {

    private final TrafficTicketRepository trafficTicketRepository;
    private final VehicleService vehicleService;
    private final TrafficTicketMapper trafficTicketMapper;

    public TrafficTicketService(TrafficTicketRepository trafficTicketRepository, VehicleService vehicleService, TrafficTicketMapper trafficTicketMapper) {
        this.trafficTicketRepository = trafficTicketRepository;
        this.vehicleService = vehicleService;
        this.trafficTicketMapper = trafficTicketMapper;
    }

    public List<TrafficTicketResponseDTO> getTrafficTicketByVehicleId(Long vehicleId) {
        Optional<Vehicle> vehicleOptional = vehicleService.getVehicleByVehicleId(vehicleId);
        if (vehicleOptional.isEmpty()) {
            throw new BadCreateRequest("El vehiculo no existe.");
        }
        Vehicle vehicle = vehicleOptional.get();
        return trafficTicketMapper.mapTrafficTicketList(trafficTicketRepository.findTrafficTicketByVehicleVehicleId(vehicleId));
    }
}
