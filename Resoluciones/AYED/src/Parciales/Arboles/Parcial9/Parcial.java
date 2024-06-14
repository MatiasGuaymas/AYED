package Parciales.Arboles.Parcial9;

import Practica2.Ejercicio1y2.BinaryTree;

public class Parcial {
    public Integer sumaImparesPosOrdenMayorA(BinaryTree<Integer> ab, int limite) {
        return !ab.isEmpty() ? this.contar(ab, limite) : 0;
    }
    
    private int contar(BinaryTree<Integer> ab, int limite) {
        int cant = 0;
        if(ab.hasLeftChild()) cant+= this.contar(ab.getLeftChild(), limite);
        if(ab.hasRightChild()) cant+= this.contar(ab.getRightChild(), limite);
        int dato = ab.getData();
        if(dato % 2 == 1 && dato > limite) cant+= dato;
        return cant;
    }
    
    public static void main(String args[]) {
        BinaryTree<Integer> ab = new BinaryTree<Integer>(7);
        ab.addLeftChild(new BinaryTree<Integer>(56));
        ab.getLeftChild().addLeftChild(new BinaryTree<Integer>(38));
        ab.getLeftChild().addRightChild(new BinaryTree<Integer>(31));
        ab.getLeftChild().getRightChild().addRightChild(new BinaryTree<Integer>(94));
        ab.getLeftChild().getRightChild().getRightChild().addRightChild(new BinaryTree<Integer>(2));
        ab.getLeftChild().getRightChild().getRightChild().getRightChild().addLeftChild(new BinaryTree<Integer>(1));
        ab.getLeftChild().getLeftChild().addLeftChild(new BinaryTree<Integer>(87));
        ab.getLeftChild().getLeftChild().addRightChild(new BinaryTree<Integer>(77));
        ab.getLeftChild().getLeftChild().getRightChild().addLeftChild(new BinaryTree<Integer>(16));
        ab.getLeftChild().getLeftChild().getRightChild().getLeftChild().addRightChild(new BinaryTree<Integer>(43));
        ab.getLeftChild().getLeftChild().getRightChild().getLeftChild().getRightChild().addLeftChild(new BinaryTree<Integer>(9));
        ab.getLeftChild().getLeftChild().getRightChild().getLeftChild().getRightChild().addRightChild(new BinaryTree<Integer>(10));
        ab.addRightChild(new BinaryTree<Integer>(25));
        ab.getRightChild().addLeftChild(new BinaryTree<Integer>(5));
        ab.getRightChild().addRightChild(new BinaryTree<Integer>(6));
        
        Parcial p = new Parcial();
        
        System.out.println(p.sumaImparesPosOrdenMayorA(ab, 30));
    
    }
}
