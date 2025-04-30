package br.edu.ufam.icomp.lab_encapsulamento;

public interface Localizavel {
    /**
     * Retorna a posição estimada deste objeto.
     * @return um objeto Posicao com latitude, longitude e altitude
     */
    Posicao getPosicao();

    /**
     * Retorna o erro de localização em metros.
     * @return valor do erro de localização
     */
    double getErroLocalizacao();
}
