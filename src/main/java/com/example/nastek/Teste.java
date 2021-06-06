package com.example.nastek;

import com.example.nastek.entities.Cliente;
import com.example.nastek.entities.Dispositivo;
import com.example.nastek.entities.Estrutura;
import com.example.nastek.entities.Linha;
import com.example.nastek.enums.StatusDispositivo;
import com.example.nastek.service.ClienteService;
import com.example.nastek.service.DispositivoService;
import com.example.nastek.service.EstruturaService;
import com.example.nastek.service.LinhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Teste implements CommandLineRunner {
    @Autowired
    private ClienteService service;

    @Autowired
    private LinhaService linhaService;

    @Autowired
    private DispositivoService dispositivoService;

    @Autowired
    private EstruturaService estruturaService;

    @Override
    public void run(String... args) throws Exception {

        Cliente c1 = new Cliente(null,"13258149000117", "Mercado LTDA", "Mercado", "Rua 1", "3025-53332");
        Cliente c2 = new Cliente(null,"13258149000118", "Mercearia LTDA", "Mercearia", "Rua 2", "3025-53333");
        Cliente c3 = new Cliente(null,"13258149000119", "Loja LTDA", "Loja", "Rua 1", "3025-53334");

        service.insert(c1);
        service.insert(c2);
        service.insert(c3);

        Linha l1 = new Linha(null, "Campo Grande", c1, null);
        linhaService.insert(l1);

        Dispositivo d1 = new Dispositivo(1L, "10kVA", StatusDispositivo.DESATIVADO, null);
        Dispositivo d2 = new Dispositivo(2L, "10kVA", StatusDispositivo.DESATIVADO, null);
        Dispositivo d3 = new Dispositivo(3L, "10kVA", StatusDispositivo.DESATIVADO, null);
        dispositivoService.insert(d1);
        dispositivoService.insert(d2);
        dispositivoService.insert(d3);

        Estrutura e1 = new Estrutura(null, "Rua 1", l1, d1, d2, d3);
        estruturaService.insert(e1);
        estruturaService.adicionarDispositivos(e1, d1, d2, d3);
    }
}
