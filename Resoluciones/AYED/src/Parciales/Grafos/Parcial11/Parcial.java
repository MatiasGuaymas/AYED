package Parciales.Grafos.Parcial11;

import Practica5.Ejercicio1.*;
import java.util.*;

public class Parcial {
    public List<String> rutaMasSegura(Graph<Calle> ciudad) {
        List<String> camino = new LinkedList<String>();
        if(!ciudad.isEmpty()) {
            Vertices v = this.buscar(ciudad);
            if(v!= null) {
                //Se supone que la casa del intendente y la municipalidad no son controladas por la mafia.
                this.dfs(ciudad, v.getCasa(), v.getMunicipalidad(), camino, new boolean[ciudad.getSize()]);
            }
        }
        return camino;
    }
    
    private Vertices buscar(Graph<Calle> ciudad) {
        Iterator<Vertex<Calle>> it = ciudad.getVertices().iterator();
        Vertex<Calle> casa = null;
        Vertex<Calle> municipio = null;
        while(it.hasNext() && (casa == null || municipio == null)) {
            Vertex<Calle> aux = it.next();
            if(aux.getData().getNombre().equals("Casa del Intendente")) {
                casa = aux;
            } else if (aux.getData().getNombre().equals("Municipalidad")) {
                municipio = aux;
            }
        }
        if(casa != null && municipio != null) return new Vertices(casa, municipio);
        else return null;
    }
    
    private boolean dfs(Graph<Calle> ciudad, Vertex<Calle> origen, Vertex<Calle> destino, List<String> camino, boolean[] marcas) {
        boolean encontre = false;
        marcas[origen.getPosition()] = true;
        camino.add(origen.getData().getNombre());
        if(origen == destino) {
            return true;
        } else {
            Iterator<Edge<Calle>> it = ciudad.getEdges(origen).iterator();
            while(it.hasNext() && !encontre) {
                Edge<Calle> ady = it.next();
                Vertex<Calle> des = ady.getTarget();
                int peso = ady.getWeight(); //SUPONGO QUE SI EL PESO ES 1, ESTA CONTROLADO POR LA MAFIA.
                if(!marcas[des.getPosition()] && peso != 1 &&!des.getData().isMafiosa()) {
                    encontre = this.dfs(ciudad, des, destino, camino, marcas);
                }
            }
        }
        if(!encontre) camino.remove(camino.size()-1);
        marcas[origen.getPosition()] = false;
        return encontre;
    }
    
    public static void main(String args[]) {
        Graph<Calle> grafo = new AdjListGraph<Calle>();
        Vertex<Calle> v1 = grafo.createVertex(new Calle("Casa del Intendente", false));
        Vertex<Calle> v2 = grafo.createVertex(new Calle("Calle 58", false));
        Vertex<Calle> v3 = grafo.createVertex(new Calle("Avenida Bengochea", true));
        Vertex<Calle> v4 = grafo.createVertex(new Calle("El Palomar", false));
        Vertex<Calle> v5 = grafo.createVertex(new Calle("Municipalidad", false));
        Vertex<Calle> v6 = grafo.createVertex(new Calle("Uruguay 121", false));
        
        grafo.connect(v1, v2, 1);
        grafo.connect(v2, v1, 1);
        grafo.connect(v1, v3, 0);
        grafo.connect(v3, v1, 0);
        grafo.connect(v1, v4, 0);
        grafo.connect(v4, v1, 0);
        grafo.connect(v2, v5, 1);
        grafo.connect(v5, v2, 1);
        grafo.connect(v3, v5, 1);
        grafo.connect(v5, v3, 1);
        grafo.connect(v4, v6, 0);
        grafo.connect(v6, v4, 0);
        grafo.connect(v6, v5, 0);
        grafo.connect(v5, v6, 0);
        
        Parcial p = new Parcial();
        
        System.out.println(p.rutaMasSegura(grafo));
    }
}
