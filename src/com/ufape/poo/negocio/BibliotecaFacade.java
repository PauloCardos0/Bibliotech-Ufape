package com.ufape.poo.negocio;

import java.util.List;

import com.ufape.poo.basicas.*;

public class BibliotecaFacade {

	private UsuarioService usuarioService;
	private AcervoService acervoService;
	private EmprestimoService emprestimoService;
	private MultaService multaService;
	private CaixaService caixaService;

	public BibliotecaFacade() {

		this.usuarioService = new UsuarioService();
		this.acervoService = new AcervoService();
		this.multaService = new MultaService(2.0);
		this.caixaService = new CaixaService();

		this.emprestimoService = new EmprestimoService(usuarioService, acervoService);
	}

	public void cadastrarAluno(int id, String nome, String email, String telefone, int limiteEmprestimo,
			String matricula, String curso, int periodo) throws UsuarioJaExisteException {

		usuarioService.cadastrarAluno(id, nome, email, telefone, limiteEmprestimo, matricula, curso, periodo);
	}

	public void cadastrarProfessor(int id, String nome, String email, String telefone, int limiteEmprestimo,
			String siape, String departamento) throws UsuarioJaExisteException {

		usuarioService.cadastrarProfessor(id, nome, email, telefone, limiteEmprestimo, siape, departamento);
	}

	public void cadastrarBibliotecario(int id, String nome, String email, String telefone, int limiteEmprestimo,
			String matriculaFuncional, String setor) throws UsuarioJaExisteException {

		usuarioService.cadastrarBibliotecario(id, nome, email, telefone, limiteEmprestimo, matriculaFuncional, setor);
	}

	public Usuario buscarUsuario(int id) throws UsuarioNaoEncontradoException {

		return usuarioService.buscarUsuarioPorId(id);
	}

	public List<Usuario> listarUsuarios() {
		return usuarioService.listaUsuarios();
	}

	public void cadastrarLivro(int id, String titulo, int anoPublicacao, String autor, String isbn, String editor,
			int numeroPaginas) throws ItemJaExisteException {

		acervoService.cadastrarLivro(id, titulo, anoPublicacao, autor, isbn, editor, numeroPaginas);
	}

	public void cadastrarArtigo(int id, String titulo, int anoPublicacao, String doi, String autor, String nomeRevista,
			String areaPesquisa) throws ItemJaExisteException {

		acervoService.cadastrarArtigo(id, titulo, anoPublicacao, doi, autor, nomeRevista, areaPesquisa);
	}

	public void cadastrarTese(int id, String titulo, int anoPublicacao, String autor, String orientador,
			String instituicao, String areaPesquisa, int anoDefesa) throws ItemJaExisteException {

		acervoService.cadastrarTese(id, titulo, anoPublicacao, autor, orientador, instituicao, areaPesquisa, anoDefesa);
	}

	public ItemAcervo buscarItem(int id) throws ItemNaoEncontradoException {

		return acervoService.buscarItemPorId(id);
	}

	public List<ItemAcervo> listarItens() {
		return acervoService.listarTodosItens();
	}

	public Emprestimo realizarEmprestimo(int usuarioId, int itemId, int diasPrazo) throws Exception {

		return emprestimoService.realizarEmprestimo(usuarioId, itemId, diasPrazo);
	}

	public void registrarDevolucao(int emprestimoId) throws Exception {

		emprestimoService.registrarDevolucao(emprestimoId);

		Emprestimo e = emprestimoService.buscarEmprestimoPorId(emprestimoId);

		Multa multa = multaService.gerarMulta(e);

		if (multa != null) {
			System.out.println("Multa gerada: R$ " + multa.getValor());
		}
	}

	public void pagarMulta(int multaId) throws Exception {

		Multa multa = multaService.buscarMultaPorId(multaId);

		multaService.pagarMulta(multaId);

		caixaService.registrarEntrada(multa.getValor(), "Pagamento multa ID " + multaId);
	}

	public void abrirCaixa() throws CaixaJaAbertoException {
		caixaService.abrirCaixa();
	}

	public void fecharCaixa() throws CaixaFechadoException {
		caixaService.fecharCaixa();
	}

	public double consultarSaldo() {
		return caixaService.consultarSaldo();
	}
}