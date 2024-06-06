package school.sptech.exerciciopraticaac2.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("\uD83C\uDFA4 - Testes de ShowController")
class ShowControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Nested
    @DirtiesContext
    @DisplayName("\uD83D\uDCCB - Testes de listagem")
    public class ListagemTest {

        @Test
        @DirtiesContext
        @DisplayName("1 - Deve retornar 3 shows")
        void teste1() throws Exception {
            mockMvc.perform(get("/shows"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(3)))
                    .andExpect(jsonPath("$[0].nome").value("Rock in Rio"))
                    .andExpect(jsonPath("$[1].nome").value("Lollapalooza"))
                    .andExpect(jsonPath("$[2].nome").value("Tomorrowland Brasil"));
        }

        @Test
        @DirtiesContext
        @Sql(scripts = "/sql/truncate-show.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
        @DisplayName("2 - Deve retornar 204 quando não houver shows")
        void teste2() throws Exception {
            mockMvc.perform(get("/shows"))
                    .andExpect(status().isNoContent());
        }
    }

    @Nested
    @DirtiesContext
    @DisplayName("\uD83D\uDD0D - Testes de busca por ID")
    public class BuscaPorIdTest {

        @Test
        @DirtiesContext
        @DisplayName("1 - Deve retornar o show com ID 1")
        void teste1() throws Exception {
            mockMvc.perform(get("/shows/1"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.nome").value("Rock in Rio"));
        }

        @Test
        @DirtiesContext
        @DisplayName("2 - Deve retornar 404 quando o show não existe")
        void teste2() throws Exception {
            mockMvc.perform(get("/shows/42"))
                    .andExpect(status().isNotFound());
        }
    }

    @Nested
    @DirtiesContext
    @DisplayName("\uD83D\uDCDD - Testes de criação")
    public class CriacaoTest {

        @Test
        @DirtiesContext
        @DisplayName("1 - Deve criar um novo show")
        void teste1() throws Exception {

            var dataAtual = LocalDateTime.now().plusDays(1);

            var json = """
                    {
                        "nome": "Show Teste",
                        "localizacao": "Local Teste",
                        "data": "%s"
                    }
                    """.formatted(dataAtual.toString());

            mockMvc.perform(post("/shows")
                            .contentType("application/json")
                            .content(json))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").isNumber())
                    .andExpect(jsonPath("$.nome").value("Show Teste"))
                    .andExpect(jsonPath("$.localizacao").value("Local Teste"))
                    .andExpect(jsonPath("$.data").value(dataAtual.toString()));
        }

        @Test
        @DirtiesContext
        @DisplayName("2 - Deve retornar 409 quando já existir um show na mesma localização e data")
        void teste2() throws Exception {

            var json = """
                    {
                        "nome": "Pop in Rio",
                        "localizacao": "Rio de Janeiro",
                        "data": "2024-09-27T20:00:00"
                    }
                    """;

            mockMvc.perform(post("/shows")
                            .contentType("application/json")
                            .content(json))
                    .andExpect(status().isConflict());
        }

        @Test
        @DirtiesContext
        @DisplayName("3 - Deve retornar 400 quando os dados estiverem vazios")
        void teste3() throws Exception {

            var json = """
                    {
                        "nome": "",
                        "localizacao": "",
                        "data": ""
                    }
                    """;

            mockMvc.perform(post("/shows")
                            .contentType("application/json")
                            .content(json))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DirtiesContext
        @DisplayName("4 - Deve retornar 400 quando os dados estiverem nulos")
        void teste4() throws Exception {

            var json = """
                    {
                        "nome": null,
                        "localizacao": null,
                        "data": null
                    }
                    """;

            mockMvc.perform(post("/shows")
                            .contentType("application/json")
                            .content(json))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DirtiesContext
        @DisplayName("5 - Deve retornar 400 quando os dados estiverem em branco")
        void teste5() throws Exception {

            var json = """
                    {
                        "nome": "   ",
                        "localizacao": "   ",
                        "data": "   "
                    }
                    """;

            mockMvc.perform(post("/shows")
                            .contentType("application/json")
                            .content(json))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DirtiesContext
        @DisplayName("6 - Deve retornar 400 quando a data for passada")
        void teste6() throws Exception {

            var json = """
                    {
                        "nome": "Show Teste",
                        "localizacao": "Local Teste",
                        "data": "2021-01-01T00:00:00"
                    }
                    """;

            mockMvc.perform(post("/shows")
                            .contentType("application/json")
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
        @DisplayName("1 - Deve remover o show com ID 1")
        void teste1() throws Exception {
            mockMvc.perform(delete("/shows/1"))
                    .andExpect(status().isNoContent());
        }

        @Test
        @DirtiesContext
        @DisplayName("2 - Deve retornar 404 quando o show não existe")
        void teste2() throws Exception {
            mockMvc.perform(get("/shows/42"))
                    .andExpect(status().isNotFound());
        }

        @Test
        @DirtiesContext
        @Sql(scripts = "/sql/truncate-show.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
        @DisplayName("3 - Deve retornar 404 quando não houver shows")
        void teste3() throws Exception {
            mockMvc.perform(delete("/shows/1"))
                    .andExpect(status().isNotFound());
        }
    }
}