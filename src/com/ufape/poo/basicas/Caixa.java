package com.ufape.poo.basicas;

import java.util.ArrayList;
import java.util.List;

public class Caixa {

	private boolean aberto;
	private List<MovimentacaoCaixa> movimentacoes;

	public Caixa() {
		this.aberto = false;
		this.movimentacoes = new ArrayList<>();
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