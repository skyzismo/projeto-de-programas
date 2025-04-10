public class Processador {
    String marca;
    String modelo;
    double velocidade;
    int numNucleos;

    // Construtor padrão
    public Processador() {
        this("Indefinida", "Indefinido", 0.0, 1); // Encadeamento com valores padrão
    }

    // Construtor com parâmetros
    public Processador(String marca, String modelo, double velocidade, int numNucleos) {
        this.marca = marca;
        this.modelo = modelo;
        this.velocidade = velocidade;
        this.numNucleos = numNucleos;
    }

    // Método que retorna a velocidade paralela
    public double getVelocidadeParalela() {
        return velocidade * numNucleos;
    }

    // Método que retorna a descrição
    public String getDescricao() {
        return String.format("Processador: marca=%s, modelo=%s, velocidade=%.1fGHz, numNucleos=%d, velocidadeParalela=%.1fGHz.",
            marca, modelo, velocidade, numNucleos, getVelocidadeParalela());
    }
}
