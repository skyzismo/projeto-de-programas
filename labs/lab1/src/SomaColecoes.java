import java.util.Scanner;

public class SomaColecoes {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int somaColecoes = 0;
        int entrada;
        boolean terminou = false;

        while (!terminou) {
        	entrada = teclado.nextInt();

            if (entrada == -1) {

            	entrada = teclado.nextInt();
                System.out.println(somaColecoes);
                somaColecoes = 0;
                if (entrada == -1) {
                    terminou = true;
                } else {
                	somaColecoes += entrada;
                }
            } else {
            	somaColecoes += entrada;
            }
        }
        teclado.close();
    }
}
