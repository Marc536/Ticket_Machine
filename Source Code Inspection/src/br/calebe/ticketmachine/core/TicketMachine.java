package br.calebe.ticketmachine.core;

import br.calebe.ticketmachine.exception.PapelMoedaInvalidaException;
import br.calebe.ticketmachine.exception.SaldoInsuficienteException;
import java.util.Iterator;

/**
 * Classe responsável por gerenciar a venda de bilhetes e cálculo de troco.
 * 
 * @author Calebe de Paula Bianchini
 */
public class TicketMachine {

    protected int valor;
    protected int saldo;
    protected int[] papelMoeda = {1, 2, 5, 10, 20, 50, 100};

    public TicketMachine(int valor) {
        this.valor = valor;
        this.saldo = 0;
    }

    /**
     * Método responsável por inserir dinheiro na TicketMachine.
     * 
     * @param quantia o valor da nota/moeda inserida.
     * @throws PapelMoedaInvalidaException se a quantia inserida não for uma nota ou moeda válida.
     */
    public void inserir(int quantia) throws PapelMoedaInvalidaException {
        boolean achou = false;
        for (int i = 0; i < papelMoeda.length && !achou; i++) {
            if (papelMoeda[i] == quantia) {
                achou = true;
            }
        }
        if (!achou) {
            throw new PapelMoedaInvalidaException();
        }
        this.saldo += quantia;
    }

    public int getSaldo() {
        return saldo;
    }

    /**
     * Método responsável por calcular o troco e retornar um Iterator contendo as notas/moedas do troco.
     * 
     * @return um Iterator contendo as notas/moedas do troco.
     */
    public Iterator<Integer> getTroco() {
        Troco troco = new Troco(saldo - valor);
        return troco.getIterator();
    }

    /**
     * Método responsável por imprimir o bilhete caso o saldo seja suficiente.
     * 
     * @return o bilhete impresso.
     * @throws SaldoInsuficienteException se o saldo for insuficiente para a compra do bilhete.
     */
    public String imprimir() throws SaldoInsuficienteException {
        if (saldo < valor) {
            throw new SaldoInsuficienteException();
        }
        String result = "*****************\n";
        result += "*** R$ " + saldo + ",00 ****\n";
        result += "*****************\n";
        saldo -= valor;
        return result;
    }

    /**
     * Retorna o valor do bilhete.
     * 
     * @return o valor do bilhete.
     */
    public int getValor() {
        return valor;
    }

    /**
     * Altera o valor do bilhete.
     * 
     * @param
