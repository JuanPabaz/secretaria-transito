package secretaria_transito.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import secretaria_transito.project.entities.TrafficTicket;

import java.util.List;

public interface TrafficTicketRepository extends JpaRepository<TrafficTicket, Long> {

    List<TrafficTicket> findTrafficTicketByVehicleVehicleId(Long vehicleId);
}
