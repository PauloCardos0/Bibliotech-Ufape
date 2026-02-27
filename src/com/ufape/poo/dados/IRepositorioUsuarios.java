package com.ufape.poo.dados;

import java.util.Map;
import com.ufape.poo.basicas.Usuario;

public interface IRepositorioUsuarios {

    void salvar(Map<Integer, Usuario> usuarios) throws Exception;

    Map<Integer, Usuario> carregar() throws Exception;
}