package com.example.nastek.controllers;

import com.example.nastek.entities.Estrutura;
import com.example.nastek.service.EstruturaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
public class EstruturaController {

    private final EstruturaService service = new EstruturaService();

    @PostMapping("/addEstrutura")
    @ResponseStatus(HttpStatus.CREATED)
    public Estrutura adionarEstrutura(@Valid @RequestBody Estrutura estrutura){
        return service.saveEstrutura(estrutura);
    }

    @GetMapping("/{EstruturaId}")
    public Estrutura buscarEstrutura(@PathVariable String id){
        return service.getEstruturaById(id);
    }

    @PutMapping("/{EstruturaId}")
    public Estrutura atualizarEstrutura(@RequestBody Estrutura estrutura){
        return service.updateEstrutura(estrutura);
    }

    @DeleteMapping("/{EstruturaId}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteEstrutura(@PathVariable String id){
        return service.deleteEstrutura(id);
    }
}
