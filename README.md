# RiordanverseAPI

## Descrição
Projeto realizado na conclusão da matéria de Desenvolvimento de Sistemas para Internet, ministrada pelo professor [Igor Rosberg de Medeiros Silva](https://github.com/igorosberg), e programado por [Bruno Cesar](https://github.com/brunoo85) e [Julio Augusto](https://github.com/maoiki).

[Editar tamanho da imagem, tá muito pequena]: # 
 <img src=".\riodanverse\assets\fotoLivros.jpg" width="500" height="300">

## Tabelas
Foram usadas 5 tabelas para a composição dessa API. São elas: Usuário, Acampamento, Mitologia, Criatura e Livro. 

## Relações entre tabelas
As tabelas são organizadas da seguinte forma: 

O **Livro** possui suas características e possui uma **Mitologia**

Uma **Mitologia** possui seu nome e é ligada a um **Acampamento**

Um **Acampamento** possui uma **Mitologia**

Uma **Criatura** possui uma **Mitologia** associada a ela

O **Usuário** possui um **Acampamento** e uma **Criatura** associado a ele

## Segurança

A segurança é feita com 3 modos de usuário: 
Administrador, funcionário e campista.

Com alguns métodos sendo acessados por pessoas que não possuem login. 

Ficou organizado da seguinte forma: 

- Público (sem login):<br>
Todos os gets, com excessão dos relacionado ao Usuário.

- Campista:<br>
Get e put do usuário cadastrado.

- Para o funcionário e administrador:<br>
Todos os puts e deletes (exceto o put de um usuário adm).

- Público (sem login):<br>
Todos os deletes e o put de um usuário adm.
