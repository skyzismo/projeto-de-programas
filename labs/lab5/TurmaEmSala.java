public class TurmaEmSala {
    Turma turma;
    Sala sala;

    // Construtor padrão
    public TurmaEmSala() {
        this(null, null);
    }

    // Construtor com parâmetros
    public TurmaEmSala(Turma turma, Sala sala) {
        this.turma = turma;
        this.sala = sala;
    }
}
