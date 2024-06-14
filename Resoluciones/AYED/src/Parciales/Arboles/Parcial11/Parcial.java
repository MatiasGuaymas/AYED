package Parciales.Arboles.Parcial11;

import Practica2.Ejercicio1y2.BinaryTree;
import java.util.*;

public class Parcial {
    public List<Integer> resolver(BinaryTree<Integer> arbol) {
        List<Integer> camino = new LinkedList<Integer>();
        if(!arbol.isEmpty()) {
            this.resolver(arbol, camino);
        }
        return camino;
    }
    
    private void resolver(BinaryTree<Integer> arbol, List<Integer> camino) {
        int cantIzq = 0;
        int cantDer = 0;
        boolean okIzq = arbol.hasLeftChild();
        boolean okDer = arbol.hasRightChild();
        if(okIzq) {
            cantIzq = this.contarNodos(arbol.getLeftChild());
            //this.resolver(arbol.getLeftChild(), camino); TIENE QUE SER PREORDEN
        }
        if(okDer) {
            cantDer = this.contarNodos(arbol.getRightChild());
            //this.resolver(arbol.getRightChild(), camino); TIENE QUE SER PREORDEN
        }
        if(cantIzq == cantDer) camino.add(arbol.getData());
        if(okIzq) this.resolver(arbol.getLeftChild(), camino);
        if(okDer) this.resolver(arbol.getRightChild(), camino);
    }
    
    private int contarNodos(BinaryTree<Integer> arbol) {
        int cant = 1;
        if(arbol.hasLeftChild()) {
            cant+= this.contarNodos(arbol.getLeftChild());
        }
        if(arbol.hasRightChild()) {
            cant+= this.contarNodos(arbol.getRightChild());
        }
        return cant;
    }
    
    public static void main(String[] args) {
        BinaryTree<Integer> ab = new BinaryTree<Integer>(2);
        ab.addLeftChild(new BinaryTree<Integer>(1));
        ab.getLeftChild().addLeftChild(new BinaryTree<Integer>(16));
        ab.getLeftChild().addRightChild(new BinaryTree<Integer>(6));
        ab.addRightChild(new BinaryTree<Integer>(5));
        ab.getRightChild().addRightChild(new BinaryTree<Integer>(8));
        ab.getRightChild().getRightChild().addLeftChild(new BinaryTree<Integer>(22));
        Parcial p = new Parcial();
        
        System.out.println(p.resolver(ab));
    }
}
