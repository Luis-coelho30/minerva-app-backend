package br.com.puctech.minerva_student_app.service;

import br.com.puctech.minerva_student_app.model.Tarefa;
import br.com.puctech.minerva_student_app.repo.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    public List<Tarefa> listarTarefas() {
        return tarefaRepository.findAll();
    }

    public Optional<Tarefa> buscarPorID (Long id) {
        return tarefaRepository.findById(id);
    }

    public Tarefa salvarTarefa(Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }

    public Tarefa atualizarTarefa(Long id, Tarefa novaTarefa) {
        return tarefaRepository.findById(id)
                .map( tarefa -> {
                    tarefa.setTitulo(novaTarefa.getTitulo());
                    tarefa.setDescricao(novaTarefa.getDescricao());
                    tarefa.setStatus(novaTarefa.getStatus());
                    tarefa.setPrioridade(novaTarefa.getPrioridade());
                    tarefa.setArquivada((novaTarefa.getArquivada()));

                    return tarefaRepository.save(tarefa);
                })
                .orElseGet(() -> {
                    return tarefaRepository.save(novaTarefa);
                });
    }

    public void deletarTarefa(Long id) {
        tarefaRepository.deleteById(id);
    }
}
