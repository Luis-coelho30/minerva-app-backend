package br.com.puctech.minerva_student_app.controller;

import br.com.puctech.minerva_student_app.dto.DisciplinaRequestDTO;
import br.com.puctech.minerva_student_app.dto.DisciplinaResponseDTO;
import br.com.puctech.minerva_student_app.model.Disciplina;
import br.com.puctech.minerva_student_app.service.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/disciplinas/me")
public class DisciplinaController {

    @Autowired
    private DisciplinaService disciplinaService;

    @GetMapping
    public List<DisciplinaResponseDTO> listarDisciplinas(@PathVariable Authentication authentication) {
        List<Disciplina> disciplina = disciplinaService.listarDisciplinas(authentication.getName());

        return disciplina.stream().map(DisciplinaResponseDTO::new).toList();
    }

    @GetMapping("/{name}")
    public ResponseEntity<DisciplinaResponseDTO> buscarPorNome(@PathVariable String name, Authentication authentication) {
        return disciplinaService.buscarPorNome(name, authentication.getName())
                .map(disciplina -> ResponseEntity.ok(new DisciplinaResponseDTO(disciplina)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public DisciplinaResponseDTO criarDisciplina(@RequestBody DisciplinaRequestDTO disciplinaDTO) {
        Disciplina disciplina = new Disciplina(disciplinaDTO.getNome(), disciplinaDTO.getDescricao(),
                disciplinaDTO.getArquivada(), disciplinaDTO.getMediaNecessaria(),
                0.0, disciplinaDTO.getCreditos(), 0);
        Disciplina disciplinaSalva = disciplinaService.salvarDisciplina(disciplina);

        return new DisciplinaResponseDTO(disciplinaSalva);
    }

    @PutMapping("/{name}")
    public DisciplinaResponseDTO atualizarDisciplina(@PathVariable String name, @RequestBody DisciplinaRequestDTO disciplinaDTO, Authentication authentication) {
        Disciplina disciplina = new Disciplina(disciplinaDTO.getNome(), disciplinaDTO.getDescricao(), disciplinaDTO.getArquivada(),
                disciplinaDTO.getMediaNecessaria(), 0.0, disciplinaDTO.getCreditos(), 0);
        Disciplina disciplinaSalva = disciplinaService.atualizarDisciplina(authentication.getName(), name, disciplina);

        return new DisciplinaResponseDTO(disciplinaSalva);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deletarDisciplina(@PathVariable String name, Authentication authentication) {
        disciplinaService.deletarDisciplina(authentication.getName(), name);
        return ResponseEntity.noContent().build();
    }
}
