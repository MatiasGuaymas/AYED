package Parciales.Grafos.Parcial13;

import Practica5.Ejercicio1.*;
import Practica1.Ejercicio8.Queue;
import java.util.*;

public class Parcial {
    public Objeto nivelPopularidad(Graph<String> red, String usuario, int distancia, int umbral) {
        Objeto obj = null;
        if(!red.isEmpty()) {
            Vertex<String> origen = red.search(usuario);
            if(origen!=null) {
                obj = new Objeto();
                this.bfs(red, origen, obj, distancia); 
                obj.setPopular(obj.getCant() >= umbral);
            } 
        }
        return obj;
    }
    
    private void bfs(Graph<String> red, Vertex<String> origen, Objeto obj, int distancia) {
        int cant = 0;
        int grado = 0;
        Queue<Vertex<String>> cola = new Queue<Vertex<String>>();
        boolean [] marcas = new boolean[red.getSize()];
        marcas[origen.getPosition()] = true;
        cola.enqueue(origen);
        cola.enqueue(null);
        while(!cola.isEmpty() && grado < distancia) {
            Vertex<String> aux = cola.dequeue();
            if(aux!=null) {
                for(Edge<String> ady : red.getEdges(aux)) {
                    Vertex<String> v = ady.getTarget();
                    int j = v.getPosition();
                    if(!marcas[j]) {
                        marcas[j] = true;
                        if(grado + 1 == distancia) cant++;
                        else cola.enqueue(v);
                    }
                }
            } else if (!cola.isEmpty()) {
                grado++;
                cola.enqueue(null);
            }
        }
        obj.setCant(cant);
    } 
    
    public static void main(String args[]) {
        Graph<String> grafo = new AdjListGraph<String>();
        Vertex<String> v1 = grafo.createVertex("Lionel");
        Vertex<String> v2 = grafo.createVertex("Rodrigo");
        Vertex<String> v3 = grafo.createVertex("Ángel");
        Vertex<String> v4 = grafo.createVertex("Emiliano");
        Vertex<String> v5 = grafo.createVertex("Julián");
        Vertex<String> v6 = grafo.createVertex("Diego");
        Vertex<String> v7 = grafo.createVertex("Lautaro");
        Vertex<String> v8 = grafo.createVertex("Enzo");
        
        grafo.connect(v1, v2);
        grafo.connect(v2, v1);
        
        grafo.connect(v1, v3);
        grafo.connect(v3, v1);
        
        grafo.connect(v2, v4);
        grafo.connect(v4, v2);
        
        grafo.connect(v2, v5);
        grafo.connect(v5, v2);
        
        grafo.connect(v3, v5);
        grafo.connect(v5, v3);
        
        grafo.connect(v3, v6);
        grafo.connect(v6, v3);
        
        grafo.connect(v6, v7);
        grafo.connect(v7, v6);
        
        grafo.connect(v5, v7);
        grafo.connect(v7, v5);
        
        grafo.connect(v6, v8);
        grafo.connect(v8, v6);
        
        grafo.connect(v4, v8);
        grafo.connect(v8, v4);
        
        grafo.connect(v4, v7);
        grafo.connect(v7, v4);
        
        Parcial p = new Parcial();
        
        System.out.println(p.nivelPopularidad(grafo, "Lionel", 2, 3));
        System.out.println(p.nivelPopularidad(grafo, "Lionel", 1, 3));
        System.out.println(p.nivelPopularidad(grafo, "Matias", 1, 0));
    }
}

    


