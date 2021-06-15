package com.sistemabancario.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContaTest {

    @Test
    void testeSetNumeroValido() {
        final Conta instance = new Conta();
        final String esperado = "12345-6";
        instance.setNumero(esperado);
        final String obtido = instance.getNumero();
        assertEquals(esperado, obtido);
    }

    @Test
    void testeSetNumeroInvalidoArmazena() {
        final Conta instance = new Conta();
        final String invalido = "123";
        assertThrows(IllegalArgumentException.class, () -> instance.setNumero(invalido));
        final String obtido = instance.getNumero();
        assertNotEquals(invalido, obtido);
    }

    @Test
    void testeInstancePadraoPoupanca() {
        final Conta instance = new Conta();
        assertFalse(instance.isPoupanca());
    }

    @Test
    void testeSetLimiteContaEspecial() {
        final Conta instance = new Conta();
        instance.setEspecial(true);
        final double esperado = 1000;
        instance.setLimite(esperado);
        final double obtido = instance.getLimite();
        assertEquals(esperado, obtido);
    }

    @Test
    void testeSetLimiteContaNaoEspecial() {
        final Conta instance = new Conta();
        final double limite = 1000;
        assertThrows(IllegalStateException.class, () -> instance.setLimite(limite));
    }

    @Test
    void testeSetLimiteContaNaoEspecialArmazena() {
        final Conta instance = new Conta();
        final double limite = 1000;
        assertThrows(IllegalStateException.class, () -> instance.setLimite(limite));
        final double obtido = instance.getLimite();
        assertNotEquals(limite, obtido);
    }

    @Test
    void testeHistoricoNotNull() {
        final Conta instance = new Conta();
        assertNotNull(instance.getMovimentacoes());

    }

    @Test
    void testeGetSaldoTotal() {
        final double limite = 500;
        final double esperado = limite;
        final Conta instance = new Conta();
        instance.setEspecial(true);
        instance.setLimite(limite);
        final double obtido = instance.getSaldoTotal();
        assertEquals(esperado, obtido);

    }

    @Test
    void testeDepositoDinheiro() {
        final double limite = 500.6, deposito = 500.8, esperado = 1001.4;
        final Conta instance = new Conta();
        instance.setEspecial(true);
        instance.setLimite(limite);
        instance.depositoDinheiro(deposito);

        final double obtido = instance.getSaldoTotal();
        assertEquals(esperado, obtido, 0.001);
    }

    @Test
    void testeSetDepositoDinheiroTipoMovimentacao() {
        final char esperado = 'C';
        final Conta instance = new Conta();
        instance.depositoDinheiro(500);
        final int indexUltimaMovimentacao = instance.getMovimentacoes().size() - 1;
        final char obtido = instance.getMovimentacoes().get(indexUltimaMovimentacao).getTipo();
        assertEquals(esperado, obtido);
    }

    @Test
    void testeSetDepositoDinheiroMovimentacaoConfirmada() {
        final Conta instance = new Conta();
        instance.depositoDinheiro(500);
        final int indexUltimaMovimentacao = instance.getMovimentacoes().size() - 1;
        assertTrue(instance.getMovimentacoes().get(indexUltimaMovimentacao).isConfirmada());
    }

    @Test
    void testeSetDepositoDinheiroMovimentacaoValorArmazenado() {
        final double valor = 500;
        final Conta instance = new Conta();
        instance.depositoDinheiro(valor);
        final int indexUltimaMovimentacao = instance.getMovimentacoes().size() - 1;
        final Movimentacao movimentacao = instance.getMovimentacoes().get(indexUltimaMovimentacao);
        final double obtido = movimentacao.getValor();
        assertEquals(valor, obtido);
    }

    @Test
    void testeSetDepositoDinheiroMovimentacaoIncluida() {
        final double valor = 500;
        final Conta instance = new Conta();
        final int esperado = instance.getMovimentacoes().size() + 1;
        instance.depositoDinheiro(valor);
        final int obtido = instance.getMovimentacoes().size();
        assertEquals(esperado, obtido);
    }

}
