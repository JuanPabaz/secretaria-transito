package secretaria_transito.project.dto;

import java.util.Date;

public class TrafficTicketResponseDTO {

    private Long trafficTicketId;
    private Long vehicleId;
    private Date date;
    private String description;
    private Double price;
    private Long trafficAgentId;
    private Integer detectionCameraId;

    public TrafficTicketResponseDTO() {
    }

    public TrafficTicketResponseDTO(Long trafficTicketId, Long vehicleId, Date date, String description, Double price, Long trafficAgentId, Integer detectionCameraId) {
        this.trafficTicketId = trafficTicketId;
        this.vehicleId = vehicleId;
        this.date = date;
        this.description = description;
        this.price = price;
        this.trafficAgentId = trafficAgentId;
        this.detectionCameraId = detectionCameraId;
    }

    public Long getTrafficTicketId() {
        return trafficTicketId;
    }

    public void setTrafficTicketId(Long trafficTicketId) {
        this.trafficTicketId = trafficTicketId;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getTrafficAgentId() {
        return trafficAgentId;
    }

    public void setTrafficAgentId(Long trafficAgentId) {
        this.trafficAgentId = trafficAgentId;
    }

    public Integer getDetectionCameraId() {
        return detectionCameraId;
    }

    public void setDetectionCameraId(Integer detectionCameraId) {
        this.detectionCameraId = detectionCameraId;
    }
}
