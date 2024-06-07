/* Ejercicio 3
1. devolverCamino (String ciudad1, String ciudad2): List<String>
Retorna la lista de ciudades que se deben atravesar para ir de ciudad1 a ciudad2 en caso de que se
pueda llegar, si no retorna la lista vacía. (Sin tener en cuenta el combustible).
2. devolverCaminoExceptuando (String ciudad1, String ciudad2, List<String> ciudades): List<String>
Retorna la lista de ciudades que forman un camino desde ciudad1 a ciudad2, sin pasar por las ciudades
que están contenidas en la lista ciudades pasada por parámetro, si no existe camino retorna la lista
vacía. (Sin tener en cuenta el combustible).
3. caminoMasCorto(String ciudad1, String ciudad2): List<String>
Retorna la lista de ciudades que forman el camino más corto para llegar de ciudad1 a ciudad2, si no
existe camino retorna la lista vacía. (Las rutas poseen la distancia).
4. caminoSinCargarCombustible(String ciudad1, String ciudad2, int tanqueAuto): List<String>
Retorna la lista de ciudades que forman un camino para llegar de ciudad1 a ciudad2. El auto no debe
quedarse sin combustible y no puede cargar. Si no existe camino retorna la lista vacía.
5. caminoConMenorCargaDeCombustible (String ciudad1, String ciudad2, int tanqueAuto): List<String>
Retorna la lista de ciudades que forman un camino para llegar de ciudad1 a ciudad2 teniendo en cuenta
que el auto debe cargar la menor cantidad de veces. El auto no se debe quedar sin combustible en
medio de una ruta, además puede completar su tanque al llegar a cualquier ciudad. Si no existe camino
retorna la lista vacía. */

package Practica5.Ejercicio3;

import Practica5.Ejercicio1.*;
import java.util.*;

public class Mapa {
    private Graph<String> mapaCiudades;
    
    public Mapa(Graph<String> mapa) {
        this.setMapaCiudades(mapa);
    }

    public Graph<String> getMapaCiudades() {
        return mapaCiudades;
    }

    public void setMapaCiudades(Graph<String> mapaCiudades) {
        this.mapaCiudades = mapaCiudades;
    }
    
    //La idea es devolver el primer camino encontrado, y recorrer con un while. No seria necesario devolver cada camino que se encuentre y usar un for.
    
    /*-------------------------------------1-------------------------------------*/
    public List<String> devolverCamino(String ciudad1, String ciudad2) {
        List<String> camino = new LinkedList<String>();
        if(!this.mapaCiudades.isEmpty()) {
            Vertex origen = this.mapaCiudades.search(ciudad1);
            Vertex destino = this.mapaCiudades.search(ciudad2);
            if(origen != null && destino != null) {
                devolverCamino(origen, destino, camino, new boolean[this.getMapaCiudades().getSize()]);
            }
        }
        return camino;
    }
    
    private boolean devolverCamino(Vertex<String> origen, Vertex<String> destino, List<String> camino, boolean[] marcas) {
        boolean encontre = false;
        marcas[origen.getPosition()] = true;
        camino.add(origen.getData());
        if(origen == destino) {
            return true;
        } else {
            List<Edge<String>> ady = this.mapaCiudades.getEdges(origen);
            Iterator<Edge<String>> it = ady.iterator();
            while(it.hasNext() && !encontre) {
                Vertex<String> v = it.next().getTarget();
                int j = v.getPosition();
                if(!marcas[j]) {
                    encontre = devolverCamino(v, destino, camino, marcas);
                }
            }
        }
        if(!encontre) {
            camino.remove(camino.size()-1); //marcas[origen.getPosition()] = false; Cuando haya encontrado el camino hacia destino, deja marcadas las posiciones del vector de marcas
        }
        marcas[origen.getPosition()] = false; //Deja desmarcadas todas las posiciones del vector de marcas: no es necesario desmarcar en este ejemplo.
        return encontre;
    }
    
    /*-------------------------------------2-------------------------------------*/
    public List<String> devolverCaminoExceptuando(String ciudad1, String ciudad2, List<String> ciudades) {
        List<String> camino = new LinkedList<String>();
        if(!this.mapaCiudades.isEmpty()) {
            Vertex origen = this.mapaCiudades.search(ciudad1);
            Vertex destino = this.mapaCiudades.search(ciudad2);
            if(origen != null && destino != null) {
                boolean [] marcas = new boolean[this.getMapaCiudades().getSize()];
                marcarRestringidos(ciudades, marcas);
                devolverCamino(origen, destino, camino, marcas);
            }
        }
        return camino;
    }
    
