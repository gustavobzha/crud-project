package com.example.nastek.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@AllArgsConstructor
@Data
public class Linha {
    @Id
    private String cidade;

    @ManyToOne(fetch = FetchType.LAZY)
    private Cliente cliente;

    @OneToMany(mappedBy = "linha", orphanRemoval = true)
    private List<Estrutura> estruturas;

    public Linha() {

    }
}
