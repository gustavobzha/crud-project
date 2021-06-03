package com.example.nastek.entities;

import javax.persistence.*;

@Entity
@Table
public class Estrutura {
    @Id
    private String identificacao;
    private String local;

    @ManyToOne(fetch = FetchType.LAZY)
    private Linha linha;

    @OneToOne(mappedBy = "estrutura")
    private Dispositivo faseA;

    @OneToOne(mappedBy = "estrutura")
    private Dispositivo faseB;

    @OneToOne(mappedBy = "estrutura")
    private Dispositivo faseC;

    public Estrutura(String identificacao, String local) {
        this.identificacao = identificacao;
        this.local = local;
    }

    public Estrutura() {
    }

    public String getIdentificacao() {
        return identificacao;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }


}
