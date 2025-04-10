import java.util.Scanner;

public class AreaVolume {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        double r = teclado.nextDouble(); // Lê o raio
        double pi = Math.PI;

        // Área do círculo
        double area = pi * Math.pow(r, 2);

        // Volume da esfera
        double volume = (4.0 / 3.0) * pi * Math.pow(r, 3);

        // Saída formatada
        System.out.printf("Um circulo com raio de %.2f centimetros tem uma area de %.2f centimetros quadrados.\n", r, area);
        System.out.printf("Uma esfera com raio de %.2f centimetros tem um volume de %.2f centimetros cubicos.\n", r, volume);

        teclado.close();
    }
}
