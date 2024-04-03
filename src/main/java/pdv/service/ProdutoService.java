package pdv.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pdv.entity.Produto;
import pdv.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private final ProdutoRepository produtoRepository;

    
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

	// Retorna todos os produtos cadastrados
	public List<Produto> findAll() {
		return produtoRepository.findAll();
	}

	// Retorna o produto correspondente ao ID especificado
	public Optional<Produto> findById(Long id) {
		return produtoRepository.findById(id);
	}

	// Deleta o produto correspondente ao ID especificado
	public void delete(Long id) {
		produtoRepository.deleteById(id);
	}

	// Salva um novo produto
	public Produto save(Produto produto) {
		return produtoRepository.save(produto);
	}

	// Atualiza o produto correspondente ao ID especificado
	public Produto update(Long id, Produto produto) {
		produto.setIdProduto(id);
		return produtoRepository.save(produto);
	}

	// Filtro JPQL para encontrar produtos pelo nome
	public List<Produto> findByNomeJPQL(String nome) {
		return produtoRepository.findByNomeJPQL(nome);
	}

	// Método automático para encontrar produtos pelo valor
	public List<Produto> findByValor(Double valor) {
		return produtoRepository.findByValor(valor);
	}

	// Método automático para encontrar produtos pelo nome
	public List<Produto> findByNomeProduto(String nome) {
		return produtoRepository.findByNomeProduto(nome);
	}
}


//Ass: Guilherme Cunha da Cruz