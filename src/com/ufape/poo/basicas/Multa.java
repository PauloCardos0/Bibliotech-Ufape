package com.ufape.poo.basicas;

import java.io.Serializable;

public class Multa implements Serializable{

	private int id;
	private Emprestimo emprestimo;
	private double valor;
	private boolean paga;

	public Multa(int id, Emprestimo emprestimo, double valor) {

		if (id <= 0) {
			throw new IllegalArgumentException("ID inválido");
		}

		if (emprestimo == null) {
			throw new IllegalArgumentException("Empréstimo inválido");
		}

		if (valor <= 0) {
			throw new IllegalArgumentException("Valor inválido");
		}

		this.id = id;
		this.emprestimo = emprestimo;
		this.valor = valor;
		this.paga = false;
	}

	public void pagar() {
		this.paga = true;
	}

	public boolean isPaga() {
		return paga;
	}

	public double getValor() {
		return valor;
	}
	
	public int getId() {
	    return id;
	}

	public Emprestimo getEmprestimo() {
	    return emprestimo;
	}
}