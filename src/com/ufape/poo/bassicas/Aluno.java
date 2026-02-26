package com.ufape.poo.bassicas;

public class Aluno extends Usuario {

	private String matricula;
	private String curso;
	private int periodo;
	private boolean multaPendente;

	public Aluno(int id, String nome, String email, String telefone, int limiteEmprestimo, String matricula,
			String curso, int periodo) {

		super(id, nome, email, telefone, limiteEmprestimo);

		if (matricula == null || matricula.isBlank()) {
			throw new IllegalArgumentException("Matrícula inválida");
		}

		if (curso == null || curso.isBlank()) {
			throw new IllegalArgumentException("Curso inválido");
		}

		if (periodo <= 0) {
			throw new IllegalArgumentException("Período inválido");
		}

		this.matricula = matricula;
		this.curso = curso;
		this.periodo = periodo;
		this.multaPendente = false;
	}

	@Override
	public void registrarEmprestimo()
			throws UsuarioBloqueadoException, LimiteEmprestimoExcedidoException, MultaPendenteException {

		if (multaPendente) {
			throw new MultaPendenteException("Usuário está com multas pendentes");
		}

		super.registrarEmprestimo();
	}

	public void registrarPagamentoMulta() {
		this.multaPendente = false;
	}

	public void adicionarMulta() {
		this.multaPendente = true;
	}

	public String getMatricula() {
		return matricula;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		if (curso == null || curso.isBlank()) {
			throw new IllegalArgumentException("Curso inválido");
		}
		this.curso = curso;
	}

	public int getPeriodo() {
		return periodo;
	}

	public void setPeriodo(int periodo) {
		if (periodo <= 0) {
			throw new IllegalArgumentException("Período inválido");
		}
		this.periodo = periodo;
	}

	public boolean isMultaPendente() {
		return multaPendente;
	}
}
