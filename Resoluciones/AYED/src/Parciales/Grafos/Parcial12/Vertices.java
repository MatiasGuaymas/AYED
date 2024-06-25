package Parciales.Grafos.Parcial12;

import Parciales.Grafos.Parcial10.*;
import Practica5.Ejercicio1.*;

public class Vertices {
    private Vertex<Ciudad> origen;
    private Vertex<Ciudad> destino;
    
    public Vertices(Vertex<Ciudad> origen, Vertex<Ciudad> destino) {
        this.origen = origen;
        this.destino = destino;
    }

    public Vertex<Ciudad> getOrigen() {
        return origen;
    }

    public void setOrigen(Vertex<Ciudad> origen) {
        this.origen = origen;
    }

    public Vertex<Ciudad> getDestino() {
        return destino;
    }

    public void setDestino(Vertex<Ciudad> destino) {
        this.destino = destino;
    }
    
    
}
