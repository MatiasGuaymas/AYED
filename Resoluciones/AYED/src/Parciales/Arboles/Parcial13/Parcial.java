package Parciales.Arboles.Parcial13;

import Practica3.Ejercicio1y3y5.GeneralTree;
import java.util.*;

public class Parcial {
    public Integer resolver(GeneralTree<Integer> arb) {
        if(!arb.isEmpty()) {
            SumaResultados sum = new SumaResultados();
            this.resolver(arb, sum);
            return (sum.getSumaTotal() % 2 == 1) ? sum.getSumaNegativos() : sum.getSumaPositivos();
        } else return 0;
    }
    
    private void resolver(GeneralTree<Integer> arb, SumaResultados sum) {
        for(GeneralTree<Integer> h: arb.getChildren()) {
            this.resolver(h, sum);
        }
        int dato = arb.getData();
        sum.incrementarTotal(dato);
        if(dato >= 0) sum.incrementarPositivos(dato);
        else sum.incrementarNegativos(dato);
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
