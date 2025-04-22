import java.util.ArrayList;

public class Ensalamento {
    ArrayList<Sala> salas;
    ArrayList<Turma> turmas;
    ArrayList<TurmaEmSala> ensalamento;

    public Ensalamento() {
        this.salas = new ArrayList<>();
        this.turmas = new ArrayList<>();
        this.ensalamento = new ArrayList<>();
    }

    // adiciona uma sala à lista
    public void addSala(Sala sala) {
        salas.add(sala);
    }

    // adiciona uma turma à lista
    public void addTurma(Turma turma) {
        turmas.add(turma);
    }

    // retorna a sala em que a turma está alocada, ou null se nenhuma
    public Sala getSala(Turma turma) {
        for (TurmaEmSala tes : ensalamento) {
            if (tes.turma.equals(turma)) {
                return tes.sala;
            }
        }
        return null;
    }

    // retorna true se a sala estiver livre no horário dado
    public boolean salaDisponivel(Sala sala, int horario) {
        for (TurmaEmSala tes : ensalamento) {
            if (tes.sala.equals(sala)) {
                // se alguma turma na mesma sala tenha o horário, não está disponível
                if (tes.turma.horarios.contains(horario)) {
                    return false;
                }
            }
        }
        return true;
    }

    // retorna true se a sala estiver livre em TODOS os horários da lista
    public boolean salaDisponivel(Sala sala, ArrayList<Integer> horarios) {
        for (int h : horarios) {
            if (!salaDisponivel(sala, h)) {
                return false;
            }
        }
        return true;
    }

    // tenta alocar a turma na sala; retorna true se conseguiu
    public boolean alocar(Turma turma, Sala sala) {
        // só aloca turma acessível em sala acessível
        if (turma.acessivel && !sala.acessivel) {
            return false;
        }
        // turma cabe na sala?
        if (turma.numAlunos > sala.capacidade) {
            return false;
        }
        // sala livre em todos os horários da turma?
        if (!salaDisponivel(sala, turma.horarios)) {
            return false;
        }
        // tudo ok: cria vínculo turma a sala
        ensalamento.add(new TurmaEmSala(turma, sala));
        return true;
    }

    // tenta alocar todas as turmas em alguma sala
    public void alocarTodas() {
        for (Turma t : turmas) {
            for (Sala s : salas) {
                if (alocar(t, s)) {
                    break;
                }
            }
        }
    }

    // quantas turmas foram efetivamente alocadas?
    public int getTotalTurmasAlocadas() {
        return ensalamento.size();
    }

    // soma do (capacidade da sala – numAlunos) para cada turma alocada
    public int getTotalEspacoLivre() {
        int total = 0;
        for (TurmaEmSala tes : ensalamento) {
            total += tes.sala.capacidade - tes.turma.numAlunos;
        }
        return total;
    }

    // cabeçalho de resumo do ensalamento
    public String relatorioResumoEnsalamento() {
        return String.format(
            "Total de Salas: %d\n" +
            "Total de Turmas: %d\n" +
            "Turmas Alocadas: %d\n" +
            "Espaços Livres: %d",
            salas.size(),
            turmas.size(),
            getTotalTurmasAlocadas(),
            getTotalEspacoLivre()
        );
    }

    // lista as salas e, sob cada uma, as turmas alocadas nela
    public String relatorioTurmasPorSala() {
        StringBuilder sb = new StringBuilder();
        sb.append(relatorioResumoEnsalamento()).append("\n\n");
        for (Sala s : salas) {
            sb.append("--- ").append(s.getDescricao()).append(" ---\n");
            boolean any = false;
            for (TurmaEmSala tes : ensalamento) {
                if (tes.sala.equals(s)) {
                    sb.append(tes.turma.getDescricao()).append("\n\n");
                    any = true;
                }
            }
            // if (!any) {
            //     sb.append("(nenhuma turma alocada)\n\n");
            // }
        }
        return sb.toString();
    }

    // lista as turmas e, ao final de cada bloco de turma, indica sua sala ou "SEM SALA"
    public String relatorioSalasPorTurma() {
        StringBuilder sb = new StringBuilder();
        sb.append(relatorioResumoEnsalamento()).append("\n\n");
        for (Turma t : turmas) {
            sb.append(t.getDescricao()).append("\n");
            Sala s = getSala(t);
            sb.append("Sala: ").append(
                s != null
                ? s.getDescricao()
                : "SEM SALA"
            ).append("\n\n");
        }
        return sb.toString();
    }
}
