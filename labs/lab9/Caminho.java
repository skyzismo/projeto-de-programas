package br.edu.ufam.icomp.lab_excecoes;

public class Caminho {
    private Coordenada[] caminho;
    private int tamanho;

    /**
     * Constroi um caminho que pode ter até maxTam coordenadas.
     */
    public Caminho(int maxTam) {
        this.caminho = new Coordenada[maxTam];
        this.tamanho = 0;
    }

    /**
     * Retorna o número atual de pontos no caminho.
     */
    public int tamanho() {
        return this.tamanho;
    }

    /**
     * Tenta adicionar uma nova coordenada. 
     * @throws TamanhoMaximoExcedidoException se já tiver atingido a capacidade máxima.
     * @throws DistanciaEntrePontosExcedidaException se a distância ao último ponto for > 15.
     */
    public void addCoordenada(Coordenada coord)
            throws TamanhoMaximoExcedidoException, DistanciaEntrePontosExcedidaException {
        // 1) verifica capacidade
        if (this.tamanho >= caminho.length) {
            throw new TamanhoMaximoExcedidoException();
        }
        // 2) verifica distância ao último ponto (se existir)
        if (this.tamanho > 0) {
            Coordenada anterior = caminho[this.tamanho - 1];
            double dist = anterior.distancia(coord);
            if (dist > 15.0) {
                throw new DistanciaEntrePontosExcedidaException();
            }
        }
        // 3) só adiciona se nenhum dos throws ocorreu
        caminho[this.tamanho++] = coord;
    }

    /**
     * Remove todos os pontos do caminho, voltando ao estado inicial.
     */
    public void reset() {
        // opcional: limpar referências
        for (int i = 0; i < this.tamanho; i++) {
            caminho[i] = null;
        }
        this.tamanho = 0;
    }

    /**
     * Gera a saída textual conforme o exemplo do enunciado.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Dados do caminho:\n");
        sb.append("- Quantidade de pontos: ").append(this.tamanho).append("\n");
        sb.append("- Pontos:\n");
        for (int i = 0; i < this.tamanho; i++) {
            Coordenada c = caminho[i];
            sb.append("-> ")
              .append(c.getPosX())
              .append(", ")
              .append(c.getPosY())
              .append("\n");
        }
        return sb.toString();
    }
}
