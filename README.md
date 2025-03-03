
```swift
src/main/java/com/gustavo/avaliacao
│── Application.java (Classe principal do Spring Boot)
│
├── config  (Configurações gerais)
│
├── model  (Entidades do domínio)
│
├── repository  (Camada de acesso a dados)
│
└── service  (Regras de negócio)
```

## Explicação dos Endpoints

| Método | Endpoint | Descrição |
| ------ | -------- | --------- |
| POST   | /api/users | Cria um novo usuário |
| POST   | /api/events | Cria um evento |
| GET    | /api/users/email/{email} | Busca um usuário pelo e-mail |
| GET    | /api/events/user/{userId} | Retorna todos os eventos de um usuário |
| GET    | /api/events/{eventId} | Busca um evento pelo ID |
| PUT    | /api/events/{eventId} | Atualiza um evento existente |
| DELETE | /api/events/{eventId} | Remove um evento pelo ID |

