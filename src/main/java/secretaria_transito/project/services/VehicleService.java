package secretaria_transito.project.services;

import org.springframework.stereotype.Service;
import secretaria_transito.project.dto.VehicleRequestDTO;
import secretaria_transito.project.dto.VehicleResponseDTO;
import secretaria_transito.project.entities.User;
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
    private final UserService userService;

    public VehicleService(VehicleRepository vehicleRepository, VehicleMapper vehicleMapper, UserService userService) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleMapper = vehicleMapper;
        this.userService = userService;
    }

    public List<VehicleResponseDTO> getVehicleByOwnerOwnerId(Integer ownerId) {
        return vehicleMapper.mapVehicleList(vehicleRepository.findVehicleByUserIdUser(ownerId));
    }

    public VehicleResponseDTO saveVehicle(VehicleRequestDTO vehicleRequestDTO) {
        Optional<User> userOptional = userService.findUserById(vehicleRequestDTO.getUserId());
        if (userOptional.isEmpty()) {
            throw new BadCreateRequest("No se encontró el propietario.");
        }
        User user = userOptional.get();
        Vehicle vehicle = Vehicle.builder()
                .vehicleType(vehicleRequestDTO.getVehicleType())
                .brand(vehicleRequestDTO.getBrand())
                .licensePlate(vehicleRequestDTO.getLicensePlate())
                .user(user)
                .build();

        return vehicleMapper.mapVehicle(vehicleRepository.save(vehicle));
    }
}
