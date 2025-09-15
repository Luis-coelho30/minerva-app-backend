package br.com.puctech.minerva_student_app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //usuarioId - Long
    //disciplinaId - Long
    private String titulo;
    private String descricao;
    private String status;
    //dataInicio - datetime
    //dataFinal - datetime
    //concluidoEm - datetime
    private String prioridade;
    private Boolean arquivada;

    public Tarefa(String titulo, String descricao, String status, String prioridade, Boolean arquivada) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.status = status;
        this.prioridade = prioridade;
        this.arquivada = arquivada;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Tarefa tarefa = (Tarefa) o;
        return Objects.equals(getId(), tarefa.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}