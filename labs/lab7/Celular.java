package br.edu.ufam.icomp.lab_encapsulamento;

import java.util.Random;

public class Celular implements Localizavel {
    private int codPais;
    private int codArea;
    private int numero;

    public Celular(int codPais, int codArea, int numero) {
        setCodPais(codPais);
        setCodArea(codArea);
        setNumero(numero);
    }

    public final void setCodPais(int codPais) {
        if (codPais >= 1 && codPais <= 1999) {
            this.codPais = codPais;
        } else {
            this.codPais = -1;
        }
    }
    public int getCodPais() {
        return codPais;
    }

    public final void setCodArea(int codArea) {
        if (codArea >= 10 && codArea <= 99) {
            this.codArea = codArea;
        } else {
            this.codArea = -1;
        }
    }
    public int getCodArea() {
        return codArea;
    }

    public final void setNumero(int numero) {
        // ACEITA agora de  10.000.000  até  999.999.999 
        if (numero >= 10_000_000 && numero <= 999_999_999) {
            this.numero = numero;
        } else {
            this.numero = -1;
        }
    }
    public int getNumero() {
        return numero;
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
        return 50.0;
    }
}
