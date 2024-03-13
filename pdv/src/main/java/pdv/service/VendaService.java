package pdv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pdv.entity.Cliente;
import pdv.entity.Funcionario;
import pdv.entity.Venda;
import pdv.repository.VendaRepository;

@Service
public class VendaService {
	@Autowired
	private VendaRepository vendaRepository;

	// Método para salvar uma venda
	public String save(Venda venda) {
		this.vendaRepository.save(venda);
		return "Venda salva com sucesso!";
	}

	// Método para atualizar uma venda pelo ID
	public String update(Long id, Venda venda) {
		// Verifica se a venda existe pelo ID
		if (!vendaRepository.existsById(id)) {
			throw new RuntimeException("Venda não encontrada!");
		}
		venda.setIdVenda(id);
		this.vendaRepository.save(venda);
		return "Venda atualizada com sucesso!";
	}

	// Método para listar todas as vendas
	public List<Venda> listAll() {
		return this.vendaRepository.findAll();
	}

	// Método para buscar uma venda pelo ID
	public Venda findById(Long id) {
		return this.vendaRepository.findById(id).orElseThrow(() -> new RuntimeException("Venda não encontrada!"));
	}

	// Método para deletar uma venda pelo ID
	public String delete(Long id) {
		// Verifica se a venda existe pelo ID
		if (!vendaRepository.existsById(id)) {
			throw new RuntimeException("Venda não encontrada!");
		}
		this.vendaRepository.deleteById(id);
		return "Venda deletada com sucesso!";
	}

	// Método para consultar vendas cujo valor total é superior a um valor especificado
	public List<Venda> consultarVendasPorValorSuperior(Double valor) {
		return vendaRepository.findByValorTotalMaior(valor);
	}

	// Método para consultar vendas por cliente
	public List<Venda> consultarVendasPorCliente(Cliente cliente) {
		return vendaRepository.findByCliente(cliente);
	}

	// Método para consultar vendas por funcionário
	public List<Venda> consultarVendasPorFuncionario(Funcionario funcionario) {
		return vendaRepository.findByFuncionario(funcionario);
	}

}


//Ass: Guilherme Cunha da Cruz