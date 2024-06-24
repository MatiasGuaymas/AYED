package Parciales.Grafos.Parcial6;

import Practica5.Ejercicio1.*;
import java.util.*;

public class Parcial {
    public List<String> caminoConPresupuesto(Graph<String> ciudades, String origen, String destino, int montoMaximo) {
        List<String> camino = new LinkedList<String>();
        if(!ciudades.isEmpty()) {
            Vertex v1 = ciudades.search(origen);
            Vertex v2 = ciudades.search(destino);
            if(v1 != null && v2 != null) {
                this.dfs(ciudades, v1, v2, camino, new boolean[ciudades.getSize()], montoMaximo);
            }
        }
        return camino;
    }
    
    private boolean dfs(Graph<String> ciudades, Vertex<String> origen, Vertex<String> destino, List<String> lis, boolean[] marcas, int montoMaximo) {
        boolean encontre = false;
        marcas[origen.getPosition()] = true;
        lis.add(origen.getData());
        if(origen == destino) {
            return true;
        } else {
            Iterator<Edge<String>> it = ciudades.getEdges(origen).iterator();
            while(it.hasNext() && !encontre) {
                Edge<String> ady = it.next();
                Vertex<String> aux = ady.getTarget();
                int peso = ady.getWeight();
                if(!marcas[aux.getPosition()] && peso <= montoMaximo) {
                    encontre = this.dfs(ciudades, aux, destino, lis, marcas, montoMaximo-peso);
                }
            }
        }
        if(!encontre) lis.remove(lis.size()-1);
        marcas[origen.getPosition()] = false;
        return encontre;
    }
    
    public static void main(String args[]) {
        Graph<String> grafo = new AdjListGraph<String>();
        Vertex<String> v1 = grafo.createVertex("Lincoln");
        Vertex<String> v2 = grafo.createVertex("Chascomús");
        Vertex<String> v3 = grafo.createVertex("Cañuelas");
        Vertex<String> v4 = grafo.createVertex("Dolores");
        Vertex<String> v5 = grafo.createVertex("Verónica");
        Vertex<String> v6 = grafo.createVertex("Villa Urquiza");
        Vertex<String> v7 = grafo.createVertex("Ranchos");
        Vertex<String> v8 = grafo.createVertex("Berisso");
        
        grafo.connect(v1, v2, 70);
        grafo.connect(v2, v1, 70);
        grafo.connect(v1, v3, 50);
        grafo.connect(v3, v1, 50);
        grafo.connect(v1, v4, 90);
        grafo.connect(v4, v1, 90);
        grafo.connect(v2, v5, 80);
        grafo.connect(v5, v2, 80);
        grafo.connect(v2, v6, 60);
        grafo.connect(v6, v2, 60);
        grafo.connect(v3, v5, 85);
        grafo.connect(v5, v3, 85);
        grafo.connect(v3, v7, 90);
        grafo.connect(v7, v3, 90);
        grafo.connect(v4, v6, 70);
        grafo.connect(v6, v4, 70);
        grafo.connect(v4, v7, 70);
        grafo.connect(v7, v4, 70);
        grafo.connect(v5, v8, 60);
        grafo.connect(v8, v5, 60);
        grafo.connect(v6, v8, 90);
        grafo.connect(v8, v6, 90);
        grafo.connect(v7, v8, 75);
        grafo.connect(v8, v7, 75);
        
        Parcial p = new Parcial();
        
        System.out.println(p.caminoConPresupuesto(grafo, "Lincoln", "Berisso", 200));
    }
}
