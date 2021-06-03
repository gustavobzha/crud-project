package com.example.nastek.entities;

import com.example.nastek.enums.StatusDispositivo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class Dispositivo {
    @Id
    private String numeroSerie; // primary key
    private String modelo;
    private StatusDispositivo status;

    @OneToOne
    private Estrutura estrutura;


    public String getNumeroSerie() {
        return numeroSerie;
    }

    public String getModelo() {
        return modelo;
    }

    public StatusDispositivo getStatus() {
        return status;
    }

    public void setStatus(StatusDispositivo status) {
        this.status = status;
    }
}
