package secretaria_transito.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import secretaria_transito.project.entities.Vehicle;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle,Long> {

    List<Vehicle> findVehicleByUserIdUser(Integer userId);
}
