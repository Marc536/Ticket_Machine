package br.calebe.ticketmachine.core;

import java.util.ArrayList;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class TrocoTest {
    
    @ParameterizedTest
    @ValueSource(ints = { 2, 5, 10, 20, 50, 100 })
    public void Troco_DeveRetornarQuantidadeUnitaria_QuandoValorIgualAUmaPapelMoeda(int valor)
    {
        var sut = new Troco(valor);
        var trocoIterator = sut.getIterator();
        var quantidadePapelMoeda = 0;
        
        while (trocoIterator.hasNext())
        {
            var nextPapelMoeda = trocoIterator.next();
            
            if (nextPapelMoeda.getValor() == valor)
            {
                quantidadePapelMoeda = nextPapelMoeda.getQuantidade();
                break;
            }
        }
        
        Assertions.assertThat(quantidadePapelMoeda).isEqualTo(1);
    }
    
    @Test
    public void Troco_InserirNotade527_TestarQuantidadePapelMoedaCorreta()
    {
        var sut = new Troco(527);
        var trocoIterator = sut.getIterator();
        var quantidadesReais = new ArrayList<Integer>();
    
        while (trocoIterator.hasNext())
        {
            var nextPapelMoeda = trocoIterator.next();
            quantidadesReais.add(nextPapelMoeda.getQuantidade());
        }
        
        var quantidadesEsperadas = new ArrayList<Integer>();
        quantidadesEsperadas.add(5); // papelMoeda 100
        quantidadesEsperadas.add(0); // papelMoeda 50
        quantidadesEsperadas.add(1); // papelMoeda 20
        quantidadesEsperadas.add(0); // papelMoeda 10
        quantidadesEsperadas.add(1); // papelMoeda 5
        quantidadesEsperadas.add(1); // papelMoeda 2
        
        Assertions.assertThat(quantidadesReais).isEqualTo(quantidadesEsperadas);
    }
}
