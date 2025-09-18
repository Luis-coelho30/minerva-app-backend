package br.com.puctech.minerva_student_app.controller;

import br.com.puctech.minerva_student_app.dto.UsuarioRequestDTO;
import br.com.puctech.minerva_student_app.dto.UsuarioResponseDTO;
import br.com.puctech.minerva_student_app.model.Usuario;
import br.com.puctech.minerva_student_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        Usuario usuario = new Usuario(usuarioRequestDTO.getUsername(), usuarioRequestDTO.getEmail(), usuarioRequestDTO.getSenha());

        return userService.verificarLogin(usuario);
    }

    @PostMapping("/register")
    public ResponseEntity<UsuarioResponseDTO> register(@RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        Usuario usuario = new Usuario(usuarioRequestDTO.getUsername(), usuarioRequestDTO.getEmail(), usuarioRequestDTO.getSenha());
        Usuario salvo = userService.registrar(usuario);
        UsuarioResponseDTO responseDTO = new UsuarioResponseDTO(salvo);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("/update/{email}")
    public ResponseEntity<String> atualizarUsuario(Authentication authentication, @RequestBody UsuarioRequestDTO usuarioDTO) {
        Usuario novoUsuario = new Usuario(usuarioDTO.getUsername(), usuarioDTO.getEmail(), usuarioDTO.getSenha());

        return userService.atualizarUsuario(authentication.getName(), novoUsuario);
    }

    @DeleteMapping("/delete/{email}")
    public ResponseEntity<Void> deletarUsuario(Authentication authentication) {
        userService.deletarUsuario(authentication.getName());
        return ResponseEntity.noContent().build();
    }
}
