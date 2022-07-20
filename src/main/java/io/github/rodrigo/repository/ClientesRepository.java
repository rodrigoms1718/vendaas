package io.github.rodrigo.repository;

import io.github.rodrigo.model.Cliente;
import org.springframework.stereotype.Repository;

@Repository
public class ClientesRepository {
    public void persistir(Cliente cliente) {
        //acessa a base e salva o cliente
    }
}
