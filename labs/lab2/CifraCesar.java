import java.util.Scanner;

public class CifraCesar {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        int desloc = teclado.nextInt();
        teclado.nextLine(); // <- isso evita o erro!
        String texto = teclado.nextLine();

        StringBuilder resultado = new StringBuilder();

        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);

            if (Character.isLetter(c)) {
                char base = Character.isLowerCase(c) ? 'a' : 'A';
                int deslocado = (c - base + desloc) % 26;
                char cifrado = (char) ('A' + deslocado); // sempre MAIÚSCULO
                resultado.append(cifrado);
            } else {
                resultado.append(c);
            }
        }

        System.out.println(resultado.toString());
        teclado.close();
    }
}
