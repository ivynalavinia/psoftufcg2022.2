package com.ufcg.psoft.mercadofacil.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufcg.psoft.mercadofacil.model.Produto;
import com.ufcg.psoft.mercadofacil.repository.ProdutoRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Testes do controlador de Produtos")
public class ProdutoV1ControllerTests {
    @Autowired
    MockMvc driver;

    @Autowired
    ProdutoRepository<Produto, Long> produtoRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    Produto produto;

    @BeforeEach
    void setup() {
        produto = produtoRepository.find(10L);
    }

    @AfterEach
    void tearDown() {
        produto = null;
    }

    @Nested
    @DisplayName("Conjunto de casos de verificação de campos obrigatórios")
    class ProdutoValidacaoCamposObrigatorios {

        @Test
        @DisplayName("Quando alteramos o nome do produto com dados válidos")
        void quandoAlteramosNomeDoProdutoValido() throws Exception {
            // Arrange
            produto.setNome("Produto Dez Alterado");
            // Act
            String responseJsonString = driver
                    .perform(put("/v1/produtos/" + produto.getId())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(produto)))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            Produto resultado = objectMapper.readValue(responseJsonString, Produto.ProdutoBuilder.class).build();
            // Assert
            assertEquals("Produto Dez Alterado", resultado.getNome());
        }

        @Test
        @DisplayName("Quando alteramos o nome do produto com dados inválidos")
        void quandoAlteramosNomeDoProdutoInvalido() throws Exception {
            // Arrange
            produto.setNome("");
            // Act
            ResultActions response = driver
                    .perform(put("/v1/produtos/" + produto.getId()))
                    .andExpect(status().is4xxClientError());
            // Assert
            response.andExpect(result -> assertTrue(result.getResolvedException() instanceof RuntimeException));
            assertEquals("Produto Dez", produtoRepository.find(10L).getNome());
        }

        @Test
        @DisplayName("Quando alteramos o nome do produto para nulo")
        void quandoAlteramosNomeDoProdutoNulo() throws Exception {
            // Arrange
            produto.setNome(null);
            // Act
            ResultActions response = driver
                    .perform(put("/v1/produtos/" + produto.getId()))
                    .andExpect(status().is4xxClientError());
            // Assert
            response.andExpect(result -> assertTrue(result.getResolvedException() instanceof RuntimeException));
            assertEquals("Produto Dez", produtoRepository.find(10L).getNome());
        }

