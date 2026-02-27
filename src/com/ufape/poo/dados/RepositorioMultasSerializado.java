package com.ufape.poo.dados;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import com.ufape.poo.basicas.Multa;

public class RepositorioMultasSerializado implements IRepositorioMultas {
    private final String ARQUIVO = "multas.txt";

    @Override
    public void salvar(Map<Integer, Multa> multas) throws Exception {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO));
        oos.writeObject(multas);
        oos.close();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Map<Integer, Multa> carregar() throws Exception {
        File arquivo = new File(ARQUIVO);
        if (!arquivo.exists()) return new HashMap<>();
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo));
        Map<Integer, Multa> multas = (Map<Integer, Multa>) ois.readObject();
        ois.close();
        return multas;
    }
}