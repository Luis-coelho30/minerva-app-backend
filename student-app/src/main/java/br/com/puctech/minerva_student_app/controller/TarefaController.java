package br.com.puctech.minerva_student_app.controller;

import br.com.puctech.minerva_student_app.dto.TarefaDTO;
import br.com.puctech.minerva_student_app.model.Tarefa;
import br.com.puctech.minerva_student_app.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @GetMapping
    public List<TarefaDTO> listarTarefas() {
        List<Tarefa> tarefa = tarefaService.listarTarefas();
        List<TarefaDTO> tarefaDTO = tarefa.stream().map(x -> new TarefaDTO(x)).toList();

        return tarefaDTO;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarefaDTO> buscarPorId(@PathVariable Long id) {
        return tarefaService.buscarPorID(id)
                .map(tarefa -> ResponseEntity.ok(new TarefaDTO(tarefa)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public TarefaDTO criarTarefa(@RequestBody TarefaDTO tarefaDTO) {
        Tarefa tarefa = new Tarefa(tarefaDTO.getTitulo(), tarefaDTO.getDescricao(), tarefaDTO.getStatus(), tarefaDTO.getPrioridade(), tarefaDTO.getArquivada());
        Tarefa tarefaSalva = tarefaService.salvarTarefa(tarefa);
        TarefaDTO tarefa_DTO = new TarefaDTO(tarefaSalva);

        return tarefa_DTO;
    }

    @PutMapping("/{id}")
    public TarefaDTO atualizarTarefa(@PathVariable Long id, @RequestBody TarefaDTO tarefaDTO) {
        Tarefa tarefa = new Tarefa(tarefaDTO.getTitulo(), tarefaDTO.getDescricao(), tarefaDTO.getStatus(), tarefaDTO.getPrioridade(), tarefaDTO.getArquivada());
        Tarefa tarefaSalva = tarefaService.atualizarTarefa(id, tarefa);
        TarefaDTO tarefa_DTO = new TarefaDTO(tarefaSalva);

        return tarefa_DTO;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTarefa(@PathVariable Long id) {
        tarefaService.deletarTarefa(id);
        return ResponseEntity.noContent().build();
    }
}
