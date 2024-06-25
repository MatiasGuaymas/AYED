package Practica5.Ejercicio6;

import Practica5.Ejercicio1.*;

public class Vertices {
    private Vertex<String> verOrigen;
    private Vertex<String> verDestino;
    
    public Vertices(Vertex<String> ver1, Vertex<String> ver2) {
        this.verOrigen = ver1;
        this.verDestino = ver2;
    }

    public Vertex<String> getVerOrigen() {
        return verOrigen;
    }

    public void setVerOrigen(Vertex<String> verOrigen) {
        this.verOrigen = verOrigen;
    }

    public Vertex<String> getVerDestino() {
        return verDestino;
    }

    public void setVerDestino(Vertex<String> verDestino) {
        this.verDestino = verDestino;
    }
    
}
