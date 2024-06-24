package Parciales.Grafos.Parcial5;

import Practica5.Ejercicio1.*;
import java.util.*;

public class Parcial {
    public List<String> estadios(Graph<Estadio> mapaEstadios, String estadioOrigen, int cantKm) {
        List<String> camMax = new LinkedList<String>();
        if(!mapaEstadios.isEmpty()) {
            Vertex<Estadio> origen = this.buscar(mapaEstadios, estadioOrigen);
            if(origen != null) {
                this.dfs(mapaEstadios, origen, camMax, new LinkedList<String>(), new boolean [mapaEstadios.getSize()], cantKm);
            }
        }
        return camMax;
    }
    
    private Vertex<Estadio> buscar(Graph<Estadio> mapaEstadios, String estadioOrigen) {
        List<Vertex<Estadio>> lis = mapaEstadios.getVertices();
        Iterator<Vertex<Estadio>> it = lis.iterator();
        Vertex<Estadio> aux = null;
        while(it.hasNext() && aux == null) {
            Vertex<Estadio> v = it.next();
            if(v.getData().getNombre().equals(estadioOrigen)) {
                aux = v;
            }
        }
        return aux;
    }
    
    private void dfs(Graph<Estadio> mapaEstadios, Vertex<Estadio> origen, List<String> camMax, List<String> camAct, boolean[] marcas, int cantKm) {
        marcas[origen.getPosition()] = true;
        camAct.add(origen.getData().getNombre());
        for(Edge<Estadio> a: mapaEstadios.getEdges(origen)) {
            int peso = a.getWeight();
            Vertex destino = a.getTarget();
            if(!marcas[destino.getPosition()] && peso <= cantKm) {
                this.dfs(mapaEstadios, destino, camMax, camAct, marcas, cantKm-peso);
            }
        }
        if(camAct.size() > camMax.size()) {
            camMax.clear();
            camMax.addAll(camAct);
        }
        marcas[origen.getPosition()] = false;
        camAct.remove(camAct.size()-1);
    }
    
    public static void main(String args[]) {
        Graph<Estadio> grafo = new AdjListGraph();
        Vertex<Estadio> v1 = grafo.createVertex(new Estadio("Jor", "AI BAYT STADIUM"));
        Vertex<Estadio> v2 = grafo.createVertex(new Estadio("Lusail", "LUSAIL STADIUM"));
        Vertex<Estadio> v3 = grafo.createVertex(new Estadio("Rayán", "EDUCATION CITY STADIUM"));
        Vertex<Estadio> v4 = grafo.createVertex(new Estadio("Rayán", "AL RAYYAN STADIUM"));
        Vertex<Estadio> v5 = grafo.createVertex(new Estadio("Doha", "STADIUM 947"));
        Vertex<Estadio> v6 = grafo.createVertex(new Estadio("Doha", "KHALIFA INTERNATIONAL STADIUM"));
        Vertex<Estadio> v7 = grafo.createVertex(new Estadio("Doha", "AL THUMAMA STADIUM"));
        Vertex<Estadio> v8 = grafo.createVertex(new Estadio("Wakrah", "AL JANOUB STADIUM"));
        
        grafo.connect(v1, v2, 42);
        grafo.connect(v2, v1, 42);
        grafo.connect(v2, v3, 24);
        grafo.connect(v3, v2, 24);
        grafo.connect(v2, v5, 52);
        grafo.connect(v5, v2, 52);
        grafo.connect(v3, v4, 11);
        grafo.connect(v4, v3, 11);
        grafo.connect(v4, v6, 8);
        grafo.connect(v6, v4, 8);
        grafo.connect(v6, v7, 12);
        grafo.connect(v7, v6, 12);
        grafo.connect(v7, v8, 12);
        grafo.connect(v8, v7, 12);
        grafo.connect(v8, v5, 19);
        grafo.connect(v5, v8, 19);
        
        Parcial p = new Parcial();
        
        System.out.println(p.estadios(grafo, "AI BAYT STADIUM", 100));
    }
}
