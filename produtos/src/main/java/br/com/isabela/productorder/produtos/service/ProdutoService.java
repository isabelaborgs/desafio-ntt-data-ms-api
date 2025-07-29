package br.com.isabela.productorder.produtos.service;

import br.com.isabela.productorder.produtos.model.Produto;

import java.util.List;

public interface ProdutoService {
    List<Produto> listarTodos();
    Produto buscarPorId(Long id);
    Produto salvar(Produto produto);
    void remover(Long id);

    List<Produto> buscarPorNome(String nome);
    List<Produto> buscarPorPrecoMaximo(Double preco);

}
