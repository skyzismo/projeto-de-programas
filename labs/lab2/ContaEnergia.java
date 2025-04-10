import java.util.Scanner;

public class ContaEnergia {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        int consumo = teclado.nextInt();
        char tipo = teclado.next().charAt(0);  // R, C ou I

        double preco = -1.0;

        if (consumo < 0) {
            // consumo inválido
            System.out.printf("%.2f\n", preco);
        } else {
            switch (tipo) {
                case 'R':
                    preco = (consumo <= 500) ? consumo * 0.40 : consumo * 0.65;
                    break;
                case 'C':
                    preco = (consumo <= 1000) ? consumo * 0.55 : consumo * 0.60;
                    break;
                case 'I':
                    preco = (consumo <= 5000) ? consumo * 0.55 : consumo * 0.60;
                    break;
                default:
                    preco = -1.0;
            }

            System.out.printf("%.2f\n", preco);
        }

        teclado.close();
    }
}
