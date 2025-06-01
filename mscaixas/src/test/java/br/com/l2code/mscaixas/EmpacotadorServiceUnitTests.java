package br.com.l2code.mscaixas;

import br.com.l2code.mscaixas.model.Caixa;
import br.com.l2code.mscaixas.model.dto.entrada.DimensoesDTO;
import br.com.l2code.mscaixas.model.dto.entrada.PedidoDTO;
import br.com.l2code.mscaixas.model.dto.entrada.ProdutoDTO;
import br.com.l2code.mscaixas.model.dto.entrada.RequisicaoEmpacotamentoDTO;
import br.com.l2code.mscaixas.model.dto.saida.RespostaEmpacotamentoDTO;
import br.com.l2code.mscaixas.repository.CaixasRepository;
import br.com.l2code.mscaixas.service.EmpacotadorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class EmpacotadorServiceUnitTests {

    @Mock
    private CaixasRepository caixasRepository;

    @InjectMocks
    private EmpacotadorService empacotadorService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveEmpacotarProdutoQueCabeEmUmaCaixa() {
        Caixa caixa = new Caixa(1L, toBigDecimal(10), toBigDecimal(10), toBigDecimal(10));
        when(caixasRepository.findAll()).thenReturn(List.of(caixa));

        ProdutoDTO produto = new ProdutoDTO(
                "produto_1",
                new DimensoesDTO(
                        toBigDecimal(2),
                        toBigDecimal(2),
                        toBigDecimal(2)
                )
        );
        PedidoDTO pedido = new PedidoDTO(1, List.of(produto));
        RequisicaoEmpacotamentoDTO requisicao = new RequisicaoEmpacotamentoDTO(List.of(pedido));

        // Executa
        RespostaEmpacotamentoDTO resposta = empacotadorService.empacotarPedidos(requisicao);

        // Valida
        assertThat(resposta.pedidos()).hasSize(1);
        assertThat(resposta.pedidos().getFirst().caixas()).hasSize(1);
        assertThat(resposta.pedidos().getFirst().caixas().getFirst().produtos()).contains("produto_1");
    }

    @Test
    void deveRetornarObservacaoQuandoProdutoNaoCabeEmNenhumaCaixa() {
        // Caixa muito pequena
        Caixa caixa = new Caixa(1L, toBigDecimal(1), toBigDecimal(1), toBigDecimal(1));
        when(caixasRepository.findAll()).thenReturn(List.of(caixa));

        ProdutoDTO produto = new ProdutoDTO(
                "produto_2",
                new DimensoesDTO(
                        toBigDecimal(5),
                        toBigDecimal(5),
                        toBigDecimal(5)
                )
        );
        PedidoDTO pedido = new PedidoDTO(2, List.of(produto));
        RequisicaoEmpacotamentoDTO requisicao = new RequisicaoEmpacotamentoDTO(List.of(pedido));

        RespostaEmpacotamentoDTO resposta = empacotadorService.empacotarPedidos(requisicao);

        assertThat(resposta.pedidos()).hasSize(1);
        assertThat(resposta.pedidos().getFirst().caixas()).hasSize(1);
        assertThat(resposta.pedidos().getFirst().caixas().getFirst().observacao()).isEqualTo("Produto não cabe em nenhuma caixa disponível.");
    }

    @Test
    void deveEmpacotarProdutosEmCaixasDiferentes() {
        Caixa pequena = new Caixa(1L, toBigDecimal(3), toBigDecimal(3), toBigDecimal(3));
        Caixa grande = new Caixa(2L, toBigDecimal(10), toBigDecimal(10), toBigDecimal(10));
        when(caixasRepository.findAll()).thenReturn(List.of(pequena, grande));

        ProdutoDTO p1 = new ProdutoDTO(
                "produto_1",
                new DimensoesDTO(
                        toBigDecimal(2),
                        toBigDecimal(2),
                        toBigDecimal(2)
                )
        );
        ProdutoDTO p2 = new ProdutoDTO(
                "produto_2",
                new DimensoesDTO(
                        toBigDecimal(9),
                        toBigDecimal(9),
                        toBigDecimal(1)
                )
        );

        PedidoDTO pedido = new PedidoDTO(3, List.of(p1, p2));
        RequisicaoEmpacotamentoDTO requisicao = new RequisicaoEmpacotamentoDTO(List.of(pedido));

        RespostaEmpacotamentoDTO resposta = empacotadorService.empacotarPedidos(requisicao);

        assertThat(resposta.pedidos().getFirst().caixas()).hasSize(2);
    }

    private BigDecimal toBigDecimal(int valor) {
        return BigDecimal.valueOf(valor);
    }
}
