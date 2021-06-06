package com.example.nastek.repositories;

import com.example.nastek.entities.Linha;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinhaRepository extends JpaRepository<Linha, Long> {
}
