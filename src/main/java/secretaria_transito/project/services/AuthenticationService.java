package secretaria_transito.project.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import secretaria_transito.project.dto.RegisterRequestDTO;
import secretaria_transito.project.dto.UserResponseDTO;
import secretaria_transito.project.entities.User;
import secretaria_transito.project.enums.Role;
import secretaria_transito.project.exceptions.BadUserCredentialsException;
import secretaria_transito.project.exceptions.ObjectNotFoundException;
import secretaria_transito.project.maps.UserMapper;
import secretaria_transito.project.repositories.UserRepository;

import java.util.Map;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtServiceImpl jwtService;

    private final UserMapper mapUser;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder,
                                 JwtServiceImpl jwtService, UserMapper mapUser) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.mapUser = mapUser;
    }


    public UserResponseDTO saveUser(RegisterRequestDTO registerRequestDTO) throws BadUserCredentialsException {
        if (userRepository.findByUsername(registerRequestDTO.getUsername()).isPresent()){
            throw new BadUserCredentialsException("Ya existe un usuario con este correo: "+ registerRequestDTO.getUsername() + ".");
        }

        String passwordRegex = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d/*\\-_.º!?¿'¡#!$%&]{6,}$";
        if (!registerRequestDTO.getPassword().matches(passwordRegex)){
            throw new BadUserCredentialsException("La contraseña debe tener al menos 6 caracteres y contener al menos una letra y un número.");
        }
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        if (!registerRequestDTO.getUsername().matches(emailRegex)){
            throw new BadUserCredentialsException("El correo no es valido.");
        }

        registerRequestDTO.setRole(Role.ADMIN);
        registerRequestDTO.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));

        User user = new User();
        user.setUsername(registerRequestDTO.getUsername());
        user.setPassword(registerRequestDTO.getPassword());
        user.setRole(registerRequestDTO.getRole());
        user.setOwnerType(registerRequestDTO.getOwnerType());
        user.setIdNumber(registerRequestDTO.getIdNumber());
        user.setAddress(registerRequestDTO.getAddress());
        user.setFullName(registerRequestDTO.getFullName());

        return mapUser.mapUsuario(userRepository.save(user));
    }

    public String generateToken(String username) throws ObjectNotFoundException {
        return jwtService.generateToken(username);
    }

    public Map<String, Object> validateToken(String token){
        return jwtService.validateToken(token);
    }
}
