package com.ufape.poo.bassicas;

public class Usuario {

	private int id;
	private String nome;
	private String email;
	private String telefone;
	private StatusUsuario status;
	private int limiteEmprestimo;
	private int emprestimosAtivos;

	public Usuario(int id, String nome, String email, String telefone, int limiteEmprestimo) {

		if (id <= 0) {
			throw new IllegalArgumentException("ID inválido");
		}

		if (nome == null || nome.isBlank()) {
			throw new IllegalArgumentException("Nome inválido");
		}

		if (email == null || email.isBlank()) {
			throw new IllegalArgumentException("Email inválido");
		}

		if (telefone == null || telefone.isBlank()) {
			throw new IllegalArgumentException("Telefone inválido");
		}

		if (limiteEmprestimo <= 0) {
			throw new IllegalArgumentException("Limite de empréstimo inválido");
		}

		this.id = id;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.limiteEmprestimo = limiteEmprestimo;
		this.status = StatusUsuario.ATIVO;
		this.emprestimosAtivos = 0;
	}

	public boolean podeRealizarEmprestimo() {
		return status == StatusUsuario.ATIVO && emprestimosAtivos < limiteEmprestimo;
	}

	public void registrarEmprestimo() throws UsuarioBloqueadoException, LimiteEmprestimoExcedidoException, MultaPendenteException {

		if (status == StatusUsuario.BLOQUEADO) {
			throw new UsuarioBloqueadoException("Usuário está bloqueado");
		}

		if (emprestimosAtivos >= limiteEmprestimo) {
			throw new LimiteEmprestimoExcedidoException("Limite de empréstimos atingido");
		}

		emprestimosAtivos++;
	}

	public void registrarDevolucao() {
		if (emprestimosAtivos > 0) {
			emprestimosAtivos--;
		}
	}

	public void bloquearUsuario() {
		status = StatusUsuario.BLOQUEADO;
	}

	public void desbloquearUsuario() {
		status = StatusUsuario.ATIVO;
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getTelefone() {
		return telefone;
	}

	public StatusUsuario getStatus() {
		return status;
	}

	public int getLimiteEmprestimo() {
		return limiteEmprestimo;
	}

	public int getEmprestimosAtivos() {
		return emprestimosAtivos;
	}
}
