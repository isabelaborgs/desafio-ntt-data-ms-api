package br.com.isabela.productorder.produtos.service;

import br.com.isabela.productorder.produtos.model.Produto;
import br.com.isabela.productorder.produtos.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoServiceImpl(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    // Operações CRUD básicas
    @Override
    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    @Override
    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com ID: " + id));
    }

    @Override
    public Produto salvar(Produto produto){
        return produtoRepository.save(produto);
    }

    @Override
    public void remover(Long id){
        if (!produtoRepository.existsById(id)) {
            throw new RuntimeException("Produto não encontrado com ID: " + id);
        }
        produtoRepository.deleteById(id);
    }

    // Métodos de consulta personalizados
    @Override
    public List<Produto> buscarPorNome(String nome) {
        return produtoRepository.findByNomeContainingIgnoreCase(nome);
    }

    @Override
    public List<Produto> buscarPorPrecoMaximo(Double preco) {
        return produtoRepository.findByPrecoLessThanEqual(preco);
    }
}
