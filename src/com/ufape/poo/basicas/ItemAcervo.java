package com.ufape.poo.basicas;

import java.io.Serializable;

public abstract class ItemAcervo implements Serializable {

    private String titulo;
    private int anoPublicacao;
    private StatusItem status;
    private int totalEmprestimos;

    public ItemAcervo(String titulo, int anoPublicacao) {

        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("Título inválido");
        }

        if (anoPublicacao <= 0) {
            throw new IllegalArgumentException("Ano inválido");
        }

        this.titulo = titulo;
        this.anoPublicacao = anoPublicacao;
        this.status = StatusItem.DISPONIVEL;
        this.totalEmprestimos = 0;
    }

    public boolean verificarDisponibilidade() {
        return status == StatusItem.DISPONIVEL;
    }

    public void marcarComoEmprestado() throws ItemNaoDisponivelException {

        if (status != StatusItem.DISPONIVEL) {
            throw new ItemNaoDisponivelException("Item não está disponível");
        }

        status = StatusItem.EMPRESTADO;
        incrementarTotalEmprestimos();
    }

    public void marcarComoDisponivel() {
        status = StatusItem.DISPONIVEL;
    }

    public void enviarParaManutencao() throws ItemNaoDisponivelException {

        if (status == StatusItem.EMPRESTADO) {
            throw new ItemNaoDisponivelException("Item emprestado não pode ir para manutenção");
        }

        status = StatusItem.MANUTENCAO;
    }

    private void incrementarTotalEmprestimos() {
        totalEmprestimos++;
    }

    public StatusItem getStatus() {
        return status;
    }

    public int getTotalEmprestimos() {
        return totalEmprestimos;
    }
}
