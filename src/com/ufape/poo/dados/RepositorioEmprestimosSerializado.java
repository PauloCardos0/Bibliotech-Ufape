package com.ufape.poo.dados;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import com.ufape.poo.basicas.Emprestimo;

public class RepositorioEmprestimosSerializado implements IRepositorioEmprestimos {
    private final String ARQUIVO = "emprestimos.txt";

    @Override
    public void salvar(Map<Integer, Emprestimo> emprestimos) throws Exception {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO));
        oos.writeObject(emprestimos);
        oos.close();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Map<Integer, Emprestimo> carregar() throws Exception {
        File arquivo = new File(ARQUIVO);
        if (!arquivo.exists()) return new HashMap<>();
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo));
        Map<Integer, Emprestimo> emprestimos = (Map<Integer, Emprestimo>) ois.readObject();
        ois.close();
        return emprestimos;
    }
}