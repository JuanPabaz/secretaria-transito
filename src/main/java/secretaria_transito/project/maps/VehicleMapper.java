package secretaria_transito.project.maps;

import org.springframework.stereotype.Component;
import secretaria_transito.project.dto.VehicleResponseDTO;
import secretaria_transito.project.entities.Vehicle;

import java.util.List;

@Component
public class VehicleMapper {

    public VehicleResponseDTO mapVehicle(Vehicle vehicle) {
        if (vehicle == null) {
            return null;
        }
        return VehicleResponseDTO.builder()
                .vehicleId(vehicle.getVehicleId())
                .vehicleType(vehicle.getVehicleType())
                .licensePlate(vehicle.getLicensePlate())
                .brand(vehicle.getBrand())
                .userId(vehicle.getUser().getIdUser())
                .build();
    }

    public List<VehicleResponseDTO> mapVehicleList(List<Vehicle> vehicleList) {
        return vehicleList.stream()
                .map(this::mapVehicle)
                .toList();
    }

}
