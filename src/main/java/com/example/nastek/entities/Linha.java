package com.example.nastek.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table
@AllArgsConstructor
@Data
public class Linha implements Serializable {
    @Id
    private String cidade;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Cliente cliente;

    @OneToMany(mappedBy = "linha", orphanRemoval = true)
    private List<Estrutura> estruturas;

    public Linha() {

    }
}
