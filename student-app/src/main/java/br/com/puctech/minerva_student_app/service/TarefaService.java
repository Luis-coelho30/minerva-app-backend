package br.com.puctech.minerva_student_app.service;

import br.com.puctech.minerva_student_app.model.Disciplina;
import br.com.puctech.minerva_student_app.model.Tarefa;
import br.com.puctech.minerva_student_app.repo.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private DisciplinaService disciplinaService;

    public List<Tarefa> listarTarefasPorUsuario(String email) {
        return tarefaRepository.findTaskByUsermail(email);
    }

    public List<Tarefa> buscarPorDisciplina(String email, Long disciplinaId) {
        return tarefaRepository.findTaskByDisciplina(email, disciplinaId);
    }

    public Tarefa salvarTarefa(Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }

    public Tarefa salvarTarefa(Tarefa tarefa, Long disciplinaId) {

        Optional<Disciplina> disciplina = disciplinaService.buscarPorId(disciplinaId);

        if(disciplina.isEmpty()) {
            throw new UsernameNotFoundException("Disciplina não encontrada");
        }

        tarefa.setDisciplina(disciplina.get());

        return tarefaRepository.save(tarefa);
    }

    public Tarefa atualizarTarefa(Long id, Tarefa novaTarefa) {
        return tarefaRepository.findById(id)
                .map( tarefa -> {
                    tarefa.setTitulo(novaTarefa.getTitulo());
                    tarefa.setDescricao(novaTarefa.getDescricao());
                    tarefa.setStatus(novaTarefa.getStatus());
                    tarefa.setDisciplina(null);
                    tarefa.setPrioridade(novaTarefa.getPrioridade());
                    tarefa.setArquivada((novaTarefa.getArquivada()));


                    return tarefaRepository.save(tarefa);
                })
                .orElseGet(() -> {
                    return tarefaRepository.save(novaTarefa);
                });
    }

    public Tarefa atualizarTarefa(Long id, Tarefa tarefa, Long disciplinaId) {

        Optional<Disciplina> disciplina = disciplinaService.buscarPorId(disciplinaId);

        if(disciplina.isEmpty()) {
            throw new UsernameNotFoundException("Disciplina não encontrada");
        }

        return tarefaRepository.findById(id)
                .map( tarefaN -> {
                    tarefa.setTitulo(tarefa.getTitulo());
                    tarefa.setDescricao(tarefa.getDescricao());
                    tarefa.setStatus(tarefa.getStatus());
                    tarefa.setPrioridade(tarefa.getPrioridade());
                    tarefa.setArquivada((tarefa.getArquivada()));
                    tarefa.setDisciplina(disciplina.get());

                    return tarefaRepository.save(tarefa);
                })
                .orElseGet(() -> {
                    return tarefaRepository.save(tarefa);
                });
    }

    public void deletarTarefa(Long id) {
        tarefaRepository.deleteById(id);
    }
}
