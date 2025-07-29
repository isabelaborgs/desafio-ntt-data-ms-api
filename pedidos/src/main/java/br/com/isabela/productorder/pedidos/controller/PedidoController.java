package br.com.isabela.productorder.pedidos.controller;

import br.com.isabela.productorder.pedidos.dto.PedidoRequestDto;
import br.com.isabela.productorder.pedidos.dto.PedidoResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.isabela.productorder.pedidos.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    /*
    @GetMapping
    public ResponseEntity<List<ProdutoDto>> listarProdutos() {
        return ResponseEntity.ok(pedidoService.listarProdutos());
    }*/

    @PostMapping
    public ResponseEntity<PedidoResponseDto> criarPedido(@RequestBody PedidoRequestDto request){
        try {
            PedidoResponseDto pedido = pedidoService.criarPedido(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(pedido);
        } catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }
}
