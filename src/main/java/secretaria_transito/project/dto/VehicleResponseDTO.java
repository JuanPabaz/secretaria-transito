package secretaria_transito.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import secretaria_transito.project.enums.VehicleType;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VehicleResponseDTO {

    private Long vehicleId;

    private String licensePlate;

    private String brand;

    private VehicleType vehicleType;

    private Integer userId;

    private Date registrationDate;
}
