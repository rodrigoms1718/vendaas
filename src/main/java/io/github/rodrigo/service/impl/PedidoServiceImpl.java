package io.github.rodrigo.service.impl;

import io.github.rodrigo.domain.entity.Produto;
import io.github.rodrigo.domain.repository.Pedidos;
import io.github.rodrigo.service.PedidoService;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl implements PedidoService {
    private Pedidos repository;

    public PedidoServiceImpl(Pedidos repository) {
        this.repository = repository;
    }
}
