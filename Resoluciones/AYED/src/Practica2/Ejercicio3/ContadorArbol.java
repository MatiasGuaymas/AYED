/* Ejercicio 3
Defina una clase Java denominada ContadorArbol cuya función principal es proveer métodos de
validación sobre árboles binarios de enteros. Para ello la clase tiene como variable de instancia un
BinaryTree<Integer>. Implemente en dicha clase un método denominado numerosPares() que
devuelve en una estructura adecuada (sin ningún criterio de orden) todos los elementos pares del
árbol (divisibles por 2).
a) Implemente el método realizando un recorrido InOrden.
b) Implemente el método realizando un recorrido PostOrden. */

package Practica2.Ejercicio3;

import java.util.*;
import Practica1.Ejercicio8.Queue;
import Practica2.Ejercicio1y2.BinaryTree;

public class ContadorArbol {
    private BinaryTree<Integer> a;
    
    public ContadorArbol(BinaryTree<Integer> unArbol) {
        a = unArbol;
    }
    
    public List<Integer> numerosParesPre() {
        List<Integer> l = new LinkedList<Integer>();
        if(!a.isEmpty()) this.nParesPRE(l, a);
        return l;
    }
    
    private void nParesPRE(List<Integer> l, BinaryTree<Integer> a) {
        if(a.getData()%2==0) l.add(a.getData());
        if(a.hasLeftChild()) nParesPRE(l, a.getLeftChild());
        if(a.hasRightChild()) nParesPRE(l, a.getRightChild());
    }
    
    public List<Integer> numerosParesIn() {
        List<Integer> l = new LinkedList<Integer>();
        if(!a.isEmpty()) this.nParesIN(l, a);
        return l;
    }
    
    private void nParesIN(List<Integer> l, BinaryTree<Integer> a) {
        if(a.hasLeftChild()) nParesIN(l, a.getLeftChild());
        if(a.getData()%2==0) l.add(a.getData());
        if(a.hasRightChild()) nParesIN(l, a.getRightChild());
    }
    
    public List<Integer> numerosParesPost() {
        List<Integer> l = new LinkedList<Integer>();
        if(!a.isEmpty()) this.nParesPOST(l, a);
        return l;
    }
    
    private void nParesPOST(List<Integer> l, BinaryTree<Integer> a) {
        if(a.hasLeftChild()) nParesPOST(l, a.getLeftChild());
        if(a.hasRightChild()) nParesPOST(l, a.getRightChild());
        if(a.getData()%2==0) l.add(a.getData());
    }
    
    public void printLevelTraversal() {
        BinaryTree<Integer> ab = null;
        Queue<BinaryTree<Integer>> cola = new Queue<BinaryTree<Integer>>();
        cola.enqueue(a);
        cola.enqueue(null);
        while (!cola.isEmpty()) {
            ab = cola.dequeue();
            if (ab != null) {
                System.out.print(ab.getData() + " | ");
                if (ab.hasLeftChild()) {
                    cola.enqueue(ab.getLeftChild());
                }
                if (ab.hasRightChild()) {
                    cola.enqueue(ab.getRightChild());
                }
            } else if (!cola.isEmpty()) {
                System.out.println();
                cola.enqueue(null);
            }
        }
 }

    
    public static void main (String[] args) {
        System.out.println("Test numeros Pares");
        BinaryTree<Integer> ab = new BinaryTree<Integer>(4);
        ab.addLeftChild(new BinaryTree<Integer>(2));
        ab.addRightChild(new BinaryTree<Integer>(6));
        ab.getLeftChild().addLeftChild(new BinaryTree<Integer>(1));
        ab.getLeftChild().addRightChild(new BinaryTree<Integer>(3));
        ab.getRightChild().addLeftChild(new BinaryTree<Integer>(5));
        ab.getRightChild().addRightChild(new BinaryTree<Integer>(8));
        
        ContadorArbol c = new ContadorArbol(ab);
        System.out.println("Imprimir por niveles:");
        c.printLevelTraversal();
        
        System.out.println("");
        List<Integer> lisIN = c.numerosParesIn();
        System.out.println("Los nodos pares del arbol en INORDER son: ");
        System.out.println(lisIN);
        
        List<Integer> lisPRE = c.numerosParesPre();
        System.out.println("Los nodos pares del arbol en PREORDER son: ");
        System.out.println(lisPRE);
        
        List<Integer> lisPOST = c.numerosParesPost();
        System.out.println("Los nodos pares del arbol en POSTORDER son: ");
        System.out.println(lisPOST);
    }
}
