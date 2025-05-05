package br.edu.ufam.icomp.lab_excecoes;

/**
 * Quantidade máxima de coordenadas excedida.
 */
public class TamanhoMaximoExcedidoException extends RoverCaminhoException {
    public TamanhoMaximoExcedidoException() {
        super("Quantidade máxima de coordenadas excedida");
    }
}
