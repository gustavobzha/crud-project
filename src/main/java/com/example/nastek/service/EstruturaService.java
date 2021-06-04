package com.example.nastek.service;

import com.example.nastek.entities.Estrutura;
import com.example.nastek.repositories.EstruturaRepository;

import java.util.List;

public class EstruturaService {
    private EstruturaRepository repository;

    public Estrutura saveEstrutura(Estrutura struct){
        return repository.save(struct);
    }

    public List<Estrutura> getEstruturas(){
        return repository.findAll();
    }

    public Estrutura getEstruturaById(String id){
        return repository.findById(id).orElse(null);
    }

    public String deleteEstrutura(String id){
        repository.deleteById(id);
        return "Estrutura removida" + id;
    }

    public Estrutura updateEstrutura(Estrutura struct){
        Estrutura existingStruct = repository.findById(struct.getId()).orElse(null);
        existingStruct.setFaseA(struct.getFaseA());
        existingStruct.setFaseB(struct.getFaseB());
        existingStruct.setFaseC(struct.getFaseC());
        return repository.save(existingStruct);
    }
}
