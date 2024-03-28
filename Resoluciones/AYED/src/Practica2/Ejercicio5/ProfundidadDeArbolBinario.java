/* Ejercicio 5
Implemente una clase Java llamada ProfundidadDeArbolBinario que tiene como variable de
instancia un árbol binario de números enteros y un método de instancia
sumaElementosProfundidad (int p):int el cuál devuelve la suma de todos los nodos del árbol
que se encuentren a la profundidad pasada como argumento */

package Practica2.Ejercicio5;
import Practica2.Ejercicio1y2.BinaryTree;

public class ProfundidadDeArbolBinario {
    private BinaryTree <Integer> ab;
    
    public ProfundidadDeArbolBinario(BinaryTree<Integer> unArbol) {
        ab = unArbol;
    }
    
    public int sumaElementosProfundidad(int p) {
        return (!ab.isEmpty()) ? sumaElementosProfundidad(p, ab, 0) : 0;
    }
    
    private int sumaElementosProfundidad(int p, BinaryTree<Integer> ab, int nivActual) {
        if(p == nivActual) {
            return ab.getData();
        } else {
            int suma = 0;
            if(ab.hasLeftChild()) suma+= sumaElementosProfundidad(p, ab.getLeftChild(), nivActual+1);
            if(ab.hasRightChild()) suma+= sumaElementosProfundidad(p, ab.getRightChild(), nivActual+1);
            return suma;
        }
    }
    
    public static void main (String[] args) {
        System.out.println("Test Profundidad");
        BinaryTree<Integer> ab = new BinaryTree<Integer>(4);
        ab.addLeftChild(new BinaryTree<Integer>(2));
        ab.addRightChild(new BinaryTree<Integer>(6));
        ab.getLeftChild().addLeftChild(new BinaryTree<Integer>(1));
        ab.getLeftChild().addRightChild(new BinaryTree<Integer>(3));
        ab.getRightChild().addLeftChild(new BinaryTree<Integer>(5));
        ab.getRightChild().addRightChild(new BinaryTree<Integer>(8));
        
        ProfundidadDeArbolBinario prof = new ProfundidadDeArbolBinario(ab);
        System.out.println(prof.sumaElementosProfundidad(2));
        
    }
}
