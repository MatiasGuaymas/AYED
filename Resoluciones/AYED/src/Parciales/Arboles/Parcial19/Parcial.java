package Parciales.Arboles.Parcial19;

import java.util.*;
import Practica3.Ejercicio1y3y5.GeneralTree;

public class Parcial {
    public List<String> resolver(int menor, int mayor, GeneralTree<Integer> arbol) {
        List<String> may = new LinkedList<String>();
        if(!arbol.isEmpty()) {
            this.resolver(arbol, may, menor, mayor, 0);
        }
        return may;
    }
    
    private void resolver(GeneralTree<Integer> arbol, List<String> may, int menor, int mayor, int nivel) {
        for(GeneralTree<Integer> h: arbol.getChildren()) {
            this.resolver(h, may, menor, mayor, nivel+1);
        }
        int dato = arbol.getData();
        if(dato >= mayor && dato <= menor) may.add((dato + " nivel " + nivel));
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
        List<String> lis = p.resolver(10, -5, a);
        System.out.println(lis);
    }
}
