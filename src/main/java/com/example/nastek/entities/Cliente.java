package com.example.nastek.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Entity
@Table
@NoArgsConstructor
@Data
public class Cliente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToMany(mappedBy = "cliente")
    private List<Linha> linhas;

    public Cliente(Long id, @NotEmpty(message = "CNPJ não pode ser vazio.") @CNPJ String cnpj, @NotBlank String razaoSocial, @NotBlank String nomeFantasia, @NotBlank String endereco, @NotBlank String telefone) {
        this.id = id;
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
        this.endereco = endereco;
        this.telefone = telefone;
    }
}
