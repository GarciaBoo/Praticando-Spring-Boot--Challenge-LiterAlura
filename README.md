## LiterAlura - Catálogo de Livros

Bem-vindo ao LiterAlura, seu catálogo de livros interativo! Este projeto desafiador foi desenvolvido para explorar o mundo do desenvolvimento Java, focando em integração com APIs, manipulação de JSON, persistência de dados em banco de dados e interação via console.

### Objetivo

O objetivo principal deste projeto é construir um sistema que permite aos usuários buscar, listar e visualizar informações sobre livros e autores. As funcionalidades principais incluem o consumo de uma API de livros para obter dados atualizados, armazenamento desses dados em um banco de dados local e interação com os usuários através de um menu interativo no console.

### Passos para Completar o Desafio

1. **Configuração do Ambiente Java**
   - Instalação do JDK e configuração da variável de ambiente `JAVA_HOME`.
   - Verificação da instalação do Java com o comando `java -version`.

2. **Criação do Projeto**
   - Criação de um novo projeto Java utilizando uma IDE (IntelliJ IDEA, Eclipse, etc.).
   - Configuração do arquivo `pom.xml` com as dependências necessárias, incluindo Spring Boot, Spring Data JPA, e PostgreSQL Driver.

3. **Consumo da API**
   - Implementação da classe `GutendexService` para realizar solicitações HTTP à API de livros (Gutendex).
   - Processamento da resposta JSON da API para extrair e modelar informações de livros e autores.

4. **Persistência de Dados**
   - Utilização de Spring Data JPA com Hibernate para persistir dados no banco de dados PostgreSQL.
   - Definição de entidades (`Livro` e `Autor`) com mapeamento objeto-relacional para representar os dados no banco de dados.

5. **Interação com o Usuário via Console**
   - Desenvolvimento da classe `LiteraluraApplication` como o ponto de entrada do programa.
   - Implementação de um menu interativo no console que permite aos usuários realizar operações como buscar livros por autor ou título, listar livros registrados, listar autores registrados, listar livros por idioma, e listar autores vivos em um determinado ano.

6. **Formatação e Visualização de Dados**
   - Utilização de formatação ANSI para estilizar a saída no console, com títulos de livros em amarelo (`ANSI_YELLOW`) e nomes de autores em azul (`ANSI_BLUE`).
   - Organização clara e legível da apresentação de informações de livros e autores para uma melhor experiência do usuário.

### Soluções Aplicadas no Projeto

- **Configuração Inicial**: Configuração adequada do ambiente Java com JDK instalado e variável `JAVA_HOME` configurada para garantir um ambiente de desenvolvimento funcional.
  
- **Consumo de API**: Implementação da classe `GutendexService` para realizar solicitações GET à API de livros Gutendex, utilizando a biblioteca Spring RestTemplate para simplicidade e eficiência na comunicação.

- **Persistência de Dados**: Utilização do Spring Data JPA com Hibernate para simplificar o acesso e a manipulação de dados no banco de dados PostgreSQL. Definição de entidades (`Livro` e `Autor`) com anotações de mapeamento para facilitar a persistência e recuperação de objetos.

- **Menu Interativo**: Desenvolvimento da classe `LiteraluraApplication` como uma aplicação de console interativa que oferece múltiplas opções para os usuários interagirem com o catálogo de livros, utilizando a classe Scanner para entrada de dados.

- **Estilização no Console**: Uso de códigos ANSI para colorir o texto no console, proporcionando uma experiência visual agradável ao usuário durante a apresentação dos dados de livros e autores.

### Classes Principais

1. **LiteraluraApplication**
   - **Descrição**: Esta classe é o ponto de entrada da aplicação. Ela implementa a interface `CommandLineRunner` do Spring Boot para executar operações no início da aplicação.
   - **Métodos Principais**:
     - `run(String... args)`: Controla o fluxo principal da aplicação, exibindo um menu interativo no console e chamando métodos específicos com base na escolha do usuário.
     - Métodos privados como `buscarLivrosPorTermo`, `listarLivrosRegistrados`, `listarAutoresRegistrados`, `listarLivrosPorIdioma`, `listarAutoresVivosNoAno`: Cada um desses métodos corresponde a uma opção do menu e realiza operações como buscar livros por termo, listar livros registrados, listar autores registrados, etc.

2. **GutendexService**
   - **Descrição**: Classe responsável por interagir com a API Gutendex para obter dados sobre livros.
   - **Métodos Principais**:
     - `fetchLivrosFromGutendex(String termo)`: Realiza uma requisição GET para a API Gutendex com o termo de busca especificado e retorna uma lista de objetos `Livro`.
     - Métodos auxiliares para construir a URL da API, fazer a requisição HTTP e processar a resposta JSON.

3. **LivroRepository**
   - **Descrição**: Interface que estende `JpaRepository` do Spring Data JPA para operações de persistência de dados relacionados a livros.
   - **Métodos**: Herda métodos básicos como `save`, `findById`, `findAll`, e também métodos personalizados como `findByIdioma` para buscar livros por idioma.

4. **AutorRepository**
   - **Descrição**: Interface que estende `JpaRepository` do Spring Data JPA para operações de persistência de dados relacionados a autores.
   - **Métodos**: Similar ao `LivroRepository`, fornece métodos básicos de CRUD e métodos personalizados como `findAutoresVivosNoAno` para buscar autores que estavam vivos em um ano específico.

### Métodos e Funcionalidades

- **Interatividade com o Usuário**: Implementação de métodos na classe `LiteraluraApplication` para interagir com o usuário através de um menu de opções. Cada método corresponde a uma operação específica que o usuário pode realizar, como busca por termo, listagem de livros por idioma, etc.

- **Persistência de Dados**: Utilização do Spring Data JPA para simplificar o acesso e a manipulação de dados no banco de dados PostgreSQL. Isso inclui definição de entidades (`Livro` e `Autor`), configuração de repositórios e métodos personalizados para consultas específicas.

- **Manipulação de Resposta JSON**: A classe `GutendexService` é responsável por fazer requisições HTTP para a API Gutendex, processar a resposta JSON obtida e mapear os dados relevantes para objetos Java (`Livro`).

- **Estilização no Console**: Uso de códigos ANSI para colorir o texto no console, proporcionando uma apresentação visual mais agradável ao exibir informações sobre livros (títulos em amarelo e nomes de autores em azul).

### Conclusão

Cada classe e método implementado no projeto LiterAlura desempenha um papel crucial na realização das funcionalidades principais, desde a interação com o usuário até a integração com APIs externas e persistência de dados. A arquitetura baseada em Spring Boot e Spring Data JPA facilita o desenvolvimento e a manutenção de um sistema robusto e eficiente.

---
Este conjunto de classes e métodos forma a espinha dorsal do projeto LiterAlura, proporcionando uma experiência interativa e funcional para os usuários interessados em explorar e descobrir novos livros e autores.
