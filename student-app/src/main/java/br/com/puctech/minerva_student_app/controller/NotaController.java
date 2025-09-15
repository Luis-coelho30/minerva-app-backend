package br.com.puctech.minerva_student_app.controller;

import br.com.puctech.minerva_student_app.dto.NotaDTO;
import br.com.puctech.minerva_student_app.model.Nota;
import br.com.puctech.minerva_student_app.service.NotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notas")
public class NotaController {

    @Autowired
    private NotaService notaService;

    @GetMapping
    public List<NotaDTO> listarNotas() {
        List<Nota> nota = notaService.listarNotas();
        List<NotaDTO> notaDTO = nota.stream().map(x -> new NotaDTO(x)).toList();

        return notaDTO;
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotaDTO> buscarPorId(@PathVariable Long id) {
        return notaService.buscarPorID(id)
                .map(nota -> ResponseEntity.ok(new NotaDTO(nota)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public NotaDTO criarNota(@RequestBody NotaDTO notaDTO) {
        Nota nota = new Nota(notaDTO.getDescricao(), notaDTO.getValor(), notaDTO.getPeso());
        Nota notaSalva = notaService.salvarNota(nota);
        NotaDTO nota_DTO = new NotaDTO(notaSalva);

        return nota_DTO;
    }

    @PutMapping("/{id}")
    public NotaDTO atualizarNota(@PathVariable Long id, @RequestBody NotaDTO notaDTO) {
        Nota nota = new Nota(notaDTO.getDescricao(), notaDTO.getValor(), notaDTO.getPeso());
        Nota notaSalva = notaService.atualizarNota(id, nota);
        NotaDTO nota_DTO = new NotaDTO(notaSalva);

        return nota_DTO;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarNota(@PathVariable Long id) {
        notaService.deletarNota(id);
        return ResponseEntity.noContent().build();
    }
}
