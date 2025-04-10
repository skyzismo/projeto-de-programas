import java.util.Scanner;

public class RotaOrtodromica {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        // Leitura das coordenadas
        double lat1 = teclado.nextDouble();
        double lon1 = teclado.nextDouble();
        double lat2 = teclado.nextDouble();
        double lon2 = teclado.nextDouble();

        // Converte para radianos
        lat1 = Math.toRadians(lat1);
        lon1 = Math.toRadians(lon1);
        lat2 = Math.toRadians(lat2);
        lon2 = Math.toRadians(lon2);

        // Raio da Terra
        double R = 6371.0;

        // Fórmula da distância ortodrômica
        double distancia = R * Math.acos(
            Math.sin(lat1) * Math.sin(lat2) +
            Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon1 - lon2)
        );

        // Saída formatada
        System.out.printf("A distancia entre os pontos (%.6f, %.6f) e (%.6f, %.6f) e de %.2f km\n",
                Math.toDegrees(lat1), Math.toDegrees(lon1),
                Math.toDegrees(lat2), Math.toDegrees(lon2),
                distancia);

        teclado.close();
    }
}
