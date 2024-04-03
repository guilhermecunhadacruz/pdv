package pdv.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import pdv.entity.Produto;
import pdv.service.ProdutoService;

class ProdutoControllerTest {

    private ProdutoService produtoService;
    private ProdutoController produtoController;

    @BeforeEach
    void setUp() {
        produtoService = mock(ProdutoService.class);
        produtoController = new ProdutoController(produtoService);
    }

    @Test
    @DisplayName("Teste do método findAll")
    void testFindAll() {
        try {
            List<Produto> produtos = new ArrayList<>();
            when(produtoService.findAll()).thenReturn(produtos);

            ResponseEntity<List<Produto>> responseEntity = produtoController.findAll();

            assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
            assertEquals(produtos, responseEntity.getBody());
        } catch (Exception e) {
            assertNull(e, "Exceção lançada: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("Teste do método findById")
    void testFindById() {
        try {
            Long id = 1L;
            Produto produto = new Produto();
            when(produtoService.findById(id)).thenReturn(Optional.of(produto));

            ResponseEntity<Produto> responseEntity = produtoController.findById(id);

            assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
            assertEquals(produto, responseEntity.getBody());
        } catch (Exception e) {
            assertNull(e, "Exceção lançada: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("Teste do método save")
    void testSave() {
        try {
            Produto produto = new Produto();
            when(produtoService.save(produto)).thenReturn(produto);

            ResponseEntity<Produto> responseEntity = produtoController.save(produto);

            assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
            assertEquals(produto, responseEntity.getBody());
        } catch (Exception e) {
            assertNull(e, "Exceção lançada: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("Teste do método update")
    void testUpdate() {
        try {
            Long id = 1L;
            Produto produto = new Produto();
            when(produtoService.update(id, produto)).thenReturn(produto);

            ResponseEntity<Produto> responseEntity = produtoController.update(id, produto);

            assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
            assertEquals(produto, responseEntity.getBody());
        } catch (Exception e) {
            assertNull(e, "Exceção lançada: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("Teste do método delete")
    void testDelete() {
        try {
            Long id = 1L;
            doNothing().when(produtoService).delete(id);

            ResponseEntity<Void> responseEntity = produtoController.delete(id);

            assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
            assertNull(responseEntity.getBody());
        } catch (Exception e) {
            assertNull(e, "Exceção lançada: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("Teste do método findByNomeJPQL")
    void testFindByNomeJPQL() {
        try {
            String nome = "Produto A";
            List<Produto> produtos = new ArrayList<>();
            when(produtoService.findByNomeJPQL(nome)).thenReturn(produtos);

            ResponseEntity<List<Produto>> responseEntity = produtoController.findByNomeJPQL(nome);

            assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
            assertEquals(produtos, responseEntity.getBody());
        } catch (Exception e) {
            assertNull(e, "Exceção lançada: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("Teste do método findByValor")
    void testFindByValor() {
        try {
            Double valor = 10.5;
            List<Produto> produtos = new ArrayList<>();
            when(produtoService.findByValor(valor)).thenReturn(produtos);

            ResponseEntity<List<Produto>> responseEntity = produtoController.findByValor(valor);

            assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
            assertEquals(produtos, responseEntity.getBody());
        } catch (Exception e) {
            assertNull(e, "Exceção lançada: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("Teste do método findByNomeProduto")
    void testFindByNomeProduto() {
        try {
            String nome = "Produto B";
            List<Produto> produtos = new ArrayList<>();
            when(produtoService.findByNomeProduto(nome)).thenReturn(produtos);

            ResponseEntity<List<Produto>> responseEntity = produtoController.findByNomeProduto(nome);

            assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
            assertEquals(produtos, responseEntity.getBody());
        } catch (Exception e) {
            assertNull(e, "Exceção lançada: " + e.getMessage());
        }
    }
}
