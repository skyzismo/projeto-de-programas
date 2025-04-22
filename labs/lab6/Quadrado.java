package br.edu.icomp.ufam.lab_heranca;

public class Quadrado extends Retangulo {

    // Construtor que recebe posição e tamanho do lado
    public Quadrado(int posX, int posY, double lado) {
        super(posX, posY, lado, lado);
    }

    // Sobrescreve apenas toString(), usando getPosString() da superclasse
    @Override
    public String toString() {
        // getPosString() retorna "posição (X, Y)"
        return String.format(
            "Quadrado na %s com lado de %.1fcm (área=%.1fcm2, perímetro=%.1fcm)",
            getPosString(),
            largura,
            getArea(),
            getPerimetro()
        );
    }
}
