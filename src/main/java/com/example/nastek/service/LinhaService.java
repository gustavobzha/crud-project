package com.example.nastek.service;

import com.example.nastek.entities.Linha;
import com.example.nastek.repositories.LinhaRepository;

import java.util.List;

public class LinhaService {

    private LinhaRepository repository;

    public Linha saveLinha(Linha struct){
        return repository.save(struct);
    }

    public List<Linha> getLinhas(){
        return repository.findAll();
    }

    public Linha getLinhaById(String id){
        return repository.findById(id).orElse(null);
    }

    public String deleteLinha(String id){
        repository.deleteById(id);
        return "Linha removida" + id;
    }

    public Linha updateLinha(Linha linha){
        Linha existingLine = repository.findById(linha.getCidade()).orElse(null);
        return repository.save(existingLine);
    }
}
