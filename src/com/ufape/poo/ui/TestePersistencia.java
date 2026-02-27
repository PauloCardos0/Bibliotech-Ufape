package com.ufape.poo.ui;


import com.ufape.poo.basicas.*;
import com.ufape.poo.negocio.*;
import com.ufape.poo.dados.*;

public class TestePersistencia {

    public static void main(String[] args) {

        try {

            BibliotecaFacade fachada = new BibliotecaFacade();
            fachada.cadastrarAluno(
                    1,
                    "João Silva",
                    "12345678900",
                    "Engenharia",
                    2023001,
                    "joao@email.com",
                    "123",
                    3
            );

            System.out.println("Aluno cadastrado com sucesso!");

            System.out.println("Usuários cadastrados:");
            fachada.listarUsuarios().forEach(u ->
                    System.out.println(u.getNome())
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}