    private void marcarRestringidos(List<String> lugaresRestringidos, boolean[] marcas) {
        for(String nom: lugaresRestringidos) {
            Vertex<String> v = this.mapaCiudades.search(nom);
            if(v!=null) {
                marcas[v.getPosition()] = true;
            }
        }
    }
    
    /*-------------------------------------3-------------------------------------*/
    public List<String> caminoMasCorto(String ciudad1, String ciudad2) {
        List<String> camino = new LinkedList<String>();
        if(!this.mapaCiudades.isEmpty()) {
            Vertex origen = this.mapaCiudades.search(ciudad1);
            Vertex destino = this.mapaCiudades.search(ciudad2);
            if(origen != null && destino != null) {
                caminoMasCorto(origen, destino, camino, new LinkedList<String>(), new boolean[this.getMapaCiudades().getSize()], 0, Integer.MAX_VALUE);
            }
        }
        return camino;
    }
    
    private int caminoMasCorto(Vertex<String> origen, Vertex<String> destino, List<String> caminoMinimo, List<String> caminoAct, boolean[] marcas, int total, int min) {
        marcas[origen.getPosition()] = true;
        caminoAct.add(origen.getData());
        if(origen == destino && total < min) {
            caminoMinimo.removeAll(caminoMinimo);
            caminoMinimo.addAll(caminoAct);
            min = total;
        } else {
            List<Edge<String>> ady = this.mapaCiudades.getEdges(origen);
            Iterator<Edge<String>> it = ady.iterator();
            while(it.hasNext() && total < min) {
                Edge<String> v = it.next();
                int j = v.getTarget().getPosition();
                int aux = total + v.getWeight();
                if(!marcas[j] && aux < min) {
                    min = caminoMasCorto(v.getTarget(), destino, caminoMinimo, caminoAct, marcas, aux, min);
                }
            }
        }
        marcas[origen.getPosition()] = false;
        caminoAct.remove(caminoAct.size()-1);
        return min;
    }
    
    /*-------------------------------------4-------------------------------------*/
    public List<String> caminoSinCargarCombustible(String ciudad1, String ciudad2, int tanqueAuto) {
        List<String> camino = new LinkedList<String>();
        if(!this.mapaCiudades.isEmpty()) {
            Vertex origen = this.mapaCiudades.search(ciudad1);
            Vertex destino = this.mapaCiudades.search(ciudad2);
            if(origen != null && destino != null) {
                caminoSinCargarCombustible(origen, destino, camino, new boolean[this.getMapaCiudades().getSize()], tanqueAuto);
            }
        }
        return camino;
    }
    
    private boolean caminoSinCargarCombustible(Vertex<String> origen, Vertex<String> destino, List<String> camino, boolean[] marcas, int tanqueAuto) {
        boolean encontre = false;
        marcas[origen.getPosition()] = true;
        camino.add(origen.getData());
        if(origen == destino) {
            return true;
        } else {
            List<Edge<String>> ady = this.mapaCiudades.getEdges(origen);
            Iterator<Edge<String>> it = ady.iterator();
            while(it.hasNext() && !encontre) {
                Edge<String> v = it.next();
                int j = v.getTarget().getPosition();
                if(!marcas[j] && tanqueAuto - v.getWeight() > 0) {
                    encontre = caminoSinCargarCombustible(v.getTarget(), destino, camino, marcas, tanqueAuto - v.getWeight());
                }
            }
        }
        if(!encontre) {
            camino.remove(camino.size()-1); 
        }
        marcas[origen.getPosition()] = false;
        return encontre;
    }
    
    /*-------------------------------------5-------------------------------------*/
    public List<String> caminoConMenorCargaCombustible(String ciudad1, String ciudad2, int tanqueAuto) {
        List<String> camino = new LinkedList<String>();
        if(!this.mapaCiudades.isEmpty()) {
            Vertex origen = this.mapaCiudades.search(ciudad1);
            Vertex destino = this.mapaCiudades.search(ciudad2);
            if(origen != null && destino != null) {
                caminoConMenorCargaCombustible(origen, destino, camino, new LinkedList<String>(), new boolean[this.getMapaCiudades().getSize()], tanqueAuto, tanqueAuto, 0, Integer.MAX_VALUE);
            }
        }
        return camino;
    }
    
