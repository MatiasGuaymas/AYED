package Parciales.Arboles.Parcial20;

import java.util.*;
import Practica3.Ejercicio1y3y5.GeneralTree;

public class Parcial {
    private GeneralTree<Integer> arb;
    
    public Parcial(GeneralTree<Integer> arb) {
        this.arb = arb;
    }
    
    public List<Integer> resolver() {
        List<Integer> lis = new LinkedList<Integer>();
        if(!this.arb.isEmpty()) {
            this.resolver(this.arb, lis);
        }
        return lis;
    }
    
    private void resolver(GeneralTree<Integer> arb, List<Integer> lis) {
        if(arb.hasChildren()) {
            for(GeneralTree<Integer> h: arb.getChildren()) {
                this.resolver(h, lis);
            }
        } else {
            int dato = arb.getData();
            if(dato % 2 == 0) lis.add(dato);
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
        
        Parcial p = new Parcial(a);
        List<Integer> lis = p.resolver();
        System.out.println(lis);
    }
}
