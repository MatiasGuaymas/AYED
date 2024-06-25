package Parciales.Grafos.Parcial4;

import java.util.*;
import Practica5.Ejercicio1.*;

public class Parcial {
    public List<ListasCuadras> resolver(Graph<String> sitios, String origen, String destino, List<String> evitarPasarPor) {
        List<ListasCuadras> lis = new LinkedList<ListasCuadras>();
        if(!sitios.isEmpty()) {
            //Se debe mejorar, se deberia hacer un unico search que devuelva una clase con ambos vertices
            Vertex v1 = sitios.search(origen);
            Vertex v2 = sitios.search(destino);
            List<String> lisAct = new LinkedList<String>();
            if(v1 != null && v2 != null) {
                boolean [] marcas = new boolean[sitios.getSize()];
                this.marcarEvitar(sitios, marcas, evitarPasarPor);
                this.dfs(sitios, lis, lisAct, v1, v2, marcas, 0);
            }
        }
        return lis;
    }
    
    private void marcarEvitar(Graph<String> sitios, boolean[] marcas, List<String> evitarPasarPor) {
        for(String nom: evitarPasarPor) {
            Vertex<String> v = sitios.search(nom);
            if(v!= null) {
                marcas[v.getPosition()] = true;
            }
        }
    }
    
    private void dfs(Graph<String> sitios, List<ListasCuadras> lis, List<String> lisActual, Vertex<String> origen, Vertex<String> destino, boolean[] marcas, int cuadras) {
        marcas[origen.getPosition()] = true;
        lisActual.add(origen.getData());
        if(origen == destino) {
            ListasCuadras l = new ListasCuadras(new LinkedList(lisActual), cuadras);
            lis.add(l);
        } else {
            for(Edge<String> a : sitios.getEdges(origen)) {
                Vertex<String> aux = a.getTarget();
                int peso = a.getWeight();
                if(!marcas[aux.getPosition()]) {
                    this.dfs(sitios, lis, lisActual, aux, destino, marcas, cuadras+peso);
                }
            }
        }
        marcas[destino.getPosition()] = false;
        lisActual.remove(lisActual.size()-1);
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
        List<ListasCuadras> lis = p.resolver(grafo, "Estadio Diego Armando Maradona", "Palacio Campodónico", evitarPasarPor);
        
        for(ListasCuadras aux: lis) {
            System.out.println(aux);
        }
    }
}
