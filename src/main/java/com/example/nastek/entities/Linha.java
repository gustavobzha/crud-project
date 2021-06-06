package com.example.nastek.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Entity
@Table
@NoArgsConstructor
@Data
public class Linha implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String cidade;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Cliente cliente;

    @OneToMany(mappedBy = "linha")
    private List<Estrutura> estruturas;

    public Linha(Long id, @NotBlank String cidade, Cliente cliente, List<Estrutura> estruturas) {
        this.id = id;
        this.cidade = cidade;
        this.cliente = cliente;
        this.estruturas = estruturas;
    }
}
