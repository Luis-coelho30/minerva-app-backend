package br.com.puctech.minerva_student_app.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USUARIO_ID", nullable = false)
    private Usuario usuario;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DISCIPLINA_ID")
    private Disciplina disciplina;
    private String titulo;
    private String descricao;
    private String status;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFinal;
    private LocalDateTime concluido_em;
    private String prioridade;
    private Boolean arquivada;

    public Tarefa(Usuario usuario, String titulo, String descricao, String status, String prioridade, Boolean arquivada) {
        this.usuario = usuario;
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