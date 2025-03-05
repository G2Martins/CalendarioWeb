# ✨ Backend - Calendário de Eventos ✨

Bem-vindo ao backend do **Calendário de Eventos**!
Este sistema foi desenvolvido para ajudar os usuários a gerenciar seus eventos de forma fácil e eficiente. Construído com **Java + Spring Boot** e **MongoDB**, ele fornece uma API REST poderosa para criação, consulta, edição e remoção de eventos.

## 📚 Estrutura do Projeto

```bash
src/main/java/com/gustavoMartinsGripaldi/avaliacao
│── Application.java (Classe principal do Spring Boot)
│
├── config  (Configuração do sistema)
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

## 👩‍💻 Tecnologias Utilizadas
- **Java 17**
- **Spring Boot** (Spring Web, Spring Data MongoDB)
- **MongoDB** (Banco de dados NoSQL)
- **Maven** (Gerenciamento de dependências e build)

## ⚙️ Como Configurar e Executar o Projeto

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/seu-repositorio.git
   ```
2. Navegue até a pasta do projeto:
   ```bash
   cd seu-repositorio
   ```
3. Configure a conexão com o MongoDB no **application.properties**:
   ```properties
   spring.data.mongodb.uri=mongodb://localhost:27017/seu-banco
   ```
4. Execute o projeto com Maven:
   ```bash
   mvn spring-boot:run
   ```

## 🔄 Endpoints Disponíveis

| Método  | Endpoint                        | Descrição |
|----------|--------------------------------|-------------|
| **POST**   | `/api/usuarios`                  | Cria um novo usuário |
| **GET**    | `/api/usuarios/{email}`          | Busca um usuário pelo e-mail |
| **POST**   | `/api/eventos`                   | Cria um evento para um usuário |
| **GET**    | `/api/eventos/{email}`           | Retorna todos os eventos de um usuário |
| **GET**    | `/api/eventos/detalhes/{id}`     | Busca um evento pelo ID |
| **PUT**    | `/api/eventos/{id}`              | Atualiza um evento existente |
| **DELETE** | `/api/eventos/{id}`              | Remove um evento pelo ID |

## 💪 Testando a API

Para testar os endpoints, você pode utilizar ferramentas como:
- [Postman](https://www.postman.com/)
- [Insomnia](https://insomnia.rest/)

### 📝 Exemplo de requisição `POST` para criar um usuário:
```json
{
  "nome": "Luisa",
  "email": "luisa@email.com",
  "senha": "123456"
}
```

### 📅 Exemplo de requisição `POST` para criar um evento:
```json
{
  "descricao": "Reunião de planejamento",
  "horaInicio": "2025-03-10T14:00:00",
  "horaTermino": "2025-03-10T15:00:00",
  "emailUsuario": "luisa@email.com"
}
```

## 🎬 Autor
Desenvolvido por [Gustavo Martins Gripaldi](https://g2martins.github.io/G2Portfolio/).

## ⚙️ Documentação de Referência
- [Spring Boot - Documentação Oficial](https://spring.io/projects/spring-boot)
- [Spring Data MongoDB](https://docs.spring.io/spring-boot/docs/current/reference/html/data.html#data.nosql.mongodb)
- [Maven - Guia Oficial](https://maven.apache.org/guides/index.html)
- [Construindo uma API REST com Spring](https://spring.io/guides/tutorials/rest/)

Se precisar de suporte, sinta-se à vontade para abrir uma issue! ✨
