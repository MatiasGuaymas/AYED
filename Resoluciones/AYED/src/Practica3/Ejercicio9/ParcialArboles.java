/* Ejercicio 9 
Implemente en la clase ParcialArboles el método:
public static boolean esDeSeleccion (GeneralTree<Integer> arbol)
que devuelve true si el árbol recibido por parámetro es de selección, falso sino lo es.
Un árbol general es de selección si cada nodo tiene en su raíz el valor del menor de sus hijos */

package Practica3.Ejercicio9;

import Practica3.Ejercicio1y3y5.GeneralTree;
import Practica1.Ejercicio8.Queue;
import java.util.*;

public class ParcialArboles {
    /* Solucion ineficiente: hacerlo de manera recursiva, provoca mas uso de memoria. Es mejor resolver este punto de manera iterativa, de igual manera cumple con lo solicitado.
    public static boolean esDeSeleccion (GeneralTree<Integer> arbol) {
        return (!arbol.isEmpty()) ? esDeSeleccionHelper(arbol) : false;
    }
    
    private static boolean esDeSeleccionHelper(GeneralTree<Integer> arbol) {
        if(arbol.isLeaf()) {
            return true;
        } else {
            Integer min = Integer.MAX_VALUE;
            boolean ok = true;
            List<GeneralTree<Integer>> children = arbol.getChildren();
            Iterator<GeneralTree<Integer>> iterator = children.iterator(); //Itero con un Iterator
            while ((iterator.hasNext()) && (ok)) { //Uso un while, porque con FOR me retornaba siempre true. Pasaba que ok se quedaba con el ultimo caso, y si este era true, todo era verdadero.
                GeneralTree<Integer> child = iterator.next();
                min = Math.min(min, child.getData());
                ok = esDeSeleccionHelper(child);
            }
            return ((min.equals(arbol.getData())) && (ok));
        }
    } */
    
    public static boolean esDeSeleccion(GeneralTree<Integer> arbol) {
        Queue<GeneralTree<Integer>> q = new Queue<GeneralTree<Integer>>();
        boolean ok = true; //Se considera que un arbol vacio devolvera true
        if(!arbol.isEmpty()) {
            q.enqueue(arbol);
            while(!q.isEmpty() && ok) {
                GeneralTree<Integer> aux = q.dequeue();
                int min = Integer.MAX_VALUE;
                for (GeneralTree<Integer> h: aux.getChildren()) {
                    q.enqueue(h);
                    min = Math.min(min, h.getData());
                }
                if(!aux.isLeaf() && aux.getData() != min) ok = false;
            }
        }
        return ok;
    }
    
    public static void main(String[] args) {
        List <GeneralTree<Integer>> subChildren1 = new LinkedList <GeneralTree<Integer>>();
        subChildren1.add(new GeneralTree<Integer>(35));
        GeneralTree<Integer> subAb1 = new GeneralTree<Integer>(35, subChildren1);
        
        List <GeneralTree<Integer>> subChildren2 = new LinkedList <GeneralTree<Integer>>();
        subChildren2.add(subAb1);
        GeneralTree<Integer> subAb2 = new GeneralTree<Integer>(35, subChildren2);
        
        List <GeneralTree<Integer>> subChildren3 = new LinkedList <GeneralTree<Integer>>();
        subChildren3.add(new GeneralTree<Integer>(35));
        subChildren3.add(new GeneralTree<Integer>(83));
        subChildren3.add(new GeneralTree<Integer>(90));
        subChildren3.add(new GeneralTree<Integer>(33));
        GeneralTree<Integer> subAb3 = new GeneralTree<Integer>(33, subChildren3);
        
        List <GeneralTree<Integer>> subChildren4 = new LinkedList <GeneralTree<Integer>>();
        subChildren4.add(new GeneralTree<Integer>(14));
        subChildren4.add(new GeneralTree<Integer>(12));
        //subChildren4.add(new GeneralTree<Integer>(18));
        subChildren4.add(subAb3);
        GeneralTree<Integer> subAb4 = new GeneralTree<Integer>(12, subChildren4);
        //GeneralTree<Integer> subAb4 = new GeneralTree<Integer>(18, subChildren4);
        
        List <GeneralTree<Integer>> subArbIzq = new LinkedList <GeneralTree<Integer>>();
        subArbIzq.add(subAb2);
        subArbIzq.add(subAb4);
        GeneralTree<Integer> arbIzq = new GeneralTree<Integer>(12, subArbIzq);
        
        List <GeneralTree<Integer>> subArbDer = new LinkedList <GeneralTree<Integer>>();
        subArbDer.add(new GeneralTree<Integer>(25));
        GeneralTree<Integer> arbDer = new GeneralTree<Integer>(25, subArbDer);
        
        List <GeneralTree<Integer>> arbol = new LinkedList <GeneralTree<Integer>>();
        arbol.add(arbIzq);
        arbol.add(arbDer);
        GeneralTree<Integer> a = new GeneralTree<Integer>(12, arbol); 
        
        System.out.println("Es de seleccion el primer arbol: " + esDeSeleccion(a));
        
        /*No iba a hacer otro arbol, probe seteando los datos y me daba true igual,
        la idea es descomentar esto y las otras lineas comentadas para probar el segundo
        arbol de la practica mostrado como ejemplo. Ademas de comentar los nodos con valor 12*/
        //System.out.println("Es de seleccion el segundo arbol: " + esDeSeleccion(a)); 
    }
}
