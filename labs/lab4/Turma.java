import java.util.ArrayList;

public class Turma {
    String disciplina;       // Nome da disciplina
    int ano;
    int semestre;
    Professor professor;
    ArrayList<Aluno> alunos;
    
    // Construtor: inicializa a turma e a lista de alunos
    public Turma(String disciplina, int ano, int semestre, Professor professor) {
        this.disciplina = disciplina;
        this.ano = ano;
        this.semestre = semestre;
        this.professor = professor;
        this.alunos = new ArrayList<>();
    }
    
    // 1) Adiciona um novo aluno à turma (método do tipo void)
    public void addAluno(Aluno aluno) {
        alunos.add(aluno);
    }
    
    // 2) Retorna a quantidade atual de alunos
    public int getQuantidadeAlunos() {
        return alunos.size();
    }
    
    // 3) Retorna o aluno da posição indicada (índice zero-based).
    // Se o índice for inválido, retorna null.
    public Aluno getAluno(int indice) {
        if (indice < 0 || indice >= alunos.size()) {
            return null;
        }
        return alunos.get(indice);
    }
    
    // Método adicional: retorna o aluno cuja matrícula seja igual ao valor passado.
    public Aluno getAlunoPorMatricula(int matricula) {
        for (Aluno a : alunos) {
            if (a.matricula == matricula) {
                return a;
            }
        }
        return null;
    }
    
    // 4) Calcula e retorna a média das idades dos alunos
    public double getMediaIdade() {
        if (alunos.isEmpty()) {
            return 0.0;
        }
        double soma = 0.0;
        for (Aluno a : alunos) {
            soma += a.getIdade();
        }
        return soma / alunos.size();
    }
    
    // 5) Retorna uma descrição completa da turma, contendo:
    // - Turma de <disciplina> (<ano>/<semestre>)
    // - Descrição do professor
    // - Lista de alunos (cada um com getDescricao())
    // - Média de idade dos alunos com 2 casas decimais.
    public String getDescricao() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Turma de %s (%d/%d)\n", disciplina, ano, semestre));
        sb.append("Professor: ").append(professor.getDescricao()).append("\n");
        sb.append("Alunos:\n");
        for (int i = 0; i < alunos.size(); i++) {
            sb.append(String.format("%d) %s\n", i + 1, alunos.get(i).getDescricao()));
        }
        sb.append(String.format("Média de idade: %.2f", getMediaIdade()));
        return sb.toString();
    }
}
