import java.util.ArrayList;
import java.util.Scanner;

public class AprovacaoDisciplina {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        ArrayList<Double> notas = new ArrayList<>();
        ArrayList<Integer> frequencias = new ArrayList<>();

        // Leitura das notas
        while (true) {
            double valor = teclado.nextDouble();
            if (valor == -1) break;
            notas.add(valor);
        }

        // Leitura das frequências
        while (true) {
            int valor = teclado.nextInt();
            if (valor == -1) break;
            frequencias.add(valor);
        }

        // Leitura da carga horária
        int cargaHoraria = teclado.nextInt();
        double limiteFrequencia = cargaHoraria * 0.75;

        int aprovados = 0;
        int reprovadosNota = 0;
        int reprovadosFrequencia = 0;

        for (int i = 0; i < notas.size(); i++) {
            double nota = notas.get(i);
            int freq = frequencias.get(i);

            if (freq < limiteFrequencia) {
                reprovadosFrequencia++;
            } else if (nota < 5.0) {
                reprovadosNota++;
            } else {
                aprovados++;
            }
        }

        System.out.printf("%d %d %d\n", aprovados, reprovadosNota, reprovadosFrequencia);
        teclado.close();
    }
}
