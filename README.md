# RecipesFood 🍽️  
Mobile App de Receitas — FIAP | Fase 1 – 2º Ano

Aplicativo Android desenvolvido progressivamente ao longo dos capítulos da Fase 1 do 2º ano do curso de Análise e Desenvolvimento de Sistemas (FIAP).

O projeto evolui a cada capítulo, incorporando novos conceitos técnicos, organização arquitetural e boas práticas de desenvolvimento mobile.

---

## 🎯 Objetivo do Projeto

Construir um aplicativo Android funcional aplicando:

- Arquitetura em camadas
- Padrão Repository
- Persistência local (SharedPreferences → evolução para Room)
- Navegação com Jetpack Compose
- Organização modular e boas práticas de código

O app é desenvolvido de forma incremental, acompanhando os conteúdos da disciplina.

---

## 🧱 Arquitetura

O projeto segue separação clara de responsabilidades:

UI (Jetpack Compose)  
→ Navegação  
→ Repository  
→ Fonte de dados (SharedPreferences / Room)

### Estrutura principal

- `ui/` → Telas (Home, Login, Cadastro, etc.)
- `navigation/` → Definição de rotas e fluxo de navegação
- `repository/` → Contrato (interface) e implementações
- `model/` → Entidades (ex: User)
- `data/` → Camada de persistência (Room, quando aplicável)

A interface `UserRepository` define o contrato de acesso a dados, permitindo múltiplas implementações (SharedPreferences ou Room).

---

## 📚 Evolução por Capítulos

### Capítulos iniciais
- Estrutura base do app
- Criação das telas com Compose
- Navegação entre telas

### Persistência com SharedPreferences
- Implementação de `SharedPreferencesUserRepository`
- Cadastro e login persistidos localmente
- Aplicação prática do padrão Repository

### Evolução para Room
- Implementação de `RoomUserRepository`
- Introdução ao banco de dados local
- Separação entre contrato e implementação

O projeto reflete a progressão técnica da disciplina.

---

## 🚀 Como Executar

### Requisitos
- Android Studio
- JDK 17
- Emulador Android ou dispositivo físico

### Executar via Android Studio
1. Abrir o projeto
2. Sincronizar o Gradle
3. Rodar no emulador ou dispositivo

### Executar via terminal
```bash
./gradlew assembleDebug
```

### 🔄 Integração Contínua (CI)

O projeto possui workflow de CI configurado via GitHub Actions:

Arquivo:

.github/workflows/android.yml

A pipeline executa:

Build do projeto (assembleDebug)

(Opcional) testes unitários

Objetivo: garantir que cada push mantenha o projeto compilando corretamente.

---

📌 Próximas Evoluções

Testes unitários para camada de Repository

Melhor tratamento de erros e validações

Melhor separação entre implementações (SharedPreferences / Room)

Organização de DI (Dependency Injection)

---

### 👩‍💻 Desenvolvido por

Évelyn Rodrigues - 
FIAP – Análise e Desenvolvimento de Sistemas
