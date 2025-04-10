import java.util.Scanner;

public class VolumeCombustivel {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        double H = teclado.nextDouble(); // altura total do tanque
        double h = teclado.nextDouble(); // nível de combustível
        double r = teclado.nextDouble(); // raio dos bojos semiesféricos

        double pi = Math.PI;
        double volume;

        // Validação dos dados
        if (H <= 2 * r || h < 0 || h > H || r <= 0) {
            System.out.printf("%.3f\n", -1.0);
            return;
        }

        if (h <= r) {
            // Caso 1: combustível está somente na calota inferior
            volume = (pi / 3.0) * Math.pow(h, 2) * (3 * r - h);
        } else if (h <= H - r) {
            // Caso 2: calota inferior (meia esfera) + cilindro
            double calotaInferior = (2.0 / 3.0) * pi * Math.pow(r, 3); // meia esfera
            double alturaCilindro = h - r;
            double cilindro = pi * Math.pow(r, 2) * alturaCilindro;
            volume = calotaInferior + cilindro;
        } else {
            // Caso 3: combustível preenche até a calota superior, subtrai calota de ar
            double volumeTotal = (4.0 / 3.0) * pi * Math.pow(r, 3) + pi * Math.pow(r, 2) * (H - 2 * r);
            double alturaAr = H - h;
            double calotaSuperior = (pi / 3.0) * Math.pow(alturaAr, 2) * (3 * r - alturaAr);
            volume = volumeTotal - calotaSuperior;
        }

        // Converte para litros
        volume *= 1000;

        System.out.printf("%.3f\n", volume);
        teclado.close();
    }
}
