package br.com.isabela.productorder.pedidos.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemPedidoRequestDto {
    private Long idProduto;
    private Integer quantidade;
}
