package io.github.rodrigo.domain.repository;

import io.github.rodrigo.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Pedidos extends JpaRepository<Pedido, Integer> {
}
