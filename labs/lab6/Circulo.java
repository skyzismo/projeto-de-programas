package br.edu.icomp.ufam.lab_heranca;

public class Circulo extends FormaGeometrica {
    public double raio;

    // Construtor
    public Circulo(int posX, int posY, double raio) {
        super(posX, posY);
        this.raio = raio;
    }

    @Override
    public double getArea() {
        return Math.PI * raio * raio;
    }

    @Override
    public double getPerimetro() {
        return 2 * Math.PI * raio;
    }

    @Override
    public String toString() {
        String pos = getPosString();               // "posição (X, Y)"
        String area = Double.toString(getArea()); // ex.: "113.0973352923255"
        String peri = Double.toString(getPerimetro());
        return String.format(
            "Círculo na %s com raio de %.1fcm (área=%scm2, perímetro=%scm)",
            pos, raio, area, peri
        );
    }
}
