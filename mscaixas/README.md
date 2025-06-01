<h1 align="center">
Avaliação Técnica Java L2 Code - Exercício 1 - Eempacotamento
</h1>

## Loja do seu Manoel
<p align="justify">
  Seu Manoel tem uma loja de jogos online e deseja automatizar o processo de embalagem dos pedidos. Ele precisa de uma API que, dado um conjunto de pedidos com produtos e suas dimensões, retorne qual o tamanho de caixa de papelão que devem ser usadas para cada pedido e quais produtos irão em cada tamanho de caixa.
</p>

## Descrição do Problema
<p align="justify">
  Desenvolva uma API Web utilizando Java com Spring Boot que receba, em formato JSON, uma lista de pedidos. Cada pedido contém uma lista de produtos, cada um com suas dimensões (altura, largura, comprimento).
</p>
<p align="justify">
  A API deve processar cada pedido e determinar a melhor forma de embalar os produtos, selecionando uma ou mais caixas para cada pedido e especificando quais produtos vão em cada caixa.
</p>

## Entrada e saída do endpoint
<p align="justify">
  Entrada: A API deve aceitar um JSON contendo N pedidos diferentes. Cada pedido deve ter entre N produtos. Cada produto deve incluir suas dimensões em centímetros (altura, largura, comprimento).
</p>
<p align="justify">
  Processamento: Para cada pedido, a API deve calcular a melhor forma de empacotar os produtos dentro das caixas de papelão disponíveis. Você poderá usar uma ou mais caixas de papelão para empacotar o pedido. Deve considerar a otimização do espaço, tentando minimizar o número de caixas de papelão a serem usadas.
</p>
<p align="justify">
Saída: A API deve retornar um JSON que, para cada pedido, lista as caixas usadas e quais produtos foram colocados em cada caixa
</p>
<p align="justify">
Exemplo de entrada:
</p>

``` bash
{
  "pedidos": [
    {
      "pedido_id": 1,
      "produtos": [
        {"produto_id": "PS5", "dimensoes": {"altura": 40, "largura": 10, "comprimento": 25}},
        {"produto_id": "Volante", "dimensoes": {"altura": 40, "largura": 30, "comprimento": 30}}
      ]
    },
    {
      "pedido_id": 2,
      "produtos": [
        {"produto_id": "Joystick", "dimensoes": {"altura": 15, "largura": 20, "comprimento": 10}},
        {"produto_id": "Fifa 24", "dimensoes": {"altura": 10, "largura": 30, "comprimento": 10}},
        {"produto_id": "Call of Duty", "dimensoes": {"altura": 30, "largura": 15, "comprimento": 10}}
      ]
    },
    {
      "pedido_id": 3,
      "produtos": [
        {"produto_id": "Headset", "dimensoes": {"altura": 25, "largura": 15, "comprimento": 20}}
      ]
    },
    {
      "pedido_id": 4,
      "produtos": [
        {"produto_id": "Mouse Gamer", "dimensoes": {"altura": 5, "largura": 8, "comprimento": 12}},
        {"produto_id": "Teclado Mecânico", "dimensoes": {"altura": 4, "largura": 45, "comprimento": 15}}
      ]
    },
    {
      "pedido_id": 5,
      "produtos": [
        {"produto_id": "Cadeira Gamer", "dimensoes": {"altura": 120, "largura": 60, "comprimento": 70}}
      ]
    },
    {
      "pedido_id": 6,
      "produtos": [
        {"produto_id": "Webcam", "dimensoes": {"altura": 7, "largura": 10, "comprimento": 5}},
        {"produto_id": "Microfone", "dimensoes": {"altura": 25, "largura": 10, "comprimento": 10}},
        {"produto_id": "Monitor", "dimensoes": {"altura": 50, "largura": 60, "comprimento": 20}},
        {"produto_id": "Notebook", "dimensoes": {"altura": 2, "largura": 35, "comprimento": 25}}
      ]
    },
    {
      "pedido_id": 7,
      "produtos": [
        {"produto_id": "Jogo de Cabos", "dimensoes": {"altura": 5, "largura": 15, "comprimento": 10}}
      ]
    },
    {
      "pedido_id": 8,
      "produtos": [
        {"produto_id": "Controle Xbox", "dimensoes": {"altura": 10, "largura": 15, "comprimento": 10}},
        {"produto_id": "Carregador", "dimensoes": {"altura": 3, "largura": 8, "comprimento": 8}}
      ]
    },
    {
      "pedido_id": 9,
      "produtos": [
        {"produto_id": "Tablet", "dimensoes": {"altura": 1, "largura": 25, "comprimento": 17}}
      ]
    },
    {
      "pedido_id": 10,
      "produtos": [
        {"produto_id": "HD Externo", "dimensoes": {"altura": 2, "largura": 8, "comprimento": 12}},
        {"produto_id": "Pendrive", "dimensoes": {"altura": 1, "largura": 2, "comprimento": 5}}
      ]
    }
  ]
}
```
<p align="justify">
Exemplo de saída:
</p>

