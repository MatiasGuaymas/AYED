package Parciales.Grafos.Parcial11;

import Practica5.Ejercicio1.*;

public class Vertices {
    private Vertex<Calle> casa;
    private Vertex<Calle> municipalidad;
    
    public Vertices(Vertex<Calle> casa, Vertex<Calle> municipalidad) {
        this.casa = casa;
        this.municipalidad = municipalidad;
    }

    public Vertex<Calle> getCasa() {
        return casa;
    }

    public void setCasa(Vertex<Calle> casa) {
        this.casa = casa;
    }

    public Vertex<Calle> getMunicipalidad() {
        return municipalidad;
    }

    public void setMunicipalidad(Vertex<Calle> municipalidad) {
        this.municipalidad = municipalidad;
    }
    
    
}
