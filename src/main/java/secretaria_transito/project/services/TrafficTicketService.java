package secretaria_transito.project.services;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.stereotype.Service;
import secretaria_transito.project.dto.TrafficTicketResponseDTO;
import secretaria_transito.project.entities.TrafficTicket;
import secretaria_transito.project.entities.User;
import secretaria_transito.project.entities.Vehicle;
import secretaria_transito.project.exceptions.BadCreateRequest;
import secretaria_transito.project.maps.TrafficTicketMapper;
import secretaria_transito.project.repositories.TrafficTicketRepository;
import secretaria_transito.project.utils.InvoiceDetail;

import java.util.*;

@Service
public class TrafficTicketService {

    private final TrafficTicketRepository trafficTicketRepository;
    private final VehicleService vehicleService;
    private final TrafficTicketMapper trafficTicketMapper;
    private final UserService userService;

    public TrafficTicketService(TrafficTicketRepository trafficTicketRepository, VehicleService vehicleService, TrafficTicketMapper trafficTicketMapper, UserService userService) {
        this.trafficTicketRepository = trafficTicketRepository;
        this.vehicleService = vehicleService;
        this.trafficTicketMapper = trafficTicketMapper;
        this.userService = userService;
    }

    public List<TrafficTicketResponseDTO> getTrafficTicketByVehicleId(Long vehicleId) {
        Optional<Vehicle> vehicleOptional = vehicleService.getVehicleByVehicleId(vehicleId);
        if (vehicleOptional.isEmpty()) {
            throw new BadCreateRequest("El vehiculo no existe.");
        }
        return trafficTicketMapper.mapTrafficTicketList(trafficTicketRepository.findTrafficTicketByVehicleVehicleId(vehicleId));
    }

    public Map<String, String> generateInvoicePdf(Integer idUser, Long idVehicle, Long idTrafficTicket) {
        Optional<Vehicle> vehicleOptional = vehicleService.getVehicleByVehicleId(idVehicle);
        if (vehicleOptional.isEmpty()) {
            throw new BadCreateRequest("El vehiculo no existe.");
        }
        Vehicle vehicle = vehicleOptional.get();

        Optional<User> userOptional = userService.findUserById(idUser);
        if (userOptional.isEmpty()) {
            throw new BadCreateRequest("El usuario no existe.");
        }
        User user = userOptional.get();

        Optional<TrafficTicket> trafficTicketOptional = trafficTicketRepository.findById(idTrafficTicket);
        if (trafficTicketOptional.isEmpty()) {
            throw new BadCreateRequest("La infracción no existe.");
        }
        TrafficTicket trafficTicket = trafficTicketOptional.get();

        String jasperFilePath = "src/main/resources/jasperInvoiceReport/pago.jasper";
        try {
            JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(jasperFilePath);

            InvoiceDetail invoiceDetail = new InvoiceDetail();
            invoiceDetail.setIdNumber(user.getIdNumber());
            invoiceDetail.setName(user.getFullName());

            if (trafficTicket.getTrafficAgent() != null) {
                invoiceDetail.setItem("Parte");
            }else{
                invoiceDetail.setItem("Fotomulta");
            }

            invoiceDetail.setDescription(trafficTicket.getDescription());
            invoiceDetail.setVehicleLicensePlate(vehicle.getRegistration().getLicensePlate());
            invoiceDetail.setVehicleBrandName(vehicle.getRegistration().getBrand());
            invoiceDetail.setValue(trafficTicket.getPrice());
            Date dateNow = new Date();
            invoiceDetail.setDate(dateNow);
            List<InvoiceDetail> invoiceDetailList = new ArrayList<>();
            invoiceDetailList.add(invoiceDetail);

            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(invoiceDetailList);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperFilePath, null, dataSource);
            JasperExportManager.exportReportToPdfFile(jasperPrint,"src/main/resources/jasperInvoiceReport/pagoPdf.pdf");

            Map<String, String> response = new HashMap<>();
            response.put("Message", "PDF generado exitosamente.");
            return response;
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }
}
