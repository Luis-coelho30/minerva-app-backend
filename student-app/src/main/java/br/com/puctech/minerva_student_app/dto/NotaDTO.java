package br.com.puctech.minerva_student_app.dto;

import br.com.puctech.minerva_student_app.model.Nota;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@NoArgsConstructor
public class NotaDTO {

    private Long id;
    private String descricao;
    private Double valor;
    private Integer peso;

    public NotaDTO(Nota nota) {
        BeanUtils.copyProperties(nota, this);
    }
}
