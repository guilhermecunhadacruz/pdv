package pdv.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_venda")
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVenda;

    private String enderecoEntrega;

    @NotBlank
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "Formato de CEP inválido. Utilize o formato 12345-678.")
    private String cep;

    @NotNull(message = "O campo não pode estar vazio")
    private Double valorTotal;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnoreProperties("vendas")
    private Cliente cliente;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "id_funcionario")
    @JsonIgnoreProperties("vendas")
    private Funcionario funcionario;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "venda_produto", joinColumns = @JoinColumn(name = "id_venda"), inverseJoinColumns = @JoinColumn(name = "id_produto"))
    private List<Produto> produtos;

    // Método para obter o ID do cliente
    public Long getIdCliente() {
        return this.cliente != null ? this.cliente.getIdCliente() : null;
    }

    // Método para obter o ID do funcionário
    public Long getIdFuncionario() {
        return this.funcionario != null ? this.funcionario.getIdFuncionario() : null;
    }

}
