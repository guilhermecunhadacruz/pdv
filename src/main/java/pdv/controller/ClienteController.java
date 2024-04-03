package pdv.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pdv.entity.Cliente;
import pdv.service.ClienteService;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
	@Autowired
	private ClienteService clienteService;

    
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    // Método: GET
    // URL: http://localhost:8080/api/clientes
    // Descrição: Retorna todos os clientes cadastrados.
    @GetMapping
    public ResponseEntity<List<Cliente>> findAll() {
        try {
            List<Cliente> clientes = clienteService.findAll();
            return ResponseEntity.ok().body(clientes);
        } catch (Exception e) {
            e.printStackTrace(); // Imprime o stack trace da exceção no console
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Método: GET
    // URL: http://localhost:8080/api/clientes/{id}
    // Descrição: Retorna o cliente correspondente ao ID especificado na URL.
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable Long id) {
        try {
            return clienteService.findById(id)
                    .map(cliente -> ResponseEntity.ok().body(cliente))
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            e.printStackTrace(); // Imprime o stack trace da exceção no console
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Método: POST
    // URL: http://localhost:8080/api/clientes
    // Descrição: Salva um novo cliente.
    @PostMapping
    public ResponseEntity<Cliente> save(@RequestBody Cliente cliente) {
        try {
            // Verifica se o cliente é nulo
            if (cliente == null) {
                return ResponseEntity.badRequest().body(null);
            }
            
            // Salva o cliente
            Cliente savedCliente = clienteService.save(cliente);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCliente);
        } catch (Exception e) {
            e.printStackTrace(); // Imprime o stack trace da exceção no console
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Método: PUT
    // URL: http://localhost:8080/api/clientes/{id}
    // Descrição: Atualiza um cliente existente pelo ID.
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> update(@PathVariable Long id, @RequestBody Cliente cliente) {
        try {
            Cliente updatedCliente = clienteService.update(id, cliente);
            return ResponseEntity.ok().body(updatedCliente);
        } catch (Exception e) {
            e.printStackTrace(); // Imprime o stack trace da exceção no console
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Método: DELETE
    // URL: http://localhost:8080/api/clientes/{id}
    // Descrição: Deleta um cliente pelo ID.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            clienteService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            e.printStackTrace(); // Imprime o stack trace da exceção no console
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Método: GET
    // URL: http://localhost:8080/api/clientes/buscarPorNome?nome={nome}
    // Descrição: Retorna os clientes com o nome especificado na consulta.
    @GetMapping("/buscarPorNome")
    public ResponseEntity<List<Cliente>> findByNome(@RequestParam String nome) {
        try {
            List<Cliente> clientes = clienteService.findByNome(nome);
            return new ResponseEntity<>(clientes, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace(); // Imprime o stack trace da exceção no console
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Método: GET
    // URL: http://localhost:8080/api/clientes/buscarPorIdade?idade={idade}
    // Descrição: Retorna os clientes com a idade especificada na consulta.
    @GetMapping("/buscarPorIdade")
    public ResponseEntity<List<Cliente>> findByIdade(@RequestParam Integer idade) {
        try {
            List<Cliente> clientes = clienteService.findByIdade(idade);
            return new ResponseEntity<>(clientes, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace(); // Imprime o stack trace da exceção no console
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Método: GET
    // URL: http://localhost:8080/api/clientes/buscarPorCpf?cpf={cpf}
    // Descrição: Retorna o cliente com o CPF especificado na consulta.
    @GetMapping("/buscarPorCpf")
    public ResponseEntity<Cliente> findByCpf(@RequestParam String cpf) {
        try {
            Optional<Cliente> cliente = clienteService.findByCpf(cpf);
            return cliente.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            e.printStackTrace(); // Imprime o stack trace da exceção no console
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}

//Ass: Guilherme Cunha da Cruz