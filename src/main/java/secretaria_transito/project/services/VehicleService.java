package secretaria_transito.project.services;

import org.springframework.stereotype.Service;
import secretaria_transito.project.dto.VehicleRequestDTO;
import secretaria_transito.project.dto.VehicleResponseDTO;
import secretaria_transito.project.entities.Registration;
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
    private final RegistrationService registrationService;

    public VehicleService(VehicleRepository vehicleRepository, VehicleMapper vehicleMapper, UserService userService, RegistrationService registrationService) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleMapper = vehicleMapper;
        this.userService = userService;
        this.registrationService = registrationService;
    }

    public Optional<Vehicle> getVehicleByVehicleId(Long vehicleId) {
        return vehicleRepository.findById(vehicleId);
    }

    public List<VehicleResponseDTO> getVehicleByOwnerOwnerId(Integer ownerId) {
        return vehicleMapper.mapVehicleList(vehicleRepository.findVehicleByUser(ownerId));
    }

    public VehicleResponseDTO saveVehicle(VehicleRequestDTO vehicleRequestDTO) {
        Optional<User> userOptional = userService.findUserById(vehicleRequestDTO.getUserId());
        if (userOptional.isEmpty()) {
            throw new BadCreateRequest("No se encontró el propietario.");
        }

        Registration registration = registrationService.save(Registration.builder()
                .user(userOptional.get())
                .registrationDate(vehicleRequestDTO.getRegistrationDate())
                .brand(vehicleRequestDTO.getBrand())
                .licensePlate(vehicleRequestDTO.getLicensePlate())
                .build());

        Vehicle vehicle = Vehicle.builder()
                .vehicleType(vehicleRequestDTO.getVehicleType())
                .registration(registration)
                .build();

        return vehicleMapper.mapVehicle(vehicleRepository.save(vehicle));
    }
}
