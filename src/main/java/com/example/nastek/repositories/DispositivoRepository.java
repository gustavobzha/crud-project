package com.example.nastek.repositories;

import com.example.nastek.entities.Dispositivo;
import com.example.nastek.entities.Estrutura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DispositivoRepository extends JpaRepository<Dispositivo, Long> {

    @Query("SELECT d FROM Dispositivo d WHERE d.estrutura.id is NULL")
    List<Dispositivo> buscarDispositivoSemEstrutura();
}
