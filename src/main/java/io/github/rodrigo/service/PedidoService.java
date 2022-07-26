package io.github.rodrigo.service;

import io.github.rodrigo.domain.entity.Pedido;
import io.github.rodrigo.rest.dto.PedidoDTO;

public interface PedidoService {
    Pedido salvar(PedidoDTO dto);
}
