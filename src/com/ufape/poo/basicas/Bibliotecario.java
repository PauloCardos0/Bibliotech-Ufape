package com.ufape.poo.basicas;

public class Bibliotecario extends Usuario {

	private String matriculaFuncional;
	private String setor;

	public Bibliotecario(int id, String nome, String email, String telefone, int limiteEmprestimo,
			String matriculaFuncional, String setor) {

		super(id, nome, email, telefone, limiteEmprestimo);

		if (matriculaFuncional == null || matriculaFuncional.isBlank()) {
			throw new IllegalArgumentException("Matrícula funcional inválida");
		}

		if (setor == null || setor.isBlank()) {
			throw new IllegalArgumentException("Setor inválido");
		}

		this.matriculaFuncional = matriculaFuncional;
		this.setor = setor;
	}

	public String getMatriculaFuncional() {
		return matriculaFuncional;
	}

	public String getSetor() {
		return setor;
	}

	public void setSetor(String setor) {
		if (setor == null || setor.isBlank()) {
			throw new IllegalArgumentException("Setor inválido");
		}
		this.setor = setor;
	}
}