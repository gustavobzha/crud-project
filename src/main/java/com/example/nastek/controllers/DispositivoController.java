package com.example.nastek.controllers;

import com.example.nastek.entities.Dispositivo;
import com.example.nastek.service.DispositivoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
public class DispositivoController {


    private final DispositivoService service;

    @PostMapping("/addDispositivo")
    @ResponseStatus(HttpStatus.CREATED)
    public Dispositivo adicionarDispositivo(@Valid @RequestBody Dispositivo dispositivo) {
        return service.saveDispositivo(dispositivo);
    }

    @GetMapping("/{DispositivoId}")
    public Dispositivo buscarDispositivo(@PathVariable String id) {
        return service.getDispositivoById(id);
    }

    @PutMapping("/{DispositivoId}")
    public Dispositivo atualizarDispositivo(@RequestBody Dispositivo dispositivo) {
        return service.updateDispositivo(dispositivo);
    }

    @DeleteMapping("/{DispositivoId}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteDispositivo(@PathVariable String id) {
        return service.deleteDispositivo(id);
    }
}
