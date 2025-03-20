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
        return UserResponseDTO.builder()
                .idUser(user.getIdUser())
                .fullName(user.getFullName())
                .role(user.getRole())
                .username(user.getUsername())
                .build();
    }

    public List<UserResponseDTO> mapUsuarioList(List<User> userList) {
        return userList.stream()
                .map(this::mapUsuario)
                .toList();
    }
}
