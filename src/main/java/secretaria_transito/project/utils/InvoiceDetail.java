package secretaria_transito.project.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InvoiceDetail {

    private String name;
    private String idNumber;
    private String vehicleBrandName;
    private String vehicleLicensePlate;
    private String description;
    private Double value;
    private String item;
    private Date date;
}
