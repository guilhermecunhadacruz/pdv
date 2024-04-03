package pdv.entity;

import java.util.List;

import org.hibernate.validator.constraints.br.CPF;

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
@Table(name = "tb_cliente")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCliente;

	@NotBlank(message = "O nome do cliente não pode estar vazio")
	@Pattern(regexp = "^[\\p{L}.]+\\s[\\p{L}.]+$", message = "O nome do cliente deve conter pelo menos dois nomes e apenas caracteres alfabéticos e pontos.")
	private String nomeCliente;

	@CPF(message = "O CPF deve ser válido") // Validação para enviar CPF válido
	@NotBlank(message = "O campo não pode estar vazio") // Para não enviar requisição vazia ou nulo
	@Column(unique = true) // Para o CPF ser unico nessa coluna
	private String cpf;

	@Digits(integer = 3, fraction = 0, message = "Deve ser um número inteiro com até 3 dígitos.")
	@NotNull(message = "O campo não pode estar vazio")
	@Min(value = 0, message = "A idade deve ser igual ou maior que 0")
	@Max(value = 130, message = "A idade deve ser menor ou igual a 130")
	private Integer idade;

	@NotBlank(message = "O número de telefone não pode estar vazio")
	@Pattern(regexp = "^\\+?\\d{0,3}?[-.\\s]?\\(?\\d{1,3}?\\)?[-.\\s]?\\d{3,4}[-.\\s]?\\d{4}$", message = "O número de telefone deve ser válido (fixo ou celular).")
	private String telefone;

	@OneToMany(mappedBy = "cliente")
	private List<Venda> vendas;



}

//Ass: Guilherme Cunha da Cruz
