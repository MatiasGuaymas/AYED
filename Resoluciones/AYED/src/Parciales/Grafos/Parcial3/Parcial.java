package Parciales.Grafos.Parcial3;

import Practica1.Ejercicio8.Queue;
import Practica5.Ejercicio1.*;
import java.util.*;


public class Parcial {
    public String resolver(Graph<Ciudad> sitios, int tiempo) {
        boolean ok = false;
        if(!sitios.isEmpty()) {
            Vertex<Ciudad> v = this.buscar(sitios);
            if(v != null && v.getData().getTiempo() <= tiempo) {
                ok = this.bfs(sitios, v, tiempo - v.getData().getTiempo(), new boolean[sitios.getSize()]);
            }
        }
        return ok ? "Alcanzable" : "No Alcanzable";
    } 
    
    private Vertex<Ciudad> buscar(Graph<Ciudad> sitios) {
        List<Vertex<Ciudad>> lis = sitios.getVertices();
        Iterator<Vertex<Ciudad>> it = lis.iterator();
        Vertex<Ciudad> entrada = null;
        while(it.hasNext() && entrada == null) {
            Vertex<Ciudad> aux = it.next();
            if(aux.getData().getRecinto().equals("Entrada")) {
                entrada = aux;
            }
        }
        return entrada;
    }
    
    private boolean bfs(Graph<Ciudad> sitios, Vertex<Ciudad> origen, int tiempo, boolean[] marcas) {
        Queue<Vertex<Ciudad>> q = new Queue<Vertex<Ciudad>>();
        q.enqueue(origen);
        marcas[origen.getPosition()] = true;
        while (!q.isEmpty() && tiempo >= 0) {
            Vertex<Ciudad> aux = q.dequeue();
            for (Edge<Ciudad> ady: sitios.getEdges(aux)) { //Deberia usar un for each y no un iterator, porque podria no llegar por un camino a algun lugar, pero si mediante otro. No seria correcto frenar el bucle por dicho motivo.
                Vertex<Ciudad> destino = ady.getTarget();
                int tiempoDestinoCamino = ady.getWeight() + destino.getData().getTiempo();
                if(!marcas[destino.getPosition()] && tiempo - tiempoDestinoCamino >= 0) {
                    marcas[destino.getPosition()] = true;
                    q.enqueue(destino);
                    tiempo-= tiempoDestinoCamino;
                }
            }
        }
        boolean cumple = true;
        int i = 0;
        while(i < marcas.length && cumple) {
            if(!marcas[i]) cumple = false;
            i++;
        } 
        return cumple;
    }
    
    public static void main(String args[]) {
        Graph<Ciudad> grafo = new AdjListGraph<Ciudad>();
        Vertex<Ciudad> Entrada = grafo.createVertex(new Ciudad("Entrada", 15));
        Vertex<Ciudad> Cebras = grafo.createVertex(new Ciudad("Cebras", 10));
        Vertex<Ciudad> Tigres = grafo.createVertex(new Ciudad("Tigres", 10));
        Vertex<Ciudad> Flamencos = grafo.createVertex(new Ciudad("Flamencos", 10));
        Vertex<Ciudad> Murcielagos = grafo.createVertex(new Ciudad("Murciélagos", 20));
        Vertex<Ciudad> Wallabies = grafo.createVertex(new Ciudad("Wallabies", 30));
        Vertex<Ciudad> Tortugas = grafo.createVertex(new Ciudad("Tortugas", 10));
        Vertex<Ciudad> Pumas = grafo.createVertex(new Ciudad("Pumas", 10));
        
        grafo.connect(Entrada, Cebras, 10);
        grafo.connect(Cebras, Entrada, 10);
        grafo.connect(Entrada, Tigres, 10);
        grafo.connect(Tigres, Entrada, 10);
        grafo.connect(Entrada, Murcielagos, 20);
        grafo.connect(Murcielagos, Entrada, 20);
        grafo.connect(Entrada, Flamencos, 25);
        grafo.connect(Flamencos, Entrada, 25);
        
        grafo.connect(Tigres, Cebras, 8);
        grafo.connect(Cebras, Tigres, 8);
        grafo.connect(Cebras, Tortugas, 10);
        grafo.connect(Tortugas, Cebras, 10);
        grafo.connect(Flamencos, Murcielagos, 25);
        grafo.connect(Murcielagos, Flamencos, 25);
        grafo.connect(Murcielagos, Wallabies, 10);
        grafo.connect(Wallabies, Murcielagos, 10);
        grafo.connect(Wallabies, Tortugas, 10);
        grafo.connect(Tortugas, Wallabies, 10);
        grafo.connect(Tortugas, Pumas, 15);
        grafo.connect(Pumas, Tortugas, 15);
        grafo.connect(Pumas, Wallabies, 2);
        grafo.connect(Wallabies, Pumas, 2);
        
        Parcial p = new Parcial();
        
        System.out.println(p.resolver(grafo, 220));
        System.out.println(p.resolver(grafo, 205));
        System.out.println(p.resolver(grafo, 100));
    }
}
