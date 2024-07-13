package Parciales.Grafos.Parcial4;

import java.util.*;
import Practica5.Ejercicio1.*;

public class Parcial {
    public List<Camino> resolver(Graph<String> sitios, String origen, String destino, List<String> evitarPasarPor) {
        List<Camino> lis = new LinkedList<Camino>();
        if(!sitios.isEmpty()) {
            Vertices v = this.buscar(sitios, origen, destino);
            if(v != null) {
                boolean [] marcas = new boolean[sitios.getSize()];
                this.marcarRestringidos(sitios, marcas, evitarPasarPor);
                this.dfs(sitios, lis, new LinkedList<String>(), v.getOrigen(), v.getDestino(), 0, marcas);
            }
        }
        return lis;
    }
    
    private void marcarRestringidos(Graph<String>sitios, boolean[] marcas, List<String> evitar) {
        for(String l: evitar) {
            Vertex<String> v = sitios.search(l);
            if(v!=null) {
                marcas[v.getPosition()] = true;
            }
        }
    }
    
    private Vertices buscar(Graph<String> sitios, String origen, String destino) {
        Vertex<String> v1 = null;
        Vertex<String> v2 = null;
        Iterator<Vertex<String>> it = sitios.getVertices().iterator();
        while(it.hasNext() && (v1 == null || v2 == null)) {
            Vertex<String> aux = it.next();
            if(aux.getData().equals(origen)) {
                v1 = aux;
            } else if (aux.getData().equals(destino)){
                v2 = aux;
            }
        }
        return (v1 != null && v2 != null) ? new Vertices(v1, v2) : null;
    }
    
    private void dfs(Graph<String> sitios, List<Camino> lis, List<String> lisActual, Vertex<String> origen, Vertex<String> destino, int cuadras, boolean[] marcas) {
        marcas[origen.getPosition()] = true;
        lisActual.add(origen.getData());
        if(origen == destino) {
            lis.add(new Camino(new LinkedList<String>(lisActual), cuadras));
        } else {
            for(Edge<String> ady: sitios.getEdges(origen)) {
                Vertex<String> des = ady.getTarget();
                int j = des.getPosition();
                if(!marcas[j]) {
                    this.dfs(sitios, lis, lisActual, des, destino, cuadras+ady.getWeight(), marcas);
                }
            }
        }
        lisActual.remove(lisActual.size()-1);
        marcas[origen.getPosition()] = false;
    }
    
    public static void main(String args[]) {
        Graph<String> grafo = new AdjListGraph();
        Vertex v1 = grafo.createVertex("Estadio Diego Armando Maradona");
        Vertex v2 = grafo.createVertex("Legislatura");
        Vertex v3 = grafo.createVertex("Coliseo Podestá");
        Vertex v4 = grafo.createVertex("MACLA");
        Vertex v5 = grafo.createVertex("Catedral La Plata");
        Vertex v6 = grafo.createVertex("Palacio Campodónico");
        Vertex v7 = grafo.createVertex("Rectorado UNLP");
        Vertex v8 = grafo.createVertex("Museo UNLP");
        
        grafo.connect(v1, v2, 25);
        grafo.connect(v2, v1, 25);
        grafo.connect(v1, v3, 20);
        grafo.connect(v3, v1, 20);
        grafo.connect(v1, v4, 35);
        grafo.connect(v4, v1, 35);
        grafo.connect(v1, v5, 40);
        grafo.connect(v5, v1, 40);
        grafo.connect(v2, v3, 25);
        grafo.connect(v3, v2, 25);
        grafo.connect(v4, v5, 8);
        grafo.connect(v5, v4, 8);
        grafo.connect(v5, v7, 5);
        grafo.connect(v7, v5, 5);
        grafo.connect(v3, v6, 10);
        grafo.connect(v6, v3, 10);
        grafo.connect(v6, v7, 30);
        grafo.connect(v7, v6, 30);
        grafo.connect(v7, v8, 15);
        grafo.connect(v8, v7, 15);
        
        List<String> evitarPasarPor = new LinkedList<String>();
        evitarPasarPor.add("Legislatura");
        evitarPasarPor.add("MACLA");
        
        Parcial p = new Parcial();
        List<Camino> lis = p.resolver(grafo, "Estadio Diego Armando Maradona", "Palacio Campodónico", evitarPasarPor);
        
        for(Camino aux: lis) {
            System.out.println(aux);
        }
    }
}
