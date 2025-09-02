package br.com.puctech.minerva_student_app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

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

    public Disciplina() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getArquivada() {
        return arquivada;
    }

    public void setArquivada(Boolean arquivada) {
        this.arquivada = arquivada;
    }

    public Double getMediaNecessaria() {
        return mediaNecessaria;
    }

    public void setMediaNecessaria(Double mediaNecessaria) {
        this.mediaNecessaria = mediaNecessaria;
    }

    public Double getMediaAtual() {
        return mediaAtual;
    }

    public void setMediaAtual(Double mediaAtual) {
        this.mediaAtual = mediaAtual;
    }

    public Integer getCreditos() {
        return creditos;
    }

    public void setCreditos(Integer creditos) {
        this.creditos = creditos;
    }

    public Integer getFaltasRestantes() {
        return faltasRestantes;
    }

    public void setFaltasRestantes(Integer faltasRestantes) {
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
