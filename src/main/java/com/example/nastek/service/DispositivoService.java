package com.example.nastek.service;

import com.example.nastek.entities.Dispositivo;
import com.example.nastek.entities.Dispositivo;
import com.example.nastek.repositories.DispositivoRepository;
import com.example.nastek.repositories.DispositivoRepository;
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
public class DispositivoService {

    @Autowired
    private DispositivoRepository repository;

    public Dispositivo insert(Dispositivo dispositivo) {
        return repository.save(dispositivo);
    }

    public List<Dispositivo> findAll() {
        return repository.findAll();
    }

    public Dispositivo findById(Long id) {
        Optional<Dispositivo> obj = repository.findById(id);
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

    public Dispositivo update(Long id, Dispositivo dispositivo) {
        try {
            Dispositivo entity = repository.getById(id);
            updateData(entity, dispositivo);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Dispositivo entity, Dispositivo dispositivo) {
        entity.setStatus(dispositivo.getStatus());
    }
}
