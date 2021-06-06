package com.example.nastek.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
@NoArgsConstructor
@Data
public class Estrutura implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    public Estrutura(Long id, String local, Linha linha, Dispositivo faseA, Dispositivo faseB, Dispositivo faseC) {
        this.id = id;
        this.local = local;
        this.linha = linha;
    }
}
