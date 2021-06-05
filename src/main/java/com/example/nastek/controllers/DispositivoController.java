package com.example.nastek.controllers;

import com.example.nastek.entities.Dispositivo;
import com.example.nastek.service.DispositivoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/dispositivos")
public class DispositivoController {


    @Autowired
    private final DispositivoService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Dispositivo> insert(@Valid @RequestBody Dispositivo dispositivo){
        dispositivo = service.insert(dispositivo);
        return ResponseEntity.ok().body(dispositivo);
    }

    @GetMapping
    public ResponseEntity<List<Dispositivo>> buscarDispositivos(){
        List<Dispositivo> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dispositivo> findById(@PathVariable String id){
        Dispositivo dispositivo = service.findById(id);
        return ResponseEntity.ok().body(dispositivo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dispositivo> update(@PathVariable String id, @RequestBody Dispositivo dispositivo){
        dispositivo = service.update(id, dispositivo);
        return ResponseEntity.ok().body(dispositivo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
