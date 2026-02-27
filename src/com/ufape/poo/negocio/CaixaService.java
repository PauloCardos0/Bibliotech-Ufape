package com.ufape.poo.negocio;

import java.util.ArrayList;
import java.util.List;

import com.ufape.poo.basicas.*;

public class CaixaService {

    private Caixa caixa;
    private boolean aberto;
    private List<MovimentacaoCaixa> movimentacoes;
    private int contadorMovimentacao;

    public CaixaService() {
        this.caixa = new Caixa();
        this.aberto = false;
        this.movimentacoes = new ArrayList<>();
        this.contadorMovimentacao = 1;
    }

   
    public void abrirCaixa() throws CaixaJaAbertoException {

        if (aberto) {
            throw new CaixaJaAbertoException("Caixa já está aberto");
        }

        aberto = true;
    }



    public void fecharCaixa() throws CaixaFechadoException {

        if (!aberto) {
            throw new CaixaFechadoException("Caixa já está fechado");
        }

        aberto = false;
    }

  

    public void registrarEntrada(double valor, String descricao)
            throws CaixaFechadoException {

        if (!aberto) {
            throw new CaixaFechadoException("Não é possível registrar movimentação com caixa fechado");
        }

        MovimentacaoCaixa mov = new MovimentacaoCaixa(
                descricao, valor,TipoMovimentacao.ENTRADA );

        movimentacoes.add(mov);
        caixa.adicionarValor(valor);
    }

 

    public double consultarSaldo() {
        return caixa.getSaldo();
    }

    public List<MovimentacaoCaixa> listarMovimentacoes() {
        return movimentacoes;
    }

    public boolean isAberto() {
        return aberto;
    }
}