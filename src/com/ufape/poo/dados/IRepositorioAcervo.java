package com.ufape.poo.dados;

import java.util.Map;
import com.ufape.poo.basicas.ItemAcervo;

public interface IRepositorioAcervo {
    void salvar(Map<Integer, ItemAcervo> itens) throws Exception;
    Map<Integer, ItemAcervo> carregar() throws Exception;
}