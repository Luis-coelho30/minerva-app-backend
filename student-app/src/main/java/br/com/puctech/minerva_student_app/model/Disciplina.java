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
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //usuarioId - Long
    private String nome;
    private String descricao;
    private Boolean arquivada;
    private Double mediaNecessaria;
    private Double mediaAtual;
    private Integer creditos;
    private Integer faltasRestantes;
    //criadoEm - datetime

    public Disciplina(String nome, String descricao, Boolean arquivada, Double mediaNecessaria, Double mediaAtual, Integer creditos, Integer faltasRestantes) {
        this.nome = nome;
        this.descricao = descricao;
        this.arquivada = arquivada;
        this.mediaNecessaria = mediaNecessaria;
        this.mediaAtual = mediaAtual;
        this.creditos = creditos;
        this.faltasRestantes = faltasRestantes;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Disciplina that = (Disciplina) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}