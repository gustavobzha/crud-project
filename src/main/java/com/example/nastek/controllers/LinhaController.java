package com.example.nastek.controllers;

import com.example.nastek.entities.Linha;
import com.example.nastek.service.LinhaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
public class LinhaController {


    private final LinhaService service = new LinhaService();

    @PostMapping("/addLinha")
    @ResponseStatus(HttpStatus.CREATED)
    public Linha adicinarLinha(@Valid @RequestBody Linha linha) {
        return service.saveLinha(linha);
    }

    @GetMapping("/{LinhaId}")
    public Linha buscarLinha(@PathVariable String id) {
        return service.getLinhaById(id);
    }

    @PutMapping("/{LinhaId}")
    public Linha atualizarLinha(@RequestBody Linha linha) {
        return service.updateLinha(linha);
    }

    @DeleteMapping("/{LinhaId}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteLinha(@PathVariable String id) {
        return service.deleteLinha(id);
    }
}
