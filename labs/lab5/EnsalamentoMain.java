public class EnsalamentoMain {
    public static void main(String[] args) {
        // 1) Criar algumas salas
        Sala s1 = new Sala(6, 101, 50, true);
        Sala s2 = new Sala(6, 102, 100, true);
        Sala s3 = new Sala(6, 203, 50, false);
        Sala s4 = new Sala(6, 204, 100, false);

        // 2) Criar algumas turmas e configurar seus horários
        Turma t1 = new Turma("Técnicas de Programação", "Horácio Fernandes", 50, false);
        t1.addHorario(5);  // terça 14hs
        t1.addHorario(19); // quinta 14hs
        t1.addHorario(33); // sexta  14hs

        Turma t2 = new Turma("Laboratório de Programação C", "Edson Nascimento", 25, true);
        t2.addHorario(1);  // segunda  8hs
        t2.addHorario(15); // quarta   8hs
        t2.addHorario(29); // sexta    8hs

        Turma t3 = new Turma("Algoritmos e Estrutura de Dados I", "Edleno Silva", 60, false);
        t3.addHorario(1);  // segunda  8hs
        t3.addHorario(15); // quarta   8hs
        t3.addHorario(29); // sexta    8hs

        Turma t4 = new Turma("Redes de Computadores", "Edjair Souza", 70, true);
        t4.addHorario(2);  // segunda 10hs
        t4.addHorario(16); // quarta  10hs

        // 3) Criar o sistema de ensalamento
        Ensalamento ens = new Ensalamento();
        ens.addSala(s1);
        ens.addSala(s2);
        ens.addSala(s3);
        ens.addSala(s4);

        ens.addTurma(t1);
        ens.addTurma(t2);
        ens.addTurma(t3);
        ens.addTurma(t4);

        // 4) Tentar alocar todas as turmas em salas
        ens.alocarTodas();

        // 5) Imprimir relatórios
        System.out.println( ens.relatorioResumoEnsalamento() );
        System.out.println(); // linha em branco
        System.out.println( ens.relatorioTurmasPorSala() );
        System.out.println(); // linha em branco
        System.out.println( ens.relatorioSalasPorTurma() );
    }
}
