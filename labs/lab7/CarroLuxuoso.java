package br.edu.ufam.icomp.lab_encapsulamento;

import java.util.Random;

public class CarroLuxuoso extends Carro implements Localizavel {

    public CarroLuxuoso(String placa) {
        super(placa);
    }

    @Override
    public Posicao getPosicao() {
        Random r = new Random();
        double latMin = -3.160000, latMax = -2.960000;
        double lonMin = -60.120000, lonMax = -59.820000;
        double altMin = 15.0,     altMax = 100.0;

        double latitude  = latMin + (latMax  - latMin)  * r.nextDouble();
        double longitude = lonMin + (lonMax  - lonMin)  * r.nextDouble();
        double altitude  = altMin + (altMax  - altMin)  * r.nextDouble();

        return new Posicao(latitude, longitude, altitude);
    }

    @Override
    public double getErroLocalizacao() {
        return 15.0;
    }
}
