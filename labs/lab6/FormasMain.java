package br.edu.icomp.ufam.lab_heranca;

public class FormasMain {
    public static void main(String[] args) {
        // Cria um vetor de formas geométricas
        FormaGeometrica[] formas = new FormaGeometrica[] {
            new Circulo(32, 87, 6.0),
            new Retangulo(12, 65, 2.0, 7.0),
            new Quadrado(45, 39, 6.0)
        };

        // Imprime cada forma usando o método toString()
        for (FormaGeometrica forma : formas) {
            System.out.println(forma);
        }
    }
}
