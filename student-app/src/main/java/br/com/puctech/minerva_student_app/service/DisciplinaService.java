package br.com.puctech.minerva_student_app.service;

import br.com.puctech.minerva_student_app.model.Disciplina;
import br.com.puctech.minerva_student_app.repo.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisciplinaService {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    public List<Disciplina> listarDisciplinas() {
        return disciplinaRepository.findAll();
    }

    public Optional<Disciplina> buscarPorID (Long id) {
        return disciplinaRepository.findById(id);
    }

    public Disciplina salvarDisciplina(Disciplina disciplina) {
        return disciplinaRepository.save(disciplina);
    }

    public Disciplina atualizarDisciplina(Long id, Disciplina novaDisciplina) {
        return disciplinaRepository.findById(id)
                .map( disciplina -> {
                    disciplina.setNome(novaDisciplina.getNome());
                    disciplina.setDescricao(novaDisciplina.getDescricao());
                    disciplina.setArquivada(novaDisciplina.getArquivada());
                    disciplina.setMediaNecessaria(novaDisciplina.getMediaNecessaria());
                    disciplina.setMediaAtual(novaDisciplina.getMediaAtual());
                    disciplina.setCreditos(novaDisciplina.getCreditos());
                    disciplina.setFaltasRestantes(novaDisciplina.getFaltasRestantes());

                    return disciplinaRepository.save(disciplina);
                })
                .orElseGet(() -> {
                    return disciplinaRepository.save(novaDisciplina);
                });
    }

    public void deletarDisciplina(Long id) {
        disciplinaRepository.deleteById(id);
    }
}