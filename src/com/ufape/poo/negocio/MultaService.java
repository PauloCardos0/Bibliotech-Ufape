package com.ufape.poo.negocio;

import java.util.*;

import com.ufape.poo.basicas.*;

public class MultaService {

    private Map<Integer, Multa> multas;
    private int contadorId;
    private double valorPorDiaAtraso;

    public MultaService(double valorPorDiaAtraso) {
        this.multas = new HashMap<>();
        this.contadorId = 1;
        this.valorPorDiaAtraso = valorPorDiaAtraso;
    }

   

    public Multa gerarMulta(Emprestimo emprestimo) {

        if (!emprestimo.estaAtrasado()) {
            return null;
        }

        long diasAtraso = emprestimo.calcularDiasAtraso();
        double valor = diasAtraso * valorPorDiaAtraso;

        Multa multa = new Multa(contadorId++, emprestimo, valor);

        multas.put(multa.getId(), multa);

        return multa;
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


    public void pagarMulta(int multaId)
            throws MultaNaoEncontradaException {

        Multa multa = buscarMultaPorId(multaId);
        multa.pagar();
    }
}