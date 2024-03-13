package pdv.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pdv.entity.Funcionario;
import pdv.repository.FuncionarioRepository;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	// Método para buscar todos os funcionários
	public List<Funcionario> findAll() {
		return funcionarioRepository.findAll();
	}

	// Método para buscar um funcionário por ID
	public Optional<Funcionario> findById(Long id) {
		return funcionarioRepository.findById(id);
	}

	// Método para salvar um novo funcionário
	public Funcionario save(Funcionario funcionario) {
		return funcionarioRepository.save(funcionario);
	}

	// Método para atualizar um funcionário existente
	public Funcionario update(Long id, Funcionario funcionario) {
		// Verifica se o funcionário com o ID especificado existe no banco de dados
		if (!funcionarioRepository.existsById(id)) {
			throw new RuntimeException("Funcionário não encontrado!");
		}
		// Define o ID do funcionário e salva no banco de dados
		funcionario.setIdFuncionario(id);
		return funcionarioRepository.save(funcionario);
	}

	// Método para deletar um funcionário pelo ID
	public void delete(Long id) {
		// Verifica se o funcionário com o ID especificado existe no banco de dados
		if (!funcionarioRepository.existsById(id)) {
			throw new RuntimeException("Funcionário não encontrado!");
		}
		// Deleta o funcionário do banco de dados
		funcionarioRepository.deleteById(id);
	}
	
	// Filtro JPQL para encontrar funcionários pelo nome
    public List<Funcionario> findByNomeJPQL(String nome) {
        return funcionarioRepository.findByNomeJPQL(nome);
    }

    // Método automático para encontrar funcionários pela idade
    public List<Funcionario> findByIdade(Integer idade) {
        return funcionarioRepository.findByIdade(idade);
    }

    // Método automático para encontrar funcionários pela matrícula
    public Funcionario findByMatricula(String matricula) {
        return funcionarioRepository.findByMatricula(matricula);
    }
}

//Ass: Guilherme Cunha da Cruz
