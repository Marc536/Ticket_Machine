package br.calebe.ticketmachine.core;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PapelMoedaTest {

    @ParameterizedTest
    @ValueSource(ints = {-2, -5, -10, -20, -50, -100})
    public void getValor_DeveRetornarValor_QuandoChamado(int quantia) {
        PapelMoeda sut = new PapelMoeda(quantia, 10);
        Assertions.assertThat(sut.getValor()).isEqualTo(quantia);
    }

    @ParameterizedTest
    @ValueSource(ints = {-2, -5, -10, -20, -50, -100})
    public void getQuantidade_DeveRetornarQuantidade_QuandoChamado(int quantia) {
        PapelMoeda sut = new PapelMoeda(quantia, quantia);
        Assertions.assertThat(sut.getQuantidade()).isEqualTo(quantia);
    }
}
