package secretaria_transito.project.controllers;

import org.springframework.web.bind.annotation.*;
import secretaria_transito.project.dto.VehicleRequestDTO;
import secretaria_transito.project.dto.VehicleResponseDTO;
import secretaria_transito.project.services.VehicleService;

import java.util.List;

@RestController
@RequestMapping("/vehicle")
@CrossOrigin(origins = "*", allowedHeaders = {"Authorization", "Content-Type"})
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("/{ownerId}")
    public List<VehicleResponseDTO> getVehicle(@PathVariable Integer ownerId) {
        return vehicleService.getVehicleByOwnerOwnerId(ownerId);
    }

    @PostMapping
    public VehicleResponseDTO saveVehicle(@RequestBody VehicleRequestDTO vehicleRequestDTO) {
        return vehicleService.saveVehicle(vehicleRequestDTO);
    }
}
