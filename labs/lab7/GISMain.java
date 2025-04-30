package br.edu.ufam.icomp.lab_encapsulamento;

public class GISMain {
    public static void main(String[] args) {
        // cria um vetor de objetos que implementam Localizavel
        Localizavel[] dispositivos = new Localizavel[] {
            new Celular(55, 92, 930154926),
            new CarroLuxuoso("ABC-1234")
        };

        // percorre e imprime a posição de cada um
        for (Localizavel d : dispositivos) {
            System.out.println(d.getPosicao());
        }
    }
}
