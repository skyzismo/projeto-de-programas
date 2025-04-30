package br.edu.ufam.icomp.lab_encapsulamento;

public class Carro {
    protected String placa;

    // Construtor
    public Carro(String placa) {
        this.placa = placa;
    }

    // Setter para placa
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    // Getter para placa
    public String getPlaca() {
        return placa;
    }
}
