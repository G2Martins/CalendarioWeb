# 📅 Projeto Calendário - Frontend Angular

Bem-vindo ao frontend do Projeto Calendário! 🎉 Este é um aplicativo desenvolvido com Angular que permite a gestão de eventos de forma intuitiva e eficiente.

## 🚀 Tecnologias Utilizadas

- **Angular 14** - Framework principal para construção da interface.
- **TypeScript** - Tipagem estática para um código mais seguro.
- **Tailwind CSS** - Estilização moderna e responsiva.
- **RxJS** - Para manipulação de eventos assíncronos.

## 📦 Instalação

Certifique-se de ter o **Node.js** instalado. Em seguida, clone o repositório e instale as dependências:

```sh
# Clonar o repositório
git clone https://github.com/seu-repositorio.git
cd nome-do-projeto

# Instalar dependências
npm install
```

## 🔥 Executando o Servidor

Para rodar o projeto em modo de desenvolvimento, use:

```sh
ng serve
```

Acesse no navegador: **[http://localhost:4200](http://localhost:4200)**

## 🎨 Estrutura do Projeto

```ruby
📂 src/
 ├── 📁 app/                  # Código principal da aplicação
 │   ├── 📁 components/       # Componentes reutilizáveis (inclui o componente de convites)
 │   ├── 📁 pages/            # Páginas do projeto (ex.: calendário, login, registro)
 │   ├── 📁 services/         # Serviços para comunicação com o backend (ex.: Auth, Event, Invite)
 │   ├── 📄 app.module.ts     # Módulo principal da aplicação
 │   └── 📄 app.component.ts  # Componente raiz
 ├── 📁 assets/               # Arquivos estáticos (imagens, fontes, etc.)
 ├── 📄 index.html            # Arquivo principal HTML
 └── 📄 styles.css            # Estilização global (Tailwind CSS configurado)
```

## 🛠 Comandos Principais

- Criar um novo componente:
  ```sh
  ng generate component nome-do-componente
  ```
- Criar um novo serviço:
  ```sh
  ng generate service nome-do-servico
  ```
- Compilar o projeto:
  ```sh
  ng build
  ```
- Rodar testes unitários:
  ```sh
  ng test
  ```
- Executar testes end-to-end:
  ```sh
  ng e2e
  ```
  
## 🔔 Funcionalidades Implementadas

- **Autenticação de Usuários:** Login e registro utilizando armazenamento local (localStorage).
- **Gestão de Eventos:** Criação, edição, exclusão e prevenção de sobreposição de eventos com alertas de conflito.
- **Convites para Eventos:** 
  - Envio de convites para outros usuários a partir de um dropdown dentro do componente de convites.
  - Aceitação e recusa de convites, com atualização automática do calendário e da lista de convites.
  - Apenas eventos com convite **ACEITO** aparecem no calendário; convites pendentes são listados separadamente.
- **Interface Responsiva:** Desenvolvida com Tailwind CSS para uma experiência agradável em qualquer dispositivo.

## 📖 Endpoints do Backend (para referência)

A comunicação com o backend ocorre por meio de diversos endpoints (consulte o README do Backend para mais detalhes).  
Exemplos:
- **Eventos:** `/api/events`, `/api/events/user/{email}`, `/api/events/{eventId}`, etc.
- **Convites:** `/api/events/{eventId}/invite`, `/api/events/{eventId}/invite/{convidadoEmail}/{status}`, `/api/events/invites/{email}`, etc.
- **Usuários:** `/api/users`, `/api/users/email/{email}`

## 🛠 Personalizações e Atualizações

### Componente de Convites
- O componente **InvitesComponent** foi adicionado em `src/app/components/invites/` para gerenciar os convites.
- Possui um dropdown para selecionar um evento existente e um modal para enviar convites.
- Ao aceitar ou recusar um convite, os métodos correspondentes atualizam tanto a lista de convites quanto o calendário (através de chamadas de callback).

### Integração com Serviços
- Os serviços **EventService** e **InviteService** se comunicam com o backend e atualizam as views chamando métodos como `carregarEventos()` e `listarConvites()`, garantindo que as informações estejam sempre atualizadas após cada operação.

## 🎬 Autor
Desenvolvido por [Gustavo Martins Gripaldi](https://g2martins.github.io/G2Portfolio/).

## ⚠️ Ajuda e Referências

Para mais detalhes sobre o Angular e Tailwind CSS, consulte:
- [Angular CLI Overview](https://angular.io/cli)
- [Tailwind CSS Documentation](https://tailwindcss.com/docs)

Se precisar de suporte, sinta-se à vontade para abrir uma issue!
