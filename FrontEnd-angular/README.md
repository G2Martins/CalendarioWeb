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

```
📂 src/
 ├── 📁 app/                  # Código principal
 │   ├── 📁 components/       # Componentes reutilizáveis
 │   ├── 📁 services/         # Serviços para integração com o backend
 │   ├── 📁 pages/            # Páginas do Projeto
 │   ├── 📄 app.module.ts     # Módulo principal
 │   ├── 📄 app.component.ts  # Componente raiz
 ├── 📁 assets/               # Imagens e outros arquivos estáticos
 ├── 📄 index.html            # Arquivo principal HTML
 ├── 📄 styles.css            # Estilização global
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

## ⚠️ Funcionalidades Implementadas

✅ Autenticação de usuários via **localStorage**  
✅ Criação, edição e remoção de eventos  
✅ Prevenção de sobreposição de eventos com alerta de conflito ⚠️  
✅ Interface responsiva e intuitiva  
✅ Alerta vermelho ao fazer logout  

## 📖 Ajuda e Referências

Para mais detalhes sobre o Angular CLI, consulte a documentação oficial: [Angular CLI Overview](https://angular.io/cli)

---

## 🎬 Autor
Desenvolvido por [Gustavo Martins Gripaldi](https://g2martins.github.io/G2Portfolio/).

