import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;

public class TrocoIteratorTest {

    @ParameterizedTest
    @ValueSource(ints = { 2, 5, 10, 20, 50, 100 })
    public void TrocoIterator_DeveRetornarTrue_QuandoPapeisMoedaNaoForNull(int valor)
    {
        Troco trocoObj = new Troco(valor);
        Troco.TrocoIterator obj = trocoObj.new TrocoIterator(trocoObj);
        Assertions.assertThat(obj.hasNext()).isEqualTo(true);
    }

    @ParameterizedTest
    @ValueSource(ints = { 2, 5, 10, 20, 50, 100 })
    public void TrocoIterator_DeveRetornarFalse_QuandoPapeisMoedaForNull(int valor)
    {
        Troco trocoObj = new Troco(valor);
        Arrays.fill(trocoObj.papeisMoeda, null);
        Troco.TrocoIterator obj = trocoObj.new TrocoIterator(trocoObj);
        Assertions.assertThat(obj.hasNext()).isEqualTo(false);
    }

    @ParameterizedTest
    @ValueSource(ints = { 100, 50, 20, 10, 5, 2})
    public void TrocoIterator_DeveRetornarTroco_QuandoPapeisMoedaNaoForNull(int valorPapelMoeda)
    {
        Troco trocoObj = new Troco(valorPapelMoeda);

        int count = valorPapelMoeda / 100;
        Troco.TrocoIterator obj = trocoObj.new TrocoIterator(trocoObj);
        PapelMoeda compare = new PapelMoeda(100, count);
        PapelMoeda retorno = obj.next();
        boolean result = retorno.quantidade == compare.quantidade && retorno.valor == compare.valor;
        Assertions.assertThat(result).isEqualTo(true);
    }

    @ParameterizedTest
    @ValueSource(ints = { 100, 50, 20, 10, 5, 2})
    public void TrocoIterator_DeveRetornarnull_QuandoPapeisMoedaForNull(int valorPapelMoeda)
    {
        Troco trocoObj = new Troco(valorPapelMoeda);
        Arrays.fill(trocoObj.papeisMoeda, null);
        Troco.TrocoIterator obj = trocoObj.new TrocoIterator(trocoObj);
        Assertions.assertThat(obj.next()).isEqualTo(null);
    }
}
