package com.ufape.poo.ui;

import com.ufape.poo.basicas.Multa;

import java.util.List;
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
			System.out.println("5. Realizar Empréstimo");
			System.out.println("6. Registrar Devolução");
			System.out.println("7. Consultar Multas");
			System.out.println("8. Pagar Multa");
			System.out.println("9. Gestão do Caixa (Abrir/Fechar/Saldo)");
			System.out.println("10. Relatório: Multas a Receber");
			System.out.println("11. Relatório: Itens Mais Emprestados");
			System.out.println("12. Relatório: Leitor do Ano");
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
					
				case 5:
				    System.out.print("ID do Utilizador: ");
				    int idUsuario = scanner.nextInt();
				    System.out.print("ID do Item (Acervo): ");
				    int idItem = scanner.nextInt();
				    System.out.print("Prazo (em dias): ");
				    int dias = scanner.nextInt();
				    
				    fachada.realizarEmprestimo(idUsuario, idItem, dias);
				    System.out.println("Empréstimo realizado com sucesso!");
				    break;

				case 6:
				    System.out.print("ID do Empréstimo para devolução: ");
				    int idEmp = scanner.nextInt();
				    fachada.registrarDevolucao(idEmp);
				    System.out.println("Devolução registada. Verifique se foram geradas multas.");
				    break;

				case 7:
				    System.out.print("ID do Utilizador: ");
				    int idUserMulta = scanner.nextInt();
				    System.out.println("--- Multas do Utilizador ---");
				    for (Multa m : fachada.consultarMultasUsuario(idUserMulta)) {
				        System.out.println("ID Multa: " + m.getId() + " | Valor: R$ " + m.getValor() + " | Paga: " + (m.isPaga() ? "Sim" : "Não"));
				    }
				    break;

				case 8:
					System.out.print("ID da Multa a pagar: ");
				    int idMulta = scanner.nextInt();
				    
				  
				    try { 
				        fachada.abrirCaixa(); 
				    } catch (Exception e) { 
				      
				    }
				    
				    fachada.pagarMulta(idMulta);
				    System.out.println("Multa paga com sucesso e valor contabilizado no caixa!");
				    break;

				case 9:
				    System.out.println("1- Abrir Caixa | 2- Fechar Caixa | 3- Saldo");
				    int opCaixa = scanner.nextInt();
				    if (opCaixa == 1) {
				        try { fachada.abrirCaixa(); System.out.println("Caixa aberto com sucesso."); } 
				        catch (Exception e) { System.out.println(e.getMessage()); }
				    } else if (opCaixa == 2) {
				        try { fachada.fecharCaixa(); System.out.println("Caixa fechado."); } 
				        catch (Exception e) { System.out.println(e.getMessage()); }
				    } else if (opCaixa == 3) {
				        System.out.println("Saldo do Caixa: R$ " + fachada.consultarSaldo());
				    }
				    break;

				case 10:
				    System.out.println("\n--- Relatório: Multas a Receber ---");
				    for (Multa m : fachada.listarTodasMultasPendentes()) {
				        System.out.println("Multa ID: " + m.getId() + " | Utilizador: " + m.getEmprestimo().getUsuario().getNome() + " | Valor: R$ " + m.getValor());
				    }
				    break;

				case 11:
				    System.out.println("\n--- Relatório: Itens Mais Emprestados ---");
				    List<ItemAcervo> ranking = fachada.listarItensMaisEmprestados();
				    for (int i = 0; i < ranking.size() && i < 5; i++) { 
				        ItemAcervo itemRanking = ranking.get(i);
				        System.out.println((i+1) + "º Lugar: " + itemRanking.getClass().getSimpleName() + " (Empréstimos: " + itemRanking.getTotalEmprestimos() + ")");
				    }
				    break;

				case 12:
				    System.out.print("Digite o ano para o relatório (ex: 2026): ");
				    int anoRela = scanner.nextInt();
				    Usuario leitor = fachada.obterLeitorDoAno(anoRela);
				    if (leitor != null) {
				        System.out.println("🏆 O Leitor do Ano é: " + leitor.getNome() + "!");
				    } else {
				        System.out.println("Nenhum empréstimo registado neste ano.");
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