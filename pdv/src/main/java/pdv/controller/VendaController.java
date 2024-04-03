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

import pdv.entity.Cliente;
import pdv.entity.Funcionario;
import pdv.entity.Venda;
import pdv.service.VendaService;

@RestController
@RequestMapping("/api/venda")
public class VendaController {

    @Autowired
    private VendaService vendaService;
    public VendaController(VendaService vendaService) {
        this.vendaService = vendaService;
    }


    // Método: GET
    // URL: http://localhost:8080/api/venda/listAll
    // Lista todas as vendas
    @GetMapping("/listAll")
    public ResponseEntity<List<Venda>> listAll() {
        try {
            List<Venda> vendas = this.vendaService.listAll();
            return new ResponseEntity<>(vendas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // Método: GET
    // URL: http://localhost:8080/api/venda/{id}
    // Busca uma venda pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Venda> findById(@PathVariable Long id) {
        try {
            Venda venda = this.vendaService.findById(id);
            return new ResponseEntity<>(venda, HttpStatus.OK);
        } catch (Exception e) {
        	e.printStackTrace(); // Log de erro no console
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Método: POST
    // URL: http://localhost:8080/api/venda/save
    // Salva uma nova venda
    @PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody Venda venda) {
		try {
			String mensagem = this.vendaService.save(venda);
			return new ResponseEntity<String>(mensagem, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace(); // Log de erro no console
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}



    // Método: PUT
    // URL: http://localhost:8080/api/venda/update/{id}
    // Atualiza uma venda existente pelo ID
    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@RequestBody Venda venda, @PathVariable Long id) {
        try {
            String mensagem = this.vendaService.update(id, venda); // Atualiza a venda
            return new ResponseEntity<String>(mensagem, HttpStatus.OK); // Retorna uma resposta de sucesso com a mensagem
        } catch (Exception e) {
            return new ResponseEntity<String>("Erro ao atualizar a venda: " + e.getMessage(), HttpStatus.BAD_REQUEST); // Retorna
                                                                                                                        // uma
                                                                                                                        // resposta
                                                                                                                        // de
                                                                                                                        // erro
                                                                                                                        // com
                                                                                                                        // a
                                                                                                                        // mensagem
        }
    }

    // Método: DELETE
    // URL: http://localhost:8080/api/venda/delete/{id}
    // Deleta uma venda pelo ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            String mensagem = this.vendaService.delete(id); // Deleta a venda
            return new ResponseEntity<>(mensagem, HttpStatus.OK); // Retorna uma resposta de sucesso com a mensagem
        } catch (Exception e) {
            return new ResponseEntity<String>("Erro ao deletar a venda: " + e.getMessage(), HttpStatus.BAD_REQUEST); // Retorna
                                                                                                                        // uma
                                                                                                                        // resposta
                                                                                                                        // de
                                                                                                                        // erro
                                                                                                                        // com
                                                                                                                        // a
                                                                                                                        // mensagem
        }
    }

    // Método: GET
    // URL: http://localhost:8080/api/venda/valor-superior?valor={valor}
    // Consulta vendas cujo valor é superior a um valor especificado
    @GetMapping("/valor-superior")
    public ResponseEntity<List<Venda>> consultarVendasPorValorSuperior(@RequestParam Double valor) {
        try {
            List<Venda> vendas = vendaService.consultarVendasPorValorSuperior(valor); // Consulta as vendas cujo valor é
                                                                                        // superior ao valor
                                                                                        // especificado
            return new ResponseEntity<>(vendas, HttpStatus.OK); // Retorna uma resposta de sucesso com as vendas
                                                                // encontradas
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Retorna uma resposta de erro
        }
    }

    // Método: GET
    // URL: http://localhost:8080/api/venda/por-cliente?idCliente={idCliente}
    // Endpoint para consultar vendas por cliente
    @GetMapping("/por-cliente")
    public ResponseEntity<List<Venda>> consultarVendasPorCliente(@RequestParam Long idCliente) {
        try {
            Cliente cliente = new Cliente();
            cliente.setIdCliente(idCliente);
            List<Venda> vendas = vendaService.consultarVendasPorCliente(cliente); // Consulta as vendas do cliente com o
                                                                                    // ID especificado
            return new ResponseEntity<>(vendas, HttpStatus.OK); // Retorna uma resposta de sucesso com as vendas
                                                                // encontradas
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Retorna uma resposta de erro
        }
    }

    // Método: GET
    // URL:
    // http://localhost:8080/api/venda/por-funcionario?idFuncionario={idFuncionario}
    // Endpoint para consultar vendas por funcionário
    @GetMapping("/por-funcionario")
    public ResponseEntity<List<Venda>> consultarVendasPorFuncionario(@RequestParam Long idFuncionario) {
        try {
            Funcionario funcionario = new Funcionario();
            funcionario.setIdFuncionario(idFuncionario);
            List<Venda> vendas = vendaService.consultarVendasPorFuncionario(funcionario); // Consulta as vendas do
                                                                                            // funcionário com o ID
                                                                                            // especificado
            return new ResponseEntity<>(vendas, HttpStatus.OK); // Retorna uma resposta de sucesso com as vendas
                                                                // encontradas
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Retorna uma resposta de erro
        }
    }
}


//Ass: Guilherme Cunha da Cruz
