package Parciales.Arboles.Parcial14;

import java.util.*;
import Practica3.Ejercicio1y3y5.GeneralTree;

public class Parcial {
    public Integer resolver(GeneralTree<Integer> arb) {
        if(!arb.isEmpty()) {
            SumaNodos sum = new SumaNodos();
            this.resolver(arb, sum);
            return sum.getCantNodos() % 2 == 1 ? sum.getCantNodosNeg() : sum.getCantNodosPos();
        } else return 0;
    }
    
    private void resolver(GeneralTree<Integer> arb, SumaNodos sum) {
        List<GeneralTree<Integer>> lis = arb.getChildren();
        if(lis.size() >= 1) this.resolver(lis.get(0), sum);
        int dato = arb.getData();
        sum.incrementarNodos(dato);
        for(int i = 1; i < lis.size(); i++) {
            this.resolver(lis.get(i), sum);
        }
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
        System.out.println(p.resolver(a));
    }
}
