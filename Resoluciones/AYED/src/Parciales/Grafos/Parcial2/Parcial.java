package Parciales.Grafos.Parcial2;

import Practica5.Ejercicio1.*;
import java.util.*;

public class Parcial {
    public int resolver(Graph<Recinto> sitios, int tiempo) {
        int cant = 0;
        if(!sitios.isEmpty()) {
            Vertex<Recinto> origen = this.buscar(sitios);
            if(origen != null) {
                int tiempoOrigen = origen.getData().getMinutos();
                if(tiempoOrigen <= tiempo) {
                    cant = this.dfs(origen, sitios, tiempo - tiempoOrigen, 1, Integer.MIN_VALUE, new boolean[sitios.getSize()]);
                }
            }
        }
        return cant;
    }
    
    private Vertex<Recinto> buscar(Graph<Recinto> sitios) {
        Vertex<Recinto> rec = null;
        Iterator<Vertex<Recinto>> it = sitios.getVertices().iterator();
        while(it.hasNext() && rec == null) {
            Vertex<Recinto> aux = it.next();
            if(aux.getData().getNombre().equals("Entrada")) {
                rec = aux;
            }
        }
        return rec;
    }
    
    private int dfs(Vertex<Recinto> origen, Graph<Recinto> grafo, int tiempo, int cant, int max, boolean[] marcas) {
        marcas[origen.getPosition()] = true;
        for(Edge<Recinto> ady : grafo.getEdges(origen)) {
            Vertex<Recinto> destino = ady.getTarget();
            int peso = destino.getData().getMinutos() + ady.getWeight();
            int j = destino.getPosition();
            if(!marcas[j] && peso <= tiempo) {
                max = this.dfs(destino, grafo, tiempo - peso, cant+1, max, marcas);
            }
        }
        marcas[origen.getPosition()] = false;
        max = Math.max(max, cant);
        return max;
    }
    
    public static void main(String args[]) {
        Graph<Recinto> grafo = new AdjListGraph<Recinto>();
        Vertex<Recinto> Entrada = grafo.createVertex(new Recinto("Entrada", 15));
        Vertex<Recinto> Cebras = grafo.createVertex(new Recinto("Cebras", 10));
        Vertex<Recinto> Tigres = grafo.createVertex(new Recinto("Tigres", 10));
        Vertex<Recinto> Flamencos = grafo.createVertex(new Recinto("Flamencos", 10));
        Vertex<Recinto> Murcielagos = grafo.createVertex(new Recinto("Murciélagos", 20));
        Vertex<Recinto> Wallabies = grafo.createVertex(new Recinto("Wallabies", 30));
        Vertex<Recinto> Tortugas = grafo.createVertex(new Recinto("Tortugas", 10));
        Vertex<Recinto> Pumas = grafo.createVertex(new Recinto("Pumas", 10));
        
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
        
        Parcial p = new Parcial();
        
        System.out.println(p.resolver(grafo, 100));
        System.out.println(p.resolver(grafo, 30));
    }
}
