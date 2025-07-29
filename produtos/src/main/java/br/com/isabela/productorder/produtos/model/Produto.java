package br.com.isabela.productorder.produtos.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private Double preco;

    public Produto() {}

    public Produto(String nome, String descricao, Double preco) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
    }

    public void atualizar(Produto produtoAtualizado) {
        this.nome = produtoAtualizado.getNome();
        this.descricao = produtoAtualizado.getDescricao();
        this.preco = produtoAtualizado.getPreco();
    }

    @Override
    public String toString() {
        return String.format("""
                [%d] %s - R$%.2f
                - %s
                """, id, nome.toUpperCase(), preco, descricao);
    }
}
