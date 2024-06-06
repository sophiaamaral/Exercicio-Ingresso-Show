[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/KJ_S1K_O)
# Preparação para prova AC3 📝

~~Tudo~~ Quase tudo que você precisa saber para a prova AC3.

## Entidades

### Show

- id: Integer
- nome: string
- localizacao: string
- data: LocalDateTime

### Ingresso

- id: Integer
- preco: Double
- tipoIngresso: TipoIngresso (Enum)
- show: Show (Entidade Show)

### TipoIngresso (Enum)

- INTEIRA("Inteira")
- MEIA("Meia")
- CORTESIA("Cortesia")

<hr>

## Requisitos

### Ingresso

- **Muitos** ingressos para **um** show.

- Um ingresso pode ser do tipo **INTEIRA, MEIA ou CORTESIA**(Esse campo não pode ser nulo).

- Um ingresso tem um **preço** (Esse campo não pode ser nulo, valor mínimo 30.0).

- Um ingresso tem um **precoFinal** que é calculado de acordo com o tipo do ingresso (Campo calculado).
    - Inteira: precoFinal = preco
    - Meia: precoFinal = preco / 2
    - Cortesia: precoFinal = 0

Um ingresso pertence a um show **(Não pode existir ingresso sem show).**

### Show

- **Um** show pode ter **muitos** ingressos **(NÃO DEVE ser bidirecional).**
- Um show tem um **nome** (Esse campo não pode ser nulo, nem ser vazio, nem conter apenas espaços).
- Um show tem uma **localização** (Esse campo não pode ser nulo, nem ser vazio, nem conter apenas espaços).
- Um show tem uma **data** (Esse campo não pode ser nulo, e deve ser uma data futura).
- Não deve ser possível ter 2 shows no mesmo local e na mesma data (realizar pesquisa no banco antes de salvar).

# Atenção 🚨

- Você não deve usar DTOs nesse projeto.
- Você não deve alterar o data.sql.
- Você não deve alterar o application.properties.
- Você não utilizar modelMapper, MapStruct ou similar.
- A atividade é individual.
