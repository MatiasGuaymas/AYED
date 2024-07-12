package Parciales.Arboles.Parcial23;

import Practica2.Ejercicio1y2.BinaryTree;

public class ParcialArboles {
    private BinaryTree<Integer> ab;
    
    public ParcialArboles(BinaryTree<Integer> ab) {
        this.ab = ab;
    }
    
    public BinaryTree<Integer> nuevoTree() {
        BinaryTree<Integer> nuevoAb = new BinaryTree<Integer>();
        if(!this.ab.isEmpty()) {
            this.crear(this.ab, nuevoAb, 0);
        }
        return nuevoAb;
    }
    
    private void crear(BinaryTree<Integer> ab, BinaryTree<Integer> nuevoAb, int num) {
        int datoOriginal = ab.getData();
        nuevoAb.setData(num+datoOriginal);
        if(ab.hasLeftChild()) {
            nuevoAb.addLeftChild(new BinaryTree<Integer>());
            this.crear(ab.getLeftChild(), nuevoAb.getLeftChild(), datoOriginal);
        }
        if(ab.hasRightChild()) {
            nuevoAb.addRightChild(new BinaryTree<Integer>());
            this.crear(ab.getRightChild(), nuevoAb.getRightChild(), 0);
        }
    }
    
    public static void main(String args[]) {
        BinaryTree<Integer> ab = new BinaryTree<Integer>(1);
        ab.addLeftChild(new BinaryTree<Integer>(2));
        ab.getLeftChild().addLeftChild(new BinaryTree<Integer>(4));
        ab.addRightChild(new BinaryTree<Integer>(3));
        ab.getRightChild().addLeftChild(new BinaryTree<Integer>(5));
        ab.getRightChild().addRightChild(new BinaryTree<Integer>(6));
        ab.getRightChild().getLeftChild().addLeftChild(new BinaryTree<Integer>(7));
        
        ParcialArboles p = new ParcialArboles(ab);
        
        BinaryTree<Integer> arb = p.nuevoTree();
        
        arb.entreNiveles(0, 3);
    }
}
