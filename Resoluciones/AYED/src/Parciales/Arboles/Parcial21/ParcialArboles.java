package Parciales.Arboles.Parcial21;

import java.util.*;
import Practica1.Ejercicio8.Queue;
import Practica3.Ejercicio1y3y5.GeneralTree;

public class ParcialArboles {

    private GeneralTree<Integer> arb;
    
    public ParcialArboles(GeneralTree<Integer> arb) {
        this.arb = arb;
    }
    
    public List<Integer> nivel(int num) {
        List<Integer> lis = new LinkedList<Integer>();
        if(!this.arb.isEmpty()) {
            Queue<GeneralTree<Integer>> cola = new Queue<GeneralTree<Integer>>();
            cola.enqueue(this.arb);
            cola.enqueue(null);
            boolean ok = false;
            boolean cumpleNivel = true;
            while(!cola.isEmpty() && !ok) {
                GeneralTree<Integer> aux = cola.dequeue();
                if(aux != null) {
                    lis.add(aux.getData());
                    List<GeneralTree<Integer>> children = aux.getChildren();
                    if(children.size() < num) {
                        cumpleNivel = false;
                    }
                    for(GeneralTree<Integer> h: children) {
                        cola.enqueue(h);
                    }
                } else { 
                    if(!cola.isEmpty()) {
                        cola.enqueue(null);
                    }
                    if(!cumpleNivel) {
                        lis.clear();
                        cumpleNivel = true;
                    } else {
                        ok = true;
                    }
                }
            }
        }
        return lis;
    }
    
    public List<Integer> nivelParcialMejorado(int num) {
        List<Integer> lis = new LinkedList<Integer>();
        if(!this.arb.isEmpty()) {
            Queue<GeneralTree<Integer>> cola = new Queue<GeneralTree<Integer>>();
            cola.enqueue(this.arb);
            boolean ok = false;
            boolean cumpleNivel = true;
            while(!cola.isEmpty() && !ok) {
                int size = cola.size();
                for(int i=0; i < size; i++) {
                    GeneralTree<Integer> aux = cola.dequeue();
                    List<GeneralTree<Integer>> children = aux.getChildren();
                    lis.add(aux.getData());
                    if(children.size() < num) {
                        cumpleNivel = false;
                    }
                    for(GeneralTree<Integer> h: children) {
                        cola.enqueue(h);
                    }
                }
                if(!cumpleNivel) {
                    lis.clear();
                    cumpleNivel = true;
                } else {
                    ok = true;
                }
            }
        }
        return lis;
    }
    
    public List<Integer> nivelParcial(int num) {
        List<Integer> lis = new LinkedList<Integer>();
        if(!this.arb.isEmpty()) {
            Queue<GeneralTree<Integer>> cola = new Queue<GeneralTree<Integer>>();
            cola.enqueue(this.arb);
            boolean ok = false;
            while(!cola.isEmpty() && !ok) {
                int size = cola.size();
                int cant = 0;
                for(int i=0; i < size; i++) {
                    GeneralTree<Integer> aux = cola.dequeue();
                    List<GeneralTree<Integer>> children = aux.getChildren();
                    if(children.size() >= num) {
                        lis.add(aux.getData());
                        cant++;
                    }
                    for(GeneralTree<Integer> h: children) {
                            cola.enqueue(h);
                    }
                }
                ok = cant == size;
                if(!ok) lis.clear();
            }
        }
        return lis;
    }
    
    /* public List<Integer> nivel(int num) {
        List<Integer> lis = new LinkedList<Integer>();
        if(!this.arb.isEmpty()) {
            Queue<GeneralTree<Integer>> cola = new Queue<GeneralTree<Integer>>();
            cola.enqueue(this.arb);
            boolean ok = false;
            while(!cola.isEmpty() && !ok) {
                int size = cola.size();
                for(int i=0; i < size; i++) {
                    GeneralTree<Integer> aux = cola.dequeue();
                    List<GeneralTree<Integer>> children = aux.getChildren();
                    if(children.size() >= num) {
                        lis.add(aux.getData());
                        ok = true;
                    } else {
                        for(GeneralTree<Integer> h: children) {
                            cola.enqueue(h);
                        }
                    }
                }
            }
        }
        return lis;
    } */
    
    public static void main(String[] args) {
        List<GeneralTree<Integer>> children1 = new LinkedList<GeneralTree<Integer>>();
        children1.add(new GeneralTree<Integer>(-6));
        children1.add(new GeneralTree<Integer>(2));
        children1.add(new GeneralTree<Integer>(8));
        GeneralTree<Integer> subAb1 = new GeneralTree<Integer>(5, children1);
        
        List<GeneralTree<Integer>> children2 = new LinkedList<GeneralTree<Integer>>();
        children2.add(new GeneralTree<Integer>(28));
        children2.add(new GeneralTree<Integer>(55));
        children2.add(new GeneralTree<Integer>(18));
        GeneralTree<Integer> subAb2 = new GeneralTree<Integer>(22, children2);
        
        List<GeneralTree<Integer>> children3 = new LinkedList<GeneralTree<Integer>>();
        children3.add(new GeneralTree<Integer>(4));
        children3.add(new GeneralTree<Integer>(2));
        children3.add(new GeneralTree<Integer>(8));
        GeneralTree<Integer> subAb3 = new GeneralTree<Integer>(19, children3);
        
        List<GeneralTree<Integer>> children4 = new LinkedList<GeneralTree<Integer>>();
        children4.add(subAb1);
        children4.add(subAb2);
        GeneralTree<Integer> a1 = new GeneralTree<Integer>(8, children4);
        
        List<GeneralTree<Integer>> children5 = new LinkedList<GeneralTree<Integer>>();
        children5.add(subAb3);
        GeneralTree<Integer> a2 = new GeneralTree<Integer>(-5, children5);
        
        List<GeneralTree<Integer>> ab = new LinkedList<GeneralTree<Integer>>();
        ab.add(a1);
        ab.add(a2);
        GeneralTree<Integer> a = new GeneralTree<Integer>(10, ab);
        
        ParcialArboles p = new ParcialArboles(a);
        
        System.out.println(p.nivel(3));
        System.out.println(p.nivel(4));
        
        System.out.println(p.nivelParcial(3));
        System.out.println(p.nivelParcial(4));
        
        System.out.println(p.nivelParcialMejorado(3));
        System.out.println(p.nivelParcialMejorado(4));
    }
    
}