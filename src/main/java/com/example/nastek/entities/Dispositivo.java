package com.example.nastek.entities;

import com.example.nastek.enums.StatusDispositivo;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
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

    @NotEmpty
    private StatusDispositivo status;

    @OneToOne
    private Estrutura estrutura;

    public Dispositivo() {

    }
}
