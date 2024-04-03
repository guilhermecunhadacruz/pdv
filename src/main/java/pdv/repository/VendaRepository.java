package pdv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pdv.entity.Cliente;
import pdv.entity.Funcionario;
import pdv.entity.Venda;

public interface VendaRepository extends JpaRepository<Venda, Long> {

	 // Consulta utilizando JPQL
    @Query("SELECT v FROM Venda v WHERE v.valorTotal > :valor")
    List<Venda> findByValorTotalMaior(Double valor);

    // Consulta automática pelo atributo cliente
    List<Venda> findByCliente(Cliente cliente);

    // Consulta automática pelo atributo funcionário
    List<Venda> findByFuncionario(Funcionario funcionario);
    
}


//Ass: Guilherme Cunha da Cruz