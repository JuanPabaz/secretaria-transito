package secretaria_transito.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import secretaria_transito.project.enums.VehicleType;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VehicleRequestDTO {

    private String licensePlate;

    private String brand;

    private VehicleType vehicleType;

    private Long ownerId;

}
