/* Ejercicio 2
a) Implemente en la clase RecorridosAG los siguientes métodos:
public List<Integer> numerosImparesMayoresQuePreOrden (GeneralTree <Integer> a,
Integer n)
Método que retorna una lista con los elementos impares del árbol “a” que sean mayores al valor “n”
pasados como parámetros, recorrido en preorden.
public List<Integer> numerosImparesMayoresQueInOrden (GeneralTree <Integer> a,
Integer n)
Método que retorna una lista con los elementos impares del árbol “a” que sean mayores al valor “n”
pasados como parámetros, recorrido en inorden.
public List<Integer> numerosImparesMayoresQuePostOrden (GeneralTree <Integer> a,
Integer n)
Método que retorna una lista con los elementos impares del árbol “a” que sean mayores al valor “n”
pasados como parámetros, recorrido en postorden.
public List<Integer> numerosImparesMayoresQuePorNiveles(GeneralTree <Integer> a,
Integer n)
Método que retorna una lista con los elementos impares del árbol “a” que sean mayores al valor “n”
pasados como parámetros, recorrido por niveles. */

package Practica3.Ejercicio2;

import java.util.*;
import Practica3.Ejercicio1y3y5.GeneralTree;
import Practica1.Ejercicio8.Queue;

public class RecorridosAG {
    private GeneralTree<Integer> a;
    
    public RecorridosAG() {}
    
    public RecorridosAG(GeneralTree<Integer> a) {
        this.a = a;
    }
    
    public List <Integer> numerosImparesMayoresQuePreOrden(GeneralTree <Integer> a, Integer n) {
        List <Integer> l = new LinkedList<Integer>();
        if(!a.isEmpty()) this.numerosImparesMayoresQuePreOrden(a, n, l);
        return l;
    }
    
    private void numerosImparesMayoresQuePreOrden(GeneralTree <Integer> a, Integer n, List <Integer> l) {
        int dato = a.getData();
        if(dato %2 != 0 && dato > n) l.add(dato);
        List<GeneralTree<Integer>> children = a.getChildren();
        for(GeneralTree<Integer> child: children) {
            numerosImparesMayoresQuePreOrden(child, n, l);
        }
    }
    
    public List<Integer> numerosImparesMayoresQueInOrden (GeneralTree <Integer> a, Integer n) {
        List <Integer> l = new LinkedList<Integer>();
        if(!a.isEmpty()) this.numerosImparesMayoresQueInOrden(a, n, l);
        return l;
    }

    private void numerosImparesMayoresQueInOrden (GeneralTree <Integer> a, Integer n, List <Integer> l) {
        List<GeneralTree<Integer>> children = a.getChildren();
        if(a.hasChildren()) {
            numerosImparesMayoresQueInOrden(children.get(0), n, l);
        }
        int dato = a.getData();
        if(dato %2 != 0 && dato > n) l.add(dato);
        for(int i=1; i < children.size(); i++) {
            numerosImparesMayoresQueInOrden(children.get(i), n, l);
        }
    }
    
    public List<Integer> numerosImparesMayoresQuePostOrden (GeneralTree <Integer> a, Integer n) {
        List <Integer> l = new LinkedList<Integer>();
        if(!a.isEmpty()) this.numerosImparesMayoresQuePostOrden(a, n, l);
        return l;
    }
    
    private void numerosImparesMayoresQuePostOrden (GeneralTree <Integer> a, Integer n, List<Integer> l) {
        List<GeneralTree<Integer>> children = a.getChildren();
        for (GeneralTree<Integer> child: children) {
            numerosImparesMayoresQuePostOrden(child, n, l);
        }
        int dato = a.getData();
        if(dato %2 != 0 && dato > n) l.add(dato);
    }
    
    public List<Integer> numerosImparesMayoresQuePorNiveles(GeneralTree <Integer> a, Integer n) {
        List<Integer> result = new LinkedList<Integer>();
        GeneralTree<Integer> aux;
        Queue<GeneralTree<Integer>> queue = new Queue<GeneralTree<Integer>>();
        queue.enqueue(a);
        while(!queue.isEmpty()) {
            aux = queue.dequeue();
            if(!aux.isEmpty()) {
                int dato = aux.getData();
                if(dato %2 != 0 && dato > n) result.add(dato);
            }
            List<GeneralTree<Integer>> children = aux.getChildren();
            for(GeneralTree<Integer> child: children) {
                queue.enqueue(child);
            }
        }
        return result;
    }
    
    public static void main(String[] args) {
        GeneralTree<Integer> a1 = new GeneralTree<Integer>(1);
        List<GeneralTree<Integer>> children2 = new LinkedList<GeneralTree<Integer>>();
        children2.add(new GeneralTree<Integer>(21));
        children2.add(new GeneralTree<Integer>(22));
        children2.add(new GeneralTree<Integer>(23));
        GeneralTree<Integer> a2 = new GeneralTree<Integer>(2, children2);
        List<GeneralTree<Integer>> children3 = new LinkedList<GeneralTree<Integer>>();
        children3.add(new GeneralTree<Integer>(31));
        children3.add(new GeneralTree<Integer>(32));
        GeneralTree<Integer> a3 = new GeneralTree<Integer>(3, children3);
        List<GeneralTree<Integer>> childen = new LinkedList<GeneralTree<Integer>>();
        childen.add(a1);childen.add(a2);childen.add(a3);
        GeneralTree<Integer> a = new GeneralTree<Integer>(11, childen);
        
        System.out.println("AlturaMax=" + a.altura());
        System.out.println("En que nivel esta el num 11? Nivel:" + a.nivel(11));
        System.out.println("En que nivel esta el num 3? Nivel:" + a.nivel(3));
        System.out.println("En que nivel esta el num 21? Nivel:" + a.nivel(21));
        System.out.println("Ancho=" + a.ancho());
        System.out.println("ES ANCESTRO 11 DE 2:" + a.esAncestro(11, 2));
        System.out.println("ES ANCESTRO 2 DE 11:" + a.esAncestro(2, 11));
        System.out.println("ES ANCESTRO 11 DE 21:" + a.esAncestro(11, 21));
        System.out.println("ES ANCESTRO 21 DE 11:" + a.esAncestro(21, 11));
        
        RecorridosAG rec = new RecorridosAG();
        
        System.out.println("PREORDEN:" + rec.numerosImparesMayoresQuePreOrden(a, 0));
        System.out.println("INORDEN:" + rec.numerosImparesMayoresQueInOrden(a, 0));
        System.out.println("POSTORDEN:" + rec.numerosImparesMayoresQuePostOrden(a, 0));
        System.out.println("POR NIVELES:" + rec.numerosImparesMayoresQuePorNiveles(a, 0));        
 }

}