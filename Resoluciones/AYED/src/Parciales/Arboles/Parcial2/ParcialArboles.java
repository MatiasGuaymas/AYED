package Parciales.Arboles.Parcial2;

import Practica2.Ejercicio1y2.BinaryTree;

public class ParcialArboles {
    private BinaryTree<Integer> arb;
    
    public ParcialArboles(BinaryTree<Integer> arb) {
        this.arb = arb;
    }
    
    public boolean resolver(int k) {
        return resolver(arb, k, 0);
    }
    
    private boolean resolver(BinaryTree<Integer> arb, int k, int suma) {
        suma += arb.getData();
        if(arb.isLeaf()) {
            return suma == k;
        } else {
            boolean ok = true;
            if(arb.hasLeftChild()) ok = ok && resolver(arb.getLeftChild(), k, suma);
            if(arb.hasRightChild()) ok = ok && resolver(arb.getRightChild(), k, suma);
            return ok;
        }
    }
    
    public static void main(String args[]) {
        BinaryTree<Integer> ab = new BinaryTree<Integer>(1);
        ab.addLeftChild(new BinaryTree<Integer>(3));
        ab.getLeftChild().addLeftChild(new BinaryTree<Integer>(3));
        //ab.getLeftChild().getLeftChild().addLeftChild(new BinaryTree<Integer>(1));
        ab.addRightChild(new BinaryTree<Integer>(4));
        ab.getRightChild().addLeftChild(new BinaryTree<Integer>(1));
        ab.getRightChild().getLeftChild().addLeftChild(new BinaryTree<Integer>(1));
        ab.getRightChild().addRightChild(new BinaryTree<Integer>(2));
        
        ParcialArboles a = new ParcialArboles(ab);
        System.out.println(a.resolver(7));
    
    }
}
