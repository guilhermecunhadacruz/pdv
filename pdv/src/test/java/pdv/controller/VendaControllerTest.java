package pdv.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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

    // Adicione testes para os métodos restantes da VendaController
}


//Ass: Guilherme Cunha da Cruz