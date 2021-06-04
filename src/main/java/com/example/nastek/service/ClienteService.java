package com.example.nastek.service;

import com.example.nastek.entities.Cliente;
import com.example.nastek.repositories.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private ClienteRepository repository;

    public Cliente saveCliente(Cliente cliente){
        return repository.save(cliente);
    }

    public List<Cliente> getClientes(){
        return repository.findAll();
    }

    public Cliente getClienteById(Long id){
        return repository.findById(id).orElse(null);
    }

    public String deleteCliente(Long id){
        repository.deleteById(id);
        return "Cliente removido" + id;
    }

    public Cliente updateCliente(Cliente cliente){
        Cliente clienteExisting = repository.findById(cliente.getId()).orElse(null);
        clienteExisting.setNomeFantasia(cliente.getNomeFantasia());
        clienteExisting.setEndereco(cliente.getEndereco());
        clienteExisting.setTelefone(cliente.getTelefone());
        return repository.save(clienteExisting);
    }
}
