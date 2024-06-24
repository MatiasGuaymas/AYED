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
                ok = this.dfs(sitios, v, new Objeto(tiempo - v.getData().getTiempo()), new boolean[sitios.getSize()], sitios.getSize());
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
    
    private boolean dfs(Graph<Ciudad> sitios, Vertex<Ciudad> origen, Objeto obj, boolean[] marcas, int max) {
        marcas[origen.getPosition()] = true;
        boolean termine = false;
        if(obj.getCantNodos() == max) {
            return true;
        } else {
            Iterator<Edge<Ciudad>> it = sitios.getEdges(origen).iterator();
            while(it.hasNext() && !termine) {
                Edge<Ciudad> ady = it.next();
                Vertex<Ciudad> destino = ady.getTarget();
                int peso = ady.getWeight() + destino.getData().getTiempo();
                if(!marcas[destino.getPosition()] && peso <= obj.getTiempo()) {
                    obj.actualizar(peso);
                    termine = this.dfs(sitios, destino, obj, marcas, max);
                }
            }
        }
        return termine;
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
        grafo.connect(Entrada, Tigres, 15);
        grafo.connect(Tigres, Entrada, 15);
        grafo.connect(Entrada, Murcielagos, 20);
        grafo.connect(Murcielagos, Entrada, 20);
        grafo.connect(Entrada, Flamencos, 25);
        grafo.connect(Flamencos, Entrada, 25);
        
        grafo.connect(Tigres, Cebras, 8);
        grafo.connect(Cebras, Tigres, 8);
        grafo.connect(Cebras, Tortugas, 5);
        grafo.connect(Tortugas, Cebras, 5);
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
        System.out.println(p.resolver(grafo, 195));
        System.out.println(p.resolver(grafo, 100));
    }
}
