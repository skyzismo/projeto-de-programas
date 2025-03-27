import java.util.Scanner;

public class IdadeUfam {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        int anoAtual = teclado.nextInt();

        int fundacao = 1909;

        int idadeUfam = anoAtual - fundacao;

        System.out.printf("A UFAM tem %d anos de fundacao", idadeUfam);

        teclado.close();
    }
}
