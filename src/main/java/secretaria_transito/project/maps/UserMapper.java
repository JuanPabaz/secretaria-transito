package secretaria_transito.project.maps;

import org.springframework.stereotype.Component;
import secretaria_transito.project.dto.UserResponseDTO;
import secretaria_transito.project.entities.User;

import java.util.List;

@Component
public class UserMapper {

    public UserResponseDTO mapUsuario(User user) {
        if (user == null) {
            return null;
        }

        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setIdUser(user.getIdUser());
        userResponseDTO.setFullName(user.getFullName());
        userResponseDTO.setRole(user.getRole());
        userResponseDTO.setUsername(user.getUsername());

        return userResponseDTO;
    }

    public List<UserResponseDTO> mapUsuarioList(List<User> userList) {
        return userList.stream()
                .map(this::mapUsuario)
                .toList();
    }
}
