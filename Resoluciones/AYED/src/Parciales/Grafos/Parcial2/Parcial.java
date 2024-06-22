package Parciales.Grafos.Parcial2;

import Practica5.Ejercicio1.*;
import java.util.*;

public class Parcial {
    public int resolver(Graph<Ciudad> sitios, int tiempo) {
        int cant = 0;
        if(!sitios.isEmpty()) {
            Vertex<Ciudad> v = this.buscar(sitios);
            if(v != null && v.getData().getTiempo() <= tiempo) {
                cant = this.dfs(sitios, v, tiempo - v.getData().getTiempo(), 1, new boolean[sitios.getSize()]);
            }
        } 
        return cant;
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
    
    private int dfs(Graph<Ciudad> sitios, Vertex<Ciudad> origen, int tiempo, int cantVisitados, boolean[] marcas) {
        marcas[origen.getPosition()] = true;
        int maxVisitados = cantVisitados;
        for(Edge<Ciudad> a: sitios.getEdges(origen)) {
            Vertex<Ciudad> destino = a.getTarget();
            int tiempoDestinoLugar = a.getWeight() + destino.getData().getTiempo();
            if(!marcas[destino.getPosition()] && tiempoDestinoLugar <= tiempo) {
                maxVisitados = Math.max(maxVisitados , this.dfs(sitios, destino, tiempo - tiempoDestinoLugar, cantVisitados+1, marcas));
            }
        }
        marcas[origen.getPosition()] = false;
        return maxVisitados;
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
        
        Parcial p = new Parcial();
        
        System.out.println(p.resolver(grafo, 100));
        System.out.println(p.resolver(grafo, 30));
    }
}
