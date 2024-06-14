package Parciales.Arboles.Parcial17;

import Practica1.Ejercicio8.Queue;
import java.util.*;
import Practica3.Ejercicio1y3y5.GeneralTree;

public class Parcial {
    public Integer resolver (GeneralTree<Integer> arbol, Integer min, Integer max) {
        if(!arbol.isEmpty()) {
            SumaProm sumProm = new SumaProm();
            this.resolver(arbol, min, max, sumProm);
            return sumProm.prom();
        } else return 0;
    }
    
    private void resolver(GeneralTree<Integer> arbol, Integer min, Integer max, SumaProm sumProm) {
        List<GeneralTree<Integer>> lis = arbol.getChildren();
        if(lis.size() >= 1) this.resolver(lis.get(0), min, max, sumProm);
        int dato = arbol.getData();
        if(dato >= min && dato <= max) {
            sumProm.incrementar(dato);
        }
        for(int i=1; i < lis.size(); i++) {
            this.resolver(lis.get(i), min, max, sumProm);
        }
    }
    
    public Integer resolverNiveles(GeneralTree<Integer> arbol, Integer min, Integer max) {
        int suma = 0;
        if(!arbol.isEmpty()) {
            Queue<GeneralTree<Integer>> cola = new Queue<GeneralTree<Integer>>();
            int cant = 0;
            if(arbol.getData() >= min && arbol.getData() <= max) {
                cant++;
                suma+= arbol.getData();
            }
            cola.enqueue(arbol);
            while(!cola.isEmpty()) {
                int act = cola.size();
                for(int i = 0; i < act; i++) {
                    GeneralTree<Integer> aux = cola.dequeue();
                    for(GeneralTree<Integer> h: aux.getChildren()) {
                        int dato = h.getData();
                        if(dato >= min && dato <= max) {
                            cant++;
                            suma+= dato;
                        }
                        cola.enqueue(h);
                    }
                }
            }
            suma/= cant;
        }
        return suma;
    }
    
    public static void main(String[] args) {
        List<GeneralTree<Integer>> subChildren1 = new LinkedList<GeneralTree<Integer>>();
        subChildren1.add(new GeneralTree<Integer>(2));
        GeneralTree<Integer> subAb1 = new GeneralTree<Integer>(7, subChildren1);
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
        System.out.println(p.resolver(a, 2, 8));
        System.out.println(p.resolverNiveles(a, 2, 8));
    }
}
