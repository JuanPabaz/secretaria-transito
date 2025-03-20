package secretaria_transito.project.services;

import org.springframework.stereotype.Service;
import secretaria_transito.project.entities.User;
import secretaria_transito.project.repositories.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findUserById(Integer id){
        return userRepository.findById(id);
    }
}
