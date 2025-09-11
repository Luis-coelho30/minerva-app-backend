package br.com.puctech.minerva_student_app.controller;

import br.com.puctech.minerva_student_app.dto.UsuarioRequestDTO;
import br.com.puctech.minerva_student_app.dto.UsuarioResponseDTO;
import br.com.puctech.minerva_student_app.model.Usuario;
import br.com.puctech.minerva_student_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UsuarioResponseDTO> register(@RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        Usuario usuario = new Usuario(usuarioRequestDTO.getUsername(), usuarioRequestDTO.getEmail(), usuarioRequestDTO.getSenha());
        Usuario salvo = userService.registrar(usuario);
        UsuarioResponseDTO responseDTO = new UsuarioResponseDTO(salvo);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        Usuario usuario = new Usuario(usuarioRequestDTO.getUsername(), usuarioRequestDTO.getEmail(), usuarioRequestDTO.getSenha());

        return userService.verificarLogin(usuario);
    }
}
