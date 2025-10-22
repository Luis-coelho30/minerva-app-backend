package br.com.puctech.minerva_student_app.service;

import br.com.puctech.minerva_student_app.model.Nota;
import br.com.puctech.minerva_student_app.model.Usuario;
import br.com.puctech.minerva_student_app.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public Usuario getUsuario(String email) {
        Usuario usuario = userRepository.findByEmail(email);
        if(usuario==null) {
            throw new UsernameNotFoundException("Usuário não foi encontrado");
        }

        return usuario;
    }

    public String verificarLogin(Usuario usuario) {
        try {
            Authentication authentication =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usuario.getEmail(), usuario.getSenha()));

            if(authentication.isAuthenticated()) {
                return jwtService.generateToken(usuario.getEmail());
            }
        } catch (AuthenticationException e) {
            return "";
        }

        return "";
    }

    public Usuario registrar(Usuario usuario) {
        if(userRepository.existsByEmail(usuario.getEmail())) {
            throw new IllegalStateException("Usuário já foi cadastrado.");
        }
        usuario.setSenha(encoder.encode(usuario.getSenha()));

        return userRepository.save(usuario);
    }

    @Transactional
    public ResponseEntity<String> atualizarUsuario(String email, Usuario novoUsuario) {
        Usuario usuario = userRepository.findByEmail(email);
        if(usuario == null) {
            throw new UsernameNotFoundException("Usuário não foi encontrado.");
        } else {
            usuario.setUsername(novoUsuario.getUsername());
            usuario.setEmail(novoUsuario.getEmail());
            usuario.setSenha(encoder.encode(novoUsuario.getSenha()));
            userRepository.save(usuario);
        }

        return ResponseEntity.ok().body(jwtService.generateToken(usuario.getEmail()));
    }

    @Transactional
    public void deletarUsuario(String email) {
        userRepository.deleteByEmail(email);
    }
}
