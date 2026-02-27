package com.ufape.poo.dados;

import java.util.Map;
import com.ufape.poo.basicas.Emprestimo;

public interface IRepositorioEmprestimos {
    void salvar(Map<Integer, Emprestimo> emprestimos) throws Exception;
    Map<Integer, Emprestimo> carregar() throws Exception;
}