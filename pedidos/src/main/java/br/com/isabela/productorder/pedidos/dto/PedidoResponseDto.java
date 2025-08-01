package br.com.isabela.productorder.pedidos.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoResponseDto {
    private List<ItemPedidoResponseDto> itens;
    private Double valorTotal;
    private LocalDateTime dataPedido;
}
