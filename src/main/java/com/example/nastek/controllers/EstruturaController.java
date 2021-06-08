package com.example.nastek.controllers;

import com.example.nastek.entities.Estrutura;
import com.example.nastek.service.EstruturaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/estruturas")
public class EstruturaController {

    @Autowired
    private final EstruturaService service;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Estrutura> insert(@Valid @RequestBody Estrutura estrutura){
        estrutura = service.insert(estrutura);
        return ResponseEntity.ok().body(estrutura);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Estrutura>> buscarEstruturas(){
        List<Estrutura> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estrutura> findById(@PathVariable Long id){
        Estrutura estrutura = service.findById(id);
        return ResponseEntity.ok().body(estrutura);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estrutura> update(@PathVariable Long id, @RequestBody Estrutura estrutura){
        estrutura = service.update(id, estrutura);
        return ResponseEntity.ok().body(estrutura);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        Estrutura estrutura = service.findById(id);
        if(estrutura != null){
            service.delete(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.noContent().build();
    }
}
