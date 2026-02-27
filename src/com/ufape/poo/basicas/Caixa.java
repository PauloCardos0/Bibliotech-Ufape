package com.ufape.poo.basicas;

import java.io.Serializable;
import java.util.ArrayList;

import java.util.List;



public class Caixa implements Serializable{

	private boolean aberto;
	private List<MovimentacaoCaixa> movimentacoes;
	private double saldo;

	public Caixa() {
		this.aberto = false;
		this.movimentacoes = new ArrayList<>();
	}
	
	public double getSaldo() {
	    return saldo;
	}
	
	public void adicionarValor(double valor) {
	    this.saldo += valor;
	}

	public void abrirCaixa() {
		if (aberto) {
			throw new IllegalStateException("Caixa já está aberto");
		}
		aberto = true;
	}

	public void fecharCaixa() {
		if (!aberto) {
			throw new IllegalStateException("Caixa já está fechado");
		}
		aberto = false;
	}

	public void registrarMovimentacao(MovimentacaoCaixa movimentacao) throws CaixaFechadoException {

		if (!aberto) {
			throw new CaixaFechadoException("Caixa está fechado");
		}

		movimentacoes.add(movimentacao);
	}

	public double calcularTotal() {
		return movimentacoes.stream().mapToDouble(MovimentacaoCaixa::getValor).sum();
	}

	public boolean isAberto() {
		return aberto;
	}
}