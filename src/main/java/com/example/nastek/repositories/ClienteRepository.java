package com.example.nastek.repositories;

import com.example.nastek.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    /*@Query(value ="SELECT * FROM cliente;", nativeQuery = true)
    List<Cliente> buscarTodosClientes();*/
}
