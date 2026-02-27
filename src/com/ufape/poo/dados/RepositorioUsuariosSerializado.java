package com.ufape.poo.dados;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import com.ufape.poo.basicas.Usuario;

public class RepositorioUsuariosSerializado implements IRepositorioUsuarios {

    private final String ARQUIVO = "usuarios.txt";

    @Override
    public void salvar(Map<Integer, Usuario> usuarios) throws Exception {

        File arquivo = new File(ARQUIVO);

        ObjectOutputStream oos =
                new ObjectOutputStream(
                        new FileOutputStream(arquivo));

        oos.writeObject(usuarios);
        oos.close();
    }

    @Override
    public Map<Integer, Usuario> carregar() throws Exception {

        File arquivo = new File(ARQUIVO);

        if (!arquivo.exists()) {
            return new HashMap<>();
        }

        ObjectInputStream ois =
                new ObjectInputStream(
                        new FileInputStream(arquivo));

        Map<Integer, Usuario> usuarios =
                (Map<Integer, Usuario>) ois.readObject();

        ois.close();

        return usuarios;
    }
}