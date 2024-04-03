package pdv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pdv.entity.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
	// Filtro JPQL para encontrar funcionários pelo nome
	@Query("SELECT f FROM Funcionario f WHERE f.nomeFuncionario = ?1")
	List<Funcionario> findByNomeJPQL(String nome);

	// Método automático para encontrar funcionários pela idade
	List<Funcionario> findByIdade(Integer idade);

	// Método automático para encontrar funcionários pela matrícula
	Funcionario findByMatricula(String matricula);
	
}


//Ass: Guilherme Cunha da Cruz