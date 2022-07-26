package io.github.rodrigo.service;

import io.github.rodrigo.domain.entity.Pedido;
import io.github.rodrigo.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {
    Pedido salvar(PedidoDTO dto);
    Optional<Pedido> obterPedidoCompleto(Integer id);
}
