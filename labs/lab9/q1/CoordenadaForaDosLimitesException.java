package br.edu.ufam.icomp.lab_excecoes;

public class CoordenadaForaDosLimitesException extends RoverCoordenadaException {
    public CoordenadaForaDosLimitesException() {
        super();
        System.out.println("Coordenada com valores fora dos limites");
    }
}