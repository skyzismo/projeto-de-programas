public class ComputadorMain {
    public static void main(String[] args) {
        // Criando objetos de Processador, Memoria e Disco
        Processador proc = new Processador("Intel", "i7", 3.2, 8);
        Memoria mem = new Memoria("Kingston", "DDR4", 8.0, 3.2, 4);
        Disco disc = new Disco("Western Digital", "HDD", 4000.0, 9600);

        // Criando o objeto Computador
        Computador computador = new Computador("Dell", proc, mem, disc);

        // Imprimindo a descrição do computador
        System.out.println(computador.getDescricao());
    }
}
