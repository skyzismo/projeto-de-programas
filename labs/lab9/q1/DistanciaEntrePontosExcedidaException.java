package br.edu.ufam.icomp.lab_excecoes;

/**
 * Distância máxima entre duas coordenadas vizinhas excedida.
 */
public class DistanciaEntrePontosExcedidaException extends RoverCaminhoException {
    public DistanciaEntrePontosExcedidaException() {
        super("Distância máxima entre duas coordenadas vizinhas excedida");
    }
}
