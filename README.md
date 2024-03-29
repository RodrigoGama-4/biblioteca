# Sistema de Biblioteca com Spring Boot

Este é um projeto de sistema de biblioteca desenvolvido utilizando o framework Spring Boot, Hibernate, JPA, autenticação, MySQL, e envio de e-mail para cadastro na biblioteca. O sistema visa oferecer uma plataforma eficiente para gerenciamento de bibliotecas, incluindo funcionalidades como cadastro de livros, usuários, autenticação segura e notificações por e-mail.

## Tecnologias Utilizadas

__Spring Boot (v3.2.1):__ Facilita a criação de aplicativos Java baseados em Spring, fornecendo uma configuração padrão.
__Hibernate e Spring Data JPA:__ Simplificam a interação com o banco de dados MySQL por meio de mapeamento objeto-relacional.
__Spring Security:__ Fornece recursos robustos para autenticação e autorização.
__Spring Boot Starter Web:__ Configurações padrão para desenvolvimento de aplicativos web.
__Spring Boot Starter Mail:__ Suporte para envio de e-mails.
__MySQL Connector-J:__ Conector JDBC para interação com o banco de dados MySQL.
__Spring Boot Starter Validation:__ Facilita a validação de dados.
__Java JWT (com.auth0:java-jwt:4.4.0):__ Biblioteca para trabalhar com JSON Web Tokens (JWT).
__Lombok (org.projectlombok:lombok):__ Simplifica a escrita de código Java, evitando a necessidade de criar getters, setters e construtores manualmente.
__Spring Boot Starter Test e Spring Security Test:__ Suporte para testes unitários e de segurança.

## Requisitos do Sistema
__Java 17:__ Versão mínima requerida para executar o sistema.
__MySQL:__ Banco de dados para armazenar informações sobre livros, usuários, etc.

## Configuração do Banco de Dados

O arquivo application.properties deve ser configurado com as informações do seu banco de dados MySQL. Por exemplo:

## Configuração do Banco de Dados

O arquivo application.properties deve ser configurado com as informações do seu banco de dados MySQL e do seu provedor de email. Por exemplo:

``` 
spring.datasource.url=jdbc:mysql://localhost:3306/nome_do_seu_banco
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

spring.mail.host=seu_smtp_host
spring.mail.port=sua_porta_smtp
spring.mail.username=seu_email
spring.mail.password=sua_senha

```



O sistema estará disponível em http://localhost:8080.