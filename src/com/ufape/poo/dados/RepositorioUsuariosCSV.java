package com.ufape.poo.dados;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import com.ufape.poo.basicas.*;

public class RepositorioUsuariosCSV implements IRepositorioUsuarios {

    private final String ARQUIVO = "usuarios.csv";

    @Override
    public void salvar(Map<Integer, Usuario> usuarios) throws Exception {

        BufferedWriter writer =
                new BufferedWriter(
                        new FileWriter(ARQUIVO));

        for (Usuario u : usuarios.values()) {

            if (u instanceof Aluno) {
                Aluno a = (Aluno) u;

                writer.write(
                        "ALUNO;" +
                        a.getId() + ";" +
                        a.getNome() + ";" +
                        a.getTelefone() + ";" +
                        a.getEmail() + ";" +
                        a.getCurso() + ";" +
                        a.getMatricula()
                );
                writer.newLine();
            }
        }

        writer.close();
    }

    @Override
    public Map<Integer, Usuario> carregar() throws Exception {

        Map<Integer, Usuario> usuarios = new HashMap<>();

        File arquivo = new File(ARQUIVO);

        if (!arquivo.exists()) {
            return usuarios;
        }

        BufferedReader reader =
                new BufferedReader(
                        new FileReader(ARQUIVO));

        String linha;

        while ((linha = reader.readLine()) != null) {

            String[] dados = linha.split(";");

            if (dados[0].equals("ALUNO")) {

                int id = Integer.parseInt(dados[1]);
                String nome = dados[2];
                String telefone = dados[3];
                String email = dados[4];
                String curso = dados[5];
                int matricula = Integer.parseInt(dados[6]);

                Aluno aluno = new Aluno(
                        id,
                        nome,
                        telefone,
                        email,
                        matricula, curso,
                        curso, matricula
                );

                usuarios.put(id, aluno);
            }
        }

        reader.close();

        return usuarios;
    }
}