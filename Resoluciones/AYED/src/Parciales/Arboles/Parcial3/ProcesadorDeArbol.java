package Parciales.Arboles.Parcial3;

import java.util.*;
import Practica2.Ejercicio1y2.BinaryTree;

public class ProcesadorDeArbol {
    private BinaryTree<Integer> ab;
    
    public ProcesadorDeArbol(BinaryTree<Integer> ab) {
        this.ab = ab;
    }
    
    public Parcial procesar() {
        int cant = 0;
        List<BinaryTree<Integer>> lis = new LinkedList<BinaryTree<Integer>>();
        if(!ab.isEmpty()) cant = procesar(ab, lis);
        return new Parcial(lis, cant);
    }
    
    private int procesar(BinaryTree<Integer> ab, List<BinaryTree<Integer>> lis) {
        int num = ab.getData();
        int cant = 0;
        if(num %2 == 0) {
            cant++;
            if(ab.hasLeftChild() && ab.hasRightChild()) lis.add(ab);
        }
        if(ab.hasLeftChild()) cant+= procesar(ab.getLeftChild(), lis);
        if(ab.hasRightChild()) cant+= procesar(ab.getRightChild(), lis);
        return cant;
    }
    
    public static void main(String[] args) {
        BinaryTree<Integer> ab = new BinaryTree<Integer>(2);
        ab.addLeftChild(new BinaryTree<Integer>(7));
        ab.addRightChild(new BinaryTree<Integer>(-5));
        ab.getLeftChild().addLeftChild(new BinaryTree<Integer>(23));
        ab.getLeftChild().addRightChild(new BinaryTree<Integer>(6));
        ab.getLeftChild().getLeftChild().addLeftChild(new BinaryTree<Integer>(-3));
        ab.getLeftChild().getRightChild().addLeftChild(new BinaryTree<Integer>(55));
        ab.getLeftChild().getRightChild().getLeftChild().addLeftChild(new BinaryTree<Integer>(9));
        ab.getLeftChild().getRightChild().getLeftChild().addRightChild(new BinaryTree<Integer>(16));
        ab.getRightChild().addLeftChild(new BinaryTree<Integer>(19));
        ab.getRightChild().addRightChild(new BinaryTree<Integer>(4));
        ab.getRightChild().getRightChild().addRightChild(new BinaryTree<Integer>(18));
        ab.getRightChild().getRightChild().getRightChild().addLeftChild(new BinaryTree<Integer>(8));
        ab.getRightChild().getRightChild().getRightChild().addRightChild(new BinaryTree<Integer>(24));
        ab.entreNiveles(0, 4);
        
        ProcesadorDeArbol p = new ProcesadorDeArbol(ab);
        Parcial parc = p.procesar();
        
        System.out.println("");
        
        System.out.println(parc.getLis());
        System.out.println(parc.getCant());
    }
}
