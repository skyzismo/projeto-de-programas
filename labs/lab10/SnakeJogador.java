import java.awt.Point;
import java.util.*;

/**
 * Implementação de um jogador automático para o Jogo Snake.
 * A estratégia utilizada é um algoritmo de busca de caminho (Breadth-First Search - BFS)
 * para encontrar o caminho mais curto e seguro até a comida.
 *
 * Estratégia:
 * 1.  Tenta encontrar o caminho mais curto até a comida usando BFS.
 * 2.  Se a comida estiver inalcançável, a cobra tenta seguir a própria cauda como
 * uma tática de sobrevivência para não ficar encurralada.
 * 3.  Se nenhum caminho for possível, move-se para qualquer direção livre como último recurso.
 *
 * @author Horácio
 */
public class SnakeJogador {
    private Snake jogo;

    /**
     * Classe interna para representar um nó no nosso algoritmo de busca.
     * Cada nó armazena sua posição e uma referência ao nó pai, permitindo
     * reconstruir o caminho quando o destino é alcançado.
     */
    private static class No {
        Point ponto;
        No pai;

        public No(Point ponto, No pai) {
            this.ponto = ponto;
            this.pai = pai;
        }
    }

    /**
     * Cria um novo jogador para o jogo passado como parâmetro.
     * @param jogo jogo snake.
     */
    public SnakeJogador(Snake jogo) {
        this.jogo = jogo;
    }

    /**
     * Executado a cada 'tick' para decidir a próxima direção da cobra.
     *
     * @return a próxima direção da cobra ('C'ima, 'D'ireita, 'B'aixo, 'E'squerda).
     */
    public char getDirecao() {
        Point cabeca = jogo.getSegmentos().getFirst();
        Point comida = jogo.getComida();

        // 1. Tenta encontrar o caminho para a comida
        List<Point> caminhoParaComida = encontrarCaminho(cabeca, comida);

        if (!caminhoParaComida.isEmpty()) {
            return getDirecaoDeMovimento(cabeca, caminhoParaComida.get(0));
        }

        // 2. Se não há caminho para a comida, tenta seguir a própria cauda para sobreviver
        Point cauda = jogo.getSegmentos().getLast();
        List<Point> caminhoParaCauda = encontrarCaminho(cabeca, cauda);

        if (!caminhoParaCauda.isEmpty()) {
            return getDirecaoDeMovimento(cabeca, caminhoParaCauda.get(0));
        }

        // 3. Como último recurso, move-se para qualquer direção segura (lógica original)
        return getMovimentoAleatorioSeguro();
    }

    /**
     * Encontra o caminho mais curto de um ponto inicial a um final usando BFS.
     *
     * @param inicio O ponto de partida (cabeça da cobra).
     * @param fim O ponto de destino (comida ou cauda).
     * @return Uma lista de pontos representando o caminho, ou uma lista vazia se não houver caminho.
     */
    private List<Point> encontrarCaminho(Point inicio, Point fim) {
        Queue<No> fila = new LinkedList<>();
        Set<Point> visitados = new HashSet<>(jogo.getSegmentos());

        // Otimização: A cauda da cobra se moverá, então o espaço dela estará livre.
        // Removemos a cauda da lista de pontos visitados para que o BFS possa usá-la como caminho.
        Point cauda = jogo.getSegmentos().getLast();
        if (visitados.contains(cauda)) {
            visitados.remove(cauda);
        }

        fila.add(new No(inicio, null));
        visitados.add(inicio);

        No noDestino = null;

        while (!fila.isEmpty()) {
            No atual = fila.poll();

            if (atual.ponto.equals(fim)) {
                noDestino = atual;
                break; // Caminho encontrado
            }

            // Define as direções de movimento: Direita, Esquerda, Baixo, Cima
            int[] dx = {1, -1, 0, 0};
            int[] dy = {0, 0, 1, -1};

            for (int i = 0; i < 4; i++) {
                Point proximoPonto = new Point(atual.ponto.x + dx[i], atual.ponto.y + dy[i]);

                if (isMovimentoSeguro(proximoPonto, visitados)) {
                    visitados.add(proximoPonto);
                    fila.add(new No(proximoPonto, atual));
                }
            }
        }

        // Reconstrói o caminho do fim para o início
        List<Point> caminho = new ArrayList<>();
        if (noDestino != null) {
            No atual = noDestino;
            while (atual.pai != null) {
                caminho.add(atual.ponto);
                atual = atual.pai;
            }
            Collections.reverse(caminho);
        }
        return caminho;
    }
    
    /**
     * Verifica se um movimento para um determinado ponto é seguro.
     * @param p O ponto para verificar.
     * @param visitados O conjunto de pontos já visitados na busca atual.
     * @return true se o movimento for seguro, false caso contrário.
     */
    private boolean isMovimentoSeguro(Point p, Set<Point> visitados) {
        // Verifica se está dentro dos limites da arena
        if (p.x < 0 || p.x >= jogo.getLargura() || p.y < 0 || p.y >= jogo.getAltura()) {
            return false;
        }
        // Verifica se já foi visitado nesta busca ou se é parte do corpo da cobra
        if (visitados.contains(p)) {
            return false;
        }
        return true;
    }

    /**
     * Calcula a direção ('C', 'D', 'B', 'E') a partir de um ponto de origem para um de destino.
     * @param origem O ponto atual (cabeça da cobra).
     * @param destino O próximo ponto no caminho.
     * @return O caractere da direção.
     */
    private char getDirecaoDeMovimento(Point origem, Point destino) {
        if (destino.y < origem.y) return 'C';
        if (destino.y > origem.y) return 'B';
        if (destino.x > origem.x) return 'D';
        if (destino.x < origem.x) return 'E';
        return 'N'; // Não deve acontecer
    }

    /**
     * Fallback para um movimento aleatório, caso nenhuma estratégia funcione.
     * @return Uma direção aleatória que não causa colisão imediata.
     */
    private char getMovimentoAleatorioSeguro() {
        ArrayList<Character> possiveisDirecoes = new ArrayList<>(4);
        Point cabeca = jogo.getSegmentos().getFirst();

        if (jogo.isCelulaLivre(cabeca.x, cabeca.y - 1)) possiveisDirecoes.add('C');
        if (jogo.isCelulaLivre(cabeca.x + 1, cabeca.y)) possiveisDirecoes.add('D');
        if (jogo.isCelulaLivre(cabeca.x, cabeca.y + 1)) possiveisDirecoes.add('B');
        if (jogo.isCelulaLivre(cabeca.x - 1, cabeca.y)) possiveisDirecoes.add('E');

        if (possiveisDirecoes.isEmpty()) return 'N'; // Preso

        return possiveisDirecoes.get(new Random().nextInt(possiveisDirecoes.size()));
    }
}