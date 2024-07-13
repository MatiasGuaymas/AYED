package Parciales.Grafos.Parcial15;

import Practica5.Ejercicio1.*;
import java.util.*;

public class Parcial {
    public List<Objeto> menorTrasbordos(Graph<String> maps, String origen) {
        List<Objeto> lis = new LinkedList<Objeto>();
        if(!maps.isEmpty()) {
            Vertex<String> ori = maps.search(origen);
            if(origen != null) {
                this.dfs(maps, ori, lis, new boolean[maps.getSize()], 0, ori.getData());
            }
        }
        return lis;
    }
    
    private void dfs(Graph<String> maps, Vertex<String> origen, List<Objeto> lis, boolean[]marcas, int cant, String principio) {
        marcas[origen.getPosition()] = true;
        String nomActual = origen.getData();
        List<Edge<String>> adyacentes = maps.getEdges(origen);
        int size = adyacentes.size();
        if(size == 1 && !nomActual.equals(principio)) {
            Iterator<Objeto> it = lis.iterator();
            boolean encontre = false;
            while(it.hasNext() && !encontre) {
                Objeto obj = it.next();
                if(obj.getNombre().equals(nomActual)) {
                    encontre = true;
                    if(obj.getCant() > cant) {
                        obj.setCant(cant);
                    }
                }
            }
            if(!encontre) {
                lis.add(new Objeto(nomActual, cant));
            }
        } else {
            for(Edge<String> ady: adyacentes) {
                Vertex<String> destino = ady.getTarget();
                int j = destino.getPosition();
                if(!marcas[j]) {
                    if(size > 2) {
                        this.dfs(maps, destino, lis, marcas, cant+1, principio);
                    } else {
                        this.dfs(maps, destino, lis, marcas, cant, principio);
                    }
                }
            }
        }
        marcas[origen.getPosition()] = false;
    }
    
    //PROGRAMA PRINCIPAL
}
