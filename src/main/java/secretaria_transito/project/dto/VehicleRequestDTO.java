package secretaria_transito.project.dto;

import jakarta.persistence.Column;
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
public class VehicleRequestDTO {

    private String licensePlate;

    private String brand;

    private Date registrationDate;

    private VehicleType vehicleType;

    private Integer userId;

}
