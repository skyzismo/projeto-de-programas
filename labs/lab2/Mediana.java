import java.util.Scanner;
import java.util.ArrayList;

public class Mediana {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        ArrayList<Integer> lista = new ArrayList<>();

        while (true) {
            int num = teclado.nextInt();
            if (num == -1) break;
            lista.add(num);
        }

        int n = lista.size();
        double mediana;

        if (n % 2 == 1) {
            mediana = lista.get(n / 2);
        } else {
            int meio1 = lista.get(n / 2 - 1);
            int meio2 = lista.get(n / 2);
            mediana = (meio1 + meio2) / 2.0;
        }

        System.out.printf("%.1f\n", mediana);
        teclado.close();
    }
}
