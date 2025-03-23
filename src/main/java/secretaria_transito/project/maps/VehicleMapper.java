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
                .brand(vehicle.getRegistration().getBrand())
                .licensePlate(vehicle.getRegistration().getLicensePlate())
                .registrationDate(vehicle.getRegistration().getRegistrationDate())
                .vehicleType(vehicle.getVehicleType())
                .build();
    }

    public List<VehicleResponseDTO> mapVehicleList(List<Vehicle> vehicleList) {
        return vehicleList.stream()
                .map(this::mapVehicle)
                .toList();
    }

}
