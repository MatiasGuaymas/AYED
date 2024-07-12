package Parciales.Grafos.Parcial14;

import Practica5.Ejercicio1.*;
import Practica1.Ejercicio8.Queue;
import java.util.*;

public class Parcial {
    public List<Objeto> invitacionMasterClass(Graph<String> red, String usuario, int distancia, int limite) {
        List<Objeto> lis = new LinkedList<Objeto>();
        if(!red.isEmpty()) {
            Vertex<String> origen = red.search(usuario);
            if(origen != null) {
                this.bfs(red, origen, lis, distancia, limite);
            }
        }
        return lis;
    }
    
    private void bfs (Graph<String> red, Vertex<String> origen, List<Objeto> lis, int distancia, int limite) {
        int grado = 1;
        int cant = 0;
        boolean [] marcas = new boolean[red.getSize()];
        marcas[origen.getPosition()] = true;
        Queue<Vertex<String>> q = new Queue<Vertex<String>>();
        q.enqueue(origen);
        q.enqueue(null);
        while(!q.isEmpty() && cant < limite && grado <= distancia) {
            Vertex<String> aux = q.dequeue();
            if(aux!=null) {
                Iterator<Edge<String>> it = red.getEdges(aux).iterator();
                while(it.hasNext() && cant < limite) {
                    Vertex<String> ady = it.next().getTarget();
                    int j = ady.getPosition();
                    if(!marcas[j]) {
                        marcas[j] = true;
                        q.enqueue(ady);
                        lis.add(new Objeto(ady.getData(), grado));
                        cant++;
                    }
                }
            } else if (!q.isEmpty()) {
                grado++;
                q.enqueue(null);
            }
        }
    }
    
    public static void main(String args[]) {
        Graph<String> grafo = new AdjListGraph<String>();
        Vertex<String> v1 = grafo.createVertex("Lionel");
        Vertex<String> v2 = grafo.createVertex("Rodrigo");
        Vertex<String> v3 = grafo.createVertex("Ángel");
        Vertex<String> v4 = grafo.createVertex("Emiliano");
        Vertex<String> v5 = grafo.createVertex("Julián");
        Vertex<String> v6 = grafo.createVertex("Diego");
        Vertex<String> v7 = grafo.createVertex("Lautaro");
        Vertex<String> v8 = grafo.createVertex("Enzo");
        
        grafo.connect(v1, v2);
        grafo.connect(v2, v1);
        
        grafo.connect(v1, v3);
        grafo.connect(v3, v1);
        
        grafo.connect(v2, v4);
        grafo.connect(v4, v2);
        
        grafo.connect(v2, v5);
        grafo.connect(v5, v2);
        
        grafo.connect(v3, v5);
        grafo.connect(v5, v3);
        
        grafo.connect(v3, v6);
        grafo.connect(v6, v3);
        
        grafo.connect(v6, v7);
        grafo.connect(v7, v6);
        
        grafo.connect(v5, v7);
        grafo.connect(v7, v5);
        
        grafo.connect(v6, v8);
        grafo.connect(v8, v6);
        
        grafo.connect(v4, v8);
        grafo.connect(v8, v4);
        
        grafo.connect(v4, v7);
        grafo.connect(v7, v4);
        
        Parcial p = new Parcial();
        
        System.out.println(p.invitacionMasterClass(grafo, "Lionel", 2, 4));
        System.out.println(p.invitacionMasterClass(grafo, "Juancito", 1, 2));
    }
}
