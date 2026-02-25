package com.ufape.poo.bassicas;

public class Livro extends ItemAcervo{

	
	private String isbn;
	private String autor;
	private String editora;
	private int numeroPaginas;
	
	
	
	public Livro(String titulo, int anoPublicacao, String isbn, String autor, String editora, int numeroPaginas) {
		super(titulo, anoPublicacao);
		
		if (autor == null || autor.isBlank()) {
            throw new IllegalArgumentException("Autor inválido");
        }

        if (isbn == null || isbn.isBlank()) {
            throw new IllegalArgumentException("ISBN inválido");
        }

        if (editora == null || editora.isBlank()) {
            throw new IllegalArgumentException("Editora inválida");
        }

        if (numeroPaginas <= 0) {
            throw new IllegalArgumentException("Número de páginas inválido");
        }

        this.autor = autor;
        this.isbn = isbn;
        this.editora = editora;
        this.numeroPaginas = numeroPaginas;

	}
	
	public String getAutor() {
        return autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getEditora() {
        return editora;
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    public void setAutor(String autor) {
        if (autor == null || autor.isBlank()) {
            throw new IllegalArgumentException("Autor inválido");
        }
        this.autor = autor;
    }

    public void setEditora(String editora) {
        if (editora == null || editora.isBlank()) {
            throw new IllegalArgumentException("Editora inválida");
        }
        this.editora = editora;
    }

    public void setNumeroPaginas(int numeroPaginas) {
        if (numeroPaginas <= 0) {
            throw new IllegalArgumentException("Número de páginas inválido");
        }
        this.numeroPaginas = numeroPaginas;
    }
	
	

	
}
