package Parciales.Arboles.Parcial12;

import Practica3.Ejercicio1y3y5.GeneralTree;
import java.util.*;

public class Recorrido {
    
    public int recorridoPostOrdenMax(GeneralTree<Integer> arbol) {
        return !arbol.isEmpty() ?  this.max(arbol, Integer.MIN_VALUE) : Integer.MIN_VALUE;
    }
    
    private int max(GeneralTree<Integer> arbol, int aux) {
        for(GeneralTree<Integer> h: arbol.getChildren()) {
            aux = this.max(h, aux);
        }
        aux = Math.max(arbol.getData(), aux);
        return aux;
    }
    
    public static void main(String[] args) {
        List<GeneralTree<Integer>> subChildren1 = new LinkedList<GeneralTree<Integer>>();
        subChildren1.add(new GeneralTree<Integer>(2));
        GeneralTree<Integer> subAb1 = new GeneralTree<Integer>(7, subChildren1);
        List<GeneralTree<Integer>> subChildren2 = new LinkedList<GeneralTree<Integer>>();
        subChildren2.add(new GeneralTree<Integer>(4));
        subChildren2.add(subAb1);
        subChildren2.add(new GeneralTree<Integer>(6));
        GeneralTree<Integer> a1 = new GeneralTree<Integer>(3, subChildren2);
        
        List<GeneralTree<Integer>> subChildren3 = new LinkedList<GeneralTree<Integer>>();
        subChildren3.add(new GeneralTree<Integer>(1));
        subChildren3.add(new GeneralTree<Integer>(9));
        subChildren3.add(new GeneralTree<Integer>(10));
        GeneralTree<Integer> a2 = new GeneralTree<Integer>(5, subChildren3);
        
        List<GeneralTree<Integer>> arbol = new LinkedList<GeneralTree<Integer>>();
        arbol.add(a1);
        arbol.add(a2);
        GeneralTree<Integer> a = new GeneralTree<Integer>(8, arbol);
        
        Recorrido r = new Recorrido();
        System.out.println(r.recorridoPostOrdenMax(a));
    }
    
}
