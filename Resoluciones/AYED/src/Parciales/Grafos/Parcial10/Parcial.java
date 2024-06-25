package Parciales.Grafos.Parcial10;

import Practica5.Ejercicio1.*;
import java.util.*;

public class Parcial {
    public List<String> resolver(Graph<Ciudad> ciudades, String origen, String destino) {
        List<String> camino = new LinkedList<String>();
        if(!ciudades.isEmpty()) {
            Vertices v = this.buscarVertices(ciudades, origen, destino);
            if(v != null) {
                //Se supone que tanto destino como origen, no son de fase 1, si no, antes de mandar el dfs, habria que hacer un chequeo con ambos nodos de sus respectivas fases.
                this.dfs(ciudades, v.getOrigen(), v.getDestino(), camino, new boolean[ciudades.getSize()]);
            }
        }
        return camino;
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
    
    private boolean dfs(Graph<Ciudad> ciudades, Vertex<Ciudad> origen, Vertex<Ciudad> destino, List<String> camino, boolean[] marcas) {
        marcas[origen.getPosition()] = true;
        boolean encontre = false;
        camino.add(origen.getData().getCiudad());
        if(origen == destino) {
            return true;
        } else {
            Iterator<Edge<Ciudad>> it = ciudades.getEdges(origen).iterator();
            while(it.hasNext() && !encontre) {
                Vertex<Ciudad> ady = it.next().getTarget();
                int pos = ady.getPosition();
                if(!marcas[pos] && !ady.getData().getFase().equals("Fase 1")) {
                    encontre = this.dfs(ciudades, ady, destino, camino, marcas);
                }
            }
        }
        if(!encontre) camino.remove(camino.size()-1);
        marcas[origen.getPosition()] = false;
        return encontre;
    }
    
    public static void main(String args[]) {
        Graph<Ciudad> grafo = new AdjListGraph<Ciudad>();
        //Descarte Saladillo, Lobos y Pinamar
        Vertex<Ciudad> v1 = grafo.createVertex(new Ciudad("Suipacha", "Fase 5"));
        Vertex<Ciudad> v2 = grafo.createVertex(new Ciudad("Carlos Keen", "Fase 3"));
        Vertex<Ciudad> v3 = grafo.createVertex(new Ciudad("Moreno", "Fase 1"));
        Vertex<Ciudad> v4 = grafo.createVertex(new Ciudad("Quilmes", "Fase 1"));
        Vertex<Ciudad> v5 = grafo.createVertex(new Ciudad("Navarro", "Fase 4"));
        Vertex<Ciudad> v6 = grafo.createVertex(new Ciudad("Cañuelas", "Fase 3"));
        Vertex<Ciudad> v7 = grafo.createVertex(new Ciudad("Abasto", "Fase 2"));
        Vertex<Ciudad> v8 = grafo.createVertex(new Ciudad("La Plata", "Fase 2"));
        
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
        
        System.out.println(p.resolver(grafo, "La Plata", "Carlos Keen"));
    }
}
