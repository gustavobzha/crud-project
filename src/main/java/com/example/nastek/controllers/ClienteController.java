package com.example.nastek.controllers;

import com.example.nastek.entities.Cliente;
import com.example.nastek.service.ClienteService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {


//    private final ClienteService service = new ClienteService();
    @Autowired
    private final ClienteService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Cliente> insert(@Valid @RequestBody Cliente cliente){
        cliente = service.insert(cliente);
        return ResponseEntity.ok().body(cliente);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> buscarClientes(){
        List<Cliente> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable Long id){
        Cliente cliente = service.findById(id);
        return ResponseEntity.ok().body(cliente);
    }

    /*@PutMapping
    public Cliente atualizar(@RequestBody Cliente cliente){
        return service.updateCliente(cliente);
    }*/

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
