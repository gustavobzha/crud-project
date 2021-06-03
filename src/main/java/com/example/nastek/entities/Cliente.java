package com.example.nastek.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table
@AllArgsConstructor
@Data
public class Cliente {
    @Id
    @GeneratedValue
    private long id;

    @NotEmpty(message = "CNPJ não pode ser vazio.")
    @CNPJ
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


}
