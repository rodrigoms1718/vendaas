package io.github.rodrigo.domain.repository;

import io.github.rodrigo.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Produtos extends JpaRepository<Produto, Integer> {
}
