package com.ufape.poo.negocio;

import java.util.HashMap;
import java.util.Map;
import com.ufape.poo.basicas.*;
import java.util.*;

public class AcervoService {

	private Map<Integer, ItemAcervo> itens;

	public AcervoService() {
		this.itens = new HashMap<>();
	}

	public void cadastrarLivro(int id, String titulo, int anoPublicacao, String autor, String isbn, String editor,
			int numeroPaginas) throws ItemJaExisteException {

		if (itens.containsKey(id)) {
			throw new ItemJaExisteException("Item já cadastrado");
		}

		ItemAcervo livro = new Livro(id, titulo, anoPublicacao, autor, isbn, editor, numeroPaginas);

		itens.put(id, livro);

	}

	public void cadastrarArtigo(int id, String titulo, int anoPublicacao, String doi, String autor, String nomeRevista,
			String areaPesquisa) throws ItemJaExisteException {
		if (itens.containsKey(id)) {
			throw new ItemJaExisteException("Item já cadastrado");
		}

		ItemAcervo artigo = new ArtigoCientifico(id, titulo, anoPublicacao, doi, autor, nomeRevista, areaPesquisa);

		itens.put(id, artigo);

	}

	public void cadastrarTese(int id, String titulo, int anoPublicacao, String autor, String orientador,
			String instituicao, String areaPesquisa, int anoDefesa) throws ItemJaExisteException {

		if (itens.containsKey(id)) {
			throw new ItemJaExisteException("Tese já cadastrada");
		}

		ItemAcervo tese = new Tese(id, titulo, anoPublicacao, autor, orientador, instituicao, areaPesquisa, anoDefesa);
		itens.put(id, tese);
	}

	public ItemAcervo buscarItemPorId(int id) throws ItemNaoEncontradoException {

		ItemAcervo item = itens.get(id);

		if (item == null) {
			throw new ItemNaoEncontradoException("Item não encontrado");
		}

		return item;
	}

	public List<ItemAcervo> listarTodosItens() {
		return new ArrayList<>(itens.values());
	}

	public List<ItemAcervo> listarItensDisponiveis() {
		List<ItemAcervo> disponiveis = new ArrayList<>();

		for (ItemAcervo item : itens.values()) {
			if (item.verificarDisponibilidade()) {
				disponiveis.add(item);
			}
		}

		return disponiveis;
	}

	public List<ItemAcervo> listarItensEmprestados() {
		List<ItemAcervo> emprestados = new ArrayList<>();

		for (ItemAcervo item : itens.values()) {
			if (!item.verificarDisponibilidade()) {
				emprestados.add(item);
			}
		}

		return emprestados;
	}

	public void removerItem(int id) throws ItemNaoEncontradoException, OperacaoNaoPermitidaException {

		ItemAcervo item = buscarItemPorId(id);

		if (!item.verificarDisponibilidade()) {
			throw new OperacaoNaoPermitidaException("Item emprestado não pode ser removido");
		}

		itens.remove(id);
	}

}
