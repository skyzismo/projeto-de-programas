package br.edu.icomp.ufam.lab_heranca;

public abstract class FormaGeometrica {
    int posX;
    int posY;

    // Construtor
    public FormaGeometrica(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    // Método abstrato para área
    public abstract double getArea();

    // Método abstrato para perímetro
    public abstract double getPerimetro();

    // Retorna a posição no formato: "posição (x, y)"
    public String getPosString() {
        return String.format("posição (%d, %d)", posX, posY);
    }
}
