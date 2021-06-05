package com.example.nastek.service;

import com.example.nastek.entities.Cliente;
import com.example.nastek.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public Cliente insert(Cliente cliente){
        return repository.save(cliente);
    }

    public List<Cliente> findAll(){
        return repository.findAll();
    }

    public Cliente findById(Long id){
        Optional<Cliente> obj = repository.findById(id);
        return obj.get();
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    /*public Cliente updateCliente(Cliente cliente){
        Cliente clienteExisting = repository.findById(cliente.getId()).orElse(null);
        clienteExisting.setNomeFantasia(cliente.getNomeFantasia());
        clienteExisting.setEndereco(cliente.getEndereco());
        clienteExisting.setTelefone(cliente.getTelefone());
        return repository.save(clienteExisting);
    }*/
}
