public class Aluno {
    // Atributos que o enunciado/teste espera
    String nome;
    int matricula;
    int anoNascimento;
    
    // Construtor padrão
    public Aluno() {
        this("Indefinido", 0, 2000); 
    }

    // Construtor com parâmetros
    public Aluno(String nome, int matricula, int anoNascimento) {
        this.nome = nome;
        this.matricula = matricula;
        this.anoNascimento = anoNascimento;
    }

    // Método que retorna a idade do aluno
    // Aqui presumimos o ano atual como 2023, 
    // mas você pode parametrizar se for o caso.
    public int getIdade() {
        return 2025 - this.anoNascimento;
    }

    // Exemplo de método de descrição
    // (caso a correção não exija exatamente esse formato, fique à vontade para ajustar)
    public String getDescricao() {
        // Jebediah Kerman (mat=30176, idade=30)    
        return String.format("%s (mat=%d, idade=%d)",
                             nome, matricula, getIdade());
    }
}
