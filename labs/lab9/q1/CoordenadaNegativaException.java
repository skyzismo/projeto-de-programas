// arquivo: br/edu/icomp/ufam/lab_excecoes/CoordenadaNegativaException.java
package br.edu.ufam.icomp.lab_excecoes;

public class CoordenadaNegativaException extends RoverCoordenadaException {
    public CoordenadaNegativaException() {
        super();
        System.out.println("Coordenada com valor negativo");
    }
}
