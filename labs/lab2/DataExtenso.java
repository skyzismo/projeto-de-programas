import java.util.Scanner;

public class DataExtenso {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        String data = teclado.next();

        String diaStr = data.substring(0, 2);
        String mesStr = data.substring(2, 4);
        String anoStr = data.substring(4);

        int dia = Integer.parseInt(diaStr);
        int mes = Integer.parseInt(mesStr);
        int ano = Integer.parseInt(anoStr);

        String[] nomesMeses = {
            "", "janeiro", "fevereiro", "março", "abril", "maio", "junho",
            "julho", "agosto", "setembro", "outubro", "novembro", "dezembro"
        };

        System.out.printf("%d de %s de %d\n", dia, nomesMeses[mes], ano);
        teclado.close();
    }
}
