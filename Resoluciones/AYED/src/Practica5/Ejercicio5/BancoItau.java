package Practica5.Ejercicio5;

import Practica1.Ejercicio8.Queue;
import Practica5.Ejercicio1.*;
import java.util.*;

public class BancoItau {
    public BancoItau() {}
    
    public List<Jubilado> carteraJubilados(Graph<Persona> grafo, String nomEmpleado, int grado, int maxList) {
        List<Jubilado> jubilados = new LinkedList<Jubilado>();
        if(!grafo.isEmpty()) {
            Vertex<Persona> emp = this.buscarEmpleado(grafo, nomEmpleado);
            if(emp != null) {
                Queue<Vertex<Persona>> c = new Queue<Vertex<Persona>>();
                boolean[] marcas = new boolean[grafo.getSize()];
                marcas[emp.getPosition()] = true;
                c.enqueue(emp);
                c.enqueue(null);
                while(!c.isEmpty() && grado > 0 && jubilados.size() < maxList) {
                    Vertex<Persona> v = c.dequeue();
                    if(v!=null) {
                        List<Edge<Persona>> adys = grafo.getEdges(v);
                        Iterator <Edge<Persona>> it = adys.iterator();
                        while(it.hasNext() && jubilados.size() < maxList) {
                            Vertex<Persona> vertDestino = it.next().getTarget();
                            int posDestino = vertDestino.getPosition();
                            if(!marcas[posDestino]) {
                                marcas[posDestino] = true;
                                c.enqueue(vertDestino);
                                if(vertDestino.getData().cumple()) {
                                    jubilados.add(new Jubilado(vertDestino.getData().getNombre(), vertDestino.getData().getDomicilio()));
                                }
                            }
                        }
                    } 
                    else if (!c.isEmpty()) {
                        grado--;
                        c.enqueue(null);
                    }
                }
            }
        }
        return jubilados;
    }
    
    private Vertex<Persona> buscarEmpleado(Graph<Persona> grafo, String nomEmpleado) {
        List<Vertex<Persona>> vertices = grafo.getVertices();
        Iterator<Vertex<Persona>> it = vertices.iterator();
        Vertex<Persona> per = null;
        boolean sigo = true;
        while ((it.hasNext()) && (sigo)) {
            Vertex<Persona> aux = it.next();
            if(aux.getData().getNombre().equals(nomEmpleado)) {
                per = aux;
                sigo = false;
            }
        }
        return per;
    }
    
    
    
}
