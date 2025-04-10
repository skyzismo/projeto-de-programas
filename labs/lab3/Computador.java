public class Computador {
    String fabricante;
    Processador processador;
    Memoria memoria;
    Disco disco;

    // Construtor da classe Computador
    public Computador(String fabricante, Processador processador, Memoria memoria, Disco disco) {
        this.fabricante = fabricante;
        this.processador = processador;
        this.memoria = memoria;
        this.disco = disco;
    }

    // Método que retorna a descrição do computador com base nas descrições de processador, memória e disco
    public String getDescricao() {
        return String.format("Computador da fabricante %s. %s %s %s", 
                             fabricante,
                             processador.getDescricao(),
                             memoria.getDescricao(),
                             disco.getDescricao());
    }
}
