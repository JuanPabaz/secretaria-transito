package secretaria_transito.project.services;

import org.springframework.stereotype.Service;
import secretaria_transito.project.dto.VehicleRequestDTO;
import secretaria_transito.project.dto.VehicleResponseDTO;
import secretaria_transito.project.entities.Owner;
import secretaria_transito.project.entities.Vehicle;
import secretaria_transito.project.exceptions.BadCreateRequest;
import secretaria_transito.project.maps.VehicleMapper;
import secretaria_transito.project.repositories.VehicleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final VehicleMapper vehicleMapper;
    private final OwnerService ownerService;

    public VehicleService(VehicleRepository vehicleRepository, VehicleMapper vehicleMapper, OwnerService ownerService) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleMapper = vehicleMapper;
        this.ownerService = ownerService;
    }

    public List<VehicleResponseDTO> getVehicleByOwnerOwnerId(Long ownerId) {
        return vehicleMapper.mapVehicleList(vehicleRepository.findVehicleByOwnerOwnerId(ownerId));
    }

    public VehicleResponseDTO saveVehicle(VehicleRequestDTO vehicleRequestDTO) {
        Optional<Owner> ownerOptional = ownerService.findOwnerById(vehicleRequestDTO.getOwnerId());
        if (ownerOptional.isEmpty()) {
            throw new BadCreateRequest("No se encontró el propietario.");
        }
        Owner owner = ownerOptional.get();
        Vehicle vehicle = Vehicle.builder()
                .vehicleType(vehicleRequestDTO.getVehicleType())
                .brand(vehicleRequestDTO.getBrand())
                .licensePlate(vehicleRequestDTO.getLicensePlate())
                .owner(ownerOptional.get())
                .build();

        return vehicleMapper.mapVehicle(vehicleRepository.save(vehicle));
    }
}
