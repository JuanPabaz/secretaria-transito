package secretaria_transito.project.services;

import org.springframework.stereotype.Service;
import secretaria_transito.project.entities.Registration;
import secretaria_transito.project.repositories.RegistrationRepository;

@Service
public class RegistrationService {

    private final RegistrationRepository registrationRepository;

    public RegistrationService(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    public Registration save(Registration registration) {
        return registrationRepository.save(registration);
    }
}
