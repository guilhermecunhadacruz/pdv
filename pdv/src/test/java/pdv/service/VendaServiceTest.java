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

import pdv.entity.Cliente;
import pdv.entity.Funcionario;
import pdv.entity.Produto;
import pdv.entity.Venda;
import pdv.repository.VendaRepository;

public class VendaServiceTest {

    private VendaRepository vendaRepository;
    private VendaService vendaService;

    @BeforeEach
    void setUp() {
        vendaRepository = mock(VendaRepository.class);
        vendaService = new VendaService(vendaRepository);
    }

    @Test
    @DisplayName("Teste para salvar uma venda")
    void testSave() {
        // Criar uma venda de exemplo
        Venda venda = new Venda();
        List<Produto> produtos = new ArrayList<>();
        produtos.add(new Produto("Produto 1", 10.0));
        produtos.add(new Produto("Produto 2", 20.0));
        venda.setProdutos(produtos);

        // Configurar comportamento simulado do repositório
        when(vendaRepository.save(venda)).thenReturn(venda);

        // Executar o método do serviço
        String result = vendaService.save(venda);

        // Verificar se o método do repositório foi chamado com os argumentos corretos
        verify(vendaRepository).save(venda);

        // Verificar o resultado
        assertEquals("Venda salva com sucesso!", result);
    }

    @Test
    @DisplayName("Teste para atualizar uma venda pelo ID")
    void testUpdate() {
        // Criar uma venda de exemplo
        Long id = 1L;
        Venda venda = new Venda();
        venda.setStatus("CANCELADO");
        List<Produto> produtos = new ArrayList<>();
        produtos.add(new Produto("Produto 1", 10.0));
        produtos.add(new Produto("Produto 2", 20.0));
        venda.setProdutos(produtos);

        // Configurar comportamento simulado do repositório
        when(vendaRepository.existsById(id)).thenReturn(true);
        when(vendaRepository.save(venda)).thenReturn(venda);

        // Executar o método do serviço
        String result = vendaService.update(id, venda);

        // Verificar se o método do repositório foi chamado com os argumentos corretos
        verify(vendaRepository).save(venda);

        // Verificar o resultado
        assertEquals("Venda atualizada com sucesso!", result);
        assertEquals(null, venda.getProdutos());
        assertEquals(0.0, venda.getValorTotal());
    }

    @Test
    @DisplayName("Teste para listar todas as vendas")
    void testListAll() {
        // Criar uma lista de vendas de exemplo
        List<Venda> vendas = new ArrayList<>();
        vendas.add(new Venda());
        vendas.add(new Venda());

        // Configurar comportamento simulado do repositório
        when(vendaRepository.findAll()).thenReturn(vendas);

        // Executar o método do serviço
        List<Venda> result = vendaService.listAll();

        // Verificar o resultado
        assertEquals(2, result.size());
    }

    @Test
    @DisplayName("Teste para buscar uma venda pelo ID")
    void testFindById() {
        // Criar uma venda de exemplo
        Long id = 1L;
        Venda venda = new Venda();

        // Configurar comportamento simulado do repositório
        when(vendaRepository.findById(id)).thenReturn(Optional.of(venda));

        // Executar o método do serviço
        Venda result = vendaService.findById(id);

        // Verificar o resultado
        assertEquals(venda, result);
    }

    @Test
    @DisplayName("Teste para deletar uma venda pelo ID")
    void testDelete() {
        // Criar uma venda de exemplo
        Long id = 1L;

        // Configurar comportamento simulado do repositório
        when(vendaRepository.existsById(id)).thenReturn(true);

        // Executar o método do serviço
        String result = vendaService.delete(id);

        // Verificar se o método do repositório foi chamado com os argumentos corretos
        verify(vendaRepository).deleteById(id);

        // Verificar o resultado
        assertEquals("Venda deletada com sucesso!", result);
    }

    @Test
    @DisplayName("Teste para consultar vendas cujo valor total é superior a um valor especificado")
    void testConsultarVendasPorValorSuperior() {
        // Criar uma lista de vendas de exemplo
        List<Venda> vendas = new ArrayList<>();
        vendas.add(new Venda());
        vendas.add(new Venda());

        // Configurar comportamento simulado do repositório
        Double valor = 100.0;
        when(vendaRepository.findByValorTotalMaior(valor)).thenReturn(vendas);

        // Executar o método do serviço
        List<Venda> result = vendaService.consultarVendasPorValorSuperior(valor);

        // Verificar o resultado
        assertEquals(2, result.size());
    }

    @Test
    @DisplayName("Teste para consultar vendas por cliente")
    void testConsultarVendasPorCliente() {
        // Criar uma lista de vendas de exemplo
        List<Venda> vendas = new ArrayList<>();
        vendas.add(new Venda());
        vendas.add(new Venda());

        // Criar um cliente de exemplo
        Cliente cliente = new Cliente();

        // Configurar comportamento simulado do repositório
        when(vendaRepository.findByCliente(cliente)).thenReturn(vendas);

        // Executar o método do serviço
        List<Venda> result = vendaService.consultarVendasPorCliente(cliente);

        // Verificar o resultado
        assertEquals(2, result.size());
    }

    @Test
    @DisplayName("Teste para consultar vendas por funcionário")
    void testConsultarVendasPorFuncionario() {
        // Criar uma lista de vendas de exemplo
        List<Venda> vendas = new ArrayList<>();
        vendas.add(new Venda());
        vendas.add(new Venda());

        // Criar um funcionário de exemplo
        Funcionario funcionario = new Funcionario();

        // Configurar comportamento simulado do repositório
        when(vendaRepository.findByFuncionario(funcionario)).thenReturn(vendas);

        // Executar o método do serviço
        List<Venda> result = vendaService.consultarVendasPorFuncionario(funcionario);

        // Verificar o resultado
        assertEquals(2, result.size());
    }

    @Test
    @DisplayName("Teste para verificar se o método save lança exceção quando a venda é nula")
    void testSaveWithNullVenda() {
        // Executar o método do serviço com venda nula e verificar se lança exceção
        assertThrows(RuntimeException.class, () -> {
            vendaService.save(null);
        });
    }

    @Test
    @DisplayName("Teste para verificar se o método update lança exceção quando a venda não existe")
    void testUpdateWithNonExistentVenda() {
        // Configurar comportamento simulado do repositório
        Long id = 1L;
        Venda venda = new Venda();
        when(vendaRepository.existsById(id)).thenReturn(false);

        // Executar o método do serviço com venda não existente e verificar se lança exceção
        assertThrows(RuntimeException.class, () -> {
            vendaService.update(id, venda);
        });
    }

    @Test
    @DisplayName("Teste para verificar se o método delete lança exceção quando a venda não existe")
    void testDeleteWithNonExistentVenda() {
        // Configurar comportamento simulado do repositório
        Long id = 1L;
        when(vendaRepository.existsById(id)).thenReturn(false);

        // Executar o método do serviço com venda não existente e verificar se lança exceção
        assertThrows(RuntimeException.class, () -> {
            vendaService.delete(id);
        });
    }

    @Test
    @DisplayName("Teste para verificar se o método findById lança exceção quando a venda não existe")
    void testFindByIdWithNonExistentVenda() {
        // Configurar comportamento simulado do repositório
        Long id = 1L;
        when(vendaRepository.findById(id)).thenReturn(Optional.empty());

        // Executar o método do serviço com venda não existente e verificar se lança exceção
        assertThrows(RuntimeException.class, () -> {
            vendaService.findById(id);
        });
    }
}
