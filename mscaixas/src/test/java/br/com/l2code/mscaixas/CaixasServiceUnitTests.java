package br.com.l2code.mscaixas;

import br.com.l2code.mscaixas.model.Caixa;
import br.com.l2code.mscaixas.repository.CaixasRepository;
import br.com.l2code.mscaixas.service.CaixasService;
import br.com.l2code.mscaixas.service.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

public class CaixasServiceUnitTests {

    @Mock
    private CaixasRepository caixasRepository;

    @InjectMocks
    private CaixasService caixasService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void deveSalvarCaixa() {
        Caixa caixaParaSalvar = new Caixa(1L, BigDecimal.TEN, BigDecimal.TEN, BigDecimal.TEN);
        Caixa caixaSalvo = new Caixa(1L, BigDecimal.TEN, BigDecimal.TEN, BigDecimal.TEN);

        when(caixasRepository.save(any(Caixa.class))).thenReturn(caixaSalvo);

        Caixa resultado = caixasService.cadastrar(caixaParaSalvar);

        assertThat(resultado.getId()).isEqualTo(1L);
        assertThat(resultado.getAltura()).isEqualTo(BigDecimal.TEN);
        assertThat(resultado.getLargura()).isEqualTo(BigDecimal.TEN);
        assertThat(resultado.getComprimento()).isEqualTo(BigDecimal.TEN);

        verify(caixasRepository, times(1)).save(caixaParaSalvar);
    }

    @Test
    public void deveAtualizarCaixa() {
        Caixa caixaAtualizada = new Caixa(1L, BigDecimal.TEN, BigDecimal.TEN, BigDecimal.TEN);
        Caixa caixaSalva = new Caixa(1L, BigDecimal.TEN, BigDecimal.TEN, BigDecimal.TWO);

        when(caixasRepository.findById(1L)).thenReturn(Optional.of(caixaSalva));
        when(caixasRepository.save(any(Caixa.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Caixa resultado = caixasService.atualizar(caixaAtualizada);

        assertThat(resultado.getId()).isEqualTo(1L);
        assertThat(resultado.getAltura()).isEqualTo(BigDecimal.TEN);
        assertThat(resultado.getLargura()).isEqualTo(BigDecimal.TEN);
        assertThat(resultado.getComprimento()).isEqualTo(BigDecimal.TEN);

        verify(caixasRepository, times(1)).findById(1L);
        verify(caixasRepository, times(1)).save(any(Caixa.class));
    }


    @Test
    public void deveBuscarCaixaPorId() {
        Caixa caixa = new Caixa(1L, BigDecimal.TEN, BigDecimal.TEN, BigDecimal.TEN);
        when(caixasRepository.findById(1L)).thenReturn(Optional.of(caixa));

        Caixa resultado = caixasService.buscarPeloId(1L);

        assertThat(resultado.getId()).isEqualTo(1L);
        assertThat(resultado.getAltura()).isEqualTo(BigDecimal.TEN);
        assertThat(resultado.getLargura()).isEqualTo(BigDecimal.TEN);
        assertThat(resultado.getComprimento()).isEqualTo(BigDecimal.TEN);
    }

    @Test
    public void deveLancarExceptionQuandoCaixaNaoEncontrada() {
        when(caixasRepository.findById(1L)).thenReturn(Optional.empty());

        try {
            caixasService.buscarPeloId(1L);
        } catch (ObjectNotFoundException e) {
            assertThat(e.getMessage()).isEqualTo("Caixa não encontrada! Id: 1");
        }
    }

    @Test
    public void deveListarTodasCaixas() {
        List<Caixa> caixas = List.of(
                new Caixa(1L, BigDecimal.TEN, BigDecimal.TEN, BigDecimal.TEN),
                new Caixa(2L, BigDecimal.TWO, BigDecimal.TWO, BigDecimal.TWO)
        );

        when(caixasRepository.findAll()).thenReturn(caixas);

        List<Caixa> resultado = caixasService.listar();
        assertThat(resultado.getFirst().getAltura()).isEqualTo(BigDecimal.TEN);
        assertThat(resultado.getLast().getAltura()).isEqualTo(BigDecimal.TWO);
    }

    @Test
    public void deveExcluirCaixaExistente() {
        Caixa caixa = new Caixa(1L, BigDecimal.TEN, BigDecimal.TEN, BigDecimal.TEN);

        when(caixasRepository.findById(1L)).thenReturn(Optional.of(caixa));

        caixasService.remover(1L);

        verify(caixasRepository, times(1)).deleteById(1L);
    }
}
