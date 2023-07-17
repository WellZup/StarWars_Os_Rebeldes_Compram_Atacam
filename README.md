# StarWars_Os_Rebeldes_Compram_Atacam

# Exercício de Banco de Dados

O império continua sua luta incessante de dominar a galáxia, tentando ao máximo expandir seu território e eliminar os rebeldes.
Você, como um soldado da resistência, foi designado para desenvolver um sistema para compartilhar recursos entre os rebeldes.

Foi desenvolvido uma API, que irá armazenar informação sobre os rebeldes, bem como os recursos que eles possuem e compras que podem ser feitas pelo menu.



![Logo](https://wallpaperaccess.com/full/1265077.jpg)


## Documentação da API

#### Função do Menu

```http
  ("1. Adicionar Rebelde")
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `Adicionar` | `string` | **Criar**. Ao clicar nesta opção, você estará adicionando um novo rebelde a lista. |



```http
  ("2. Atualizar Localização do Rebelde")
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `Localização`      | `string` | **Atualizar Local**. Neste ponto você estará alterando a Localização do ID informado do rebelde que você quer |



```http
  ("3. Reportar Rebelde como Traidor")
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `Reportar` | `string` | **Reporta Traidor**. Para indicar um traidor digite o ID do rebelde. |



```http
  ("4. Relatório: Porcentagem de Traidores")
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `Porcentagem Traidor`      | `string` | **Traidor**. Verifica a porcentagem que tem de traidores registrados pelo ID. |



```http
  ("5. Relatório: Porcentagem de Rebeldes")
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `Porcentagem Rebelde` | `string` | **Rebelde**. Verifica a porcentagem que tem de rebeldes registrados pelo ID. |


```http
  ("6. Exibir todos rebeldes")
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `Listar`      | `string` | **Exibir lista**. Exibir lista de todos os registrados |


```http
  ("7. Remover rebelde")
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `Remover` | `string` | **Retirar da lista**. Remove Rebelde pelo ID informado. |



```http
  ("8. Base de Compras")
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `Compras`      | `string` | **Base de Compras**. O usuario informa qual item quer adicionar ao rebelde escolhido |

## Aprendizados

Ao criar o aplicativo, foi interessante ver a funcionalidade do bando de dados interagindo com o back end. Tem muitas funcionalidade que preciso aprender e entender mais a fundo. Foi divertido e desafiador a forma que foi conduzida a tarefa.


## Melhorias

Fiz algumas alterações para deixar cada função em classes separadas, refatorando o código. Preciso fazer um tratamento de exceção na Base de compras.
## Stack utilizada

**Banco de Dados:** Postgres, Docker

**Back-end:** Intellij


## Roadmap

- Melhorar a Base de Compras

- Adicionar mais integrações


## 🛠 Habilidades
Javascript, HTML, CSS...


## Autores

- [Wellington Rodrigues de Oliveira](https://www.github.com/WellZup)

 
