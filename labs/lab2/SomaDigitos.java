import java.util.Scanner;

public class SomaDigitos {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        
        int numero = teclado.nextInt();
        int soma = 0;

        while (numero > 0) {
            soma += numero % 10;  // Pega o último dígito
            numero /= 10;         // Remove o último dígito
        }

        System.out.println(soma);

        teclado.close();
    }
}
