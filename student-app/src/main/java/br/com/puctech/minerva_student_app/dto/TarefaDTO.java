package br.com.puctech.minerva_student_app.dto;

import br.com.puctech.minerva_student_app.model.Tarefa;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@NoArgsConstructor
public class TarefaDTO {

    private Long id;
    private String titulo;
    private String descricao;
    private String status;
    private String prioridade;
    private Boolean arquivada;

    public TarefaDTO(Tarefa tarefa) {
        BeanUtils.copyProperties(tarefa, this);
    }
}
