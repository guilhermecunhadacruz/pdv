package pdv.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pdv.entity.Cliente;
import pdv.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	// Retorna todos os clientes cadastrados
	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	// Retorna o cliente correspondente ao ID especificado
	public Optional<Cliente> findById(Long id) {
		return clienteRepository.findById(id);
	}

	// Salva um novo cliente
	public Cliente save(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	// Atualiza um cliente existente pelo ID
	public Cliente update(Long id, Cliente cliente) {
		if (!clienteRepository.existsById(id)) {
			throw new RuntimeException("Cliente não encontrado!");
		}
		cliente.setIdCliente(id);
		return clienteRepository.save(cliente);
	}

	// Deleta um cliente pelo ID
	public void delete(Long id) {
		if (!clienteRepository.existsById(id)) {
			throw new RuntimeException("Cliente não encontrado!");
		}
		clienteRepository.deleteById(id);
	}

	// Método para encontrar clientes pelo nome
	public List<Cliente> findByNome(String nome) {
		return clienteRepository.findByNome(nome);
	}

	// Método para encontrar clientes pela idade
	public List<Cliente> findByIdade(Integer idade) {
		return clienteRepository.findByIdade(idade);
	}

	// Método para encontrar um cliente pelo CPF
	public Optional<Cliente> findByCpf(String cpf) {
		return clienteRepository.findByCpf(cpf);
	}
}

//Ass: Guilherme Cunha da Cruz