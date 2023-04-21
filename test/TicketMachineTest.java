import br.calebe.ticketmachine.exception.PapelMoedaInvalidaException;
import br.calebe.ticketmachine.exception.SaldoInsuficienteException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

public class TicketMachineTest {
    
    @ParameterizedTest
    @ValueSource(ints = {-2, -5, -10, -20, -50, -100})
    public void Inserir_DeveLancarExcecaoPapelMoedaInvalida_QuandoInformarQuantiaNegativa(int quantia) 
    {
        var sut = new TicketMachine(Mockito.anyInt());
        
        var thrown  = Assertions.catchThrowable(() -> sut.inserir(quantia));

        Assertions.assertThat(thrown).isInstanceOf(PapelMoedaInvalidaException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 5, 10, 20, 50, 100})
    public void Inserir_DeveAtualizarSaldo_QuandoInseridoPapelMoeda(int quantia)
    {
        var sut = new TicketMachine(Mockito.anyInt());

        var thrown  = Assertions.catchThrowable(() -> sut.inserir(quantia));

        Assertions.assertThat(sut.getSaldo()).isEqualTo(quantia);
    }
    
    @ParameterizedTest
    @CsvSource({
        "2, 5",
        "5, 10",
        "10, 20"
    })
    public void Imprimir_DeveLancarExcecaoSaldoInsuficiente_QuandoSaldoMenorQueValor(int saldo, int valorBilhete) 
    {
        var sut = new TicketMachine(valorBilhete);
        sut.saldo = saldo;

        var thrown  = Assertions.catchThrowable(() -> sut.imprimir());

        Assertions.assertThat(thrown).isInstanceOf(SaldoInsuficienteException.class);
    }
    
    @ParameterizedTest
    @ValueSource(ints = {2, 5, 10, 20, 50, 100})
    public void Imprimir_DeveObterSaldoSemCasasDecimais(int saldo) throws SaldoInsuficienteException
    {
        var sut = new TicketMachine(0);
        sut.saldo = saldo;

        var resultSaldo = sut.imprimir();
        
        var expectedResult = "*****************\n";
        expectedResult += "*** R$ " + sut.getSaldo() + ",00 ****\n";
        expectedResult += "*****************\n";
        Assertions.assertThat(resultSaldo).isEqualTo(expectedResult);
    }
    
    @ParameterizedTest
    @ValueSource(ints = {2, 5, 10, 20, 50, 100})
    public void Imprimir_DeveAtualizarSaldo_AposInserirQuantia(int quantia) throws PapelMoedaInvalidaException, SaldoInsuficienteException
    {
        var valorBilhete = 2;
        var sut = new TicketMachine(valorBilhete);
        
        sut.inserir(quantia);
        sut.imprimir();
        
        Assertions.assertThat(sut.getSaldo()).isEqualTo(quantia - valorBilhete);
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 5, 10, 20, 50, 100})
    public void GetTroco_DeveRetornarTroco(int quantia)
    {
        TicketMachine obj = new TicketMachine(10);
        var thrown  = Assertions.catchThrowable(() -> obj.inserir(quantia));

        Assertions.assertThat(obj.getTroco()).isEqualTo(obj.saldo - obj.valor);
    }
}
