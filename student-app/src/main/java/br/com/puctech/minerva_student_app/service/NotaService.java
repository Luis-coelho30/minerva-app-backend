package br.com.puctech.minerva_student_app.service;

import br.com.puctech.minerva_student_app.exception.disciplina.DisciplinaNaoEncontradaException;
import br.com.puctech.minerva_student_app.model.Disciplina;
import br.com.puctech.minerva_student_app.model.Nota;
import br.com.puctech.minerva_student_app.repo.NotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotaService {

    @Autowired
    private NotaRepository notaRepository;

    @Autowired
    private DisciplinaService disciplinaService;

    public List<Nota> listarNotasPorDisciplina(Long id) {
        return notaRepository.findNotasByDisciplina(id);
    }

    public Optional<Nota> buscarPorID(Long id) {
        return notaRepository.findById(id);
    }

    public Nota salvarNota(Nota nota, Long disciplinaId) {
        Optional<Disciplina> disciplina = disciplinaService.buscarPorId(disciplinaId);

        if(disciplina.isPresent()) {
            nota.setDisciplina(disciplina.get());
        } else {
            throw new DisciplinaNaoEncontradaException(disciplinaId);
        }

        return notaRepository.save(nota);
    }

    public Nota atualizarNota(Long id, Nota novaNota, Long disciplinaId) {
        Optional<Disciplina> disciplina = disciplinaService.buscarPorId(disciplinaId);

        if(disciplina.isPresent()) {
            novaNota.setDisciplina(disciplina.get());
        } else {
            throw new DisciplinaNaoEncontradaException(disciplinaId);
        }

        return notaRepository.findById(id)
                .map( nota -> {
                    nota.setDescricao(novaNota.getDescricao());
                    nota.setValor(novaNota.getValor());
                    nota.setPeso(novaNota.getPeso());
                    nota.setDisciplina(disciplina.get());

                    return notaRepository.save(nota);
                })
                .orElseGet(() -> notaRepository.save(novaNota));
    }

    public void deletarNota(Long id) {
        notaRepository.deleteById(id);
    }
}
