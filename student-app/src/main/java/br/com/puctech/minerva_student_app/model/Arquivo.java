package br.com.puctech.minerva_student_app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Entity
public class Arquivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //usuarioId - Long
    //disciplinaId - Long
    private String nomeOriginal;
    private String url;
    private String tipo;
    //criadoEm - datetime

    public Arquivo(){

    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Arquivo arquivo = (Arquivo) o;
        return Objects.equals(getId(), arquivo.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}