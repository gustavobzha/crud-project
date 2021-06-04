package com.example.nastek.controllers;

import com.example.nastek.entities.Cliente;
import com.example.nastek.service.ClienteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
public class ClienteController {


    private final ClienteService service = new ClienteService();

    @PostMapping("/addCliente")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente adicionarCliente(@Valid @RequestBody Cliente cliente){
        return service.saveCliente(cliente);
    }

    @GetMapping
    public List<Cliente> buscarClientes(){
        return service.getClientes();
    }

    @GetMapping("/{ClienteId}")
    public Cliente buscar(@PathVariable Long id){
        return service.getClienteById(id);
    }

    @PutMapping("/{ClienteId}")
    public Cliente atualizar(@RequestBody Cliente cliente){
        return service.updateCliente(cliente);
    }

    @DeleteMapping("/{ClienteId}")
    public String deleteCliente(@PathVariable Long id){
        return service.deleteCliente(id);
    }
}
