package com.example.nastek.entities;

import com.example.nastek.enums.StatusDispositivo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;


@Entity
@Table
@NoArgsConstructor
@Data
public class Dispositivo implements Serializable {
    @Id
    private Long numeroSerie;

    @NotBlank
    private String modelo;

    private StatusDispositivo status = StatusDispositivo.DESATIVADO;

    @JsonIgnore
    @OneToOne
    private Estrutura estrutura;

    public Dispositivo(Long numeroSerie, @NotBlank String modelo, StatusDispositivo status, Estrutura estrutura) {
        this.numeroSerie = numeroSerie;
        this.modelo = modelo;
        this.status = status;
    }
}
