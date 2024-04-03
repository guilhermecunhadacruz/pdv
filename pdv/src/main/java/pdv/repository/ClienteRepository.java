package pdv.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pdv.entity.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	// Método JPQL para encontrar clientes pelo nome
	@Query("SELECT c FROM Cliente c WHERE c.nomeCliente = ?1")
	List<Cliente> findByNome(String nome);

	// Método automático para encontrar clientes pela idade
	List<Cliente> findByIdade(Integer idade);

	// Método automático para encontrar clientes pelo CPF
	Optional<Cliente> findByCpf(String cpf);
}

//Ass: Guilherme Cunha da Cruz