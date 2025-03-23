import java.util.Scanner;

public class TipoTriangulo {
	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		
		int a = teclado.nextInt();
		int b = teclado.nextInt();
		int c = teclado.nextInt();
		
		if ( ((a > 0) && (b > 0) && (c > 0)) ){
			
			if ( ((a + b) > c) && ((a + c) > b) && ((b + c) > a) ) {
				
				if ((a == b) && (a == c) && (b == c)) {
					System.out.println("equilatero");
				}
				
				else if ((a != b) && (a != c) && (b != c)) {
					System.out.println("escaleno");
				}
				
				else if ((a == b && b != c) || (a == c && b != c) || (b == c && a != b)) {
					System.out.println("isosceles");
				}
			}
			
			else {
				System.out.println("invalido");
			}
		}
		
		else {
			System.out.println("invalido");
		}
		
		teclado.close();
	}

}
