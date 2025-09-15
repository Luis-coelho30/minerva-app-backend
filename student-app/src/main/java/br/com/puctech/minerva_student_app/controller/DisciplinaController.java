package br.com.puctech.minerva_student_app.controller;

import br.com.puctech.minerva_student_app.dto.DisciplinaRequestDTO;
import br.com.puctech.minerva_student_app.dto.DisciplinaResponseDTO;
import br.com.puctech.minerva_student_app.model.Disciplina;
import br.com.puctech.minerva_student_app.service.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/disciplinas")
public class DisciplinaController {

    @Autowired
    private DisciplinaService disciplinaService;

    @GetMapping
    public List<DisciplinaResponseDTO> listarDisciplinas() {
        List<Disciplina> disciplina = disciplinaService.listarDisciplinas();
        List<DisciplinaResponseDTO> disciplinaDTO = disciplina.stream().map(x -> new DisciplinaResponseDTO(x)).toList();

        return disciplinaDTO;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisciplinaResponseDTO> buscarPorId(@PathVariable Long id) {
        return disciplinaService.buscarPorID(id)
                .map(disciplina -> ResponseEntity.ok(new DisciplinaResponseDTO(disciplina)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public DisciplinaResponseDTO criarDisciplina(@RequestBody DisciplinaRequestDTO disciplinaDTO) {
        Disciplina disciplina = new Disciplina(disciplinaDTO.getNome(), disciplinaDTO.getDescricao(), disciplinaDTO.getArquivada(), disciplinaDTO.getMediaNecessaria(), null, disciplinaDTO.getCreditos(), null);
        Disciplina disciplinaSalva = disciplinaService.salvarDisciplina(disciplina);
        DisciplinaResponseDTO disciplina_DTO = new DisciplinaResponseDTO(disciplinaSalva);

        return disciplina_DTO;
    }

    @PutMapping("/{id}")
    public DisciplinaResponseDTO atualizarDisciplina(@PathVariable Long id, @RequestBody DisciplinaRequestDTO disciplinaDTO) {
        Disciplina disciplina = new Disciplina(disciplinaDTO.getNome(), disciplinaDTO.getDescricao(), disciplinaDTO.getArquivada(), disciplinaDTO.getMediaNecessaria(), null, disciplinaDTO.getCreditos(), null);
        Disciplina disciplinaSalva = disciplinaService.atualizarDisciplina(id, disciplina);
        DisciplinaResponseDTO disciplina_DTO = new DisciplinaResponseDTO(disciplinaSalva);

        return disciplina_DTO;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDisciplina(@PathVariable Long id) {
        disciplinaService.deletarDisciplina(id);
        return ResponseEntity.noContent().build();
    }
}
