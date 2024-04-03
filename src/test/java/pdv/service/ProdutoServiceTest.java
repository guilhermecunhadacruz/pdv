package pdv.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import pdv.entity.Produto;
import pdv.repository.ProdutoRepository;

@SpringBootTest
public class ProdutoServiceTest {

    private ProdutoRepository produtoRepository;
    private ProdutoService produtoService;

    @BeforeEach
    void setUp() {
        produtoRepository = mock(ProdutoRepository.class);
        produtoService = new ProdutoService(produtoRepository);
    }

    @Test
    @DisplayName("Teste para encontrar todos os produtos")
    void testFindAll() {
        // Configuração do comportamento simulado do repository
        List<Produto> produtos = new ArrayList<>();
        produtos.add(new Produto(1L, "Produto 1", 10.0));
        produtos.add(new Produto(2L, "Produto 2", 20.0));
        when(produtoRepository.findAll()).thenReturn(produtos);

        // Execução do método do serviço
        List<Produto> result = produtoService.findAll();

        // Verificação do resultado
        assertEquals(2, result.size());
        assertEquals("Produto 1", result.get(0).getNomeProduto());
        assertEquals("Produto 2", result.get(1).getNomeProduto());
    }

    @Test
    @DisplayName("Teste para encontrar produto por ID")
    void testFindById() {
        // Configuração do comportamento simulado do repository
        Long id = 1L;
        Produto produto = new Produto(id, "Produto 1", 10.0);
        when(produtoRepository.findById(id)).thenReturn(Optional.of(produto));

        // Execução do método do serviço
        Optional<Produto> result = produtoService.findById(id);

        // Verificação do resultado
        assertEquals("Produto 1", result.get().getNomeProduto());
    }

    @Test
    @DisplayName("Teste para encontrar todos os produtos pelo nome usando JPQL")
    void testFindByNomeJPQL() {
        // Configuração do comportamento simulado do repository
        String nome = "Produto";
        List<Produto> produtos = new ArrayList<>();
        produtos.add(new Produto(1L, "Produto 1", 10.0));
        produtos.add(new Produto(2L, "Produto 2", 20.0));
        when(produtoRepository.findByNomeJPQL(nome)).thenReturn(produtos);

        // Execução do método do serviço
        List<Produto> result = produtoService.findByNomeJPQL(nome);

        // Verificação do resultado
        assertEquals(2, result.size());
        assertEquals("Produto 1", result.get(0).getNomeProduto());
        assertEquals("Produto 2", result.get(1).getNomeProduto());
    }

    @Test
    @DisplayName("Teste para encontrar todos os produtos pelo valor")
    void testFindByValor() {
        // Configuração do comportamento simulado do repository
        Double valor = 10.0;
        List<Produto> produtos = new ArrayList<>();
        produtos.add(new Produto(1L, "Produto 1", valor));
        produtos.add(new Produto(2L, "Produto 2", valor));
        when(produtoRepository.findByValor(valor)).thenReturn(produtos);

        // Execução do método do serviço
        List<Produto> result = produtoService.findByValor(valor);

        // Verificação do resultado
        assertEquals(2, result.size());
        assertEquals(valor, result.get(0).getValor());
        assertEquals(valor, result.get(1).getValor());
    }

    @Test
    @DisplayName("Teste para encontrar todos os produtos pelo nome do produto")
    void testFindByNomeProduto() {
        // Configuração do comportamento simulado do repository
        String nome = "Produto";
        List<Produto> produtos = new ArrayList<>();
        produtos.add(new Produto(1L, "Produto 1", 10.0));
        produtos.add(new Produto(2L, "Produto 2", 20.0));
        when(produtoRepository.findByNomeProduto(nome)).thenReturn(produtos);

        // Execução do método do serviço
        List<Produto> result = produtoService.findByNomeProduto(nome);

        // Verificação do resultado
        assertEquals(2, result.size());
        assertEquals("Produto 1", result.get(0).getNomeProduto());
        assertEquals("Produto 2", result.get(1).getNomeProduto());
    }

    @Test
    @DisplayName("Teste para salvar um novo produto")
    void testSave() {
        // Configuração do comportamento simulado do repository
        Produto produto = new Produto("Novo Produto", 30.0);
        when(produtoRepository.save(produto)).thenReturn(produto);

        // Execução do método do serviço
        Produto result = produtoService.save(produto);

        // Verificação do resultado
        assertEquals("Novo Produto", result.getNomeProduto());
        assertEquals(30.0, result.getValor());
    }

    @Test
    @DisplayName("Teste para atualizar um produto existente")
    void testUpdate() {
        // Configuração do comportamento simulado do repository
        Long id = 1L;
        Produto produto = new Produto(id, "Produto Atualizado", 50.0);
        when(produtoRepository.save(produto)).thenReturn(produto);

        // Execução do método do serviço
        Produto result = produtoService.update(id, produto);

        // Verificação do resultado
        assertEquals("Produto Atualizado", result.getNomeProduto());
        assertEquals(50.0, result.getValor());
    }

    @Test
    @DisplayName("Teste para deletar um produto pelo ID")
    void testDelete() {
        // Configuração do método do serviço
        Long id = 1L;

        // Execução do método do serviço
        produtoService.delete(id);

        // Verificação se o método deleteById foi chamado no repository com o ID correto
        verify(produtoRepository).deleteById(id);
    }

    @Test
    @DisplayName("Teste para tratamento de exceção ao encontrar todos os produtos")
    void testFindAllExceptionHandling() {
        // Configuração do comportamento simulado do repository
        when(produtoRepository.findAll()).thenThrow(new RuntimeException("Erro ao buscar produtos"));

        // Execução e verificação do método do serviço
        assertThrows(RuntimeException.class, () -> {
            produtoService.findAll();
        });
    }

    @Test
    @DisplayName("Teste para tratamento de exceção ao encontrar produto por ID")
    void testFindByIdExceptionHandling() {
        // Configuração do comportamento simulado do repository
        Long id = 1L;
        when(produtoRepository.findById(id)).thenThrow(new RuntimeException("Erro ao buscar produto por ID"));

        // Execução e verificação do método do serviço
        assertThrows(RuntimeException.class, () -> {
            produtoService.findById(id);
        });
    }
}
