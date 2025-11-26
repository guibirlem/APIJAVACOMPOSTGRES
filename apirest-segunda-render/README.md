# API Spring — Usuários e Tarefas

API REST em Java com Spring Boot para gerenciamento de usuários e tarefas (posts).  
Entidades principais:
- `Usuario` → tabela `tb_usuario`
- `Post` / `Tarefa` → tabela `tb_post` (muitos posts pertencem a um usuário)

---

## Tecnologias
- Java 17+
- Spring Boot (Web, Data JPA)
- Hibernate / JPA
- PostgreSQL (ou H2 para testes)
- Gradle

---

## Pré-requisitos
- Java 17+
- PostgreSQL (ou use H2 para desenvolvimento)
- Gradle

---

## Configuração (application.properties)
Edite `src/main/resources/application.properties` com seus dados. Exemplo PostgreSQL:

spring.datasource.url=jdbc:postgresql://localhost:5432/sua_base  
spring.datasource.username=seu_usuario  
spring.datasource.password=sua_senha

spring.jpa.hibernate.ddl-auto=update  
spring.jpa.show-sql=true  
spring.jpa.properties.hibernate.format_sql=true

---

## Como executar
1. Build:
   ```
   ./gradlew build
   ```
2. Executar:
   ```
   ./gradlew bootRun
   ```
A API ficará disponível em: `http://localhost:8080`

---

## Modelo de entidades (resumo)
Usuario (tb_usuario)
- id: Long
- nome: String
- email: String
- posts: List<Post> (OneToMany, mappedBy = "user")

Post / Tarefa (tb_post)
- id: Long
- descricao: String
- user / usuario: ManyToOne para Usuario (coluna `usuario_id`)

---

## Endpoints principais
Base URL: `http://localhost:8080`

Usuarios (`/users`)
- GET /users — listar todos os usuários  
  Exemplo response 200:
  [
    { "id": 1, "nome": "Daniel", "email": "daniel@example.com" }
  ]
- GET /users/{id} — obter usuário por id  
- POST /users — criar usuário (body: { "nome", "email" }) → 201 Created
- PUT /users/{id} — atualizar usuário (body: { "nome", "email" }) → 200 OK
- DELETE /users/{id} — deletar usuário → 200 OK (texto simples)

---

Tarefas / Posts (`/tarefas` — alias `/posts`)
- DTOs usados:
  - Request: `PostRequest { usuarioId, descricao }`
  - Response: `PostResponseDTO { id, descricao, usuario }` onde `usuario` é `{ id, nome }`
- POST /tarefas — criar tarefa (body: { "usuarioId", "descricao" }) → 201 Created  
  Obs: `usuarioId` deve existir em `tb_usuario`.
- GET /tarefas — listar todas as tarefas
- GET /tarefas/{id} — obter tarefa por id
- PUT /tarefas/{id} — atualizar tarefa (body com `descricao` e opcional `usuarioId`)
- DELETE /tarefas/{id} — deletar tarefa → 200 OK (MessageDTO ou JSON com mensagem)

Exemplo POST criar tarefa:
```
POST /tarefas
Content-Type: application/json

{
  "usuarioId": 1,
  "descricao": "Comprar leite"
}
```

Resposta 201:
```
{
  "id": 1,
  "descricao": "Comprar leite",
  "usuario": {
    "id": 1,
    "nome": "Daniel"
  }
}
```

---

## Collection (Postman)
Sugestão de variáveis:
- baseUrl: http://localhost:8080
- userId: 1
- tarefaId: 1

Principais chamadas:
- GET {{baseUrl}}/users
- GET {{baseUrl}}/users/{{userId}}
- POST {{baseUrl}}/users
- PUT {{baseUrl}}/users/{{userId}}
- DELETE {{baseUrl}}/users/{{userId}}
- POST {{baseUrl}}/tarefas
- GET {{baseUrl}}/tarefas
- GET {{baseUrl}}/tarefas/{{tarefaId}}
- PUT {{baseUrl}}/tarefas/{{tarefaId}}
- DELETE {{baseUrl}}/tarefas/{{tarefaId}}

Corpos esperados:
- Usuário: { "nome": "...", "email": "..." }
- Tarefa: { "usuarioId": <id>, "descricao": "..." }

---

