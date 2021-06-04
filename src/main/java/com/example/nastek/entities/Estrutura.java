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
    @JoinTable(name = "Table_Join_Linha_Estrutura")
    private Linha linha;

    @OneToOne(mappedBy = "estrutura")
    private Dispositivo faseA;

    @OneToOne(mappedBy = "estrutura")
    private Dispositivo faseB;

    @OneToOne(mappedBy = "estrutura")
    private Dispositivo faseC;

    public Estrutura() {

    }
}
