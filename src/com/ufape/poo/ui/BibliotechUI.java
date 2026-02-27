package com.ufape.poo.ui;

import java.util.Scanner;
import com.ufape.poo.negocio.BibliotecaFacade;
import com.ufape.poo.basicas.ItemAcervo;
import com.ufape.poo.basicas.Usuario;

public class BibliotechUI {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		BibliotecaFacade fachada;

		try {
			fachada = new BibliotecaFacade();
		} catch (Exception e) {
			System.err.println("Erro crítico ao carregar dados: " + e.getMessage());
			return;
		}

		int opcao = -1;

		while (opcao != 0) {
			System.out.println("\n=== Sistema Bibliotech UFAPE ===");
			System.out.println("1. Cadastrar Aluno");
			System.out.println("2. Listar Utilizadores");
			System.out.println("3. Cadastrar Livro");
			System.out.println("4. Listar Acervo");
			System.out.println("0. Sair");
			System.out.print("Escolha uma opção: ");

			opcao = scanner.nextInt();
			scanner.nextLine();
			try {
				switch (opcao) {
				case 1:
					System.out.print("ID: ");
					int id = scanner.nextInt();
					scanner.nextLine();
					System.out.print("Nome: ");
					String nome = scanner.nextLine();
					System.out.print("Email: ");
					String email = scanner.nextLine();
					System.out.print("Telefone: ");
					String telefone = scanner.nextLine();
					System.out.print("Matrícula: ");
					String matricula = scanner.nextLine();
					System.out.print("Curso: ");
					String curso = scanner.nextLine();
					System.out.print("Período: ");
					int periodo = scanner.nextInt();

					fachada.cadastrarAluno(id, nome, email, telefone, 3, matricula, curso, periodo);
					System.out.println("Aluno cadastrado com sucesso!");
					break;

				case 2:
					System.out.println("\n--- Lista de Utilizadores ---");
					for (Usuario u : fachada.listarUsuarios()) {
						System.out.println("ID: " + u.getId() + " | Nome: " + u.getNome() + " | Tipo: "
								+ u.getClass().getSimpleName());
					}
					break;

				case 3:
					System.out.print("ID do Livro: ");
					int idLivro = scanner.nextInt();
					scanner.nextLine();
					System.out.print("Título: ");
					String titulo = scanner.nextLine();
					System.out.print("Ano: ");
					int ano = scanner.nextInt();
					scanner.nextLine();
					System.out.print("Autor: ");
					String autor = scanner.nextLine();
					System.out.print("ISBN: ");
					String isbn = scanner.nextLine();
					System.out.print("Editora: ");
					String editora = scanner.nextLine();
					System.out.print("Nº de Páginas: ");
					int paginas = scanner.nextInt();

					fachada.cadastrarLivro(idLivro, titulo, ano, autor, isbn, editora, paginas);
					System.out.println("Livro cadastrado com sucesso!");
					break;

				case 4:
					System.out.println("\n--- Lista do Acervo ---");
					for (ItemAcervo item : fachada.listarItens()) {
						System.out.println(
								"ID/Status: " + item.getStatus() + " | Classe: " + item.getClass().getSimpleName());
					}
					break;

				case 0:
					System.out.println("A encerrar o sistema...");
					break;

				default:
					System.out.println("Opção inválida!");
				}
			} catch (Exception e) {
				System.err.println("Erro na operação: " + e.getMessage());
			}
		}
		scanner.close();
	}
}