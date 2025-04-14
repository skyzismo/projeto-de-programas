public class TurmaMain {
    public static void main(String[] args) {
        // Criação de um professor de exemplo
        Professor prof = new Professor("Dr.", "Hubert J. Farnsworth", 2028);
        
        // Criação de uma turma usando o atributo "disciplina" (nome exato esperado)
        Turma turma = new Turma("Engenharia de Software", 2025, 1, prof);
        
        // Criação de três alunos
        Aluno aluno1 = new Aluno("Egon Spengler", 123, 2000);    // Idade: 2025 - 2000 = 25
        Aluno aluno2 = new Aluno("Ray Stantz", 456, 1998);        // Idade: 2025 - 1998 = 27
        Aluno aluno3 = new Aluno("Peter Venkman", 4361, 1990);     // Idade: 2025 - 1990 = 35
        
        // Adiciona os alunos na turma
        turma.addAluno(aluno1);
        turma.addAluno(aluno2);
        turma.addAluno(aluno3);
        
        // O teste espera que getAluno(2) (terceiro aluno) retorne a descrição de Peter Venkman.
        System.out.println(turma.getAluno(2).getDescricao());
        
        // Se quiser imprimir a descrição completa da turma, descomente a linha abaixo:
        // System.out.println(turma.getDescricao());
    }
}
