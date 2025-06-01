package br.com.l2code.mscaixas.service;

import br.com.l2code.mscaixas.model.Caixa;
import br.com.l2code.mscaixas.model.dto.entrada.DimensoesDTO;
import br.com.l2code.mscaixas.model.dto.entrada.PedidoDTO;
import br.com.l2code.mscaixas.model.dto.entrada.ProdutoDTO;
import br.com.l2code.mscaixas.model.dto.entrada.RequisicaoEmpacotamentoDTO;
import br.com.l2code.mscaixas.model.dto.saida.CaixaEmpacotadaDTO;
import br.com.l2code.mscaixas.model.dto.saida.PedidoEmpacotadoDTO;
import br.com.l2code.mscaixas.model.dto.saida.RespostaEmpacotamentoDTO;
import br.com.l2code.mscaixas.repository.CaixasRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

@Service
@AllArgsConstructor
public class EmpacotadorService {

    private final CaixasRepository caixaRepository;

    public RespostaEmpacotamentoDTO empacotarPedidos(RequisicaoEmpacotamentoDTO requisicao) {
        List<Caixa> caixasDisponiveis = new ArrayList<>(caixaRepository.findAll());
        caixasDisponiveis.sort(Comparator.comparing(Caixa::getVolume));

        List<PedidoEmpacotadoDTO> pedidosEmpacotados = new ArrayList<>();

        for (PedidoDTO pedido : requisicao.pedidos()) {
            List<ProdutoDTO> produtosRestantes = new ArrayList<>(pedido.produtos());
            produtosRestantes.sort(Comparator.comparing(this::calcularVolumeProduto).reversed());

            List<CaixaEmpacotadaDTO> caixasUsadas = new ArrayList<>();

            while (!produtosRestantes.isEmpty()) {
                boolean produtoEmpacotado = false;

                for (Caixa caixa : caixasDisponiveis) {
                    BigDecimal volumeDisponivel = caixa.getVolume();
                    List<String> produtosNaCaixa = new ArrayList<>();

                    Iterator<ProdutoDTO> it = produtosRestantes.iterator();
                    while (it.hasNext()) {
                        ProdutoDTO produto = it.next();
                        if (produtoCabeNaCaixa(produto, caixa)) {
                            BigDecimal volumeProduto = calcularVolumeProduto(produto);
                            if (volumeProduto.compareTo(volumeDisponivel) <= 0) {
                                produtosNaCaixa.add(produto.produto_id());
                                volumeDisponivel = volumeDisponivel.subtract(volumeProduto);
                                it.remove();
                            }
                        }
                    }

                    if (!produtosNaCaixa.isEmpty()) {
                        caixasUsadas.add(new CaixaEmpacotadaDTO("Caixa " + caixa.getId(), produtosNaCaixa, null));
                        produtoEmpacotado = true;
                        break;
                    }
                }

                if (!produtoEmpacotado) {
                    ProdutoDTO naoEncaixavel = produtosRestantes.removeFirst();
                    caixasUsadas.add(new CaixaEmpacotadaDTO(
                            null,
                            List.of(naoEncaixavel.produto_id()),
                            "Produto não cabe em nenhuma caixa disponível."
                    ));
                }
            }

            pedidosEmpacotados.add(new PedidoEmpacotadoDTO(pedido.pedido_id(), caixasUsadas));
        }

        return new RespostaEmpacotamentoDTO(pedidosEmpacotados);
    }

    private BigDecimal calcularVolumeProduto(ProdutoDTO produto) {
        DimensoesDTO d = produto.dimensoes();
        return d.altura().multiply(d.largura()).multiply(d.comprimento());
    }

    private boolean produtoCabeNaCaixa(ProdutoDTO produto, Caixa caixa) {
        DimensoesDTO d = produto.dimensoes();
        return d.altura().compareTo(caixa.getAltura()) <= 0 &&
                d.largura().compareTo(caixa.getLargura()) <= 0 &&
                d.comprimento().compareTo(caixa.getComprimento()) <= 0;
    }
}