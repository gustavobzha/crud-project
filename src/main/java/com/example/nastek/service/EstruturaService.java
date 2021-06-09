package com.example.nastek.service;

import com.example.nastek.entities.Dispositivo;
import com.example.nastek.entities.Estrutura;
import com.example.nastek.entities.Linha;
import com.example.nastek.enums.StatusDispositivo;
import com.example.nastek.repositories.DispositivoRepository;
import com.example.nastek.repositories.EstruturaRepository;
import com.example.nastek.service.exceptions.DatabaseException;
import com.example.nastek.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class EstruturaService {

    @Autowired
    private EstruturaRepository repository;

    @Autowired
    DispositivoRepository dr;

    public Estrutura insert(Estrutura estrutura) {
        return repository.save(estrutura);
    }

    public List<Estrutura> findAll() {
        return repository.findAll();
    }

    public Estrutura findById(Long id) {
        Optional<Estrutura> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public Estrutura update(Long id, Estrutura estrutura) {
        try {
            Estrutura entity = repository.getById(id);
            updateData(entity, estrutura);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Estrutura entity, Estrutura estrutura) {
        entity.setFaseA(estrutura.getFaseA());
        entity.setFaseB(estrutura.getFaseB());
        entity.setFaseC(estrutura.getFaseC());
    }

    public void adicionarDispositivos(Estrutura estrutura, Dispositivo faseA, Dispositivo faseB, Dispositivo faseC){
        faseA.setEstrutura(estrutura);
        faseB.setEstrutura(estrutura);
        faseC.setEstrutura(estrutura);
        faseA.setStatus(StatusDispositivo.ATIVADO);
        faseB.setStatus(StatusDispositivo.ATIVADO);
        faseC.setStatus(StatusDispositivo.ATIVADO);
        dr.save(faseA);
        dr.save(faseB);
        dr.save(faseC);
        estrutura.setFaseA(faseA);
        estrutura.setFaseB(faseB);
        estrutura.setFaseC(faseC);
        repository.save(estrutura);
    }

    public List<Estrutura> buscarEstruturasLinha(Long id){
        return repository.buscarEstruturasLinha(id);
    }


}
