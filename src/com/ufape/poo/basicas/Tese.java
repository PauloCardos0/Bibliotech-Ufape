package com.ufape.poo.basicas;

public class Tese extends ItemAcervo {

	private String autor;
	private String orientador;
	private String instituicao;
	private String areaPesquisa;
	private int anoDefesa;

	public Tese(String titulo, int anoPublicacao, String autor, String orientador, String instituicao,
			String areaPesquisa, int anoDefesa) {
		super(titulo, anoPublicacao);

		if (autor == null || autor.isBlank()) {
			throw new IllegalArgumentException("Autor Inválido");
		}

		if (orientador == null || orientador.isBlank()) {
			throw new IllegalArgumentException("Orientador Inválido");
		}

		if (instituicao == null || instituicao.isBlank()) {
			throw new IllegalArgumentException("Instituição Inválida");

		}

		if (areaPesquisa == null || areaPesquisa.isBlank()) {
			throw new IllegalArgumentException("Area de Pesquisa Inválida");
		}

		if (anoDefesa <= 0) {
			throw new IllegalArgumentException("Ano de Defesa Inválido");
		}

		this.autor = autor;
		this.orientador = orientador;
		this.instituicao = instituicao;
		this.areaPesquisa = areaPesquisa;
		this.anoDefesa = anoDefesa;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		if (autor == null || autor.isBlank()) {
			throw new IllegalArgumentException("Autor Inválido");
		}
		this.autor = autor;
	}

	public String getOrientador() {
		return orientador;
	}

	public void setOrientador(String orientador) {
		if (orientador == null || orientador.isBlank()) {
			throw new IllegalArgumentException("Orientador Inválido");
		}
		this.orientador = orientador;
	}

	public String getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(String instituicao) {
		if (instituicao == null || instituicao.isBlank()) {
			throw new IllegalArgumentException("Instituição Inválida");

		}
		this.instituicao = instituicao;
	}

	public String getAreaPesquisa() {
		return areaPesquisa;
	}

	public void setAreaPesquisa(String areaPesquisa) {
		if (areaPesquisa == null || areaPesquisa.isBlank()) {
			throw new IllegalArgumentException("Area de Pesquisa Inválida");
		}
		this.areaPesquisa = areaPesquisa;
	}

	public int getAnoDefesa() {
		return anoDefesa;
	}

	public void setAnoDefesa(int anoDefesa) {
		if (anoDefesa <= 0) {
			throw new IllegalArgumentException("Ano de Defesa Inválido");
		}
		this.anoDefesa = anoDefesa;
	}

}
