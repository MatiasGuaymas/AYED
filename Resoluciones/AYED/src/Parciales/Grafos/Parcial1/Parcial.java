package Parciales.Grafos.Parcial1;

import Practica5.Ejercicio1.*;
import java.util.*;

public class Parcial {
    public List<String> resolver(Graph<Ciudad> mapa, String ciudad, int cantDiasVacas) {
        List<String> camMax = new LinkedList<String>();
        if(!mapa.isEmpty()) {
            Vertex<Ciudad> v = this.buscar(mapa, ciudad);
            if(v!= null && v.getData().getCantDias() <= cantDiasVacas) {
                this.dfs(mapa, v, cantDiasVacas - v.getData().getCantDias(), new LinkedList<String>(), camMax, new boolean[mapa.getSize()]);
            }
        }
        return camMax;
    }
    
    private Vertex<Ciudad> buscar(Graph<Ciudad> m,  String ciudad) {
        List<Vertex<Ciudad>> lis = m.getVertices();
        Iterator<Vertex<Ciudad>> it = lis.iterator();
        Vertex<Ciudad> ciu = null;
        while(it.hasNext() && ciu == null) {
            Vertex<Ciudad> aux = it.next();
            if(aux.getData().getNombre().equals(ciudad)) {
                ciu = aux;
            }
        }
        return ciu;
    }
    
    private void dfs(Graph<Ciudad> mapa, Vertex<Ciudad> origen, int cantAct, List<String> camAct, List<String> camMax, boolean[] marcas) {
        marcas[origen.getPosition()] = true;
        camAct.add(origen.getData().getNombre());
        if(cantAct == 0) {
            if(camAct.size() > camMax.size()) {
                camMax.clear();
                camMax.addAll(camAct);
            }
        } else {
            List<Edge<Ciudad>> ady = mapa.getEdges(origen);
            for(Edge<Ciudad> d: ady) {
                Vertex<Ciudad> des = d.getTarget();
                int j = d.getTarget().getPosition();
                if(!marcas[j] && cantAct >= d.getTarget().getData().getCantDias()) {
                    this.dfs(mapa, des, cantAct - des.getData().getCantDias(), camAct, camMax, marcas);
                }
            }
        }
        camAct.remove(camAct.size()-1);
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
