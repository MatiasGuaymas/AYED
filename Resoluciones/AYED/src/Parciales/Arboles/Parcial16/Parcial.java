package Parciales.Arboles.Parcial16;

import java.util.*;
import Practica1.Ejercicio8.Queue;
import Practica3.Ejercicio1y3y5.GeneralTree;

public class Parcial {
    public int resolver(GeneralTree<Integer> arb) {
        int suma = 0;
        if(!arb.isEmpty()) {
            Queue<GeneralTree<Integer>> cola = new Queue<GeneralTree<Integer>>();
            cola.enqueue(arb);
            while(!cola.isEmpty()) {
                int act = cola.size();
                suma = 1;
                for(int i = 0; i < act; i++) {
                    GeneralTree<Integer> aux = cola.dequeue();
                    if(aux.isLeaf()) {
                        suma *= aux.getData();
                    } else {
                        for(GeneralTree<Integer> h: aux.getChildren()) {
                            cola.enqueue(h);
                        }
                    }
                }
            }
        }
        return suma;
    }
    
    public static void main(String[] args) {
        //List<GeneralTree<Integer>> subChildren1 = new LinkedList<GeneralTree<Integer>>();
        //subChildren1.add(new GeneralTree<Integer>(2));
        GeneralTree<Integer> subAb1 = new GeneralTree<Integer>(7);
        List<GeneralTree<Integer>> subChildren2 = new LinkedList<GeneralTree<Integer>>();
        subChildren2.add(new GeneralTree<Integer>(-4));
        subChildren2.add(subAb1);
        subChildren2.add(new GeneralTree<Integer>(-6));
        GeneralTree<Integer> a1 = new GeneralTree<Integer>(3, subChildren2);
        
        List<GeneralTree<Integer>> subChildren3 = new LinkedList<GeneralTree<Integer>>();
        subChildren3.add(new GeneralTree<Integer>(1));
        subChildren3.add(new GeneralTree<Integer>(-9));
        subChildren3.add(new GeneralTree<Integer>(10));
        GeneralTree<Integer> a2 = new GeneralTree<Integer>(5, subChildren3);
        
        List<GeneralTree<Integer>> arbol = new LinkedList<GeneralTree<Integer>>();
        arbol.add(a1);
        arbol.add(a2);
        GeneralTree<Integer> a = new GeneralTree<Integer>(-7, arbol);
        
        Parcial p = new Parcial();
        System.out.println(p.resolver(a));
    }
}
