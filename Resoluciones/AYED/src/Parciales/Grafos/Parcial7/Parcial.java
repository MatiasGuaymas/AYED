package Parciales.Grafos.Parcial7;

import Practica5.Ejercicio1.*;
import java.util.*;

public class Parcial {
    public List<String> recorrido(Graph<String> grafo, int cantLocalidades, int cantNafta, List<String> localidadesExceptuadas) {
        List<String> camino = new LinkedList<String>();
        if(!grafo.isEmpty()) {
            Vertex<String> mendoza = grafo.search("Mendoza");
            if(mendoza != null) {
                boolean [] marcas = new boolean [grafo.getSize()];
                this.marcar(grafo, marcas, localidadesExceptuadas);
                this.dfs(grafo, mendoza, cantLocalidades, cantNafta, 1, camino, marcas);
            }
        }
        return camino;
    }
    
    private void marcar(Graph<String> grafo, boolean[] marcas, List<String> localidades) {
        for(String l: localidades) {
            Vertex<String> v = grafo.search(l);
            if(v != null) {
                marcas[v.getPosition()] = true;
            }
        }
    }
    
    private boolean dfs (Graph<String> grafo, Vertex<String> origen, int cantLocalidades, int cantNafta, int cant, List<String> camino, boolean[] marcas) {
        boolean encontre = false;
        marcas[origen.getPosition()] = true;
        camino.add(origen.getData());
        if(cantLocalidades == cant) {
            return true;
        } else {
            Iterator<Edge<String>> it = grafo.getEdges(origen).iterator();
            while(it.hasNext() && !encontre) {
                Edge<String> ady = it.next();
                Vertex<String> aux = ady.getTarget();
                int peso = ady.getWeight();
                if(!marcas[aux.getPosition()] && peso <= cantNafta) {
                    encontre = this.dfs(grafo, aux, cantLocalidades, cantNafta-peso, cant+1, camino, marcas);
                }
            }
        }
        marcas[origen.getPosition()] = false;
        if(!encontre) camino.remove(camino.size()-1);
        return encontre;
    }
    
    public static void main(String args[]) {
        Graph<String> grafo = new AdjListGraph<String>();
        Vertex<String> v1 = grafo.createVertex("Mendoza");
        Vertex<String> v2 = grafo.createVertex("Tunuyán");
        Vertex<String> v3 = grafo.createVertex("San Martin");
        Vertex<String> v4 = grafo.createVertex("La Paz");
        Vertex<String> v5 = grafo.createVertex("Santa Rosa");
        Vertex<String> v6 = grafo.createVertex("San Rafael");
        Vertex<String> v7 = grafo.createVertex("Malargue");
        Vertex<String> v8 = grafo.createVertex("General Alvear");
        
        grafo.connect(v1, v2, 10);
        grafo.connect(v1, v3, 6);
        grafo.connect(v2, v3, 10);
        grafo.connect(v3, v4, 8);
        grafo.connect(v4, v5, 2);
        grafo.connect(v3, v6, 13);
        grafo.connect(v6, v2, 11);
        grafo.connect(v6, v8, 7);
        grafo.connect(v2, v7, 12);
        grafo.connect(v8, v7, 6);
        
        List<String> localidadesExceptuadas = new LinkedList<String>();
        localidadesExceptuadas.add("General Alvear");
        localidadesExceptuadas.add("La Paz");
        
        Parcial p = new Parcial();
        
        System.out.println(p.recorrido(grafo, 5, 80, localidadesExceptuadas));
    }
}
