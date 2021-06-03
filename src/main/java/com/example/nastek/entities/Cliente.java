package com.example.nastek.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.Map;

@Entity
@Table
public class Cliente {
    @Id
    private String cnpj; // primary key
    private String razaoSocial;
    private String nomeFantasia;
    private String endereco;
    private String telefone;

    @OneToMany(mappedBy = "cliente", orphanRemoval = true)
    private Linha linha;

    public Cliente(String cnpj, String razaoSocial, String nomeFantasia, String endereco, String telefone) {
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    public Cliente() {
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
