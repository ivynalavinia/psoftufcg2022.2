package com.ufcg.psoft.mercadofacil.service;

import com.ufcg.psoft.mercadofacil.model.Produto;
import com.ufcg.psoft.mercadofacil.repository.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Testes para a validação do código de barra do Produto")
public class ProdutoValidarCodigoDeBarraTest {

    @Autowired
    ProdutoValidarCodigoDeBarraService driver;

    @MockBean
    ProdutoRepository<Produto, Long> produtoRepository;

    Produto produto;

    // logica
    // 7896645900026
    // 1. 2+0+9+4+6+8 = 29
    // 2. 29 * 3 = 87
    // 3. 0+0+5+6+9+7 = 27
    // 4. 114
    // 5. 114 => multiplo 120 - etapa 4

    @BeforeEach
    void setup() {
        produto = Produto.builder()
                .id(1L)
                .nome("Produto Base")
                .codigoBarra("123456789")
                .fabricante("Fabricante Base")
                .preco(125.36)
                .build();
    }

    @Test
    @DisplayName("Quando tento validar um produto com código de barras válido")
    void validarCodigoDeBarra() {
        /* AAA */
        //Arrange
        produto.setCodigoBarra("7896645900026");
        //Act
        boolean resultado = driver.validar(produto);
        //Assert
        assertTrue(resultado);
    }
}
