package io.github.rodrigo.rest.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

/**
 * {
 *     "cliente" : 1,
 *     "total" : 100,
 *     "items" :[
 *         {
 *             "produto" : 1,
 *             "quantidade" : 10
 *         }
 *     ]
 * }
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {
    @NotNull(message = "Informe o código do cliente")
    private Integer cliente;

    @NotNull(message = "O campo Total do pedido é obrigatório.")
    private BigDecimal total;
    private List<ItemPedidoDTO> items;
}
