package pdv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pdv.entity.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	// Filtro JPQL para encontrar produtos pelo nome
	@Query("SELECT p FROM Produto p WHERE p.nomeProduto = ?1")
	List<Produto> findByNomeJPQL(String nome);

	// Método automático para encontrar produtos pelo valor
	List<Produto> findByValor(Double valor);

	// Método automático para encontrar produtos pelo nome
	List<Produto> findByNomeProduto(String nome);
}


//Ass: Guilherme Cunha da Cruz