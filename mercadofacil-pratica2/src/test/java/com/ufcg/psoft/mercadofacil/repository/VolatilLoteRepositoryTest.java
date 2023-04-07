package com.ufcg.psoft.mercadofacil.repository;

import com.ufcg.psoft.mercadofacil.model.Lote;
import com.ufcg.psoft.mercadofacil.model.Produto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VolatilLoteRepositoryTest {

   @Autowired
   VolatilLoteRepository driver;

   Lote lote;
   Lote resultado;
   Produto produto;

   @BeforeEach
   void setup() {
       produto = Produto.builder()
               .id(1L)
               .nome("Produto Base")
               .codigoBarra("123456789")
               .fabricante("Fabricante Base")
               .preco(125.36)
               .build();
       lote = Lote.builder()
               .id(1L)
               .numeroDeItens(100)
               .produto(produto)
               .build();
   }

   @AfterEach
   void tearDown() {
       produto = null;
       driver.deleteAll();
   }

   @Test
   @DisplayName("Adicionar o primeiro Lote no repositorio de dados")
   void salvarPrimeiroLote() {
       //Arrange
       //Act
       resultado = driver.save(lote);
       //Assert
       assertEquals(driver.findAll().size(),1);
       assertEquals(resultado.getId().longValue(), lote.getId().longValue());
       assertEquals(resultado.getProduto(), produto);
   }

   @Test
   @DisplayName("Adicionar o segundo Lote (ou posterior) no repositorio de dados")
   void salvarSegundoLoteOuPosterior() {
       //Arrange
       Produto produtoExtra = Produto.builder()
               .id(2L)
               .nome("Produto Extra")
               .codigoBarra("987654321")
               .fabricante("Fabricante Extra")
               .preco(125.36)
               .build();
       Lote loteExtra = Lote.builder()
               .id(2L)
               .numeroDeItens(100)
               .produto(produtoExtra)
               .build();
       //Act
       driver.save(lote);
       resultado = driver.save(loteExtra);
       //Assert
       assertEquals(driver.findAll().size(),2);
       assertEquals(resultado.getId().longValue(), loteExtra.getId().longValue());
       assertEquals(resultado.getProduto(), produtoExtra);
   }

    @Test
    @DisplayName("Encontrar Lote armazenado no repositorio de dados")
    void encontrarLoteArmazenado() {
        //Arrange
        //Act
        driver.save(lote);
        //Assert
        assertEquals(lote.getId(), driver.find(lote.getId()));
    }

    @Test
    @DisplayName("Encontrar Lote não armazenado no repositorio de dados")
    void encontrarLoteNaoArmazenado() {
        //Arrange
        //Act
        driver.save(lote);
        //Assert
        assertEquals(null, driver.find(2L));
    }

    @Test
    @DisplayName("Encontrar todos os Lotes armazenados no repositorio de dados")
    void encontrarTodosLotesArmazenados() {
        //Arrange
        Produto produtoExtra = Produto.builder()
                .id(2L)
                .nome("Produto Extra")
                .codigoBarra("987654321")
                .fabricante("Fabricante Extra")
                .preco(125.36)
                .build();
        Lote loteExtra = Lote.builder()
                .id(2L)
                .numeroDeItens(100)
                .produto(produtoExtra)
                .build();
        List<Lote> lista = new ArrayList<>();
        lista.add(lote);
        lista.add(loteExtra);
        //Act
        driver.save(lote);
        driver.save(loteExtra);
        //Assert
        assertEquals(lista, driver.findAll());
    }

    @Test
    @DisplayName("Encontrar repositorio de Lotes vazio")
    void encontrarRepositorioDeLotesVazio() {
        //Arrange
        List<Lote> lista = new ArrayList<>();
        //Act
        //Assert
        assertEquals(lista, driver.findAll());
    }

    @Test
    @DisplayName("Atualizar Lote armazenado no repositorio de dados")
    void atualizarLoteArmazenado() {
        //Arrange
        driver.save(lote);
        Produto produtoExtra = Produto.builder()
                .id(2L)
                .nome("Produto Extra")
                .codigoBarra("987654321")
                .fabricante("Fabricante Extra")
                .preco(125.36)
                .build();
        lote.setProduto(produtoExtra);
        //Act
        driver.update(lote);
        //Assert
        assertEquals(produtoExtra, driver.find(lote.getId()).getProduto());
    }

    @Test
    @DisplayName("Atualizar um Lote armazenado entre vários no repositorio de dados")
    void atualizarUmLoteArmazenado() {
        //Arrange
        driver.save(lote);
        Produto produtoExtra = Produto.builder()
                .id(2L)
                .nome("Produto Extra")
                .codigoBarra("987654321")
                .fabricante("Fabricante Extra")
                .preco(125.36)
                .build();
        Lote loteExtra = Lote.builder()
                .id(2L)
                .numeroDeItens(100)
                .produto(produtoExtra)
                .build();
        driver.save(loteExtra);
        lote.setProduto(produtoExtra);
        //Act
        driver.update(lote);
        //Assert
        assertEquals(produtoExtra, driver.find(lote.getId()).getProduto());
        assertEquals(2, driver.findAll().size());
    }

    @Test
    @DisplayName("Atualizar Lote não armazenado no repositorio de dados")
    void atualizarLoteNaoArmazenado() {
        //Arrange
        Produto produtoExtra = Produto.builder()
                .id(2L)
                .nome("Produto Extra")
                .codigoBarra("987654321")
                .fabricante("Fabricante Extra")
                .preco(125.36)
                .build();
        lote.setProduto(produtoExtra);
        //Act
        driver.update(lote);
        //Assert
        assertEquals(produtoExtra, driver.find(lote.getId()).getProduto());
    }

    @Test
    @DisplayName("Remover Lote armazenado no repositorio de dados")
    void removerLoteArmazenado() {
        //Arrange
        driver.save(lote);
        List<Lote> lista = new ArrayList<>();
        //Act
        driver.delete(lote);
        //Assert
        assertEquals(lista, driver.findAll());
    }

    @Test
    @DisplayName("Remover Lote não armazenado no repositorio de dados")
    void removerLoteNaoArmazenado() {
        //Arrange
        driver.save(lote);
        Produto produtoExtra = Produto.builder()
                .id(2L)
                .nome("Produto Extra")
                .codigoBarra("987654321")
                .fabricante("Fabricante Extra")
                .preco(125.36)
                .build();
        Lote loteExtra = Lote.builder()
                .id(2L)
                .numeroDeItens(100)
                .produto(produtoExtra)
                .build();
        List<Lote> lista = new ArrayList<>();
        lista.add(lote);
        //Act
        driver.delete(loteExtra);
        //Assert
        assertEquals(lista, driver.findAll());
        assertEquals(1, driver.findAll().size());
    }

    @Test
    @DisplayName("Remover todos os Lotes armazenados no repositorio de dados")
    void removerTodosLotesArmazenados() {
        //Arrange
        driver.save(lote);
        Produto produtoExtra = Produto.builder()
                .id(2L)
                .nome("Produto Extra")
                .codigoBarra("987654321")
                .fabricante("Fabricante Extra")
                .preco(125.36)
                .build();
        Lote loteExtra = Lote.builder()
                .id(2L)
                .numeroDeItens(100)
                .produto(produtoExtra)
                .build();
        driver.save(loteExtra);
        List<Lote> lista = new ArrayList<>();
        //Act
        driver.deleteAll();
        //Assert
        assertEquals(lista, driver.findAll());
        assertEquals(0, driver.findAll().size());
    }
}
