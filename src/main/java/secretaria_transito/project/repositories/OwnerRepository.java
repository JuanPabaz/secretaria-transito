package secretaria_transito.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import secretaria_transito.project.entities.Owner;

public interface OwnerRepository extends JpaRepository<Owner,Long> {
}
