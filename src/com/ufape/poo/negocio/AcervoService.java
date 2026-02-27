package com.ufape.poo.negocio;

import java.util.*;
import com.ufape.poo.basicas.*;
import com.ufape.poo.dados.IRepositorioAcervo;

public class AcervoService {

    private Map<Integer, ItemAcervo> itens;
    private IRepositorioAcervo repositorio;

   
    public AcervoService(IRepositorioAcervo repositorio) throws Exception {
        this.repositorio = repositorio;
        this.itens = repositorio.carregar(); 
    }

    public void cadastrarLivro(int id, String titulo, int anoPublicacao, String autor, String isbn, String editor, int numeroPaginas) throws Exception {
        if (itens.containsKey(id)) {
            throw new ItemJaExisteException("Item já cadastrado");
        }
        ItemAcervo livro = new Livro(id, titulo, anoPublicacao, isbn, autor, editor, numeroPaginas);
        itens.put(id, livro);
        repositorio.salvar(itens); 
    }

    public void cadastrarArtigo(int id, String titulo, int anoPublicacao, String doi, String autor, String nomeRevista, String areaPesquisa) throws Exception {
        if (itens.containsKey(id)) {
            throw new ItemJaExisteException("Item já cadastrado");
        }
        ItemAcervo artigo = new ArtigoCientifico(id, titulo, anoPublicacao, doi, autor, nomeRevista, areaPesquisa);
        itens.put(id, artigo);
        repositorio.salvar(itens); 
    }

    public void cadastrarTese(int id, String titulo, int anoPublicacao, String autor, String orientador, String instituicao, String areaPesquisa, int anoDefesa) throws Exception {
        if (itens.containsKey(id)) {
            throw new ItemJaExisteException("Tese já cadastrada");
        }
        ItemAcervo tese = new Tese(id, titulo, anoPublicacao, autor, orientador, instituicao, areaPesquisa, anoDefesa);
        itens.put(id, tese);
        repositorio.salvar(itens);
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

    public void removerItem(int id) throws Exception {
        ItemAcervo item = buscarItemPorId(id);
        if (!item.verificarDisponibilidade()) {
            throw new OperacaoNaoPermitidaException("Item emprestado não pode ser removido");
        }
        itens.remove(id);
        repositorio.salvar(itens); 
    }
}