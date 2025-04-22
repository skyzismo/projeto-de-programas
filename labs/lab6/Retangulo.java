package br.edu.icomp.ufam.lab_heranca;

public class Retangulo extends FormaGeometrica {
    public double largura;
    public double altura;

    // Construtor
    public Retangulo(int posX, int posY, double largura, double altura) {
        super(posX, posY);
        this.largura = largura;
        this.altura = altura;
    }

    // Implementa getArea(): A = largura * altura
    @Override
    public double getArea() {
        return largura * altura;
    }

    // Implementa getPerimetro(): P = 2 * (largura + altura)
    @Override
    public double getPerimetro() {
        return 2 * (largura + altura);
    }

    // Sobrescreve toString() para:
    // "Retângulo na posição (X, Y) com largura de L.cm e altura de H.cm (área=Ac m2, perímetro=Pc m)"
    @Override
    public String toString() {
        String pos = getPosString();               // "posição (X, Y)"
        double area = getArea();                   // ex.: 14.0
        double peri = getPerimetro();              // ex.: 18.0
        return String.format(
            "Retângulo na %s com largura de %.1fcm e altura de %.1fcm (área=%.1fcm2, perímetro=%.1fcm)",
            pos, largura, altura, area, peri
        );
    }
}
