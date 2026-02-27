package com.ufape.poo.negocio;

import java.util.*;

import com.ufape.poo.basicas.*;
import com.ufape.poo.dados.IRepositorioEmprestimos;

public class EmprestimoService {

	private Map<Integer, Emprestimo> emprestimos;
	private UsuarioService usuarioService;
	private AcervoService acervoService;
	private IRepositorioEmprestimos repositorio;
	private int contadorId;

	public EmprestimoService(UsuarioService usuarioService, AcervoService acervoService,
			IRepositorioEmprestimos repositorio) throws Exception {
		this.usuarioService = usuarioService;
		this.acervoService = acervoService;
		this.repositorio = repositorio;
		this.emprestimos = repositorio.carregar();
		this.contadorId = emprestimos.isEmpty() ? 1 : Collections.max(emprestimos.keySet()) + 1;
	}

	public EmprestimoService() {
		this.emprestimos = new HashMap<>();
		this.contadorId = 1;
	}

	public List<Emprestimo> listarEmprestimosPorUsuario(int usuarioId) {

		List<Emprestimo> lista = new ArrayList<>();

		for (Emprestimo e : emprestimos.values()) {
			if (e.getUsuario().getId() == usuarioId) {
				lista.add(e);
			}
		}

		return lista;
	}

	public Emprestimo realizarEmprestimo(int usuarioId, int itemId, int diasPrazo) throws Exception {
		Usuario usuario = usuarioService.buscarUsuarioPorId(usuarioId);
		ItemAcervo item = acervoService.buscarItemPorId(itemId);
		Emprestimo emprestimo = new Emprestimo(contadorId++, usuario, item, diasPrazo);
		emprestimos.put(emprestimo.getId(), emprestimo);
		repositorio.salvar(emprestimos);
		return emprestimo;
	}

	public void registrarDevolucao(int emprestimoId) throws Exception {
		Emprestimo emprestimo = buscarEmprestimoPorId(emprestimoId);
		emprestimo.registrarDevolucao();
		repositorio.salvar(emprestimos);
	}

	public Emprestimo buscarEmprestimoPorId(int id) throws EmprestimoNaoEncontradoException {

		Emprestimo emprestimo = emprestimos.get(id);

		if (emprestimo == null) {
			throw new EmprestimoNaoEncontradoException("Empréstimo não encontrado");
		}

		return emprestimo;
	}

	public List<Emprestimo> listarEmprestimosAtivos() {

		List<Emprestimo> ativos = new ArrayList<>();

		for (Emprestimo e : emprestimos.values()) {
			if (e.isAtivo()) {
				ativos.add(e);
			}
		}

		return ativos;
	}

	public List<Emprestimo> listarTodosEmprestimos() {
		return new ArrayList<>(emprestimos.values());
	}
}