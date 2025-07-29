<div style="text-align: center;">

# Sistema de catálogo de produtos e simulação de pedidos

#### Uma **API REST** baseada em **microsserviços** com **Spring Boot** e **Spring Cloud**

![Java](https://img.shields.io/badge/Java-21-blue)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5.4-brightgreen)
![Spring Cloud](https://img.shields.io/badge/Spring_Cloud-2025.0.0-green)
![Maven](https://img.shields.io/badge/Maven-4.0-red)

</div>

---

## Índice

* [Visão geral](#visão-geral)
* [Arquitetura](#arquitetura)
  * [Diagrama de fluxo](#diagrama-de-fluxo)
* [Ferramentas e tecnologias](#ferramentas-e-tecnologias)
* [Como executar](#como-executar)
* [Como utilizar](#como-utilizar)
  * [Autenticação das requisições](#autenticação-das-requisições)
  * [Catálogo de produtos](#catálogo-de-produtos-produtos)
  * [Simulador de pedidos](#simulador-de-pedidos-pedidos)
* [Demonstração da solução](#demonstração-da-solução)
* [Licença](#licença)

---

## Visão geral

Este projeto foi desenvolvido no desafio técnico da NTT Data no Bootcamp de Java e IA (DIO & NTT Data). O projeto consiste em uma API REST que simula um sistema simples de catálogo de produtos e criação de pedidos, composta por dois microsserviços independentes, implementados utilizando Spring Boot e Spring Cloud. O sistema demonstra a comunicação entre microsserviços por meio de requisições HTTP, com o uso de um API Gateway e Service Discovery.

---

## Arquitetura

O sistema é composto por quatro componentes principais que rodam de forma independente:

1.  **Service Discovery (Eureka):** Centraliza o registro de todos os microsserviços. Ele sabe qual serviço está disponível e onde encontrá-lo (endereço e porta).
2.  **API Gateway (Spring Cloud Gateway):** O ponto de entrada único para todas as requisições externas. Ele autentica e autoriza as requisições por meio de um Bearer Token fixo no header e consulta o Eureka para rotear as chamadas para o microsserviço correspondente.
3.  **Microsserviço 1 - Catálogo de Produtos (`produtos`):** Responsável pelo gerenciamento de produtos, realizando operações CRUD básicas (criar, listar, consultar, atualizar e excluir). Possui seu próprio banco de dados em memória (H2).
4.  **Microsserviço 2 - Simulador de Pedidos (`pedidos`):** Responsável por criar pedidos. Não possui persistência de dados e consome a API `produtos` para obter informações dos produtos.

### Diagrama de fluxo

A arquitetura proposta no desafio está representada no seguinte fluxo:

![Diagrama da Arquitetura](docs/arquitetura-ms.png)

<small>Diagrama elaborado e disponibilizado pela NTT Data para o desafio</small>

---

## Ferramentas e tecnologias

* **Java 21:** linguagem de programação utilizada para desenvolvimento da aplicação.
* **Spring Boot**: principal framework utilizado.
* **Módulos Spring:**
    * Spring Web
    * Spring Data JPA
    * Spring Cloud Gateway
    * Spring Cloud Netflix Eureka (Server & Client)
    * Spring Cloud OpenFeign (para comunicação interna)
    * Spring Security (para autenticação das requisições)
* **H2 Database:** banco de dados utilizado para armazenar as informações de produtos (em memória).
* **Maven:** ferramenta de build
* **IntelliJ IDEA:** IDE utilizada para desenvolvimento da aplicação.
* **Lombok:** biblioteca Java utilizada para reduzir a necessidade de código boilerplate.
* **Insomnia**: cliente HTTP utilizado para testar os endpoints.

---

## Como executar

**Pré-requisitos**: JDK 21, Maven, uma IDE (como IntelliJ, VSCode ou Eclipse) e um cliente HTTP (como Postman ou Insomnia).

Inicie os serviços na seguinte ordem:

1.  **Eureka Server (`eurekaserver`):**
    * Navegue até a pasta `eurekaserver`.
    * Execute o comando: `mvn spring-boot:run`
    * Aguarde até que o servidor esteja no ar. Você pode acessar a dashboard do Eureka em `http://localhost:8761`.

2.  **Microsserviço de Produtos (`produtos`):**
    * Navegue até a pasta `produtos`.
    * Execute o comando: `mvn spring-boot:run`
    * Verifique na dashboard do Eureka se o `CATALOGO-SERVICE` foi registrado com sucesso.

3.  **Microsserviço de Pedidos (`pedidos`):**
    * Navegue até a pasta `pedidos`.
    * Execute o comando: `mvn spring-boot:run`
    * Verifique na dashboard do Eureka se o `PEDIDO-SERVICE` foi registrado.

4.  **API Gateway (`gateway`):**
    * Navegue até a pasta `gateway`.
    * Execute o comando: `mvn spring-boot:run`
    * Este será o ponto de entrada. Todas as requisições devem ser feitas para `http://localhost:8765`.

---

## Como utilizar

Após executar todos os serviços, utilize o cliente HTTP de sua escolha para testar os endpoints. As seções a seguir detalham o processo de autenticação e as requisições HTTP disponíveis para cada microsserviço. **Todas as requisições devem ser feitas através do API Gateway na porta `8765`.** 

Você pode fazer as requisições manualmente no cliente HTTP, ou baixar os arquivos a seguir, que contêm as requisições HTTP e importar diretamente no Insomnia para executá-las:
* [Requisições API Produtos](docs/insomnia-produtos-requests.yaml)
* [Requisições API Pedidos](docs/insomnia-pedidos-request.yaml)

### Autenticação das requisições

A aplicação utiliza um sistema de autenticação que filtra as requisições a partir de um Bearer Token fixo no header da requisição. Apenas as requisições que contenham o token correto no header "Authorization" serão redirecionadas para o serviço solicitado. Requisições sem o token serão rejeitadas com o código de status HTTP `401 Unauthorized`.

Para ter acesso aos endpoints, adicione o seguinte header na requisição: 
```http
Authorization: Bearer TOKEN_SECRETO_123456
```

### Catálogo de produtos (`/produtos`)

> **Nota**: A API utiliza um `DataLoader` no momento da inicialização que popula o banco de dados com alguns produtos por padrão.

* **`GET /produtos`**: Lista todos os produtos cadastrados.
* **`GET /produtos/{id}`**: Busca um produto por seu ID.
* **`GET /produtos/nome?nome={valor}`**: Busca produtos que contenham ao valor `String` passado por parâmetro.
* **`GET /produtos/preco?maximo={valor}`**: Busca produtos com valor menor ou igual ao valor `Double` passado por parâmetro.
* **`POST /produtos`**: Cria um novo produto a partir do JSON fornecido no body da requisição (campos `nome`, `descricao` e `preco`)
  * Exemplo de body da requisição:
    ```json
    {
      "nome": "Camiseta Preta Básica",
      "descricao": "Camiseta preta 100% algodão",
      "preco": 59.9
    }
    ```

* **`PUT /produtos/{id}`**: Atualiza o produto com a ID informada (se existente). Requer JSON com os mesmos campos do POST no body da requisição.
* **`DELETE /produtos/{id}`**: Exclui o produto com a ID informada (se existente).

### Simulador de pedidos (`/pedidos`)

* **`POST /pedidos`**: Cria um novo pedido a partir da ID do produto desejado e sua quantidade, especificados em JSON no body da requisição (campos `idProduto` e `quantidade`)
    * O microsserviço de pedidos buscará internamente as informações do produto (usando a ID fornecido) no serviço de produtos, utilizando `OpenFeign`, e retornará o resumo do pedido com as informações completas.
    * **Exemplo de body da requisição:**
        ```json
        {
          "itens": [
            {
              "idProduto": 1,
              "quantidade": 5
            },
            {
              "idProduto": 2,
              "quantidade": 2
            }
          ]
        }
        ```
    * **Exemplo de body da resposta (sucesso):**
        ```json
        {
            "itens": [
                {
                    "idProduto": 1,
                    "quantidade": 5,
                    "nomeProduto": "Camiseta Preta Básica",
                    "preco": 59.9,
                    "subtotal": 299.5
                },
                {
                    "idProduto": 2,
                    "quantidade": 2,
                    "nomeProduto": "Legging Esportiva Feminina",
                    "preco": 189.99,
                    "subtotal": 379.98
                }
            ],
            "valorTotal": 679.48,
            "dataPedido": "2025-07-28T19:32:40.6426492"
        }
        ```
  
---

## Demonstração da solução

O vídeo a seguir demonstra as funcionalidades dos serviços por meio do Insomnia.

<video controls width="100%">
    <source src="docs/demo-api-produtos.mp4" type="video/mp4" />
</video>

---

## Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

---

<div style="text-align: center;">

**Desenvolvido por Isabela Borges**<br>
[LinkedIn](https://www.linkedin.com/in/isabela-borgs/) | [Portfólio](https://isabelaborgesdev.vercel.app/)

</div>
