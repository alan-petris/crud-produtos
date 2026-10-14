# CRUD de Produtos — Java

Projeto desenvolvido para praticar os fundamentos de Java, Programação Orientada a Objetos (POO), operações CRUD e testes automatizados com JUnit 5.

## Objetivo

Desenvolver uma aplicação de gerenciamento de produtos utilizando Java, aplicando boas práticas de organização de código, validação de dados, tratamento de exceções e testes automatizados.

Este projeto faz parte do meu processo de aprendizado em desenvolvimento backend e servirá como base para estudos futuros com Spring Boot e persistência de dados.

## Tecnologias utilizadas

- **Java 21** — linguagem de programação.
- **Maven** — gerenciamento de dependências e build do projeto.
- **JUnit 5** — testes automatizados.
- **IntelliJ IDEA** — ambiente de desenvolvimento.
- **Git e GitHub** — versionamento e hospedagem do código.

## Funcionalidades

### Cadastro de produtos
- Cadastrar produtos com nome e preço.
- Gerar identificadores automaticamente.
- Inicializar a quantidade do produto em 1.
- Validar os dados informados antes do cadastro.

### Consulta de produtos
- Buscar um produto pelo ID.
- Listar os produtos cadastrados.
- Retornar um `Optional` quando a busca por ID não encontrar um produto.
- Impedir modificações estruturais externas na lista retornada pela listagem.

### Atualização de produtos
- Atualizar o nome e o preço de um produto.
- Preservar o ID e a quantidade durante a atualização.
- Retornar um resultado indicando se a atualização foi realizada.
- Validar os dados recebidos.

### Remoção de produtos
- Remover um produto pelo ID.
- Retornar um resultado indicando se a remoção foi realizada.
- Preservar os demais produtos cadastrados.

### Tratamento de erros
- Utilizar uma exceção específica para dados inválidos.
- Fornecer mensagens de erro para as validações realizadas pelo serviço.

## Testes automatizados

O projeto utiliza JUnit 5 para verificar os comportamentos implementados.

Os testes abrangem cenários como:

- Cadastro de produtos com dados válidos e inválidos.
- Busca por identificadores existentes e inexistentes.
- Listagem dos produtos cadastrados.
- Atualização de produtos.
- Remoção de produtos.
- Validação de parâmetros.
- Tratamento de exceções.
- Preservação dos dados de outros produtos após uma remoção.

Os testes são desenvolvidos para ajudar a identificar falhas e garantir que alterações futuras não comprometam comportamentos já implementados.

## Estrutura do projeto

A estrutura principal segue a organização abaixo:

```text
crud-produtos/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── exemplo/
│   │               └── crud/
│   │                   ├── Produto.java
│   │                   ├── ProdutoService.java
│   │                   └── exception/
│   │                       └── DadosInvalidosException.java
│   └── test/
│       └── java/
│           └── com/
│               └── exemplo/
│                   └── crud/
│                       └── ProdutoServiceTest.java
├── pom.xml
└── README.md
```

*Observação: a estrutura acima representa a organização prevista para as classes principais e os testes. Os caminhos e nomes devem corresponder aos arquivos existentes no repositório.*

## Como executar os testes

1. Clone o repositório:

   ```bash
   git clone https://github.com/alan-petris/crud-produtos.git
   ```

2. Entre na pasta do projeto:

   ```bash
   cd crud-produtos
   ```

3. Abra o projeto no IntelliJ IDEA e aguarde o Maven carregar as dependências.

4. Execute os testes pela classe de testes ou pela opção de execução dos testes no IntelliJ IDEA.

Se o Maven estiver instalado e configurado no sistema, também é possível executar:

```bash
mvn test
```

## Próximas etapas

- [x] Implementar o modelo de produto.
- [x] Implementar o cadastro de produtos.
- [x] Implementar consultas de produtos.
- [x] Implementar atualização de produtos.
- [x] Implementar remoção de produtos.
- [x] Desenvolver testes automatizados para as operações CRUD.
- [x] Melhorar o tratamento de erros e as exceções.
- [ ] Criar uma API REST utilizando Spring Boot.
- [ ] Implementar persistência de dados com PostgreSQL.
- [ ] Validar requisições e padronizar respostas de erro da API.
- [ ] Documentar e testar os endpoints da API.

## Aprendizados

Este projeto tem como foco a prática de:

- Programação Orientada a Objetos.
- Encapsulamento e organização em classes e pacotes.
- Collections e manipulação de listas.
- `Optional` e Streams.
- Validação de dados e tratamento de exceções.
- Testes automatizados com JUnit 5.
- Versionamento de código com Git e GitHub.
- Organização e evolução incremental de um projeto Java.

## Autor

**Alan Petris**

Estudante de Análise e Desenvolvimento de Sistemas, com foco em desenvolvimento backend e evolução dos conhecimentos em Java.

[GitHub — alan-petris](https://github.com/alan-petris)