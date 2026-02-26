package com.ufape.poo.negocio;

import java.util.*;

import com.ufape.poo.basicas.*;

public class UsuarioService {

	private Map<Integer, Usuario> usuarios;

	public UsuarioService() {
		this.usuarios = new HashMap<>();
	}

	// CADASTRO USUARIOS - PAULO CARDOSO

	public void cadastrarAluno(int id, String nome, String email, String telefone, int limiteEmprestimo,
			String matricula, String curso, int periodo) throws UsuarioJaExisteException {

		if (usuarios.containsKey(id)) {
			throw new UsuarioJaExisteException("Usuario com esse id já existe");
		}

		Usuario aluno = new Aluno(id, nome, email, telefone, limiteEmprestimo, matricula, curso, periodo);

		usuarios.put(id, aluno);

	}

	public void cadastrarProfessor(int id, String nome, String email, String telefone, int limiteEmprestimo,
			String siape, String departamento) throws UsuarioJaExisteException {

		// Pensando bem agora o id do professor pode ser o siape e o de aluno a
		// matricula, vamos analisar melhor isso
		if (usuarios.containsKey(id)) {
			throw new UsuarioJaExisteException("Já existe Professor com esse id");
		}

		Usuario professor = new Professor(id, nome, email, telefone, limiteEmprestimo, siape, departamento);

		usuarios.put(id, professor);

	}

	public void cadastrarBibliotecario(int id, String nome, String email, String telefone, int limiteEmprestimo,
			String matriculaFuncional, String setor) throws UsuarioJaExisteException {

		if (usuarios.containsKey(id)) {
			throw new UsuarioJaExisteException("Usuário com esse ID já existe");
		}

		Usuario bibliotecario = new Bibliotecario(id, nome, email, telefone, limiteEmprestimo, matriculaFuncional,
				setor);

		usuarios.put(id, bibliotecario);
	}

	// BUSCA USUARIOS - PAULO CARDOSO

	public Usuario buscarUsuarioPorId(int id) throws UsuarioNaoEncontradoException {

		Usuario usuario = usuarios.get(id);

		if (usuario == null) {
			throw new UsuarioNaoEncontradoException("Usuário Não encontrado");
		}

		return usuario;
	}

	public List<Usuario> listaUsuarios() {
		return new ArrayList<>(usuarios.values());
	}

	public List<Usuario> listaUsuariosAtivos() {

		List<Usuario> ativos = new ArrayList<>();

		for (Usuario u : usuarios.values()) {
			if (u.getStatus() == StatusUsuario.ATIVO) {
				ativos.add(u);
			}
		}

		return ativos;
	}

	public List<Usuario> listaUsuariosBloqueados() {
		List<Usuario> bloqueados = new ArrayList<>();

		for (Usuario u : usuarios.values()) {
			if (u.getStatus() == StatusUsuario.BLOQUEADO) {
				bloqueados.add(u);
			}
		}
		return bloqueados;

	}

	public void bloquearUsuario(int id) throws UsuarioNaoEncontradoException {

		Usuario usuario = buscarUsuarioPorId(id);

		usuario.bloquearUsuario();

	}

	public void desbloquearUsuario(int id) throws UsuarioNaoEncontradoException {

		Usuario usuario = buscarUsuarioPorId(id);

		usuario.desbloquearUsuario();

	}

	public void removerUsuario(int id) throws UsuarioNaoEncontradoException, OperacaoNaoPermitidaException {

		Usuario usuario = buscarUsuarioPorId(id);

		if (usuario.getEmprestimosAtivos() > 0) {
			throw new OperacaoNaoPermitidaException("Usuário possui empréstimos ativos e não pode ser removido");
		}

		usuarios.remove(id);
	}

}
