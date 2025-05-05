package br.edu.ufam.icomp.lab_excecoes;

/**
 * Dígito da coordenada inválido.
 */
public class DigitoInvalidoException extends RoverCoordenadaException {
    public DigitoInvalidoException() {
        super("Digito da coordenada inválido");
    }
}