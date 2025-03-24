package secretaria_transito.project.dto;

import secretaria_transito.project.enums.VehicleType;

import java.util.Date;

public class VehicleResponseDTO {

    private Long vehicleId;

    private String licensePlate;

    private String brand;

    private VehicleType vehicleType;

    private Integer userId;

    private Date registrationDate;

    public VehicleResponseDTO() {
    }

    public VehicleResponseDTO(Long vehicleId, String licensePlate, String brand, VehicleType vehicleType, Integer userId, Date registrationDate) {
        this.vehicleId = vehicleId;
        this.licensePlate = licensePlate;
        this.brand = brand;
        this.vehicleType = vehicleType;
        this.userId = userId;
        this.registrationDate = registrationDate;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
}