``` bash
{
  "pedidos": [
    {
      "pedido_id": 1,
      "caixas": [
        {
          "caixa_id": "Caixa 2",
          "produtos": ["PS5", "Volante"]
        }
      ]
    },
    {
      "pedido_id": 2,
      "caixas": [
        {
          "caixa_id": "Caixa 1",
          "produtos": ["Joystick", "Fifa 24", "Call of Duty"]
        }
      ]
    },
    {
      "pedido_id": 3,
      "caixas": [
        {
          "caixa_id": "Caixa 1",
          "produtos": ["Headset"]
        }
      ]
    },
    {
      "pedido_id": 4,
      "caixas": [
        {
          "caixa_id": "Caixa 1",
          "produtos": ["Mouse Gamer", "Teclado Mecânico"]
        }
      ]
    },
    {
      "pedido_id": 5,
      "caixas": [
        {
          "caixa_id": null,
          "produtos": ["Cadeira Gamer"],
          "observacao": "Produto não cabe em nenhuma caixa disponível."
        }
      ]
    },
    {
      "pedido_id": 6,
      "caixas": [
        {
          "caixa_id": "Caixa 3",
          "produtos": ["Monitor", "Notebook"]
        },
        {
          "caixa_id": "Caixa 1",
          "produtos": ["Webcam", "Microfone"]
        }
      ]
    },
    {
      "pedido_id": 7,
      "caixas": [
        {
          "caixa_id": "Caixa 1",
          "produtos": ["Jogo de Cabos"]
        }
      ]
    },
    {
      "pedido_id": 8,
      "caixas": [
        {
          "caixa_id": "Caixa 1",
          "produtos": ["Controle Xbox", "Carregador"]
        }
      ]
    },
    {
      "pedido_id": 9,
      "caixas": [
        {
          "caixa_id": "Caixa 1",
          "produtos": ["Tablet"]
        }
      ]
    },
    {
      "pedido_id": 10,
      "caixas": [
        {
          "caixa_id": "Caixa 1",
          "produtos": ["HD Externo", "Pendrive"]
        }
      ]
    }
  ]
}
```

## Requisitos para a entrega
- Fazer microserviço em Java
- Criar uma API com swagger
- Enviar o código fonte via link do repositório do github pronto para rodar com docker.

## Requisitos opcionais
- Segurança na autenticação da API
- Conter teste unitário

## Requisitos para executar o projeto
- Git instalado - [**Download**](https://git-scm.com/downloads).
- JDK 21 instalado - [**Download**](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html).
- Docker Desktop for Windows - [**Download**](https://docs.docker.com/desktop/setup/install/windows-install/).
  ### Opção executar Linux (Ubuntu)
- Docker for Ubuntu - [**Download**](https://docs.docker.com/engine/install/ubuntu/).

## Iniciando
``` bash
  # Clonar o projeto:
  $ git clone https://github.com/diasRibeirao/l2code.git

  # Entrar no diretório do projeto:
  $ cd l2code
  $ cd mscaixas
```

## Executando o projeto Spring Boot com Maven
```bash
  # Instalar as dependências:
  $ mvn clean install 

  # Rodar a aplicação:
  $ mvn spring-boot:run

  # Rodar a aplicação com o CMD:
  $ Executar o seguinte comando: java -jar target/mscaixas-0.0.1-SNAPSHOT.jar

```

## Executando o projeto com Docker
```bash
  # Criar imagem docker:
  $ docker build -t mscaixas .

  # Executa um contêiner baseado na imagem mscaixas:
  $ docker run -p 8081:8081 --name mscaixas-container mscaixas

```

## Banco de Dados

#### [**http://localhost:8081/mscaixas/h2-console/login.jsp**](http://localhost:8081/api/h2-console/login.jsp)
```bash
 spring.datasource.driverClassName=org.h2.Driver
 spring.datasource.url=jdbc:h2:mem:mscaixasdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
 spring.datasource.username=sa
 spring.datasource.password=
```

## Endpoints

#### [**http://localhost:8081/mscaixas/swagger-ui/index.html**](http://localhost:8081/mscaixas/swagger-ui/index.html)

![image](https://github.com/user-attachments/assets/ef884732-bb66-42b9-9b69-6c44b74e9245)

## Executando o Endpoints

#### Inserir o usuário e senha na entrada de texto do modal aberto ao clicar no botão com cadeado no topo "Authorize":

![image](https://github.com/user-attachments/assets/076a881b-8710-4242-887f-7c0fd6989994)

```bash
  # Permissão de administrador [roles("ADMIN")]:
  Usuário: l2code.admin
  Senha: Admin@2025
  
  # Permissão de usuário do sistema [roles("USER")]:
  Usuário: l2code
  Senha: User@2025
```

### Caixas
```bash
  # Cadastrar uma caixa:
  # Método: POST
  # Role: ADMIN
  http://localhost:8081/mscaixas/caixas

  # Atualizar os dados cadastrais de uma caixa
  # Método: PUT
  # Role: ADMIN
  http://localhost:8081/mscaixas/beneficiarios/1
  
  # Listar todas as caixas:
  # Método: GET
  # Role: ADMIN ou USER
  http://localhost:8081/mscaixas/caixas
  
  # Buscar uma caixa pelo seu id:
  # Método: GET
  # Role: ADMIN ou USER
  http://localhost:8081/mscaixas/caixas/1
 
  # Remover uma caixa:
  # Método: DELETE
   # Role: ADMIN
  http://localhost:8081/mscaixas/caixas/1
```


### Empacotamento
```bash
  # Retorna a melhor forma de empacotar os produtos dentro das caixas de papelão disponíveis:
  # Método: POST
  # Role: ADMIN ou USER
  http://localhost:8081/mscaixas/empacotar
```

<br /><br />
Emerson Dias de Oliveira<br />
https://github.com/diasRibeirao
