package com.example.nastek.repositories;

import com.example.nastek.entities.Estrutura;
import com.example.nastek.entities.Linha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EstruturaRepository extends JpaRepository<Estrutura, Long> {

    @Query("SELECT e FROM Estrutura e WHERE e.linha.id = ?1")
    List<Estrutura> buscarEstruturasLinha(Long id);
}
