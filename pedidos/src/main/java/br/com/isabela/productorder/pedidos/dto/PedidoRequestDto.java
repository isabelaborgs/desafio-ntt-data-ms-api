package br.com.isabela.productorder.pedidos.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoRequestDto {
    private List<ItemPedidoRequestDto> itens;
}
