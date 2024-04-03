package pdv.controller;

import java.util.List;

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

import pdv.entity.Funcionario;
import pdv.service.FuncionarioService;

@RestController
@RequestMapping("/api/funcionarios")
public class FuncionarioController {

	@Autowired
	private FuncionarioService funcionarioService;

	// Construtor que aceita FuncionarioService como parâmetro
	public FuncionarioController(FuncionarioService funcionarioService) {
		this.funcionarioService = funcionarioService;
	}

	// Método: GET
	// URL: http://localhost:8080/api/funcionarios
	// Descrição: Retorna todos os funcionários cadastrados.
	@GetMapping
	public ResponseEntity<List<Funcionario>> findAll() {
		try {
			List<Funcionario> funcionarios = funcionarioService.findAll();
			return new ResponseEntity<>(funcionarios, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace(); // Log de erro no console
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	// Método: GET
	// URL: http://localhost:8080/api/funcionarios/{id}
	// Descrição: Retorna o funcionário correspondente ao ID especificado na URL.
	@GetMapping("/{id}")
	public ResponseEntity<Funcionario> findById(@PathVariable Long id) {
		try {
			Funcionario funcionario = funcionarioService.findById(id)
					.orElseThrow(() -> new RuntimeException("Funcionário não encontrado!"));
			return ResponseEntity.ok().body(funcionario);
		} catch (Exception e) {
			e.printStackTrace(); // Log de erro no console
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	// Método: POST
	// URL: http://localhost:8080/api/funcionarios
	// Corpo: JSON representando o funcionário a ser salvo.
	// Descrição: Salva um novo funcionário.
	@PostMapping
	public ResponseEntity<Funcionario> save(@RequestBody Funcionario funcionario) {
		try {
			Funcionario savedFuncionario = funcionarioService.save(funcionario);
			return ResponseEntity.status(HttpStatus.CREATED).body(savedFuncionario);
		} catch (Exception e) {
			e.printStackTrace(); // Log de erro no console
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	// Método: PUT
	// URL: http://localhost:8080/api/funcionarios/{id}
	// Corpo: JSON representando os dados atualizados do funcionário.
	// Descrição: Atualiza o funcionário correspondente ao ID especificado na URL.
	@PutMapping("/{id}")
	public ResponseEntity<Funcionario> update(@PathVariable Long id, @RequestBody Funcionario funcionario) {
		try {
			Funcionario updatedFuncionario = funcionarioService.update(id, funcionario);
			return ResponseEntity.ok().body(updatedFuncionario);
		} catch (Exception e) {
			e.printStackTrace(); // Log de erro no console
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	// Método: DELETE
	// URL: http://localhost:8080/api/funcionarios/{id}
	// Descrição: Deleta o funcionário correspondente ao ID especificado na URL.
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		try {
			funcionarioService.delete(id);
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			e.printStackTrace(); // Log de erro no console
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	// Método: GET
	// URL: http://localhost:8080/api/funcionarios/buscarPorNomeJPQL?nome={nome}
	// Descrição: Retorna os funcionários com o nome especificado na consulta usando
	// JPQL.
	@GetMapping("/buscarPorNomeJPQL")
	public ResponseEntity<List<Funcionario>> findByNomeJPQL(@RequestParam String nome) {
		try {
			List<Funcionario> funcionarios = funcionarioService.findByNomeJPQL(nome);
			return ResponseEntity.ok().body(funcionarios);
		} catch (Exception e) {
			e.printStackTrace(); // Log de erro no console
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	// Método: GET
	// URL: http://localhost:8080/api/funcionarios/buscarPorIdade?idade={idade}
	// Descrição: Retorna os funcionários com a idade especificada na consulta.
	@GetMapping("/buscarPorIdade")
	public ResponseEntity<List<Funcionario>> findByIdade(@RequestParam Integer idade) {
		try {
			List<Funcionario> funcionarios = funcionarioService.findByIdade(idade);
			return ResponseEntity.ok().body(funcionarios);
		} catch (Exception e) {
			e.printStackTrace(); // Log de erro no console
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	// Método: GET
	// URL:http://localhost:8080/api/funcionarios/buscarPorMatricula?matricula={matricula}
	// Descrição: Retorna o funcionário com a matrícula especificada na consulta.
	@GetMapping("/buscarPorMatricula")
	public ResponseEntity<Funcionario> findByMatricula(@RequestParam String matricula) {
		try {
			Funcionario funcionario = funcionarioService.findByMatricula(matricula);
			return ResponseEntity.ok().body(funcionario);
		} catch (Exception e) {
			e.printStackTrace(); // Log de erro no console
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
}

//Ass: Guilherme Cunha da Cruz
