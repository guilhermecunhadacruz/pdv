package pdv.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
@Table(name = "tb_funcionario")
public class Funcionario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idFuncionario;
	
	@NotBlank(message = "O nome do funcionário não pode estar vazio")
	@Pattern(regexp = "^[\\p{L}]+(\\s[\\p{L}]+)+$", message = "O nome do funcionário deve conter pelo menos dois nomes e apenas caracteres alfabéticos e pontos.")
	private String nomeFuncionario;

	@Digits(integer = 3, fraction = 0, message = "Deve ser um número inteiro com até 3 dígitos.")
	@NotNull(message = "O campo não pode estar vazio")
	@Min(value = 14, message = "A idade deve ser igual ou maior que 14")
	@Max(value = 130, message = "A idade deve ser menor ou igual a 130")
	private Integer idade;

	@Column(unique = true)
	@NotBlank(message = "O campo não pode estar vazio")
	private String matricula;

	@OneToMany(mappedBy = "funcionario")
	private List<Venda> vendas;
	
	public Long getId() {
	    return this.idFuncionario;
	}

}

//Ass: Guilherme Cunha da Cruz
