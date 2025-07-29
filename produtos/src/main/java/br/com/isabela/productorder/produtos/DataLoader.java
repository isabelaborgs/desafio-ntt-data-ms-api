package br.com.isabela.productorder.produtos;

import br.com.isabela.productorder.produtos.model.Produto;
import br.com.isabela.productorder.produtos.repository.ProdutoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final ProdutoRepository produtoRepository;
    public DataLoader(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public void run(String... args) {
        System.out.println("\n====== INICIALIZANDO CATÁLOGO DE PRODUTOS ======\n");
        System.out.println("Adicionando produtos...");
        List<Produto> produtos = Arrays.asList(
                new Produto("Camiseta Preta Básica", "Camiseta preta 100% algodão", 59.90),
                new Produto("Legging Esportiva", "Calça legging com tecido de alta compressão", 189.99),
                new Produto("Tênis de Corrida Performance", "Tênis com amortecimento responsivo e solado de borracha para alta tração", 499.50),
                new Produto("Bota de Couro", "Bota feminina de cano curto em couro legítimo na cor preta", 399.00),
                new Produto("Jaqueta Corta-Vento Impermeável", "Jaqueta unissex com capuz, ideal para atividades ao ar livre", 299.90),
                new Produto("Moletom com Capuz e Zíper", "Blusão de moletom cinza mescla com interior flanelado", 219.90),
                new Produto("Sapato Oxford", "Sapato em couro envernizado, modelo Oxford", 349.50),
                new Produto("Shorts de Corrida 2 em 1", "Shorts com bermuda interna de compressão", 149.50),
                new Produto("Top Alta Sustentação", "Top com bojo removível e alças largas, ideal para atividades de alto impacto.", 129.90),
                new Produto("Camiseta Dry-Fit com Proteção UV", "Camiseta de manga longa com tecnologia Dry-Fit e fator de proteção solar UV 50+.", 119.90),
                new Produto("Tênis para Treino de Academia", "Tênis com solado plano e estável, perfeito para levantamento de peso e treinos funcionais.", 449.00),
                new Produto("Meia Esportiva de Cano Alto", "Par de meias de algodão com reforço no calcanhar", 39.90)
        );

        produtoRepository.saveAll(produtos);
        System.out.println("Produtos adicionados com sucesso!");

        System.out.println("\n====== LISTA DE PRODUTOS ======\n");
        produtoRepository.findAll().forEach(produto -> System.out.println(produto.toString()));
    }
}
