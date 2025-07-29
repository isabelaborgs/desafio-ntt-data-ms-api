package br.com.isabela.productorder.pedidos.client;

import br.com.isabela.productorder.pedidos.dto.ProdutoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "produtos-service")
public interface ProdutoClient {

    @GetMapping("/produtos")
    List<ProdutoDto> listarProdutos();

    @GetMapping("/produtos/{id}")
    ProdutoDto buscarProdutoPorId(@PathVariable Long id);
}
