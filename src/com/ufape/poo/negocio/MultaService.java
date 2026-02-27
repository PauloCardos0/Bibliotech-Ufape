package com.ufape.poo.negocio;

import java.util.*;

import com.ufape.poo.basicas.*;
import com.ufape.poo.dados.IRepositorioMultas;

public class MultaService {

	private Map<Integer, Multa> multas;
    private int contadorId;
    private double valorPorDiaAtraso;
    private IRepositorioMultas repositorio; 

   
    public MultaService(double valorPorDiaAtraso, IRepositorioMultas repositorio) throws Exception {
        this.repositorio = repositorio;
        this.valorPorDiaAtraso = valorPorDiaAtraso;
        this.multas = repositorio.carregar();
        
      
        this.contadorId = multas.isEmpty() ? 1 : Collections.max(multas.keySet()) + 1; 
    }

    public Multa gerarMulta(Emprestimo emprestimo) throws Exception {
        if (!emprestimo.estaAtrasado()) {
            return null;
        }
        long diasAtraso = emprestimo.calcularDiasAtraso();
        double valor = diasAtraso * valorPorDiaAtraso;
        Multa multa = new Multa(contadorId++, emprestimo, valor);
        multas.put(multa.getId(), multa);
        
        repositorio.salvar(multas);
        return multa;
    }

    public void pagarMulta(int multaId) throws Exception {
        Multa multa = buscarMultaPorId(multaId);
        multa.pagar();
        
        repositorio.salvar(multas);
    }
    
    public List<Multa> listarMultasPorUsuario(int usuarioId) {

        List<Multa> lista = new ArrayList<>();

        for (Multa multa : multas.values()) {

            if (multa.getEmprestimo()
                     .getUsuario()
                     .getId() == usuarioId) {

                lista.add(multa);
            }
        }

        return lista;
    }


    public Multa buscarMultaPorId(int id)
            throws MultaNaoEncontradaException {

        Multa multa = multas.get(id);

        if (multa == null) {
            throw new MultaNaoEncontradaException("Multa não encontrada");
        }

        return multa;
    }

    public List<Multa> listarMultasUsuario(Usuario usuario) {

        List<Multa> lista = new ArrayList<>();

        for (Multa multa : multas.values()) {
            if (multa.getEmprestimo().getUsuario().equals(usuario)) {
                lista.add(multa);
            }
        }

        return lista;
    }

    public List<Multa> listarMultasPendentes(Usuario usuario) {

        List<Multa> pendentes = new ArrayList<>();

        for (Multa multa : multas.values()) {
            if (!multa.isPaga() &&
                multa.getEmprestimo().getUsuario().equals(usuario)) {

                pendentes.add(multa);
            }
        }

        return pendentes;
    }

}