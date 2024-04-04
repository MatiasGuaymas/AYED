package Practica2.Ejercicio6;

import java.util.*;
import Practica2.Ejercicio1y2.BinaryTree;

public class Transformacion {
    private BinaryTree<Integer> ab;
    
    public Transformacion(BinaryTree<Integer> unArbol) {
        ab = unArbol;
    }

    public BinaryTree<Integer> getAb() {
        return ab;
    }
    
    public BinaryTree <Integer> suma() {
        suma(ab);
        return ab;
    }
    
    private int suma(BinaryTree<Integer>ab) {
        int sum = 0;
        if(ab.isLeaf()) {
            sum = ab.getData();
            ab.setData(0);
            return sum;
        }
        if(ab.hasLeftChild()) {
            sum+= suma(ab.getLeftChild());
        }
        if(ab.hasRightChild()) {
            sum+= suma(ab.getRightChild());
        }
        int actual = ab.getData();
        ab.setData(sum);
        return actual + sum;
    }
    
    public static void main (String[] args) {
        System.out.println("Test Transformacion");
        BinaryTree<Integer> ab = new BinaryTree<Integer>(4);
        ab.addLeftChild(new BinaryTree<Integer>(2));
        ab.addRightChild(new BinaryTree<Integer>(6));
        ab.getLeftChild().addLeftChild(new BinaryTree<Integer>(1));
        ab.getLeftChild().addRightChild(new BinaryTree<Integer>(3));
        ab.getRightChild().addLeftChild(new BinaryTree<Integer>(5));
        ab.getRightChild().addRightChild(new BinaryTree<Integer>(8));
        
        ab.imprimirArbol();
        Transformacion tras = new Transformacion(ab);
        tras.suma();
        System.out.println();
        System.out.println("Arbol transformado");
        tras.getAb().imprimirArbol();
    }
}
