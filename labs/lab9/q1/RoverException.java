package br.edu.ufam.icomp.lab_excecoes;

/**
 * Exceção geral do rover.
 */
public class RoverException extends Exception {
    /**
     * Construtor padrão com mensagem pré-definida.
     */
    public RoverException() {
        super("Exceção geral do rover");
    }

    /**
     * Construtor que recebe mensagem personalizada.
     */
    public RoverException(String message) {
        super(message);
    }
}