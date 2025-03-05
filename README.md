# 📅 Sistema de Calendário de Eventos

Bem-vindo ao repositório do **Sistema de Calendário de Eventos**! 🚀

Este projeto consiste em um sistema web completo para gerenciar eventos, com **frontend** e **backend** integrados. Ele permite que os usuários criem, editem, visualizem e removam eventos de maneira intuitiva e eficiente.

## 🏗 Estrutura do Repositório

O projeto está dividido em duas principais pastas:

📂 **backend/** - Desenvolvido com **Java 17 + Spring Boot** e **MongoDB** para armazenar os eventos.

📂 **frontend/** - Criado com **Angular 14** e estilizado com **Tailwind CSS** para uma interface moderna e responsiva.

---

## 🚀 Tecnologias Utilizadas

### 🖥 Backend:
- **Java 17**
- **Spring Boot** (Spring Web, Spring Data MongoDB)
- **MongoDB** (Banco de dados NoSQL)
- **Maven** (Gerenciador de dependências)

### 🎨 Frontend:
- **Angular 14**
- **Tailwind CSS**
- **TypeScript**

---

## ⚙ Como Executar o Projeto

### 🔹 Clonando o repositório
```bash
   git clone https://github.com/seu-usuario/seu-repositorio.git
   cd seu-repositorio
```

### 🔹 Configuração do Backend
1. Configure o MongoDB no arquivo **application.properties**:
   ```properties
   spring.data.mongodb.uri=mongodb://localhost:27017/seu-banco
   ```
2. Execute o backend:
   ```bash
   cd backend
   mvn spring-boot:run
   ```

### 🔹 Configuração do Frontend
1. Instale as dependências:
   ```bash
   cd frontend
   npm install
   ```
2. Execute o frontend:
   ```bash
   ng serve
   ```
3. Acesse no navegador: [http://localhost:4200](http://localhost:4200)

---

## 🔗 Endpoints da API

Os principais endpoints do backend podem ser acessados via **Postman** ou outro cliente HTTP.

| Método  | Endpoint                | Descrição |
|---------|-------------------------|-----------|
| **POST** | `/api/users`            | Cria um novo usuário |
| **POST** | `/api/events`           | Cria um evento |
| **GET**  | `/api/users/{email}`    | Busca um usuário pelo e-mail |
| **GET**  | `/api/events/{email}`   | Retorna todos os eventos de um usuário |
| **PUT**  | `/api/events/{eventId}` | Atualiza um evento existente |
| **DELETE** | `/api/events/{eventId}` | Remove um evento |

---

## 📌 Funcionalidades
✅ Criar conta e autenticar usuário
✅ Criar, editar e excluir eventos
✅ Exibir calendário de eventos
✅ Interface responsiva

---

## 🛠 Como Contribuir
Sinta-se à vontade para abrir **Issues** ou enviar **Pull Requests**! 😊

1. Faça um Fork do projeto
2. Crie uma Branch (`git checkout -b minha-feature`)
3. Commit suas alterações (`git commit -m 'Adicionando minha feature'`)
4. Faça um Push (`git push origin minha-feature`)
5. Abra um Pull Request

---

## 👨‍💻 Autor
Desenvolvido por **[Gustavo Martins Gripaldi](https://g2martins.github.io/G2Portfolio/)** 🚀

