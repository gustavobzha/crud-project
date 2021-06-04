package com.example.nastek.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@AllArgsConstructor
@Data
public class Estrutura {
    @Id
    private String id;
    private String local;

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
