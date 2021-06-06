package com.example.nastek;

import com.example.nastek.entities.Cliente;
import com.example.nastek.entities.Linha;
import com.example.nastek.service.ClienteService;
import com.example.nastek.service.LinhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NastekApplication {

    public static void main(String[] args) {
        SpringApplication.run(NastekApplication.class, args);
    }


}
