package br.edu.ufam.icomp.lab_encapsulamento;

public class Posicao {
    private double latitude;
    private double longitude;
    private double altitude;

    // Construtor
    public Posicao(double latitude, double longitude, double altitude) {
        this.latitude  = latitude;
        this.longitude = longitude;
        this.altitude  = altitude;
    }

    // getters e setters
    public double getLatitude() {
        return latitude;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getAltitude() {
        return altitude;
    }
    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    // toString no formato:
    // "Posição: lat, long, alt"
    @Override
    public String toString() {
        return "Posição: "
            + latitude
            + ", "
            + longitude
            + ", "
            + altitude;
    }
}
