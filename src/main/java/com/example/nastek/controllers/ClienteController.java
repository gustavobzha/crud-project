package com.example.nastek.controllers;

import com.example.nastek.entities.Cliente;
import com.example.nastek.entities.Dto;
import com.example.nastek.entities.Linha;
import com.example.nastek.service.ClienteService;
import com.example.nastek.service.LinhaService;
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

    @Autowired
    private final LinhaService linhaService;


    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Cliente> insert(@Valid @RequestBody Cliente cliente){
        cliente = service.insert(cliente);
        return ResponseEntity.ok().body(cliente);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Cliente>> buscarClientes(){
        List<Cliente> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable Long id){
        Cliente cliente = service.findById(id);
        return ResponseEntity.ok().body(cliente);
    }

    @PostMapping("/addLinha")
    public ResponseEntity<Cliente> adicionarLinha(@RequestBody Dto dto ){
        Cliente cliente = service.findById(dto.getIdEntidadePai());
        Linha linha = linhaService.findById(dto.getIdEntidadeFilho());
        linha.setCliente(cliente);
        cliente.getLinhas().add(linha);
        cliente = service.insert(cliente);
        return ResponseEntity.ok().body(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> update(@PathVariable Long id, @RequestBody Cliente cliente){
        cliente = service.update(id, cliente);
        return ResponseEntity.ok().body(cliente);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        Cliente cliente = service.findById(id);
        if(cliente != null){
            service.delete(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.noContent().build();
    }
}
