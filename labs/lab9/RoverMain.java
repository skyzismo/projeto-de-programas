package br.edu.ufam.icomp.lab_excecoes;

public class RoverMain {
    public static void main(String[] args) {
        // Vamos supor que o controle permitiu até 10 coordenadas
        Caminho caminho = new Caminho(10);

        try {
            // Adicionamos várias coordenadas válidas
            caminho.addCoordenada(new Coordenada(32, 30, 0));
            caminho.addCoordenada(new Coordenada(35, 40, 1));
            caminho.addCoordenada(new Coordenada(38, 30, 2));
            caminho.addCoordenada(new Coordenada(30, 36, 3));
            caminho.addCoordenada(new Coordenada(40, 36, 4));
            caminho.addCoordenada(new Coordenada(33, 31, 5));

            // Uncomment abaixo para forçar uma exceção de distância (>15)
            // caminho.addCoordenada(new Coordenada(0, 0, 6));

            // Uncomment abaixo para forçar uma exceção de tamanho excedido
            // for (int i = 0; i < 10; i++) {
            //     caminho.addCoordenada(new Coordenada(32+i, 30, i+6));
            // }
        }
        catch (RoverException ex) {
            // Imprime a mensagem de erro da exceção (getMessage())
            System.out.println(ex.getMessage());
            // Reseta o caminho para não usar um caminho inválido
            caminho.reset();
        }

        // Sempre imprimimos o estado final do caminho (pode estar vazio se houve erro)
        System.out.println(caminho);
    }
}
