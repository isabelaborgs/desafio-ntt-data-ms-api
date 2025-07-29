package br.com.isabela.productorder.pedidos.service;

import br.com.isabela.productorder.pedidos.client.ProdutoClient;
import br.com.isabela.productorder.pedidos.dto.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PedidoService {

    private final ProdutoClient produtoClient;

    public PedidoService(ProdutoClient produtoClient) {
        this.produtoClient = produtoClient;
    }

    public PedidoResponseDto criarPedido(PedidoRequestDto request) {
        // Valida que produtos existem e obtém detalhes
        List<ItemPedidoResponseDto> itensPedido = request.getItens().stream()
                .map(item -> {
                    ProdutoDto produto = produtoClient.buscarProdutoPorId(item.getIdProduto());

                    if (produto == null) {
                        throw new RuntimeException("Produto não encontrado com a ID: " + item.getIdProduto());
                    }

                    return new ItemPedidoResponseDto(
                            produto.getId(),
                            item.getQuantidade(),
                            produto.getNome(),
                            produto.getPreco(),
                            produto.getPreco() * item.getQuantidade()
                    );
                })
                .toList();

        // Calcula valor total do pedido
        double totalPedido = itensPedido.stream()
                .mapToDouble(ItemPedidoResponseDto::getSubtotal)
                .sum();

        // Cria e retorna resposta
        return new PedidoResponseDto(
                itensPedido,
                totalPedido,
                LocalDateTime.now()
        );
    }

}
