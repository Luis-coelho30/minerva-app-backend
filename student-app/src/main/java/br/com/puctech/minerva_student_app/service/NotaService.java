package br.com.puctech.minerva_student_app.service;

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

    public List<Nota> listarNotas() {
        return notaRepository.findAll();
    }

    public Optional<Nota> buscarPorID (Long id) {
        return notaRepository.findById(id);
    }

    public Nota salvarNota(Nota nota) {
        return notaRepository.save(nota);
    }

    public Nota atualizarNota(Long id, Nota novaNota) {
        return notaRepository.findById(id)
                .map( nota -> {
                    nota.setDescricao(novaNota.getDescricao());
                    nota.setValor(novaNota.getValor());
                    nota.setPeso(novaNota.getPeso());

                    return notaRepository.save(nota);
                })
                .orElseGet(() -> {
                    return notaRepository.save(novaNota);
                });
    }

    public void deletarNota(Long id) {
        notaRepository.deleteById(id);
    }
}
