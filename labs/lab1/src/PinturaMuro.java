import java.util.Scanner;

public class PinturaMuro {
	
	static int alt = 3;
	static int comp = 12;
	
	public static void main(String[] args) {
		
		int area = alt * comp;
		
		Scanner teclado = new Scanner(System.in);
		
		double valor = teclado.nextDouble();
		
		double custo = (area * valor) + 100;
		
		System.out.println(custo);
		
		teclado.close();
	}
}
