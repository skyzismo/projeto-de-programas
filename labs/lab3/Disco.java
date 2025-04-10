public class Disco {
    String marca;
    String tipo;
    double capacidade;
    int rpm;

    // Construtor padrão
    public Disco() {
        this("Desconhecida", "Desconhecido", 0.0, 0); // Valores padrão
    }

    // Construtor com parâmetros
    public Disco(String marca, String tipo, double capacidade, int rpm) {
        this.marca = marca;
        this.tipo = tipo;
        this.capacidade = capacidade;
        this.rpm = rpm;
    }

    // Retorna a descrição do disco formatada
    public String getDescricao() {
        return String.format("Disco: marca=%s, tipo=%s, capacidade=%.1fGB, rpm=%drpm.", marca, tipo, capacidade, rpm);
    }
}
