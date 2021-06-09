package com.example.nastek.controllers;

import com.example.nastek.entities.Linha;
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
@RequestMapping("/linhas")
public class LinhaController {


    @Autowired
    private final LinhaService service;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Linha> insert(@Valid @RequestBody Linha linha){
        linha = service.insert(linha);
        return ResponseEntity.ok().body(linha);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Linha>> buscarLinhas(){
        List<Linha> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Linha> findById(@PathVariable Long id){
        Linha linha = service.findById(id);
        return ResponseEntity.ok().body(linha);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Linha> update(@PathVariable Long id, @RequestBody Linha linha){
        linha = service.update(id, linha);
        return ResponseEntity.ok().body(linha);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        Linha linha = service.findById(id);
        if(linha != null){
            service.delete(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/cliente/{id}")
    public ResponseEntity<List<Linha>> buscarLinhasCliente(@PathVariable Long id){
        List<Linha> list = service.buscarLinhasCliente(id);
        return ResponseEntity.ok().body(list);
    }
}
