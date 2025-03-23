package secretaria_transito.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import secretaria_transito.project.entities.Registration;

public interface RegistrationRepository extends JpaRepository<Registration,Long> {
}
