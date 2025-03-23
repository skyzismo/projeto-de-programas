import java.util.Scanner;

public class AsciiArt {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int in = teclado.nextInt();

        int spaces = 0;

        for (int i = in; i > 0; i--) {
            
            for (int j = 0; j < i; j++) {
                System.out.print("*");
            }

            for (int j = 0; j < spaces; j++) {
                System.out.print(" ");
            }

            for (int j = 0; j < i; j++) {
                System.out.print("*");
            }

            System.out.println();
            spaces += 2;
        }

        teclado.close();
    }
}
