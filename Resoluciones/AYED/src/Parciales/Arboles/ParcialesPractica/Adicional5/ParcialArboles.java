package Parciales.Arboles.ParcialesPractica.Adicional5;

import Practica3.Ejercicio1y3y5.*;
import java.util.*;

public class ParcialArboles {
    public static List<Integer> resolver(GeneralTree<Integer> arbol) {
        List<Integer> lis = new LinkedList<Integer>();
        if(!arbol.isEmpty()) {
            resolver(arbol, lis, new LinkedList<Integer>(), 0, 0, -9999);
        }
        return lis;
    }
    
    private static int resolver(GeneralTree<Integer> arbol, List<Integer> listaMax, List<Integer> camAct, int nivel, int suma, int max) {
        int dato = arbol.getData();
        boolean ok = dato == 1;
        if(ok) { 
            camAct.add(dato);
            suma+= dato * nivel;
        }
        if(!arbol.isLeaf()) {
            for(GeneralTree<Integer> h: arbol.getChildren()) {
                max = resolver(h, listaMax, camAct, nivel+1, suma, max);
            }
        } else if (suma > max) {
            max = suma;
            listaMax.clear();
            listaMax.addAll(camAct);
        }
        if(ok) camAct.remove(camAct.size()-1);
        return max;
    }
    
    public static void main(String[] args){
        List<GeneralTree<Integer>> subChildren1 = new LinkedList<GeneralTree<Integer>>();
        subChildren1.add(new GeneralTree<Integer>(1));
        subChildren1.add(new GeneralTree<Integer>(1));
        subChildren1.add(new GeneralTree<Integer>(1));
        GeneralTree<Integer> subA = new GeneralTree<Integer>(1, subChildren1);
        List<GeneralTree<Integer>> subChildren2 = new LinkedList<GeneralTree<Integer>>();
        subChildren2.add(subA);
        subChildren2.add(new GeneralTree<Integer>(1));
        GeneralTree<Integer> a1 = new GeneralTree<Integer>(0, subChildren2);
        
        List<GeneralTree<Integer>> subChildren3 = new LinkedList<GeneralTree<Integer>>();
        subChildren3.add(new GeneralTree<Integer>(1));
        GeneralTree<Integer> subB = new GeneralTree<Integer>(0, subChildren3);
        List<GeneralTree<Integer>> subChildren4 = new LinkedList<GeneralTree<Integer>>();
        subChildren4.add(subB);
        GeneralTree<Integer> subC = new GeneralTree<Integer>(0, subChildren4);
        List<GeneralTree<Integer>> subChildren5 = new LinkedList<GeneralTree<Integer>>();
        subChildren5.add(new GeneralTree<Integer>(1));
        subChildren5.add(subC);
        GeneralTree<Integer> a2 = new GeneralTree<Integer>(1, subChildren5);
        
        List<GeneralTree<Integer>> subChildren6 = new LinkedList<GeneralTree<Integer>>();
        subChildren6.add(new GeneralTree<Integer>(0));
        subChildren6.add(new GeneralTree<Integer>(0));
        GeneralTree<Integer> subD = new GeneralTree<Integer>(0, subChildren6);
        List<GeneralTree<Integer>> subChildren7 = new LinkedList<GeneralTree<Integer>>();
        subChildren7.add(subD);
        GeneralTree<Integer> subE = new GeneralTree<Integer>(0, subChildren7);
        List<GeneralTree<Integer>> subChildren8 = new LinkedList<GeneralTree<Integer>>();
        subChildren8.add(subE);
        GeneralTree<Integer> a3 = new GeneralTree<Integer>(1, subChildren8);
        
        List<GeneralTree<Integer>> arbol = new LinkedList<GeneralTree<Integer>>();
        arbol.add(a1);
        arbol.add(a2);
        arbol.add(a3);
        GeneralTree<Integer> a = new GeneralTree<Integer>(1, arbol);
        
        System.out.println(resolver(a));
        
    }
}
