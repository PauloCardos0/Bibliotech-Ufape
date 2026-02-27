package com.ufape.poo.basicas;

import java.io.Serializable;
import java.time.LocalDate;

public class Emprestimo implements Serializable{

    private int id;
    private Usuario usuario;
    private ItemAcervo item;
    private LocalDate dataEmprestimo;
    private LocalDate dataPrevistaDevolucao;
    private LocalDate dataDevolucao;
    private boolean ativo;

    public Emprestimo(int id, Usuario usuario, ItemAcervo item, int diasPrazo) throws ItemNaoDisponivelException, UsuarioBloqueadoException, LimiteEmprestimoExcedidoException, MultaPendenteException {

        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido");
        }

        if (usuario == null) {
            throw new IllegalArgumentException("Usuário inválido");
        }

        if (item == null) {
            throw new IllegalArgumentException("Item inválido");
        }

        if (!item.verificarDisponibilidade()) {
            throw new ItemNaoDisponivelException("Item não disponível");
        }

        this.id = id;
        this.usuario = usuario;
        this.item = item;
        this.dataEmprestimo = LocalDate.now();
        this.dataPrevistaDevolucao = dataEmprestimo.plusDays(diasPrazo);
        this.ativo = true;

        usuario.registrarEmprestimo();
        item.marcarComoEmprestado();
    }

    public void registrarDevolucao() {

        if (!ativo) {
            throw new IllegalStateException("Empréstimo já finalizado");
        }

        this.dataDevolucao = LocalDate.now();
        this.ativo = false;

        usuario.registrarDevolucao();
        item.marcarComoDisponivel();
    }

    public boolean estaAtrasado() {
        return ativo && LocalDate.now().isAfter(dataPrevistaDevolucao);
    }

    public long calcularDiasAtraso() {
        if (!estaAtrasado()) {
            return 0;
        }
        return dataPrevistaDevolucao.until(LocalDate.now()).getDays();
    }

    public boolean isAtivo() {
        return ativo;
    }
    
    public int getId() {
        return id;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }
    public java.time.LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }
}