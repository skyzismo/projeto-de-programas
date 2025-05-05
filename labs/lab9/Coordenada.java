package br.edu.ufam.icomp.lab_excecoes;

public class Coordenada {
    private int posX;
    private int posY;
    private int digito;

    /**
     * Construtor que verifica, em ordem de prioridade:
     * 1) CoordenadaNegativaException se posX<0 ou posY<0
     * 2) CoordenadaForaDosLimitesException se posX>30000 ou posY>30000
     * 3) DigitoInvalidoException se (posX+posY)%10 != digito
     */
    public Coordenada(int posX, int posY, int digito)
            throws CoordenadaNegativaException,
                   CoordenadaForaDosLimitesException,
                   DigitoInvalidoException {
        if (posX < 0 || posY < 0) {
            throw new CoordenadaNegativaException();
        }
        if (posX > 30000 || posY > 30000) {
            throw new CoordenadaForaDosLimitesException();
        }
        if (( (posX + posY) % 10) != digito) {
            throw new DigitoInvalidoException();
        }
        this.posX = posX;
        this.posY = posY;
        this.digito = digito;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    /**
     * Distância euclidiana entre esta coordenada e outra.
     */
    public double distancia(Coordenada outra) {
        double dx = outra.posX - this.posX;
        double dy = outra.posY - this.posY;
        return Math.sqrt(dx*dx + dy*dy);
    }

    /**
     * toString no formato "X, Y"
     */
    @Override
    public String toString() {
        return posX + ", " + posY;
    }
}
