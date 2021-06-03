package com.example.nastek.entities;

import com.example.nastek.enums.StatusDispositivo;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
@AllArgsConstructor
@Data
public class Dispositivo {
    @Id
    private String numeroSerie; // primary key

    private String modelo;
    private StatusDispositivo status;

    @OneToOne
    private Estrutura estrutura;

    public Dispositivo() {

    }
}
