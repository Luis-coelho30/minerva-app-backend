package br.com.puctech.minerva_student_app.service;

import br.com.puctech.minerva_student_app.model.Arquivo;
import br.com.puctech.minerva_student_app.repo.ArquivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArquivoService {

    @Autowired
    private ArquivoRepository arquivoRepository;

    public List<Arquivo> listarArquivos() {
        return arquivoRepository.findAll();
    }

    public Optional<Arquivo> buscarPorID(Long id) {
        return arquivoRepository.findById(id);
    }

    public Arquivo salvarArquivo(Arquivo arquivo) {
        return arquivoRepository.save(arquivo);
    }

    public Arquivo atualizarArquivo(Long id, Arquivo novoArquivo) {
        return arquivoRepository.findById(id)
                .map( arquivo -> {
                    arquivo.setNomeOriginal(novoArquivo.getNomeOriginal());
                    arquivo.setUrl(novoArquivo.getUrl());
                    arquivo.setTipo(novoArquivo.getTipo());

                    return arquivoRepository.save(arquivo);
                })
                .orElseGet(() -> {
                    return arquivoRepository.save(novoArquivo);
                });
    }

    public void deletarArquivo(Long id) {
        arquivoRepository.deleteById(id);
    }
}
