# Desafio Projeto [TODO List](https://github.com/simplify-tec/desafio-junior-backend-simplify)

Projeto desenvolvido utilizando a linguagem Java e o framework Spring Boot.


## Sobre o projeto
Projeto documentado pelo Swagger.

Utilizei o ModelMapper para fazer o parse da entity Task e o Value Object TaskVO.

Adicionei duas migrations no projeto para facilitar os testes.

Também utilizei o Mockito para testes unitários.

## Iniciar o projeto

#### Requisitos
Ter instalado o JDK 22 e o Maven para executar o comando mvn

Criar um novo banco de dados no MySQL chamado "desafio_todolist"

Exemplo de como criar no PowerShell
```bash
# Primeiro faça login com seu nome de usuário no MySQL
# Neste exemplo utilizei o username root
# A senha utilizada no projeto é 12345
# caso a sua seja diferente, abra o projeto e no arquivo properties.yml,
# altere o valor do campo 'password' para a sua senha

mysql -u root -p
# Digite a senha

CREATE DATABASE desafio_todolist;

# Pronto, o projeto estará apto para iniciar sem problemas.
```

Clone o projeto, e na pasta do projeto execute o maven.

```bash
mvn clean package
```
Após todos arquivos necessários serem baixados execute o java.

```bash
java -jar target/desafio-todolist-0.0.1-SNAPSHOT.jar
```

## Tecnologias

[Spring Boot](https://spring.io/guides/gs/spring-boot)

[Spring MVC](https://docs.spring.io/spring-framework/reference/web/webmvc.html)

[Spring HATEOAS](https://spring.io/projects/spring-hateoas)

[Spring JPA](https://spring.io/guides/gs/accessing-data-jpa)

[Swagger / OpenAPI 3](https://springdoc.org/)

[ModelMapper](https://modelmapper.org/)

[MySQL](https://www.mysql.com/)

[Flyway](https://documentation.red-gate.com/flyway/flyway-cli-and-api/supported-databases/mysql)

[Mockito](https://site.mockito.org/)

*****
