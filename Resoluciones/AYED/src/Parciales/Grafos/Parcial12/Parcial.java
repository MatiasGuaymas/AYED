package Parciales.Grafos.Parcial12;

import Practica5.Ejercicio1.*;
import java.util.*;

public class Parcial {
    
    public int resolver(Graph<Ciudad> ciudades, String origen, String destino, int maxControles) {
        int max = 0;
        if(!ciudades.isEmpty()) {
            Vertices v = this.buscarVertices(ciudades, origen, destino);
            if(v!=null) {
                max = this.dfs(ciudades, v.getOrigen(), v.getDestino(), new boolean[ciudades.getSize()], v.getOrigen().getData().getTiempo(), Integer.MIN_VALUE , maxControles);
            }
        }
        return max;
    }
    
    private Vertices buscarVertices(Graph<Ciudad> ciudades, String origen, String destino) {
        Vertex<Ciudad> ver1 = null;
        Vertex<Ciudad> ver2 = null;
        Iterator<Vertex<Ciudad>> it = ciudades.getVertices().iterator();
        while(it.hasNext() && (ver1 == null || ver2 == null)) {
            Vertex<Ciudad> aux = it.next();
            if(aux.getData().getCiudad().equals(origen)) {
                ver1 = aux;
            } else if (aux.getData().getCiudad().equals(destino)) {
                ver2 = aux;
            }
        }
        if(ver1 != null && ver2 != null) return new Vertices(ver1, ver2);
        else return null;
    }
    
    private int dfs(Graph<Ciudad> ciudades, Vertex<Ciudad> origen, Vertex<Ciudad> destino, boolean[] marcas, int total, int max, int maxControles) {
        marcas[origen.getPosition()] = true;
        if(origen == destino) {
            if (total > max) {
                max = total;
            }
        } else {
            for(Edge<Ciudad> ady : ciudades.getEdges(origen)) {
                Vertex<Ciudad> des = ady.getTarget();
                int pos = des.getPosition();
                if(!marcas[pos] && ady.getWeight() <= maxControles) {
                    max = this.dfs(ciudades, des, destino, marcas, total+des.getData().getTiempo(), max, maxControles);
                }
            }
        }
        marcas[origen.getPosition()] = false;
        return max;
    }
    
    public static void main(String args[]) {
        Graph<Ciudad> grafo = new AdjListGraph<Ciudad>();
        //Descarte Saladillo, Lobos y Pinamar
        Vertex<Ciudad> v1 = grafo.createVertex(new Ciudad("Suipacha", 3));
        Vertex<Ciudad> v2 = grafo.createVertex(new Ciudad("Carlos Keen", 2));
        Vertex<Ciudad> v3 = grafo.createVertex(new Ciudad("Moreno", 2));
        Vertex<Ciudad> v4 = grafo.createVertex(new Ciudad("Quilmes", 3));
        Vertex<Ciudad> v5 = grafo.createVertex(new Ciudad("Navarro", 1));
        Vertex<Ciudad> v6 = grafo.createVertex(new Ciudad("Cañuelas", 2));
        Vertex<Ciudad> v7 = grafo.createVertex(new Ciudad("Abasto", 3));
        Vertex<Ciudad> v8 = grafo.createVertex(new Ciudad("La Plata", 1));
        
        grafo.connect(v1, v2, 2);
        grafo.connect(v2, v1, 2);
        grafo.connect(v2, v3, 2);
        grafo.connect(v3, v2, 2);
        grafo.connect(v3, v4, 2);
        grafo.connect(v4, v3, 2);
        grafo.connect(v1, v5, 2);
        grafo.connect(v5, v1, 2);
        grafo.connect(v5, v6, 2);
        grafo.connect(v6, v5, 2);
        grafo.connect(v6, v7, 2);
        grafo.connect(v7, v6, 2);
        grafo.connect(v7, v3, 3);
        grafo.connect(v3, v7, 3);
        grafo.connect(v7, v8, 2);
        grafo.connect(v8, v7, 2);
        grafo.connect(v8, v4, 2);
        grafo.connect(v4, v8, 2);
        
        Parcial p = new Parcial();
        
        System.out.println(p.resolver(grafo, "La Plata", "Suipacha", 2));
        //System.out.println(p.resolver(grafo, "La Plata", "Carlos Keen", 2));
    }
}
