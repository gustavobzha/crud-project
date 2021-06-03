package com.example.nastek.entities;

import javax.persistence.*;

@Entity
public class Linha {
    @Id
    private String cidade;

    @ManyToOne(fetch = FetchType.LAZY)
    private Cliente cliente;

    @OneToMany(mappedBy = "linha", orphanRemoval = true)
    private Estrutura estrutura;
}
