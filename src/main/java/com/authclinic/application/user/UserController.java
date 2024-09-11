package com.authclinic.application.user;

import com.authclinic.application.user.dtos.UserAuthDTO;
import com.authclinic.application.user.dtos.UserRegisterDTO;
import com.authclinic.application.user.dtos.UserResponseDTO;
import com.authclinic.domain.user.UserModel;
import com.authclinic.exceptions.InvalidCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserAuthDTO authDTO) {
        try {
            String token = userService.authenticate(authDTO);
            return ResponseEntity.ok(token);
        } catch (InvalidCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody UserRegisterDTO dto) {
        Long userId = userService.registerUser(dto);
        UserModel createdUser = userService.findUserById(userId);
        UserResponseDTO responseDTO = new UserResponseDTO(
                createdUser.getId(),
                createdUser.getUsername(),
                createdUser.getRole()
        );
        String location = "/auth/users/" + userId;
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(location));
        return new ResponseEntity<>(responseDTO, headers, HttpStatus.CREATED);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(Authentication authentication) {
        return ResponseEntity.status(HttpStatus.OK).body("Logout realizado com sucesso.");
    }
}