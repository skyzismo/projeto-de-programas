public class Sala {
    int bloco;
    int sala;
    int capacidade;
    boolean acessivel;

    // Construtor padrão
    public Sala() {
        // Chama o outro construtor com valores padrão
        this(0, 0, 0, false);
    }

    // Construtor com parâmetros
    public Sala(int bloco, int sala, int capacidade, boolean acessivel) {
        this.bloco = bloco;
        this.sala = sala;
        this.capacidade = capacidade;
        this.acessivel = acessivel;
    }

    // Retorna a descrição no formato:
    // "Bloco 6, Sala 101 (50 lugares, acessível)"
    public String getDescricao() {
        String acesso = acessivel ? "acessível" : "não acessível";
        return String.format(
            "Bloco %d, Sala %d (%d lugares, %s)",
            bloco, sala, capacidade, acesso
        );
    }
}
