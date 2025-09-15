package br.com.puctech.minerva_student_app.controller;

import br.com.puctech.minerva_student_app.dto.UsuarioRequestDTO;
import br.com.puctech.minerva_student_app.dto.UsuarioResponseDTO;
import br.com.puctech.minerva_student_app.model.Usuario;
import br.com.puctech.minerva_student_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PutMapping("/{id}")
    public UsuarioResponseDTO atualizarUsuario(@PathVariable Long id, @RequestBody UsuarioRequestDTO usuarioDTO) {
        Usuario usuario = new Usuario(usuarioDTO.getUsername(), usuarioDTO.getEmail(), usuarioDTO.getSenha());
        Usuario usuarioSalvo = userService.atualizarUsuario(id, usuario);
        UsuarioResponseDTO usuario_DTO = new UsuarioResponseDTO(usuarioSalvo);

        return usuario_DTO;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        userService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
