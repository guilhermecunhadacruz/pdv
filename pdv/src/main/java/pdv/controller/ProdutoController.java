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

import pdv.entity.Produto;
import pdv.service.ProdutoService;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    // Método: GET
    // URL: http://localhost:8080/api/produtos
    // Endpoint para buscar todos os produtos
    @GetMapping
    public ResponseEntity<List<Produto>> findAll() {
        try {
            List<Produto> produtos = produtoService.findAll();
            return new ResponseEntity<>(produtos, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Método: GET
    // URL: http://localhost:8080/api/produtos/{id}
    // Endpoint para buscar um produto pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Produto> findById(@PathVariable Long id) {
        try {
            Optional<Produto> produto = produtoService.findById(id);
            return produto.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    // Método: POST
    // URL: http://localhost:8080/api/produtos
    // Endpoint para salvar um novo produto
    @PostMapping
    public ResponseEntity<Produto> save(@RequestBody Produto produto) {
        try {
            Produto savedProduto = produtoService.save(produto);
            return new ResponseEntity<>(savedProduto, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Método: PUT
    // URL: http://localhost:8080/api/produtos/{id}
    // Endpoint para atualizar um produto pelo ID
    @PutMapping("/{id}")
    public ResponseEntity<Produto> update(@PathVariable Long id, @RequestBody Produto produto) {
        try {
            Produto updatedProduto = produtoService.update(id, produto);
            return new ResponseEntity<>(updatedProduto, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Método: DELETE
    // URL: http://localhost:8080/api/produtos/{id}
    // Endpoint para deletar um produto pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            produtoService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

   

    // Método: GET
    // URL: http://localhost:8080/api/produtos/buscarPorNomeJPQL?nome={nome}
    // Endpoint para encontrar produtos pelo nome usando JPQL
    @GetMapping("/buscarPorNomeJPQL")
    public ResponseEntity<List<Produto>> findByNomeJPQL(@RequestParam String nome) {
        try {
            List<Produto> produtos = produtoService.findByNomeJPQL(nome);
            return new ResponseEntity<>(produtos, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Método: GET
    // URL: http://localhost:8080/api/produtos/buscarPorValor?valor={valor}
    // Endpoint para encontrar produtos pelo valor
    @GetMapping("/buscarPorValor")
    public ResponseEntity<List<Produto>> findByValor(@RequestParam Double valor) {
        try {
            List<Produto> produtos = produtoService.findByValor(valor);
            return new ResponseEntity<>(produtos, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Método: GET
    // URL: http://localhost:8080/api/produtos/buscarPorNome?nome={nome}
    // Endpoint para encontrar produtos pelo nome do produto
    @GetMapping("/buscarPorNome")
    public ResponseEntity<List<Produto>> findByNomeProduto(@RequestParam String nome) {
        try {
            List<Produto> produtos = produtoService.findByNomeProduto(nome);
            return new ResponseEntity<>(produtos, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

//Ass: Guilherme Cunha da Cruz