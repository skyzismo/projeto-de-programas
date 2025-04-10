public class Memoria {
    String marca;
    String tipo;
    double tamanho;
    double velocidade;
    int numPentes;

    // Construtor padrão
    public Memoria() {
        this("Indefinida", "Indefinido", 0.0, 0.0, 1); // valores padrão
    }

    // Construtor com parâmetros
    public Memoria(String marca, String tipo, double tamanho, double velocidade, int numPentes) {
        this.marca = marca;
        this.tipo = tipo;
        this.tamanho = tamanho;
        this.velocidade = velocidade;
        this.numPentes = numPentes;
    }

    // Retorna o tamanho total da memória (quantidade * tamanho)
    public double getTamanhoTotal() {
        return numPentes * tamanho;
    }

    // Retorna a velocidade paralela da memória (quantidade * velocidade)
    public double getVelocidadeParalela() {
        return numPentes * velocidade;
    }

    // Retorna a descrição formatada da memória
    public String getDescricao() {
        return String.format("Memoria: marca=%s, tipo=%s, tamanho=%.1fGB, velocidade=%.1fGHz, numPentes=%d, tamanhoTotal=%.1fGB, velocidadeParalela=%.1fGHz.",
            marca, tipo, tamanho, velocidade, numPentes, getTamanhoTotal(), getVelocidadeParalela());
    }
}