    private int caminoConMenorCargaCombustible(Vertex<String> origen, Vertex<String> destino, List<String> caminoMinimo, List<String> caminoAct, boolean[] marcas, int tanqueActual, int tanque, int recargas, int recargasMin) {
        marcas[origen.getPosition()] = true;
        caminoAct.add(origen.getData());
        if(origen == destino && recargas < recargasMin) {
            caminoMinimo.removeAll(caminoMinimo);
            caminoMinimo.addAll(caminoAct);
            recargasMin = recargas;
        } else {
            List<Edge<String>> ady = this.mapaCiudades.getEdges(origen);
            Iterator<Edge<String>> it = ady.iterator();
            while(it.hasNext() && recargas < recargasMin) {
                Edge<String> v = it.next();
                int j = v.getTarget().getPosition();
                int distancia = v.getWeight();
                if(!marcas[j]) {
                    if(tanqueActual >= distancia) {
                        recargasMin = caminoConMenorCargaCombustible(v.getTarget(), destino, caminoMinimo, caminoAct, marcas, tanqueActual - distancia, tanque, recargas, recargasMin);
                    } else if (tanque >= distancia) {
                        recargasMin = caminoConMenorCargaCombustible(v.getTarget(), destino, caminoMinimo, caminoAct, marcas, tanque - distancia, tanque, recargas+1, recargasMin);
                    }
                }
            }
        }
        marcas[origen.getPosition()] = false;
        caminoAct.remove(caminoAct.size()-1);
        return recargasMin;
    }
    
    public static void main(String[] args) {
        Graph<String> mapaCiudades = new AdjListGraph<String>();
        Vertex<String> v1 = mapaCiudades.createVertex("La Plata"); //Casa Caperucita
        Vertex<String> v2 = mapaCiudades.createVertex("Ensenada"); //Claro 3
        Vertex<String> v3 = mapaCiudades.createVertex("Berisso"); //Claro 1
        Vertex<String> v4 = mapaCiudades.createVertex("Berazategui"); //Claro 2
        Vertex<String> v5 = mapaCiudades.createVertex("Florencio Varela"); //Claro 5
        Vertex<String> v6 = mapaCiudades.createVertex("Presidente Peron"); //Claro 4
        Vertex<String> v7 = mapaCiudades.createVertex("San Vicente"); //Casa Abuelita
        mapaCiudades.connect(v1, v2, 4);
        mapaCiudades.connect(v2, v1, 4);
        mapaCiudades.connect(v1, v3, 3);
        mapaCiudades.connect(v3, v1, 3);
        mapaCiudades.connect(v1, v4, 4);
        mapaCiudades.connect(v4, v1, 4);
        mapaCiudades.connect(v2, v5, 15);
        mapaCiudades.connect(v5, v2, 15);
        mapaCiudades.connect(v3, v5, 3);
        mapaCiudades.connect(v5, v3, 3);
        mapaCiudades.connect(v4, v3, 4);
        mapaCiudades.connect(v3, v4, 4);
        mapaCiudades.connect(v4, v5, 11);
        mapaCiudades.connect(v5, v4, 11);
        mapaCiudades.connect(v4, v6, 10);
        mapaCiudades.connect(v6, v4, 10);
        mapaCiudades.connect(v4, v3, 4);
        mapaCiudades.connect(v3, v4, 4);
        mapaCiudades.connect(v5, v7, 4);
        mapaCiudades.connect(v7, v5, 4);
        mapaCiudades.connect(v6, v7, 9);
        mapaCiudades.connect(v7, v6, 9);
        
        Mapa mapa = new Mapa(mapaCiudades);
        
        System.out.print("LISTA DEVOLVER CAMINO: ");
        System.out.print(mapa.devolverCamino("La Plata", "San Vicente"));
        
        System.out.println("");
        
        System.out.print("LISTA DEVOLVER CAMINO EXCEPTUANDO LUGARES:");
        List<String> restringidos = new LinkedList<String>();
        restringidos.add("Berisso");
        System.out.print(mapa.devolverCaminoExceptuando("La Plata", "San Vicente", restringidos));
        
        System.out.println("");
        
        System.out.print("LISTA CAMINO MAS CORTO (SEGUN DISTANCIA): ");
        System.out.print(mapa.caminoMasCorto("La Plata", "San Vicente"));
        
        System.out.println("");
        
        System.out.print("LISTA CAMINO SIN CARGAR COMBUSTIBLE: ");
        System.out.print(mapa.caminoSinCargarCombustible("La Plata", "San Vicente", 30));
        
        System.out.println("");
        
        System.out.print("LISTA CAMINO CON MENOR CARGAS DE COMBUSTIBLE: ");
        System.out.print(mapa.caminoConMenorCargaCombustible("La Plata", "San Vicente", 10));
        
        System.out.println("");
    }
}
