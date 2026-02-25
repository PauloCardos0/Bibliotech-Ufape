package com.ufape.poo.bassicas;

public class ArtigoCientifico extends ItemAcervo {

	private String doi;
	private String autor;
	private String nomeRevista;
	private String areapesquisa;

	public ArtigoCientifico(String titulo, int anoPublicacao, String doi, String autor, String nomeRevista,
			String areaPesquisa) {

		super(titulo, anoPublicacao);

		if (autor == null || autor.isBlank()) {
			throw new IllegalArgumentException("Autor inválido");
		}

		if (doi == null || doi.isBlank()) {
			throw new IllegalArgumentException("Doi inválido");

		}

		if (nomeRevista == null || nomeRevista.isBlank()) {
			throw new IllegalArgumentException("Artigo sem nome");
		}

		if (areaPesquisa == null || areaPesquisa.isBlank()) {
			throw new IllegalArgumentException("Area de Pesquisa Inválida");
		}

		this.doi = doi;
		this.autor = autor;
		this.areapesquisa = areaPesquisa;
		this.nomeRevista = nomeRevista;

	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {

		if (autor == null || autor.isBlank()) {
			throw new IllegalArgumentException("Autor inválido");
		}
		this.autor = autor;

	}

	public String getNomeRevista() {
		return nomeRevista;
	}

	public void setNomeRevista(String nomeRevista) {
		if (nomeRevista == null || nomeRevista.isBlank()) {
			throw new IllegalArgumentException("Revista inválido");
		}

		this.nomeRevista = nomeRevista;
	}

	public String getAreapesquisa() {
		return areapesquisa;
	}

	public void setAreapesquisa(String areapesquisa) {

		if (areapesquisa == null || areapesquisa.isBlank()) {
			throw new IllegalArgumentException("Area de Pesquisa Inválida");
		}
		this.areapesquisa = areapesquisa;
	}

	public String getDoi() {
		return doi;
	}

}