        @Test
        @DisplayName("Quando alteramos o fabricante com dados válidos")
        void quandoAlteramosFabricanteValido() throws Exception {
            // Arrange
            produto.setFabricante("Empresa Dez Alterada");
            // Act
            String responseJsonString = driver
                    .perform(put("/v1/produtos/" + produto.getId())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(produto)))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            Produto resultado = objectMapper.readValue(responseJsonString, Produto.ProdutoBuilder.class).build();
            // Assert
            assertEquals("Empresa Dez Alterada", resultado.getFabricante());
        }

        @Test
        @DisplayName("Quando alteramos o fabricante com dados inválidos")
        void quandoAlteramosFabricanteInvalido() throws Exception {
            // Arrange
            produto.setFabricante("");
            // Act
            ResultActions response = driver
                    .perform(put("/v1/produtos/" + produto.getId()))
                    .andExpect(status().is4xxClientError());
            // Assert
            response.andExpect(result -> assertTrue(result.getResolvedException() instanceof RuntimeException));
            assertEquals("Empresa Dez", produtoRepository.find(10L).getFabricante());
        }

        @Test
        @DisplayName("Quando alteramos o fabricante para nulo")
        void quandoAlteramosFabricanteNulo() throws Exception {
            // Arrange
            produto.setFabricante(null);
            // Act
            ResultActions response = driver
                    .perform(put("/v1/produtos/" + produto.getId()))
                    .andExpect(status().is4xxClientError());
            // Assert
            response.andExpect(result -> assertTrue(result.getResolvedException() instanceof RuntimeException));
            assertEquals("Empresa Dez", produtoRepository.find(10L).getFabricante());
        }

        @Test
        @DisplayName("Quando alteramos o preço com dados válidos")
        void quandoAlteramosPrecoValido() throws Exception {
            // Arrange
            produto.setPreco(500.00);
            // Act
            String responseJsonString = driver
                    .perform(put("/v1/produtos/" + produto.getId())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(produto)))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            Produto resultado = objectMapper.readValue(responseJsonString, Produto.ProdutoBuilder.class).build();
            // Assert
            assertEquals(500.00, resultado.getPreco());
        }

        @Test
        @DisplayName("Quando alteramos o código de barras com dados válidos")
        void quandoAlteramosCodigoBarraValido() throws Exception {
            // Arrange
            produto.setCodigoBarra("9786588664025");
            // Act
            String responseJsonString = driver
                    .perform(put("/v1/produtos/" + produto.getId())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(produto)))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            Produto resultado = objectMapper.readValue(responseJsonString, Produto.ProdutoBuilder.class).build();
            // Assert
            assertEquals("9786588664025", resultado.getCodigoBarra());
        }

        @Test
        @DisplayName("Quando alteramos o código de barras com dados inválidos")
        void quandoAlteramosCodigoBarraInvalido() throws Exception {
            // Arrange
            produto.setCodigoBarra("");
            // Act
            ResultActions response = driver
                    .perform(put("/v1/produtos/" + produto.getId()))
                    .andExpect(status().is4xxClientError());
            // Assert
            response.andExpect(result -> assertTrue(result.getResolvedException() instanceof RuntimeException));
            assertEquals("7896645900026", produtoRepository.find(10L).getCodigoBarra());
        }

        @Test
        @DisplayName("Quando alteramos o código de barras para nulo")
        void quandoAlteramosCodigoBarraNulo() throws Exception {
            // Arrange
            produto.setCodigoBarra(null);
            // Act
            ResultActions response = driver
                    .perform(put("/v1/produtos/" + produto.getId()))
                    .andExpect(status().is4xxClientError());
            // Assert
            response.andExpect(result -> assertTrue(result.getResolvedException() instanceof RuntimeException));
            assertEquals("7896645900026", produtoRepository.find(10L).getCodigoBarra());
        }

    }

    @Nested
    @DisplayName("Conjunto de casos de verificação da regra sobre o preço")
    class ProdutoValidacaoRegrasDoPreco {

        @Test
        @DisplayName("Quando alteramos o preço para menor ou igual a zero")
        void quandoNovoPrecoInvalido() throws Exception {
            // Arrange
            produto.setPreco(0.0);
            // Act
            ResultActions response = driver
                    .perform(put("/v1/produtos/" + produto.getId()))
                    .andExpect(status().is4xxClientError());
            // Assert
            response.andExpect(result -> assertTrue(result.getResolvedException() instanceof RuntimeException));
            assertEquals(450.00, produtoRepository.find(10L).getPreco());
        }

    }

    @Nested
    @DisplayName("Conjunto de casos de verificação da validação do código de barras")
    class ProdutoValidacaoCodigoDeBarras {

        @Test
        @DisplayName("Quando alteramos o código de barras com dados inválidos")
        void quandoAlteramosCodigoBarraTamanhoInvalido() throws Exception {
            // Arrange
            produto.setCodigoBarra("78966459000260");
            // Act
            ResultActions response = driver
                    .perform(put("/v1/produtos/" + produto.getId()))
                    .andExpect(status().is4xxClientError());
            // Assert
            response.andExpect(result -> assertTrue(result.getResolvedException() instanceof RuntimeException));
            assertEquals("7896645900026", produtoRepository.find(10L).getCodigoBarra());
        }

        @Test
        @DisplayName("Quando alteramos o código de barras com dígito verificador inválido")
        void quandoAlteramosCodigoBarraDigitoInvalido() throws Exception {
            // Arrange
            produto.setCodigoBarra("7899137500104");
            // Act
            ResultActions response = driver
                    .perform(put("/v1/produtos/" + produto.getId()))
                    .andExpect(status().is4xxClientError());
            // Assert
            response.andExpect(result -> assertTrue(result.getResolvedException() instanceof RuntimeException));
            assertEquals("7896645900026", produtoRepository.find(10L).getCodigoBarra());
        }

    }

}
