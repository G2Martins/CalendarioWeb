# Backend - Calendário de Eventos

Este é o backend de um sistema de Calendário de Eventos desenvolvido com **Java + Spring Boot** e **MongoDB**. Ele fornece uma API REST para criação, consulta, edição e remoção de eventos.

## Estrutura do Projeto

```bash
src/main/java/com/gustavoMartinsGripaldi/avaliacao
│── Application.java (Classe principal do Spring Boot)
│
├── config
│
├── dto  (Objetos de Transferência de Dados)
│
├── model  (Entidades do domínio)
│
├── repository  (Camada de acesso a dados)
│
├── service  (Regras de negócio)
│
└── controller  (API REST - Endpoints)
```

## Tecnologias Utilizadas
- **Java 17**
- **Spring Boot** (Spring Web, Spring Data MongoDB)
- **MongoDB** (Banco de dados NoSQL)

## Configuração do Projeto
1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/seu-repositorio.git
   ```
2. Navegue até a pasta do projeto:
   ```bash
   cd seu-repositorio
   ```
3. Configure o MongoDB no **application.properties**:
   ```properties
   spring.data.mongodb.uri=mongodb://localhost:27017/seu-banco
   ```
4. Execute o projeto:
   ```bash
   mvn spring-boot:run
   ```

## Endpoints Disponíveis

| Método | Endpoint | Descrição |
| ------ | -------- | --------- |
| **POST**   | `/api/users` | Cria um novo usuário |
| **POST**   | `/api/events` | Cria um evento |
| **GET**    | `/api/users/email/{email}` | Busca um usuário pelo e-mail |
| **GET**    | `/api/events/user/{userId}` | Retorna todos os eventos de um usuário |
| **GET**    | `/api/events/{eventId}` | Busca um evento pelo ID |
| **PUT**    | `/api/events/{eventId}` | Atualiza um evento existente |
| **DELETE** | `/api/events/{eventId}` | Remove um evento pelo ID |

## Testando a API
Para testar os endpoints, você pode utilizar ferramentas como:
- [Postman](https://www.postman.com/)


Exemplo de requisição `POST` para criar um usuário:
```json
{
  "nome": "Luisa",
  "email": "luisa@email.com",
  "senha": "123456"
}
```

## Autor 
Desenvolvido por [Gustavo Martins Gripaldi](https://g2martins.github.io/G2Portfolio/).

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/3.4.3/maven-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.4.3/maven-plugin/build-image.html)
* [Spring Web](https://docs.spring.io/spring-boot/3.4.3/reference/web/servlet.html)
* [Spring Data MongoDB](https://docs.spring.io/spring-boot/3.4.3/reference/data/nosql.html#data.nosql.mongodb)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/3.4.3/reference/using/devtools.html)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Accessing Data with MongoDB](https://spring.io/guides/gs/accessing-data-mongodb/)

### Maven Parent overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the parent.
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.

