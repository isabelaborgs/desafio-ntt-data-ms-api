package br.com.isabela.productorder.pedidos.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedidoResponseDto {
    private Long idProduto;
    private Integer quantidade;
    private String nomeProduto;
    private Double preco;
    private Double subtotal;

}
