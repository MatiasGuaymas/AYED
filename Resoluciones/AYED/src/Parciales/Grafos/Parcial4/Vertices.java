package Parciales.Grafos.Parcial4;

import Practica5.Ejercicio1.*;

public class Vertices {
    private Vertex<String> origen;
    private Vertex<String> destino;
    
    public Vertices(Vertex<String> v1, Vertex<String> v2) {
        this.origen = v1;
        this.destino = v2;
    }

    public Vertex<String> getOrigen() {
        return origen;
    }

    public void setOrigen(Vertex<String> origen) {
        this.origen = origen;
    }

    public Vertex<String> getDestino() {
        return destino;
    }

    public void setDestino(Vertex<String> destino) {
        this.destino = destino;
    }
    
}
