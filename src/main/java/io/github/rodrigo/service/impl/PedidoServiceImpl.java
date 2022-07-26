package io.github.rodrigo.service.impl;

import io.github.rodrigo.domain.entity.Cliente;
import io.github.rodrigo.domain.entity.ItemPedido;
import io.github.rodrigo.domain.entity.Pedido;
import io.github.rodrigo.domain.entity.Produto;
import io.github.rodrigo.domain.repository.Clientes;
import io.github.rodrigo.domain.repository.ItemsPedido;
import io.github.rodrigo.domain.repository.Pedidos;
import io.github.rodrigo.domain.repository.Produtos;
import io.github.rodrigo.exception.RegraNegocioException;
import io.github.rodrigo.rest.dto.ItemPedidoDTO;
import io.github.rodrigo.rest.dto.PedidoDTO;
import io.github.rodrigo.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PedidoServiceImpl implements PedidoService {
    private final Pedidos repository;
    private final Clientes clientesRepository;
    private final Produtos produtosRepository;
    private final ItemsPedido itemsPedidoRepository;

    @Override
    @Transactional
    public Pedido salvar( PedidoDTO dto ) {
        Integer idCliente = dto.getCliente();
        Cliente cliente = clientesRepository
                .findById(idCliente)
                .orElseThrow( () -> new RegraNegocioException("codigo de cliente invalido."));

        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);

        List<ItemPedido> itemsPedido = converterItems(pedido, dto.getItems());
        repository.save(pedido);
        itemsPedidoRepository.saveAll(itemsPedido);
        pedido.setItens(itemsPedido);

        return pedido;
    }

    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer id) {
        return repository.findByIdFetchItens(id);
    }

    private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> items){
        if (items.isEmpty()){
            throw new RegraNegocioException("nao e possivel realizar um pedido sem item.");
        }

        return items
                .stream()
                .map( dto -> {
                  Integer idProduto = dto.getProduto();
                  Produto produto = produtosRepository
                          .findById(idProduto)
                          .orElseThrow( () -> new RegraNegocioException("codigo de produto invalido: "+ idProduto));

                  ItemPedido itemPedido = new ItemPedido();
                  itemPedido.setQuantidade(dto.getQuantidade());
                  itemPedido.setPedido(pedido);
                  itemPedido.setProduto(produto);
                  return itemPedido;
                }).collect(Collectors.toList());
    }


}
