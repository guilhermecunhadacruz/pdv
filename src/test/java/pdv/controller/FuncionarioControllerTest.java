package pdv.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
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

import pdv.entity.Funcionario;
import pdv.service.FuncionarioService;

class FuncionarioControllerTest {

    private FuncionarioService funcionarioService;
    private FuncionarioController funcionarioController;

    @BeforeEach
    void setUp() {
        funcionarioService = mock(FuncionarioService.class);
        funcionarioController = new FuncionarioController(funcionarioService);
    }

    @Test
    @DisplayName("Teste do método findAll")
    void testFindAll() {
        try {
            List<Funcionario> funcionarios = new ArrayList<>();
            when(funcionarioService.findAll()).thenReturn(funcionarios);

            ResponseEntity<List<Funcionario>> responseEntity = funcionarioController.findAll();

            assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
            assertEquals(funcionarios, responseEntity.getBody());
        } catch (Exception e) {
            fail("Exceção não esperada: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("Teste do método findById")
    void testFindById() {
        try {
            Long id = 1L;
            Funcionario funcionario = new Funcionario();
            when(funcionarioService.findById(id)).thenReturn(Optional.of(funcionario));

            ResponseEntity<Funcionario> responseEntity = funcionarioController.findById(id);

            assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
            assertEquals(funcionario, responseEntity.getBody());
        } catch (Exception e) {
            fail("Exceção não esperada: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("Teste do método save")
    void testSave() {
        try {
            Funcionario funcionario = new Funcionario();
            when(funcionarioService.save(funcionario)).thenReturn(funcionario);

            ResponseEntity<Funcionario> responseEntity = funcionarioController.save(funcionario);

            assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
            assertEquals(funcionario, responseEntity.getBody());
        } catch (Exception e) {
            fail("Exceção não esperada: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("Teste do método update")
    void testUpdate() {
        try {
            Long id = 1L;
            Funcionario funcionario = new Funcionario();
            when(funcionarioService.update(id, funcionario)).thenReturn(funcionario);

            ResponseEntity<Funcionario> responseEntity = funcionarioController.update(id, funcionario);

            assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
            assertEquals(funcionario, responseEntity.getBody());
        } catch (Exception e) {
            fail("Exceção não esperada: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("Teste do método delete")
    void testDelete() {
        try {
            Long id = 1L;
            doNothing().when(funcionarioService).delete(id);

            ResponseEntity<Void> responseEntity = funcionarioController.delete(id);

            assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
            assertNull(responseEntity.getBody());
        } catch (Exception e) {
            fail("Exceção não esperada: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("Teste do método findByNomeJPQL")
    void testFindByNomeJPQL() {
        try {
            String nome = "John Doe";
            List<Funcionario> funcionarios = new ArrayList<>();
            when(funcionarioService.findByNomeJPQL(nome)).thenReturn(funcionarios);

            ResponseEntity<List<Funcionario>> responseEntity = funcionarioController.findByNomeJPQL(nome);

            assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
            assertEquals(funcionarios, responseEntity.getBody());
        } catch (Exception e) {
            fail("Exceção não esperada: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("Teste do método findByIdade")
    void testFindByIdade() {
        try {
            Integer idade = 30;
            List<Funcionario> funcionarios = new ArrayList<>();
            when(funcionarioService.findByIdade(idade)).thenReturn(funcionarios);

            ResponseEntity<List<Funcionario>> responseEntity = funcionarioController.findByIdade(idade);

            assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
            assertEquals(funcionarios, responseEntity.getBody());
        } catch (Exception e) {
            fail("Exceção não esperada: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("Teste do método findByMatricula")
    void testFindByMatricula() {
        try {
            String matricula = "12345";
            Funcionario funcionario = new Funcionario();
            when(funcionarioService.findByMatricula(matricula)).thenReturn(funcionario);

            ResponseEntity<Funcionario> responseEntity = funcionarioController.findByMatricula(matricula);

            assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
            assertNotNull(responseEntity.getBody());
            assertEquals(funcionario, responseEntity.getBody());
        } catch (Exception e) {
            fail("Exceção não esperada: " + e.getMessage());
        }
    }
}
