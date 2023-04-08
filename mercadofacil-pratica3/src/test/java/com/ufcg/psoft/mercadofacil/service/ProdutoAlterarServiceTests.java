package com.ufcg.psoft.mercadofacil.service;

import com.ufcg.psoft.mercadofacil.model.Produto;
import com.ufcg.psoft.mercadofacil.repository.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("Testes do Serviço de alteração do produto")
public class ProdutoAlterarServiceTests {

    @Autowired
    ProdutoAlterarService driver;

    @MockBean
    ProdutoRepository<Produto, Long> produtoRepository;

    Produto produto;

    @BeforeEach
    void setup() {
        Mockito.when(produtoRepository.find(10L))
                .thenReturn(Produto.builder()
                        .id(10L)
                        .codigoBarra("7896645900026")
                        .nome("Produto Dez")
                        .fabricante("Empresa Dez")
                        .preco(450.00)
                        .build()
                );
        produto = produtoRepository.find(10L);
    }

    @Test
    @DisplayName("Quando um novo nome válido for fornecido para o produto")
    void quandoNovoNomeValido() {
        // Arrange
        produto.setNome("Produto Dez Atualizado");
        Mockito.when(produtoRepository.update(produto))
                .thenReturn(Produto.builder()
                        .id(10L)
                        .codigoBarra("7896645900026")
                        .nome("Produto Dez Atualizado")
                        .fabricante("Empresa Dez")
                        .preco(450.00)
                        .build()
                );
        // Act
        Produto resultado = driver.alterar(produto);
        // Assert
        assertEquals("Produto Dez Atualizado", resultado.getNome());
    }

    @Test
    @DisplayName("Quando um novo nome inválido for fornecido para o produto")
    void quandoNovoNomeInvalido() throws RuntimeException {
        // Arrange
        produto.setNome("   ");
        // Act
        RuntimeException thrown = assertThrows(
                RuntimeException.class,
                () -> driver.alterar(produto)
        );
        // Assert
        assertEquals("Nome invalido", thrown.getMessage());
    }

    @Test
    @DisplayName("Quando um novo nome nulo for fornecido para o produto")
    void quandoNovoNomeNulo() throws RuntimeException {
        // Arrange
        produto.setNome(null);
        // Act
        RuntimeException thrown = assertThrows(
                RuntimeException.class,
                () -> driver.alterar(produto)
        );
        // Assert
        assertEquals("Nome invalido", thrown.getMessage());
    }

    @Test
    @DisplayName("Quando um novo fabricante válido for fornecido para o produto")
    void quandoNovoFabricanteValido() {
        // Arrange
        produto.setFabricante("Empresa Dez Atualizada");
        Mockito.when(produtoRepository.update(produto))
                .thenReturn(Produto.builder()
                        .id(10L)
                        .codigoBarra("7896645900026")
                        .nome("Produto Dez")
                        .fabricante("Empresa Dez Atualizada")
                        .preco(450.00)
                        .build()
                );
        // Act
        Produto resultado = driver.alterar(produto);
        // Assert
        assertEquals("Empresa Dez Atualizada", resultado.getFabricante());
    }

    @Test
    @DisplayName("Quando um novo fabricante inválido for fornecido para o produto")
    void quandoNovoFabricanteInvalido() throws RuntimeException {
        // Arrange
        produto.setFabricante("   ");
        // Act
        RuntimeException thrown = assertThrows(
                RuntimeException.class,
                () -> driver.alterar(produto)
        );
        // Assert
        assertEquals("Fabricante invalido", thrown.getMessage());
    }

    @Test
    @DisplayName("Quando um novo fabricante nulo for fornecido para o produto")
    void quandoNovoFabricanteNulo() throws RuntimeException {
        // Arrange
        produto.setFabricante(null);
        // Act
        RuntimeException thrown = assertThrows(
                RuntimeException.class,
                () -> driver.alterar(produto)
        );
        // Assert
        assertEquals("Fabricante invalido", thrown.getMessage());
    }

    @Test
    @DisplayName("Quando um novo preço válido for fornecido para o produto")
    void quandoNovoPrecoValido() {
        // Arrange
        produto.setPreco(500.00);
        Mockito.when(produtoRepository.update(produto))
                .thenReturn(Produto.builder()
                        .id(10L)
                        .codigoBarra("7896645900026")
                        .nome("Produto Dez")
                        .fabricante("Empresa Dez")
                        .preco(500.00)
                        .build()
                );
        // Act
        Produto resultado = driver.alterar(produto);
        // Assert
        assertEquals(500.00, resultado.getPreco());
    }

    @Test
    @DisplayName("Quando o preço é menor ou igual a zero")
    void quandoNovoPrecoInvalido() throws RuntimeException {
        //Arrange
        produto.setPreco(0.0);
        //Act
        RuntimeException thrown = assertThrows(
                RuntimeException.class,
                () -> driver.alterar(produto)
        );
        //Assert
        assertEquals("Preco invalido", thrown.getMessage());
    }

    @Test
    @DisplayName("Quando um novo código de barra válido for fornecido para o produto")
    void quandoNovoCodigoBarraValido() {
        //Arrange
        produto.setCodigoBarra("9786588664025");
        Mockito.when(produtoRepository.update(produto))
                .thenReturn(Produto.builder()
                        .id(10L)
                        .codigoBarra("9786588664025")
                        .nome("Produto Dez")
                        .fabricante("Empresa Dez")
                        .preco(450.00)
                        .build()
                );
        //Act
        Produto resultado = driver.alterar(produto);
        //Assert
        assertEquals("9786588664025", resultado.getCodigoBarra());
    }

    @Test
    @DisplayName("Quando um novo código de barra inválido for fornecido para o produto")
    void quandoNovoCodigoBarraInvalido() throws RuntimeException {
        // Arrange
        produto.setCodigoBarra("   ");
        // Act
        RuntimeException thrown = assertThrows(
                RuntimeException.class,
                () -> driver.alterar(produto)
        );
        // Assert
        assertEquals("Codigo de barra invalido", thrown.getMessage());
    }

    @Test
    @DisplayName("Quando um novo código de barra nulo for fornecido para o produto")
    void quandoNovoCodigoBarraNulo() throws RuntimeException {
        // Arrange
        produto.setCodigoBarra(null);
        // Act
        RuntimeException thrown = assertThrows(
                RuntimeException.class,
                () -> driver.alterar(produto)
        );
        // Assert
        assertEquals("Codigo de barra invalido", thrown.getMessage());
    }

    @Test
    @DisplayName("Quando um novo código de barra com tamanho inválido for fornecido para o produto")
    void quandoNovoCodigoBarraTamanhoInvalido() throws RuntimeException {
        // Arrange
        produto.setCodigoBarra("78966459000260");
        // Act
        RuntimeException thrown = assertThrows(
                RuntimeException.class,
                () -> driver.alterar(produto)
        );
        // Assert
        assertEquals("Codigo de barra invalido", thrown.getMessage());
    }

    @Test
    @DisplayName("Quando um novo código de barra com dígito verficador inválido for fornecido para o produto")
    void quandoNovoCodigoBarraDigitoInvalido() throws RuntimeException {
        // Arrange
        produto.setCodigoBarra("7899137500104");
        // Act
        RuntimeException thrown = assertThrows(
                RuntimeException.class,
                () -> driver.alterar(produto)
        );
        // Assert
        assertEquals("Codigo de barra invalido", thrown.getMessage());
    }

}
