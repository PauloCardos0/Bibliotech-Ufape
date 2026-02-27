package com.ufape.poo.basicas;

import java.io.Serializable;
import java.time.LocalDateTime;

public class MovimentacaoCaixa implements Serializable{

	private String descricao;
	private double valor;
	private LocalDateTime data;
	private TipoMovimentacao tipo;

	public MovimentacaoCaixa(String descricao, double valor, TipoMovimentacao tipo) {

		if (descricao == null || descricao.isBlank()) {
			throw new IllegalArgumentException("Descrição inválida");
		}

		if (valor <= 0) {
			throw new IllegalArgumentException("Valor inválido");
		}

		this.descricao = descricao;
		this.valor = valor;
		this.data = LocalDateTime.now();
		this.tipo = tipo;
	}

	public double getValor() {
		return valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public LocalDateTime getData() {
		return data;
	}
}