package school.sptech.exerciciopraticaac2.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("\uD83C\uDF9F\uFE0F - Testes de IngressoController")
class IngressoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Nested
    @DisplayName("\uD83D\uDCCB - Testes de listagem")
    public class ListagemTest {

        @Test
        @DirtiesContext
        @DisplayName("1 - Deve retornar 9 ingressos")
        void teste1() throws Exception {

            mockMvc.perform(get("/ingressos"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(9)))
                    .andExpect(jsonPath("$[0].id").value(1))
                    .andExpect(jsonPath("$[0].preco").value(590.00))
                    .andExpect(jsonPath("$[0].tipoIngresso").value("INTEIRA"))
                    .andExpect(jsonPath("$[0].precoFinal").value(590.00))
                    .andExpect(jsonPath("$[0].show.id").value(1))
                    .andExpect(jsonPath("$[0].show.nome").value("Rock in Rio"))
                    .andExpect(jsonPath("$[0].show.localizacao").value("Parque Olímpico, Rio de Janeiro"))
                    .andExpect(jsonPath("$[0].show.data").value("2024-09-27T20:00:00"))
                    .andExpect(jsonPath("$[1].id").value(2))
                    .andExpect(jsonPath("$[1].preco").value(550.00))
                    .andExpect(jsonPath("$[1].tipoIngresso").value("MEIA"))
                    .andExpect(jsonPath("$[1].precoFinal").value(275.00))
                    .andExpect(jsonPath("$[1].show.id").value(1))
                    .andExpect(jsonPath("$[1].show.nome").value("Rock in Rio"))
                    .andExpect(jsonPath("$[1].show.localizacao").value("Parque Olímpico, Rio de Janeiro"))
                    .andExpect(jsonPath("$[1].show.data").value("2024-09-27T20:00:00"))
                    .andExpect(jsonPath("$[2].id").value(3))
                    .andExpect(jsonPath("$[2].preco").value(80.00))
                    .andExpect(jsonPath("$[2].tipoIngresso").value("CORTESIA"))
                    .andExpect(jsonPath("$[2].precoFinal").value(0.00))
                    .andExpect(jsonPath("$[2].show.id").value(1))
                    .andExpect(jsonPath("$[2].show.nome").value("Rock in Rio"))
                    .andExpect(jsonPath("$[2].show.localizacao").value("Parque Olímpico, Rio de Janeiro"))
                    .andExpect(jsonPath("$[2].show.data").value("2024-09-27T20:00:00"));
        }

        @Test
        @DirtiesContext
        @Sql(scripts = "/sql/truncate-ingresso.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
        @DisplayName("2 - Deve retornar 204 quando não houver shows")
        void teste2() throws Exception {
            mockMvc.perform(get("/ingressos"))
                    .andExpect(status().isNoContent());
        }
    }

    @Nested
    @DirtiesContext
    @DisplayName("\uD83D\uDD0D - Testes de busca por ID")
    public class BuscaPorIdTest {

        @Test
        @DirtiesContext
        @DisplayName("1 - Deve retornar um ingresso com ID 1")
        void teste1() throws Exception {
            mockMvc.perform(get("/ingressos/1"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(1))
                    .andExpect(jsonPath("$.preco").value(590.00))
                    .andExpect(jsonPath("$.tipoIngresso").value("INTEIRA"))
                    .andExpect(jsonPath("$.precoFinal").value(590.00))
                    .andExpect(jsonPath("$.show.id").value(1))
                    .andExpect(jsonPath("$.show.nome").value("Rock in Rio"))
                    .andExpect(jsonPath("$.show.localizacao").value("Parque Olímpico, Rio de Janeiro"))
                    .andExpect(jsonPath("$.show.data").value("2024-09-27T20:00:00"));
        }

        @Test
        @DirtiesContext
        @DisplayName("2 - Deve retornar 404 quando o ingresso não existe")
        void teste2() throws Exception {
            mockMvc.perform(get("/ingressos/42"))
                    .andExpect(status().isNotFound());
        }

        @Test
        @DirtiesContext
        @Sql(scripts = "/sql/truncate-ingresso.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
        @DisplayName("3 - Deve retornar 404 quando não houver ingressos")
        void teste3() throws Exception {
            mockMvc.perform(get("/ingressos/1"))
                    .andExpect(status().isNotFound());
        }
    }

    @Nested
    @DirtiesContext
    @DisplayName("\uD83D\uDCDD - Testes de criação")
    public class CriacaoTest {

        @Test
        @DirtiesContext
        @DisplayName("1 - Deve retornar 201 quando o ingresso é criado com sucesso (INTEIRA)")
        void teste1() throws Exception {
            var json = """
                    {
                        "preco": 100.00,
                        "tipoIngresso": "INTEIRA"
                    }
                    """;

            mockMvc.perform(post("/ingressos/shows/2")
                            .contentType("application/json")
                            .content(json))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.id").isNumber())
                    .andExpect(jsonPath("$.preco").value(100.00))
                    .andExpect(jsonPath("$.tipoIngresso").value("INTEIRA"))
                    .andExpect(jsonPath("$.precoFinal").value(100.00))
                    .andExpect(jsonPath("$.show.id").value(2))
                    .andExpect(jsonPath("$.show.nome").value("Lollapalooza"))
                    .andExpect(jsonPath("$.show.localizacao").value("Autódromo de Interlagos, São Paulo"))
                    .andExpect(jsonPath("$.show.data").value("2024-03-25T14:00:00"));
        }

        @Test
        @DirtiesContext
        @DisplayName("2 - Deve retornar 201 quando o ingresso é criado com sucesso (MEIA)")
        void teste2() throws Exception {
            var json = """
                    {
                        "preco": 100.00,
                        "tipoIngresso": "MEIA"
                    }
                    """;

            mockMvc.perform(post("/ingressos/shows/1")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.id").isNumber())
                    .andExpect(jsonPath("$.preco").value(100.00))
                    .andExpect(jsonPath("$.tipoIngresso").value("MEIA"))
                    .andExpect(jsonPath("$.precoFinal").value(50.00))
                    .andExpect(jsonPath("$.show.id").value(1))
                    .andExpect(jsonPath("$.show.nome").value("Rock in Rio"))
                    .andExpect(jsonPath("$.show.localizacao").value("Parque Olímpico, Rio de Janeiro"))
                    .andExpect(jsonPath("$.show.data").value("2024-09-27T20:00:00"));
        }

        @Test
        @DirtiesContext
        @DisplayName("3 - Deve retornar 201 quando o ingresso é criado com sucesso (CORTESIA)")
        void teste3() throws Exception {
            var json = """
                    {
                        "preco": 100.00,
                        "tipoIngresso": "CORTESIA"
                    }
                    """;

            mockMvc.perform(post("/ingressos/shows/1")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.id").isNumber())
                    .andExpect(jsonPath("$.preco").value(100.00))
                    .andExpect(jsonPath("$.tipoIngresso").value("CORTESIA"))
                    .andExpect(jsonPath("$.precoFinal").value(0.00))
                    .andExpect(jsonPath("$.show.id").value(1))
                    .andExpect(jsonPath("$.show.nome").value("Rock in Rio"))
                    .andExpect(jsonPath("$.show.localizacao").value("Parque Olímpico, Rio de Janeiro"))
                    .andExpect(jsonPath("$.show.data").value("2024-09-27T20:00:00"));
        }

        @Test
        @DirtiesContext
        @DisplayName("4 - Deve retornar 400 quando o tipo de ingresso é inválido")
        void teste4() throws Exception {
            var json = """
                    {
                        "preco": 100.00,
                        "tipoIngresso": "NA_FAIXA"
                    }
                    """;

            mockMvc.perform(post("/ingressos/shows/1")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DirtiesContext
        @DisplayName("5 - Deve retornar 404 quando o show não existe")
        void teste5() throws Exception {
            var json = """
                    {
                        "preco": 100.00,
                        "tipoIngresso": "INTEIRA"
                    }
                    """;

            mockMvc.perform(post("/ingressos/shows/42")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isNotFound());
        }

        @Test
        @DirtiesContext
        @DisplayName("6 - Deve retornar 400 quando o preço é menor que 30.0")
        void teste6() throws Exception {
            var json = """
                    {
                        "preco": 29.99,
                        "tipoIngresso": "INTEIRA"
                    }
                    """;

            mockMvc.perform(post("/ingressos/shows/1")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DirtiesContext
        @DisplayName("7 - Deve retornar 400 quando os campos estiverem vazios")
        void teste7() throws Exception {
            var json = """
                    {
                        "preco": "",
                        "tipoIngresso": "" 
                    }
                    """;

            mockMvc.perform(post("/ingressos/shows/1")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DirtiesContext
        @DisplayName("8 - Deve retornar 400 quando os campos estiverem nulos")
        void teste8() throws Exception {
            var json = """
                    {
                        "preco": null,
                        "tipoIngresso": null
                    }
                    """;

            mockMvc.perform(post("/ingressos/shows/1")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DirtiesContext
        @DisplayName("9 - Deve retornar 400 quando o preço for negativo")
        void teste9() throws Exception {
            var json = """
                    {
                        "preco": -100.00,
                        "tipoIngresso": "INTEIRA"
                    }
                    """;

            mockMvc.perform(post("/ingressos/shows/1")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DirtiesContext
        @DisplayName("10 - Deve retornar 400 quando o preço for 29.0")
        void teste10() throws Exception {
            var json = """
                    {
                        "preco": 29.0,
                        "tipoIngresso": "INTEIRA"
                    }
                    """;

            mockMvc.perform(post("/ingressos/shows/1")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DirtiesContext
        @DisplayName("11 - Deve retornar 400 quando os campos estiverem com espaços")
        void teste11() throws Exception {
            var json = """
                    {
                        "preco": " ",
                        "tipoIngresso": " " 
                    }
                    """;

            mockMvc.perform(post("/ingressos/shows/1")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isBadRequest());
        }
    }

    @Nested
    @DirtiesContext
    @DisplayName("\uD83D\uDDD1\uFE0F - Testes de remoção")
    public class RemocaoTest {

        @Test
        @DirtiesContext
        @DisplayName("1 - Deve retornar 204 quando o ingresso é removido com sucesso")
        void teste1() throws Exception {
            mockMvc.perform(delete("/ingressos/1"))
                    .andExpect(status().isNoContent());
        }

        @Test
        @DirtiesContext
        @DisplayName("2 - Deve retornar 404 quando o ingresso não existe")
        void teste2() throws Exception {
            mockMvc.perform(delete("/ingressos/42"))
                    .andExpect(status().isNotFound());
        }

        @Test
        @DirtiesContext
        @Sql(scripts = "/sql/truncate-ingresso.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
        @DisplayName("3 - Deve retornar 404 quando não houver ingressos")
        void teste3() throws Exception {
            mockMvc.perform(delete("/ingressos/1"))
                    .andExpect(status().isNotFound());
        }
    }
}