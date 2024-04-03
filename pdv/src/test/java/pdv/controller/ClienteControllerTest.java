package pdv.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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

import pdv.entity.Cliente;
import pdv.service.ClienteService;

class ClienteControllerTest {

    private ClienteService clienteService;
    private ClienteController clienteController;

    @BeforeEach
    void setUp() {
        clienteService = mock(ClienteService.class);
        clienteController = new ClienteController(clienteService);
    }

    @Test
    @DisplayName("Teste do método findAll")
    void testFindAll() {
        List<Cliente> clientes = new ArrayList<>();

        when(clienteService.findAll()).thenReturn(clientes);

        ResponseEntity<List<Cliente>> responseEntity = clienteController.findAll();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(clientes, responseEntity.getBody());
    }

    @Test
    @DisplayName("Teste do método findById")
    void testFindById() {
        Long id = 1L;
        Cliente cliente = new Cliente();

        when(clienteService.findById(id)).thenReturn(Optional.of(cliente));

        ResponseEntity<Cliente> responseEntity = clienteController.findById(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(cliente, responseEntity.getBody());
    }

    @Test
    @DisplayName("Teste do método save")
    void testSave() {
        Cliente cliente = new Cliente();

        when(clienteService.save(cliente)).thenReturn(cliente);

        ResponseEntity<Cliente> responseEntity = clienteController.save(cliente);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(cliente, responseEntity.getBody());
    }

    @Test
    @DisplayName("Teste do método update")
    void testUpdate() {
        Long id = 1L;
        Cliente cliente = new Cliente();

        when(clienteService.update(id, cliente)).thenReturn(cliente);

        ResponseEntity<Cliente> responseEntity = clienteController.update(id, cliente);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(cliente, responseEntity.getBody());
    }

    @Test
    @DisplayName("Teste do método delete")
    void testDelete() {
        Long id = 1L;

        doNothing().when(clienteService).delete(id);

        ResponseEntity<Void> responseEntity = clienteController.delete(id);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }

    @Test
    @DisplayName("Teste do método findByNome")
    void testFindByNome() {
        String nome = "John Doe";
        List<Cliente> clientes = new ArrayList<>();

        when(clienteService.findByNome(nome)).thenReturn(clientes);

        ResponseEntity<List<Cliente>> responseEntity = clienteController.findByNome(nome);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(clientes, responseEntity.getBody());
    }

    @Test
    @DisplayName("Teste do método findByIdade")
    void testFindByIdade() {
        Integer idade = 30;
        List<Cliente> clientes = new ArrayList<>();

        when(clienteService.findByIdade(idade)).thenReturn(clientes);

        ResponseEntity<List<Cliente>> responseEntity = clienteController.findByIdade(idade);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(clientes, responseEntity.getBody());
    }

    @Test
    @DisplayName("Teste do método findByCpf")
    void testFindByCpf() {
        String cpf = "12345678901";
        Cliente cliente = new Cliente();

        when(clienteService.findByCpf(cpf)).thenReturn(Optional.of(cliente));

        ResponseEntity<Cliente> responseEntity = clienteController.findByCpf(cpf);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(cliente, responseEntity.getBody());
    }
}
