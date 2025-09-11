package br.com.puctech.minerva_student_app.dto;

import br.com.puctech.minerva_student_app.model.Arquivo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@NoArgsConstructor
public class ArquivoDTO {

    private Long id;
    private String nomeOriginal;
    private String url;
    private String tipo;

    public ArquivoDTO(Arquivo arquivo) {
        BeanUtils.copyProperties(arquivo, this);
    }
}
