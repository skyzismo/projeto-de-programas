import java.util.Scanner;

public class OperacoesInteiros {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        while (true) {
            int quantidade = 0, pares = 0, impares = 0, soma = 0;
            int maior = Integer.MIN_VALUE;
            int menor = Integer.MAX_VALUE;

            boolean primeiro = true;
            while (true) {
                int num = teclado.nextInt();
                if (num == -1) break;

                primeiro = false;
                quantidade++;

                soma += num;

                if (num % 2 == 0) {
                    pares++;
                } else {
                    impares++;
                }

                if (num > maior) maior = num;
                if (num < menor) menor = num;
            }

            // fim do programa se vetor estiver vazio
            if (primeiro) break;

            double media = (double) soma / quantidade;

            System.out.println(quantidade);
            System.out.println(pares);
            System.out.println(impares);
            System.out.println(soma);
            System.out.printf("%.2f\n", media);
            System.out.println(maior);
            System.out.println(menor);
        }

        teclado.close();
    }
}
