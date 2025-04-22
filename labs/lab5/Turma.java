import java.util.ArrayList;

public class Turma {
    String nome;
    String professor;
    int numAlunos;
    boolean acessivel;
    ArrayList<Integer> horarios;

    // Construtor padrão
    public Turma() {
        this("", "", 0, false);
    }

    // Construtor com parâmetros
    public Turma(String nome, String professor, int numAlunos, boolean acessivel) {
        this.nome = nome;
        this.professor = professor;
        this.numAlunos = numAlunos;
        this.acessivel = acessivel;
        this.horarios = new ArrayList<>();
    }

    // 1) Adiciona um código de horário à lista
    public void addHorario(int horario) {
        horarios.add(horario);
    }

    // 2) Converte a lista de códigos em string legível
    public String getHorariosString() {
        String[] dias = { "segunda", "terça", "quarta", "quinta", "sexta" };
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < horarios.size(); i++) {
            int code = horarios.get(i);
            int col = (code - 1) / 7;   // coluna: 0=Seg,1=Ter,...4=Sex
            int row = (code - 1) % 7;   // linha: 0..6 correspondem a horas
            int hora = 8 + 2 * row;     // hora = 8,10,...20
            sb.append(dias[col]).append(" ").append(hora).append("hs");
            if (i < horarios.size() - 1) {
                sb.append(", ");
            }
        }

        return sb.toString();
    }

    // 3) Descrição completa da turma
    public String getDescricao() {
        String acessoStr = acessivel ? "sim" : "não";
        return String.format(
            "Turma: %s\n" +
            "Professor: %s\n" +
            "Número de Alunos: %d\n" +
            "Horário: %s\n" +
            "Acessível: %s",
            nome,
            professor,
            numAlunos,
            getHorariosString(),
            acessoStr
        );
    }
}
