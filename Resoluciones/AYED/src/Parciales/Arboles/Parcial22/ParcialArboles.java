package Parciales.Arboles.Parcial22;

import Practica3.Ejercicio1y3y5.GeneralTree;
import java.util.*;


public class ParcialArboles {
    private GeneralTree<Integer> arbol;
    
    public ParcialArboles(GeneralTree<Integer> arbol) {
        this.arbol = arbol;
    }
    
    public List<Integer> camino (int num) {
        List<Integer> cam = new LinkedList<Integer>();
        if(!this.arbol.isEmpty()) {
            this.camino(arbol, cam, num);
        }
        return cam;
    }
    
    private boolean camino(GeneralTree<Integer> arbol, List<Integer> camino, int num) {
        boolean encontre = false;
        camino.add(arbol.getData());
        if(arbol.isLeaf()) {
            return true;
        } else {
            List<GeneralTree<Integer>> children = arbol.getChildren();
            int hijos = children.size();
            if(hijos >= num) {
                Iterator<GeneralTree<Integer>> it = children.iterator();
                while(it.hasNext() && !encontre) {
                    encontre = this.camino(it.next(), camino, num);
                }
            }
        }
        if (!encontre) camino.remove(camino.size()-1);
        return encontre;
    }
    
    public static void main(String args[]) {
        GeneralTree<Integer> arbol = new GeneralTree<Integer>(10);
        
        GeneralTree<Integer> subAb1 = new GeneralTree<Integer>(5);
        subAb1.addChild(new GeneralTree<Integer>(-6));
        GeneralTree<Integer> subAb2 = new GeneralTree<Integer>(22);
        subAb2.addChild(new GeneralTree<Integer>(28));
        subAb2.addChild(new GeneralTree<Integer>(55));
        subAb2.addChild(new GeneralTree<Integer>(18));
        GeneralTree<Integer> a1 = new GeneralTree<Integer>(8);
        a1.addChild(subAb1);
        a1.addChild(subAb2);
        
        arbol.addChild(a1);
        arbol.addChild(new GeneralTree<Integer>(42));
        
        GeneralTree<Integer> subAb3 = new GeneralTree<Integer>(19);
        subAb3.addChild(new GeneralTree<Integer>(4));
        GeneralTree<Integer> a2 = new GeneralTree<Integer>(-5);
        a2.addChild(subAb3);
        a2.addChild(new GeneralTree<Integer>(-9));
        
        arbol.addChild(a2);
        
        ParcialArboles p = new ParcialArboles(arbol);
        
        System.out.println(p.camino(2));
        System.out.println(p.camino(3));
    }
}
