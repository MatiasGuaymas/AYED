package Parciales.Grafos.Parcial9;

import Practica5.Ejercicio1.*;
import java.util.*;

public class Parcial {
    //El grafo sera de Strings y no de objeto Ciudad, porque dice que solo se conoce nombre, a pesar del grafico.
    public List<String> resolver(Graph<String> ciudades, String origen, String destino, List<String> pasandoPor) {
        List<String> camino = new LinkedList<String>();
        if(!ciudades.isEmpty()) {
            Vertices v = this.buscarVertices(ciudades, origen, destino);
            if(v != null) {
                this.dfs(ciudades, v.getOrigen(), v.getDestino(), camino, pasandoPor, new boolean[ciudades.getSize()]);
            }
        }
        return camino;
    }
    
    private Vertices buscarVertices(Graph<String> ciudades, String origen, String destino) {
        Vertex<String> ver1 = ciudades.search(origen);
        Vertex<String> ver2 = ciudades.search(destino);
        if(ver1 != null && ver2 != null) return new Vertices(ver1, ver2);
        else return null;
    }
    
    private boolean dfs(Graph<String> ciudades, Vertex<String> origen, Vertex<String> destino, List<String> camino, List<String> pasandoPor, boolean[] marcas) {
        marcas[origen.getPosition()] = true;
        boolean encontre = false;
        camino.add(origen.getData());
        if(origen == destino) {
            if(camino.containsAll(pasandoPor)) {
                return true;
            }
        } else {
            Iterator<Edge<String>> it = ciudades.getEdges(origen).iterator();
            while(it.hasNext() && !encontre) {
                Vertex<String> ady = it.next().getTarget();
                int pos = ady.getPosition();
                if(!marcas[pos]) {
                    encontre = this.dfs(ciudades, ady, destino, camino, pasandoPor, marcas);
                }
            }
        }
        if(!encontre) camino.remove(camino.size()-1);
        marcas[origen.getPosition()] = false;
        return encontre;
    }
    
    public static void main(String args[]) {
        Graph<String> grafo = new AdjListGraph<String>();
        //Descarte Saladillo, Lobos y Pinamar
        Vertex<String> v1 = grafo.createVertex("Suipacha");
        Vertex<String> v2 = grafo.createVertex("Carlos Keen");
        Vertex<String> v3 = grafo.createVertex("Moreno");
        Vertex<String> v4 = grafo.createVertex("Quilmes");
        Vertex<String> v5 = grafo.createVertex("Navarro");
        Vertex<String> v6 = grafo.createVertex("Cañuelas");
        Vertex<String> v7 = grafo.createVertex("Abasto");
        Vertex<String> v8 = grafo.createVertex("La Plata");
        
        grafo.connect(v1, v2);
        grafo.connect(v2, v1);
        grafo.connect(v2, v3);
        grafo.connect(v3, v2);
        grafo.connect(v3, v4);
        grafo.connect(v4, v3);
        grafo.connect(v1, v5);
        grafo.connect(v5, v1);
        grafo.connect(v5, v6);
        grafo.connect(v6, v5);
        grafo.connect(v6, v7);
        grafo.connect(v7, v6);
        grafo.connect(v7, v3);
        grafo.connect(v3, v7);
        grafo.connect(v7, v8);
        grafo.connect(v8, v7);
        grafo.connect(v8, v4);
        grafo.connect(v4, v8);
        
        Parcial p = new Parcial();
        
        List<String> pasandoPor = new LinkedList<String>();
        
        pasandoPor.add("Quilmes");
        //pasandoPor.add("Abasto");
        pasandoPor.add("Carlos Keen");
        
        System.out.println(p.resolver(grafo, "La Plata", "Suipacha", pasandoPor));
    }
}
