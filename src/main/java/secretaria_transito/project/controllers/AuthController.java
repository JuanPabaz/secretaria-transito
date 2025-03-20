package secretaria_transito.project.controllers;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import secretaria_transito.project.dto.AuthRequestDTO;
import secretaria_transito.project.dto.AuthResponseDTO;
import secretaria_transito.project.dto.RegisterRequestDTO;
import secretaria_transito.project.dto.UserResponseDTO;
import secretaria_transito.project.entities.RefreshToken;
import secretaria_transito.project.exceptions.BadUserCredentialsException;
import secretaria_transito.project.exceptions.ExpiredRefreshTokenException;
import secretaria_transito.project.exceptions.ObjectNotFoundException;
import secretaria_transito.project.repositories.UserRepository;
import secretaria_transito.project.services.AuthenticationService;
import secretaria_transito.project.services.JwtServiceImpl;
import secretaria_transito.project.services.RefreshTokenService;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*", allowedHeaders = {"Authorization", "Content-Type"})
public class AuthController {

    private final AuthenticationService authenticationService;

    private final RefreshTokenService refreshTokenService;

    private final AuthenticationManager authenticationManager;

    private final JwtServiceImpl jwtService;

    private final UserRepository userRepository;

    public AuthController(AuthenticationService authenticationService, RefreshTokenService refreshTokenService,
                          AuthenticationManager authenticationManager, JwtServiceImpl jwtService,
                          UserRepository userRepository) {
        this.authenticationService = authenticationService;
        this.refreshTokenService = refreshTokenService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public UserResponseDTO addNewUser(@RequestBody RegisterRequestDTO registerRequestDTO){
        return authenticationService.saveUser(registerRequestDTO);
    }

    @PostMapping("/login")
    public AuthResponseDTO getToken(@RequestBody AuthRequestDTO authRequest){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
            Optional<RefreshToken> refreshTokenOptional = refreshTokenService.findByUsername(authRequest.getUsername());
            refreshTokenOptional.ifPresent(refreshTokenService::DeleteRefreshToken);
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(authRequest.getUsername());
            return AuthResponseDTO
                    .builder()
                    .accessToken(authenticationService.generateToken(authRequest.getUsername()))
                    .refreshToken(refreshToken.getToken())
                    .role(userRepository.findRoleByUsername(authRequest.getUsername()))
                    .build();

        }catch (BadUserCredentialsException e){
            throw new BadUserCredentialsException(e.getMessage());
        } catch (Exception e){
            throw new BadUserCredentialsException("Usuario y/o contraseña incorrectas");
        }

    }

    @PostMapping("/refreshToken")
    public AuthResponseDTO refreshToken(@RequestBody AuthResponseDTO authResponseDTO){

        return refreshTokenService.findByToken(authResponseDTO.getRefreshToken())
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(userCredential -> {
                    String accessToken = null;
                    try {
                        accessToken = jwtService.generateToken(userCredential.getUsername());
                    } catch (ObjectNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    return AuthResponseDTO.builder()
                            .accessToken(accessToken)
                            .refreshToken(authResponseDTO.getRefreshToken()).build();
                }).orElseThrow(() ->new ExpiredRefreshTokenException("El refresh token no se encuentra en la base de datos"));
    }

    @GetMapping("/validateToken/{token}")
    public Map<String, Object> validateToken(@PathVariable(name = "token") String token){
        return authenticationService.validateToken(token);
    }

}
