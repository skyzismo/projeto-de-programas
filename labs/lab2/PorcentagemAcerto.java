import java.util.ArrayList;
import java.util.Scanner;

public class PorcentagemAcerto {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        ArrayList<Integer> respostas = new ArrayList<>();
        ArrayList<Integer> gabarito = new ArrayList<>();

        // Lê vetor de respostas
        while (true) {
            int valor = teclado.nextInt();
            if (valor == -1) break;
            respostas.add(valor);
        }

        // Lê vetor de gabarito
        while (true) {
            int valor = teclado.nextInt();
            if (valor == -1) break;
            gabarito.add(valor);
        }

        // Compara as respostas
        int acertos = 0;
        int total = respostas.size();

        for (int i = 0; i < total; i++) {
            if (respostas.get(i).equals(gabarito.get(i))) {
                acertos++;
            }
        }

        double porcentagem = (100.0 * acertos) / total;
        System.out.printf("%.2f\n", porcentagem);
        teclado.close();
    }
}
