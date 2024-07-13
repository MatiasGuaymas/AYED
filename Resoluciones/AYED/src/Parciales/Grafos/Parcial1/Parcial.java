package Parciales.Grafos.Parcial1;

import Practica5.Ejercicio1.*;
import java.util.*;

public class Parcial {
    public List<String> resolver(Graph<Ciudad> mapa, String ciudad, int cantDiasVacas) {
        List<String> lisMax = new LinkedList<String>();
        if(!mapa.isEmpty()) {
            Vertex<Ciudad> origen = this.buscar(mapa, ciudad);
            if(origen != null) {
                int dias = origen.getData().getDias();
                if(dias <= cantDiasVacas) {
                    this.dfs(origen, mapa, cantDiasVacas - dias, lisMax, new LinkedList<String>(), new boolean[mapa.getSize()]);
                }
            }
        }
        return lisMax;
    }
    
    private Vertex<Ciudad> buscar(Graph<Ciudad> mapa, String ciudad) {
        Vertex<Ciudad> ciu = null;
        Iterator<Vertex<Ciudad>> it = mapa.getVertices().iterator();
        while(it.hasNext() && ciu == null) {
            Vertex<Ciudad> aux = it.next();
            if(aux.getData().getNombre().equals(ciudad)) {
                ciu = aux;
            }
        } 
        return ciu;
    }
    
    private void dfs(Vertex<Ciudad> origen, Graph<Ciudad> mapa, int cantDias, List<String> lisMax, List<String> lisActual, boolean[] marcas) {
        lisActual.add(origen.getData().getNombre());
        marcas[origen.getPosition()] = true;
        if(cantDias == 0) {
            if(lisActual.size() > lisMax.size()) {
                lisMax.clear();
                lisMax.addAll(lisActual);
            }
        } else {
            for(Edge<Ciudad> ady: mapa.getEdges(origen)) {
                Vertex<Ciudad> destino = ady.getTarget();
                int j = destino.getPosition();
                int peso = destino.getData().getDias();
                if(!marcas[j] && peso <= cantDias) {
                    this.dfs(destino, mapa, cantDias-peso, lisMax, lisActual, marcas);
                }
            }
        }
        lisActual.remove(lisActual.size()-1);
        marcas[origen.getPosition()] = false;
    }
    
    public static void main(String args[]) {
        Graph<Ciudad> mapa = new AdjListGraph<Ciudad>();
        Vertex<Ciudad> MarDelPlata = mapa.createVertex(new Ciudad(7, "Mar Del Plata"));
        Vertex<Ciudad> Pila = mapa.createVertex(new Ciudad(1, "Pila"));
        Vertex<Ciudad> Dolores = mapa.createVertex(new Ciudad(1, "Dolores"));
        Vertex<Ciudad> Chascomus = mapa.createVertex(new Ciudad(1, "Chascomús"));
        Vertex<Ciudad> MarAzul = mapa.createVertex(new Ciudad(3, "Mar Azul"));
        Vertex<Ciudad> Pinamar = mapa.createVertex(new Ciudad(4, "Pinamar"));
        Vertex<Ciudad> Madariaga = mapa.createVertex(new Ciudad(1, "Madariaga"));
        Vertex<Ciudad> LaPlata = mapa.createVertex(new Ciudad(5, "La Plata"));
        Vertex<Ciudad> LasGaviotas = mapa.createVertex(new Ciudad(1, "Las Gaviotas"));
        Vertex<Ciudad> Querandi = mapa.createVertex(new Ciudad(1, "Querandi"));
        Vertex<Ciudad> Hudson = mapa.createVertex(new Ciudad(1, "Hudson"));
        
        mapa.connect(MarDelPlata, Pila);
        mapa.connect(Pila, MarDelPlata);
        mapa.connect(Pila, Dolores);
        mapa.connect(Dolores, Pila);
        mapa.connect(Dolores, Chascomus);
        mapa.connect(Chascomus, Dolores);
        
        mapa.connect(MarDelPlata, MarAzul);
        mapa.connect(MarAzul, MarDelPlata);
        mapa.connect(MarAzul, Pinamar);
        mapa.connect(Pinamar, MarAzul);
        mapa.connect(Pinamar, Madariaga);
        mapa.connect(Madariaga, Pinamar);
        mapa.connect(Dolores, Madariaga);
        mapa.connect(Madariaga, Dolores);
        mapa.connect(Madariaga, LaPlata);
        mapa.connect(LaPlata, Madariaga);
        mapa.connect(Chascomus, LaPlata);
        mapa.connect(LaPlata, Chascomus);
        
        mapa.connect(MarAzul, LasGaviotas);
        mapa.connect(LasGaviotas, MarAzul);
        mapa.connect(MarAzul, Querandi);
        mapa.connect(Querandi, MarAzul);
        mapa.connect(LaPlata, Hudson);
        mapa.connect(Hudson, LaPlata);
        
        Parcial p = new Parcial();
        System.out.println(p.resolver(mapa, "Mar Del Plata", 15));
    }
}
