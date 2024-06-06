<p>Este exercício foca na criação de um modelo de entidades para gerenciar shows e ingressos em um sistema. Abaixo está uma descrição detalhada das entidades envolvidas, seus atributos e os requisitos que devem ser atendidos.</p>

# Entidades

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


