package com.example.nastek.service;

import com.example.nastek.entities.Linha;
import com.example.nastek.repositories.LinhaRepository;
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
public class LinhaService {

    @Autowired
    private LinhaRepository repository;

    public Linha insert(Linha linha) {
        return repository.save(linha);
    }

    public List<Linha> findAll() {
        return repository.findAll();
    }

    public Linha findById(Long id) {
        Optional<Linha> obj = repository.findById(id);
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

    public Linha update(Long id, Linha linha) {
        try {
            Linha entity = repository.getById(id);
            updateData(entity, linha);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    public void updateData(Linha entity, Linha linha) {
        entity.setEstruturas(linha.getEstruturas());
    }
}
