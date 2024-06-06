/* Ejercicio 4
Se quiere realizar un paseo en bicicleta por lugares emblemáticos de Oslo. Para esto se cuenta con un grafo de
bicisendas. Partiendo desde el “Ayuntamiento” hasta un lugar destino en menos de X minutos, sin pasar por un
conjunto de lugares que están restringidos.
Escriba una clase llamada VisitaOslo e implemente su método:
paseoEnBici(Graph<String> lugares, String destino, int maxTiempo, List<String> lugaresRestringidos) :
List<String>
En este ejemplo, para llegar desde Ayuntamiento a Museo Vikingo, sin pasar por: {“Akker Brigge”, “Palacio
Real”} y en no más de 120 minutos, el camino marcado en negrita cumple las condiciones.
Notas:
● El “Ayuntamiento” debe ser buscado antes de comenzar el recorrido para encontrar un camino.
● De no existir camino posible, se debe retornar una lista vacía.
● Debe retornar el primer camino que encuentre que cumple las restricciones.
● Ejemplos de posibles caminos a retornar, sin pasar por “Akker Brigge” y “Palacio Real” en no más de
120 min (maxTiempo)
● Ayuntamiento, El Tigre, Museo Munch, Parque Botánico, Galería Nacional, Parque Vigeland,
FolkMuseum, Museo Fram, Museo del Barco Polar, Museo Vikingo. El recorrido se hace en 91
minutos.
● Ayuntamiento, Parque Botánico, Galería Nacional, Parque Vigeland, FolkMuseum, Museo
Fram, Museo del Barco Polar, Museo Vikingo. El recorrido se hace en 70 minutos. */

package Practica5.Ejercicio4;

import Practica5.Ejercicio1.*;
import java.util.*;

public class VisitaOslo {
    public VisitaOslo(){}
    
    public List<String> paseoEnBici(Graph<String> lugares, String destino, int maxTiempo, List<String> lugaresRestringidos) {
        List<String> camino = new LinkedList<String>();
        if(!lugares.isEmpty()) {
            Vertex ver1 = lugares.search("Ayuntamiento");
            Vertex ver2 = lugares.search(destino);
            if(ver1 != null && ver2 != null) {
                boolean [] marcas = new boolean[lugares.getSize()];
                marcarRestringidos(lugares, lugaresRestringidos, marcas);
                dfs(lugares, ver1, ver2, camino, marcas, maxTiempo);
            }
        }
        return camino;
    }
    
    private void marcarRestringidos(Graph<String> lugares, List<String> lugaresRestringidos, boolean[] marcas) {
        for(String nom: lugaresRestringidos) {
            Vertex<String> v = lugares.search(nom);
            if(v!=null) {
                marcas[v.getPosition()] = true;
            }
        }
    }
    
    private boolean dfs(Graph<String> lugares, Vertex<String> origen, Vertex<String> destino, List<String> camino, boolean[] marcas, int tiempo) {
        boolean encontre = false;
        marcas[origen.getPosition()] = true;
        camino.add(origen.getData());
        if(origen == destino) {
            return true;
        } else {
            List<Edge<String>> ady = lugares.getEdges(origen);
            Iterator<Edge<String>> it = ady.iterator();
            while(it.hasNext() && !encontre) {
                Edge<String> v = it.next();
                int j = v.getTarget().getPosition();
                if(!marcas[j] && tiempo - v.getWeight() >= 0) {
                    encontre = dfs(lugares, v.getTarget(), destino, camino, marcas, tiempo - v.getWeight());
                }
            }
        }
        if(!encontre) {
            camino.remove(camino.size()-1); //marcas[origen.getPosition()] = false; Cuando haya encontrado el camino hacia destino, deja marcadas las posiciones del vector de marcas
        }
        marcas[origen.getPosition()] = false; //Deja desmarcadas todas las posiciones del vector de marcas
        return encontre;
    }
    
    public static void main(String[] args) {
        Graph<String> lugares = new AdjListGraph<String>();
        Vertex<String> v1 = lugares.createVertex("Holmenkollen");
        Vertex<String> v2 = lugares.createVertex("Parque Vigeland");
        Vertex<String> v3 = lugares.createVertex("Galería Nacional");
        Vertex<String> v4 = lugares.createVertex("Parque Botánico");
        Vertex<String> v5 = lugares.createVertex("Museo Munch");
        Vertex<String> v6 = lugares.createVertex("FolkMuseum");
        Vertex<String> v7 = lugares.createVertex("Palacio Real");
        Vertex<String> v8 = lugares.createVertex("Ayuntamiento");
        Vertex<String> v9 = lugares.createVertex("El Tigre");
        Vertex<String> v10 = lugares.createVertex("Akker Brigge");
        Vertex<String> v11 = lugares.createVertex("Museo Fram");
        Vertex<String> v12 = lugares.createVertex("Museo Vikingo");
        Vertex<String> v13 = lugares.createVertex("La Opera");
        Vertex<String> v14 = lugares.createVertex("Museo del Barco Polar");
        Vertex<String> v15 = lugares.createVertex("Fortaleza Akershus");   
        
        lugares.connect(v1, v2, 30);
        lugares.connect(v2, v1, 30);
        lugares.connect(v2, v3, 10);
        lugares.connect(v3, v2, 10);
        lugares.connect(v3, v4, 15);
        lugares.connect(v4, v3, 15);
        lugares.connect(v4, v5, 1);
        lugares.connect(v5, v4, 1);
        
        lugares.connect(v5, v9, 15);
        lugares.connect(v9, v5, 15);
        lugares.connect(v9, v13, 5);
        lugares.connect(v13, v9, 5);
        lugares.connect(v13, v15, 10);
        lugares.connect(v15, v13, 10);
        
        lugares.connect(v2, v6, 20);
        lugares.connect(v6, v2, 20);
        lugares.connect(v6, v7, 5);
        lugares.connect(v7, v6, 5);
        lugares.connect(v7, v8, 5);
        lugares.connect(v8, v7, 5);
        lugares.connect(v6, v10, 30);
        lugares.connect(v10, v6, 30);
        lugares.connect(v10, v8, 20);
        lugares.connect(v8, v10, 20);
        lugares.connect(v8, v4, 10);
        lugares.connect(v4, v8, 10);
        lugares.connect(v8, v9, 15);
        lugares.connect(v9, v8, 15);
        
        lugares.connect(v6, v11, 5);
        lugares.connect(v11, v6, 5);
        lugares.connect(v10, v12, 30);
        lugares.connect(v12, v10, 30);
        lugares.connect(v11, v14, 5);
        lugares.connect(v14, v11, 5);
        lugares.connect(v12, v14, 5);
        lugares.connect(v14, v12, 5);
        
        List<String> lugaresRestringidos = new LinkedList<String>();
        lugaresRestringidos.add("Akker Brigge");
        lugaresRestringidos.add("Palacio Real");
        VisitaOslo vis = new VisitaOslo();
        List<String> camino = vis.paseoEnBici(lugares, "Museo Vikingo", 120, lugaresRestringidos);
        
        System.out.println(camino);
    }
}
