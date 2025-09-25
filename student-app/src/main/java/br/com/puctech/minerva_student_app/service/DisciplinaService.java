package br.com.puctech.minerva_student_app.service;

import br.com.puctech.minerva_student_app.model.Disciplina;
import br.com.puctech.minerva_student_app.repo.DisciplinaRepository;
import jakarta.transaction.Transactional;
import net.minidev.asm.ex.NoSuchFieldException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisciplinaService {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    public List<Disciplina> listarDisciplinas(String email) {
        return disciplinaRepository.findDisciplinasByUsermail(email);
    }

    public Optional<Disciplina> buscarPorId(Long id) {
        return disciplinaRepository.findById(id);
    }

    @Transactional
    public Disciplina salvarDisciplina(Disciplina disciplina) {
        return disciplinaRepository.save(disciplina);
    }

    @Transactional
    public Disciplina atualizarDisciplina(String email, String name, Disciplina novaDisciplina) {
        Disciplina disciplina = disciplinaRepository.findDisciplinasByName(email, name);

        if(disciplina != null) {
            disciplina.setNome(novaDisciplina.getNome());
            disciplina.setDescricao(novaDisciplina.getDescricao());
            disciplina.setArquivada(novaDisciplina.getArquivada());
            disciplina.setMediaNecessaria(novaDisciplina.getMediaNecessaria());
            disciplina.setMediaAtual(novaDisciplina.getMediaAtual());
            disciplina.setCreditos(novaDisciplina.getCreditos());
            disciplina.setFaltasRestantes(novaDisciplina.getFaltasRestantes());
        } else {
            throw new NoSuchFieldException("Nenhuma disciplina encontrada");
        }

        return disciplinaRepository.save(disciplina);
    }

    @Transactional
    public void deletarDisciplina(String email, String name) {
        disciplinaRepository.deleteDisciplinasByName(email, name);
    }

    public Optional<Disciplina> buscarPorNome(String email, String name) {
        return Optional.of(disciplinaRepository.findDisciplinasByName(email, name));
    }
}