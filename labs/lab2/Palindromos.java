import java.util.Scanner;

public class Palindromos {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        String linha = teclado.nextLine();

        // Remove espaços e converte para maiúsculas
        String texto = linha.replace(" ", "").toUpperCase();

        // Verifica palíndromo
        int n = texto.length();
        boolean ehPalindromo = true;

        for (int i = 0; i < n / 2; i++) {
            if (texto.charAt(i) != texto.charAt(n - 1 - i)) {
                ehPalindromo = false;
                break;
            }
        }

        System.out.print(texto + " ");
        System.out.println(ehPalindromo ? "1" : "0");
        teclado.close();
    }
}
