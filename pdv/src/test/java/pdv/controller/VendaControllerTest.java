package pdv.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import pdv.entity.Cliente;
import pdv.entity.Funcionario;
import pdv.entity.Venda;
import pdv.service.VendaService;

class VendaControllerTest {

    private VendaService vendaService;
    private VendaController vendaController;

    @BeforeEach
    void setUp() {
        vendaService = mock(VendaService.class);
        vendaController = new VendaController(vendaService);
    }

    @Test
    @DisplayName("Teste do método listAll")
    void testListAll() {
        List<Venda> vendas = new ArrayList<>();
        // Adicione vendas fictícias à lista de vendas

        when(vendaService.listAll()).thenReturn(vendas);

        ResponseEntity<List<Venda>> responseEntity = vendaController.listAll();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(vendas, responseEntity.getBody());
    }

    @Test
    @DisplayName("Teste do método findById")
    void testFindById() {
        Long id = 1L;
        Venda venda = new Venda();
        // Defina os detalhes da venda fictícia

        when(vendaService.findById(id)).thenReturn(venda);

        ResponseEntity<Venda> responseEntity = vendaController.findById(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(venda, responseEntity.getBody());
    }

    @Test
    @DisplayName("Teste do método save")
    void testSave() {
        Venda venda = new Venda();
        // Defina os detalhes da venda fictícia

        when(vendaService.save(venda)).thenReturn("Venda salva com sucesso.");

        ResponseEntity<String> responseEntity = vendaController.save(venda);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("Venda salva com sucesso.", responseEntity.getBody());
    }

    @Test
    @DisplayName("Teste do método update")
    void testUpdate() {
        Long id = 1L;
        Venda venda = new Venda();
        // Defina os detalhes da venda fictícia

        when(vendaService.update(id, venda)).thenReturn("Venda atualizada com sucesso.");

        ResponseEntity<String> responseEntity = vendaController.update(venda, id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Venda atualizada com sucesso.", responseEntity.getBody());
    }

    @Test
    @DisplayName("Teste do método delete")
    void testDelete() {
        Long id = 1L;

        when(vendaService.delete(id)).thenReturn("Venda deletada com sucesso.");

        ResponseEntity<String> responseEntity = vendaController.delete(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Venda deletada com sucesso.", responseEntity.getBody());
    }

    @Test
    @DisplayName("Teste de exceção do método findById")
    void testFindByIdException() {
        Long id = 1L;

        when(vendaService.findById(id)).thenThrow(new RuntimeException("Erro ao buscar venda por ID"));

        ResponseEntity<Venda> responseEntity = vendaController.findById(id);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }

    @Test
    @DisplayName("Teste de exceção do método save")
    void testSaveException() {
        Venda venda = new Venda();

        when(vendaService.save(venda)).thenThrow(new RuntimeException("Erro ao salvar venda"));

        ResponseEntity<String> responseEntity = vendaController.save(venda);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }

    @Test
    @DisplayName("Teste de exceção do método update")
    void testUpdateException() {
        Long id = 1L;
        Venda venda = new Venda();

        when(vendaService.update(id, venda)).thenThrow(new RuntimeException("Erro ao atualizar venda"));

        ResponseEntity<String> responseEntity = vendaController.update(venda, id);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    @DisplayName("Teste de exceção do método delete")
    void testDeleteException() {
        Long id = 1L;

        when(vendaService.delete(id)).thenThrow(new RuntimeException("Erro ao deletar venda"));

        ResponseEntity<String> responseEntity = vendaController.delete(id);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    @DisplayName("Teste do método consultarVendasPorValorSuperior")
    void testConsultarVendasPorValorSuperior() {
        Double valor = 100.0;

        when(vendaService.consultarVendasPorValorSuperior(eq(valor))).thenThrow(new RuntimeException("Erro na consulta por valor"));

        ResponseEntity<List<Venda>> responseEntity = vendaController.consultarVendasPorValorSuperior(valor);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }

    @Test
    @DisplayName("Teste do método consultarVendasPorCliente")
    void testConsultarVendasPorCliente() {
        Long idCliente = 1L;

        when(vendaService.consultarVendasPorCliente(any(Cliente.class))).thenThrow(new RuntimeException("Erro na consulta por cliente"));

        ResponseEntity<List<Venda>> responseEntity = vendaController.consultarVendasPorCliente(idCliente);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }

    @Test
    @DisplayName("Teste do método consultarVendasPorFuncionario")
    void testConsultarVendasPorFuncionario() {
        Long idFuncionario = 1L;

        when(vendaService.consultarVendasPorFuncionario(any(Funcionario.class))).thenThrow(new RuntimeException("Erro na consulta por funcionário"));

        ResponseEntity<List<Venda>> responseEntity = vendaController.consultarVendasPorFuncionario(idFuncionario);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }
}
