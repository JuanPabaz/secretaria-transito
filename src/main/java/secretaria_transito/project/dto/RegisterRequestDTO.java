package secretaria_transito.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import secretaria_transito.project.enums.Role;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class RegisterRequestDTO {

    private String fullName;
    private String username;
    private String password;
    private Role role;

}
