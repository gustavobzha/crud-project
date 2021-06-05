package com.example.nastek.entities;

import com.example.nastek.enums.StatusDispositivo;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@Table
@AllArgsConstructor
@Data
public class Dispositivo implements Serializable {
    @Id
    private String numeroSerie;

    @NotBlank
    private String modelo;


    private StatusDispositivo status = StatusDispositivo.DESATIVADO;

    @OneToOne
    private Estrutura estrutura;

    public Dispositivo() {

    }
}
