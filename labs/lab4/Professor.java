public class Professor {
    String titulacao;
    String nome;
    int matricula;

    // Construtor padrão
    public Professor() {
        this("Indefinida", "Indefinido", 0);
    }

    // Construtor com parâmetros
    public Professor(String titulacao, String nome, int matricula) {
        this.titulacao = titulacao;
        this.nome = nome;
        this.matricula = matricula;
    }

    // Método que retorna a descrição completa do professor
    // Ex.: "Dr. Hubert J. Farnsworth - mat: 2028"
    public String getDescricao() {
        return String.format("Prof. %s %s - mat %d", titulacao, nome, matricula);
    }
}
