package br.com.puctech.minerva_student_app.controller;

import br.com.puctech.minerva_student_app.dto.ArquivoDTO;
import br.com.puctech.minerva_student_app.model.Arquivo;
import br.com.puctech.minerva_student_app.service.ArquivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/arquivos")
public class ArquivoController {

    @Autowired
    private ArquivoService arquivoService;

    @GetMapping
    public List<ArquivoDTO> listarArquivos() {
        List<Arquivo> arquivo = arquivoService.listarArquivos();
        List<ArquivoDTO> arquivoDTO = arquivo.stream().map(x -> new ArquivoDTO(x)).toList();

        return arquivoDTO;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArquivoDTO> buscarPorId(@PathVariable Long id) {
        return arquivoService.buscarPorID(id)
                .map(arquivo -> ResponseEntity.ok(new ArquivoDTO(arquivo)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ArquivoDTO criarArquivo(@RequestBody ArquivoDTO arquivoDTO) {
        Arquivo arquivo = new Arquivo(arquivoDTO.getNomeOriginal(), arquivoDTO.getUrl(), arquivoDTO.getTipo());
        Arquivo arquivoSalvo = arquivoService.salvarArquivo(arquivo);
        ArquivoDTO arquivo_DTO = new ArquivoDTO(arquivoSalvo);

        return arquivo_DTO;
    }

    @PutMapping("/{id}")
    public ArquivoDTO atualizarArquivo(@PathVariable Long id, @RequestBody ArquivoDTO arquivoDTO) {
        Arquivo arquivo = new Arquivo(arquivoDTO.getNomeOriginal(), arquivoDTO.getUrl(), arquivoDTO.getTipo());
        Arquivo arquivoSalvo = arquivoService.atualizarArquivo(id, arquivo);
        ArquivoDTO arquivo_DTO = new ArquivoDTO(arquivoSalvo);

        return arquivo_DTO;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarArquivo(@PathVariable Long id) {
        arquivoService.deletarArquivo(id);
        return ResponseEntity.noContent().build();
    }
}
