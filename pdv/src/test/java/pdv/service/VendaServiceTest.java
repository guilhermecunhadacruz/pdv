package pdv.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.xml.bind.ValidationException;
import pdv.entity.Produto;
import pdv.entity.Venda;

@SpringBootTest
public class VendaServiceTest {

    @Mock
    private ProdutoService produtoService;

    @InjectMocks
    private VendaService vendaService;

    private List<Produto> produtos;

    @BeforeEach
    void setup() {
        produtos = new ArrayList<>();
        produtos.add(new Produto("Produto 1", 10.0));
        produtos.add(new Produto("Produto 2", 20.0));
    }


    @Test
    @DisplayName("Teste de cálculo do valor total com sucesso")
    void testCalcularValorTotal_Success() {
        when(produtoService.findAll()).thenReturn(produtos);

        double valorTotal = vendaService.calcularValorTotal(produtos);

        assertEquals(30.0, valorTotal);
    }

    @Test
    @DisplayName("Teste de cálculo do valor total com exceção")
    void testCalcularValorTotal_Exception() {
        when(produtoService.findAll()).thenReturn(null);

        assertThrows(Exception.class, () -> {
            vendaService.calcularValorTotal(null);
        });
    }

    @Test
    @DisplayName("Teste de verificação de status com venda cancelada")
    void testVerificarStatus_VendaCancelada() {
        Venda venda = new Venda();
        venda.setStatus("CANCELADO");

        List<Produto> produtos = null;
        venda.setProdutos(produtos);

        vendaService.verificarStatus(venda);

        assertEquals("CANCELADO", venda.getStatus());
        assertEquals(0.0, venda.getValorTotal());
    }

    @Test
    @DisplayName("Teste de verificação de status com exceção de validação")
    void testVerificarStatus_Exception() {
        Venda venda = new Venda();
        venda.setStatus(null);

        assertThrows(ValidationException.class, () -> {
            vendaService.verificarStatus(venda);
        });
    }
}


//Ass: Guilherme Cunha da Cruz