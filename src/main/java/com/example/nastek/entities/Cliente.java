package com.example.nastek.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Entity
@Table
@AllArgsConstructor
@Data
public class Cliente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @NotEmpty(message = "CNPJ não pode ser vazio.")
    private String cnpj;

    @NotBlank
    private String razaoSocial;

    @NotBlank
    private String nomeFantasia;

    @NotBlank
    private String endereco;

    @NotBlank
    private String telefone;

    @OneToMany(mappedBy = "cliente", orphanRemoval = true)
    private List<Linha> linhas;

    public Cliente() {
    }

    public Cliente(Long id, @NotEmpty(message = "CNPJ não pode ser vazio.") @CNPJ String cnpj, @NotBlank String razaoSocial, @NotBlank String nomeFantasia, @NotBlank String endereco, @NotBlank String telefone) {
        this.id = id;
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
        this.endereco = endereco;
        this.telefone = telefone;
    }
}
