package com.ufape.poo.dados;

import java.util.Map;
import com.ufape.poo.basicas.Multa;

public interface IRepositorioMultas {
    void salvar(Map<Integer, Multa> multas) throws Exception;
    Map<Integer, Multa> carregar() throws Exception;
}