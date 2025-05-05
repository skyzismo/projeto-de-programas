package br.edu.ufam.icomp.lab_excecoes;

/**
 * Exceção geral de caminho do rover.
 */
public class RoverCaminhoException extends RoverException {
    public RoverCaminhoException() {
        super("Exceção geral de caminho do rover");
    }

    public RoverCaminhoException(String message) {
        super(message);
    }
}