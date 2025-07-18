# README - Configuração da Aplicação

Este projeto é configurado para operar em dois ambientes distintos, facilitando tanto o desenvolvimento local quanto a
execução em um ambiente conteinerizado. A configuração do banco de dados muda automaticamente dependendo de como a
aplicação é iniciada.

## 1. Ambiente de Desenvolvimento (Iniciado pela IDE)

Quando a aplicação é iniciada diretamente através de uma IDE (como IntelliJ, Eclipse ou VS Code), ela utiliza um perfil
de configuração padrão otimizado para desenvolvimento.

- **Banco de Dados:** Neste modo, a aplicação se conecta a um banco de dados **H2 em memória**.
- **Vantagens:** Esta abordagem é extremamente rápida e simples. Não há necessidade de instalar ou configurar um banco
  de dados externo na sua máquina. O banco de dados é criado quando a aplicação inicia e destruído quando ela para,
  tornando o ciclo de desenvolvimento e teste muito mais ágil.

## 2. Ambiente Conteinerizado (Iniciado via Docker Compose)

Para simular um ambiente mais próximo do de produção e garantir a portabilidade, a aplicação pode ser iniciada usando
Docker e Docker Compose com o comando `docker-compose up --build`.

- **Banco de Dados:** Neste modo, um perfil específico (`docker`) é ativado. Este perfil configura a aplicação para se
  conectar a um banco de dados **PostgreSQL**.
- **Como Funciona:** O Docker Compose não só constrói e executa a aplicação Java, mas também cria um contêiner separado
  para o banco de dados PostgreSQL. Os dois contêineres são colocados na mesma rede virtual, permitindo que a aplicação
  se comunique com o banco de dados de forma transparente. As credenciais e o endereço do banco de dados são gerenciados
  pelo Docker Compose.

## 3. População Inicial de Dados com data.sql

Independentemente do ambiente (IDE com H2 ou Docker com PostgreSQL), a aplicação está configurada para popular o
banco de dados com dados iniciais sempre que é iniciada.

Mecanismo: Isso é realizado através de um script padrão do Spring Boot chamado data.sql, localizado na pasta
src/main/resources.

Ativação: A execução deste script é um comportamento padrão do Spring Boot quando ele detecta um banco de dados. A
estratégia de inicialização (como sempre executar o script) é controlada por propriedades definidas no
application.properties, como spring.sql.init.mode=always.

Dessa forma, ao iniciar a aplicação em qualquer um dos modos, as tabelas do banco de dados já estarão populadas com os
dados definidos no data.sql, garantindo que a aplicação esteja pronta para uso imediato.

## 4. Documentação da API com Swagger (OpenAPI)

Este projeto utiliza o Swagger para gerar uma documentação interativa e visual da sua API REST. O Swagger (baseado na
Especificação OpenAPI) analisa o código da aplicação e cria uma interface de usuário que permite a qualquer pessoa
visualizar e interagir com os endpoints da API sem precisar ter acesso ao código-fonte.

Funcionalidades:

Lista todos os endpoints disponíveis (ex: GET, POST, PUT, DELETE).

Descreve os parâmetros necessários para cada requisição.

Mostra os modelos de dados (schemas) de requisição e resposta.

Permite executar requisições de teste diretamente do navegador, facilitando a depuração e a verificação do comportamento
da API.

URL de Acesso:
Após iniciar a aplicação (seja via IDE ou Docker Compose), a interface do Swagger UI estará disponível no seguinte
endereço:

http://localhost:8080/swagger-ui/index.html