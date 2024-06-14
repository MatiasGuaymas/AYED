package Parciales.Arboles.Parcial7;

import Practica3.Ejercicio1y3y5.GeneralTree;
import java.util.*;

public class Parcial {
    
    public List<List<Character>> caminosPares(GeneralTree<Character> arbol){
        List<List<Character>> lis = new LinkedList<List<Character>>();
        if(!arbol.isEmpty()) {
            List<Character> camAct = new LinkedList<Character>();
            caminosPares(arbol, lis, camAct, 0);
        }
        return lis;
    }
    
    private void caminosPares(GeneralTree<Character> arbol, List<List<Character>> lis, List<Character> camActual, int cant) {
        cant++;
        camActual.add(arbol.getData());
        if(arbol.isLeaf() && cant % 2 == 0)
            lis.add(new LinkedList(camActual));
        else {
            for(GeneralTree<Character> a: arbol.getChildren()) {
                caminosPares(a, lis, camActual, cant);
            }
        }
        camActual.remove(camActual.size()-1);
    }
    
    public static void main(String args[]) {
        Parcial p = new Parcial();
        
        List<GeneralTree<Character>> subChildren1 = new LinkedList<GeneralTree<Character>>();
        subChildren1.add(new GeneralTree<Character>('E'));
        GeneralTree<Character> a1 = new GeneralTree<Character>('B', subChildren1);
        
        List<GeneralTree<Character>> subChildren2 = new LinkedList<GeneralTree<Character>>();
        subChildren2.add(new GeneralTree<Character>('H'));
        subChildren2.add(new GeneralTree<Character>('I'));
        GeneralTree<Character> subAb1 = new GeneralTree<Character>('F', subChildren2);
        List<GeneralTree<Character>> subChildren3 = new LinkedList<GeneralTree<Character>>();
        subChildren3.add(subAb1);
        subChildren3.add(new GeneralTree<Character>('G'));
        GeneralTree<Character> a2 = new GeneralTree<Character>('C', subChildren3);
        
        List<GeneralTree<Character>> arbol = new LinkedList<GeneralTree<Character>>();
        arbol.add(a1);
        arbol.add(a2);
        arbol.add(new GeneralTree<Character>('D'));
        GeneralTree<Character> a = new GeneralTree<Character>('A', arbol);
        
        List<List<Character>> lis = p.caminosPares(a);
        for(List<Character>l: lis) {
            System.out.println(l);
        }
    }
}
