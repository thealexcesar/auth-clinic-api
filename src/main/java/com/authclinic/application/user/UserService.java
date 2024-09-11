package com.authclinic.application.user;

import com.authclinic.application.TokenService;
import com.authclinic.application.user.dtos.UserAuthDTO;
import com.authclinic.application.user.dtos.UserRegisterDTO;
import com.authclinic.domain.user.UserModel;
import com.authclinic.exceptions.InvalidCredentialsException;
import com.authclinic.exceptions.UserAlreadyExistsException;
import com.authclinic.infrastructure.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String authenticate(UserAuthDTO authDTO) {
        Optional<UserModel> userOptional = userRepository.findByUsername(authDTO.username());
        if (userOptional.isPresent()) {
            UserModel user = userOptional.get();
            if (passwordEncoder.matches(authDTO.password(), user.getPassword())) {
                return tokenService.generateToken(user);
            }
        }
        throw new InvalidCredentialsException("Credenciais inválidas.");
    }

    public Long registerUser(UserRegisterDTO dto) {
        if (userRepository.findByUsername(dto.username()).isPresent()) {
            throw new UserAlreadyExistsException("Username já existe, por favor tente outro.");
        }

        UserModel newUser = new UserModel();
        newUser.setUsername(dto.username());
        newUser.setPassword(passwordEncoder.encode(dto.password()));
        newUser.setRole(dto.role());

        UserModel createdUser = userRepository.save(newUser);
        return createdUser.getId();
    }

    public UserModel findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado."));
    }
}
