package com.example.nastek.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
@AllArgsConstructor
@Data
public class Estrutura implements Serializable {
    @Id
    private String id;
    private String local;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Linha linha;

    @OneToOne
    @JoinColumn(name = "FASE_A")
    private Dispositivo faseA;

    @OneToOne
    @JoinColumn(name = "FASE_B")
    private Dispositivo faseB;

    @OneToOne
    @JoinColumn(name = "FASE_C")
    private Dispositivo faseC;

    public Estrutura() {

    }
}
