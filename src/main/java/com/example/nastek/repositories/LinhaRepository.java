package com.example.nastek.repositories;

import com.example.nastek.entities.Linha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.*;
import java.util.List;

public interface LinhaRepository extends JpaRepository<Linha, Long> {



    @Query("SELECT l FROM Linha l WHERE l.cliente.id = ?1")
    List<Linha> buscarLinhasCliente(Long id);


}