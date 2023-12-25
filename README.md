# RiordanverseAPI

Projeto desenvolvido como parte da disciplina Desenvolvimento de Sistemas para Internet. Os autores principais deste projeto foram:

- [Bruno Cesar](https://github.com/brunoo85)
- [Julio Duarte](https://github.com/maoiki)

## Descrição
A RiordanverseAPI tem como objetivo permitir o registro e consulta de informações relacionadas aos livros, mitologias, acampamentos e criaturas do universo literário criado por Rick Riordan. 
Nela é possível também cadastrar os fãs como usuários, onde estes podem se associar a um acampamento e também assumir um papel nele.

## Tecnologias e ferramentas utilizadas
- Java
- SpringBoot
- Hibernate
- Java JWT
- MySQL
- Bcrypt

## Tabelas
Foram usadas 5 tabelas para a composição dessa API. São elas: Usuário, Acampamento, Mitologia, Criatura e Livro. 
A relação entre as entidades dessas tabelas pode ser observada no diagrama a seguir:

<img src=".\assets\relacaoentidades.jpg">

## Autenticação
A RiordanverseAPI utiliza JWT (JSON Web Token) para autenticação. 
Para obter um token JWT e autenticar suas solicitações, você deve realizar o seguinte procedimento:

1. **Solicitar Token:**
    
    Envie uma solicitação para o endpoint de autenticação, fornecendo suas credenciais.
    
    Exemplo de solicitação:
     ```http
     POST localhost:8080/login
     Content-Type: application/json

     {
       "login": "seu_login",
       "senha": "sua_senha"
     }
     ```
    
    Exemplo de resposta bem-sucedida:
     ```json
     {
       "token": "seu_token_jwt_aqui"
     }
     ```

2. **Incluir Token nas Solicitações:**

    Inclua o token JWT nas solicitações autenticadas no cabeçalho `Authorization`.

    Exemplo:
    ```http
    GET localhost:8080/livro/id/1
    Authorization: Bearer seu_token_jwt_aqui
    ```

## Segurança
Para garantir a integridade da senha dos usuários, foi utilizada criptografia do tipo hash.

A segurança das rotas foi desenvolvida para permitir fácil acesso a consulta dados não sensíveis, por meio de rotas públicas. 
Já para alterar dados ou consultar usuários, são utilizadas rotas privadas que requerem autenticação.

### Rotas públicas
São públicas as rotas de Login, Acampamento, Mitologia, Livros e Criaturas utilizando o método GET.

Exemplos:

```http
GET localhost:8080/login
GET localhost:8080/mitologia/all
GET localhost:8080/criatura/nome/semideus
```

### Rotas privadas
O usuário autenticado pode ter 3 funções: administrador, funcionário e campista. 
Na tabela a seguir é possível ver quais as permissões e restrições de cada método:

| Função       | POST   | PUT     | DELETE |
| ---          | ---    | ---     | ---    |
| Administrador| ✅     | ✅     | ✅     |
| Funcionário  | ✅[^1] | ✅[^1] | ❌[^2] |
| Campista     | ❌     | ❌[^2] | ❌[^2] |

[^1]: Não possui permissão para alterar a função de um usuário para administrador ou funcionário.
[^2]: Todos os usuários podem utilizar GET, PUT e DELETE referentes a própria conta.
