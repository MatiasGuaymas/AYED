package Parciales.Arboles.Parcial10;

import Practica3.Ejercicio1y3y5.GeneralTree;
import java.util.*;

public class CaminoHojaMasLejana {
    public List<Integer> caminoMasLargo(GeneralTree<Integer> ab) {
        List<Integer> lisMax = new LinkedList<Integer>();
        if(!ab.isEmpty()) {
            this.caminoMasLargo(ab, lisMax, new LinkedList<Integer>());
        }
        return lisMax;
    }
    
    private void caminoMasLargo(GeneralTree<Integer> ab, List<Integer> lisMax, List<Integer> lisAct) {
        lisAct.add(ab.getData());
        if(ab.isLeaf() && lisAct.size() > lisMax.size()) {
            lisMax.clear();
            lisMax.addAll(lisAct);
        } else {
            for(GeneralTree<Integer> h: ab.getChildren()) {
                this.caminoMasLargo(h, lisMax, lisAct);
            }
        }
        lisAct.remove(lisAct.remove(lisAct.size()-1));
    }
    
    public static void main(String main[]) {
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
        
        CaminoHojaMasLejana cam = new CaminoHojaMasLejana();
        System.out.println(cam.caminoMasLargo(a));
    }
}
