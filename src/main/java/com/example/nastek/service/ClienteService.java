package com.example.nastek.service;

import com.example.nastek.entities.Cliente;
import com.example.nastek.repositories.ClienteRepository;
import com.example.nastek.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public Cliente insert(Cliente cliente) {
        return repository.save(cliente);
    }

    public List<Cliente> findAll() {
        return repository.findAll();
    }

    public Cliente findById(Long id) {
        Optional<Cliente> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Cliente update(Long id, Cliente cliente) {
        Cliente entity = repository.getById(id);
        updateData(entity, cliente);
        return repository.save(entity);
    }

    private void updateData(Cliente entity, Cliente cliente) {
        entity.setNomeFantasia(cliente.getNomeFantasia());
        entity.setRazaoSocial(cliente.getRazaoSocial());
        entity.setEndereco(cliente.getEndereco());
        entity.setTelefone(cliente.getTelefone());
    }
}
