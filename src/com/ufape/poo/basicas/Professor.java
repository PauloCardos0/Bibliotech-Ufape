package com.ufape.poo.basicas;

public class Professor extends Usuario {

	private String siape;
	private String departamento;
	private boolean multaPendente;

	public Professor(int id, String nome, String email, String telefone, int limiteEmprestimo, String siape,
			String departamento) {

		super(id, nome, email, telefone, limiteEmprestimo);

		if (siape == null || siape.isBlank()) {
			throw new IllegalArgumentException("SIAPE inválido");
		}

		if (departamento == null || departamento.isBlank()) {
			throw new IllegalArgumentException("Departamento inválido");
		}

		this.siape = siape;
		this.departamento = departamento;
		this.multaPendente = false;
	}

	@Override
	public void registrarEmprestimo()
			throws UsuarioBloqueadoException, LimiteEmprestimoExcedidoException, MultaPendenteException {

		if (multaPendente) {
			throw new MultaPendenteException("Professor está com multa pendente");
		}

		super.registrarEmprestimo();
	}

	public void adicionarMulta() {
		this.multaPendente = true;
	}

	public void registrarPagamentoMulta() {
		this.multaPendente = false;
	}

	public String getSiape() {
		return siape;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		if (departamento == null || departamento.isBlank()) {
			throw new IllegalArgumentException("Departamento inválido");
		}
		this.departamento = departamento;
	}

	public boolean isMultaPendente() {
		return multaPendente;
	}
}