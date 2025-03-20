package secretaria_transito.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import secretaria_transito.project.entities.User;
import secretaria_transito.project.enums.Role;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    @Query("SELECT u.role FROM User u WHERE u.username = :nombre")
    Role findRoleByUsername(String nombre);

}
