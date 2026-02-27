package com.ufape.poo.dados;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import com.ufape.poo.basicas.ItemAcervo;

public class RepositorioAcervoSerializado implements IRepositorioAcervo {

    private final String ARQUIVO = "acervo.txt";

    @Override
    public void salvar(Map<Integer, ItemAcervo> itens) throws Exception {
        File arquivo = new File(ARQUIVO);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo));
        oos.writeObject(itens);
        oos.close();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Map<Integer, ItemAcervo> carregar() throws Exception {
        File arquivo = new File(ARQUIVO);
        if (!arquivo.exists()) {
            return new HashMap<>();
        }

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo));
        Map<Integer, ItemAcervo> itens = (Map<Integer, ItemAcervo>) ois.readObject();
        ois.close();

        return itens;
    }
}