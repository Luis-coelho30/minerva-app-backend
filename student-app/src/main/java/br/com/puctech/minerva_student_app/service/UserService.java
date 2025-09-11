package br.com.puctech.minerva_student_app.service;

import br.com.puctech.minerva_student_app.model.Usuario;
import br.com.puctech.minerva_student_app.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public Usuario registrar(Usuario usuario) {
        if(userRepository.existsByEmail(usuario.getEmail())) {
            throw new IllegalStateException("Usuário já foi cadastrado.");
        }
        usuario.setSenha(encoder.encode(usuario.getSenha()));

        return userRepository.save(usuario);
    }

    public ResponseEntity<String> verificarLogin(Usuario usuario) {
        try {
            Authentication authentication =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usuario.getUsername(), usuario.getSenha()));

            if(authentication.isAuthenticated()) {
                return ResponseEntity.status(HttpStatus.OK).body(jwtService.generateToken(usuario.getUsername()));
            }
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }
}
