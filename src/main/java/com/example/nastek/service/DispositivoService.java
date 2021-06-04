package com.example.nastek.service;

import com.example.nastek.entities.Dispositivo;
import com.example.nastek.repositories.DispositivoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DispositivoService {
    
    private DispositivoRepository repository;
    
    public Dispositivo saveDispositivo(Dispositivo device){
        return repository.save(device);
    }

    public List<Dispositivo> getDispositivos(){
        return repository.findAll();
    }

    public Dispositivo getDispositivoById(String id){
        return repository.findById(id).orElse(null);
    }

    public String deleteDispositivo(String id){
        repository.deleteById(id);
        return "Dispositivo removido" + id;
    }

    public Dispositivo updateDispositivo(Dispositivo device){
        Dispositivo existingDevice = repository.findById(device.getNumeroSerie()).orElse(null);
        existingDevice.setStatus(device.getStatus());
        return repository.save(existingDevice);
    }
}
