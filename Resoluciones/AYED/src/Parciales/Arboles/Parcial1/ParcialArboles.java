package Parciales.Arboles.Parcial1;

import Practica2.Ejercicio1y2.BinaryTree;

public class ParcialArboles {
    private BinaryTree<Integer> ab;
    
    public ParcialArboles(BinaryTree<Integer> ab) {
        this.ab = ab;
    }
    
    public Boolean isTwoTree(int num) {
        BinaryTree<Integer> arb = buscar(ab, num);
        return (!arb.isEmpty()) ? isTwoTree(arb) : false;
    }
    
    private BinaryTree<Integer> buscar (BinaryTree<Integer> ab, int num) {
        if(ab.getData() == num) return ab;
        BinaryTree<Integer> res = new BinaryTree<Integer>();
        if(ab.hasLeftChild()) {
            res = buscar(ab.getLeftChild(), num);
        }
        if((ab.hasRightChild()) && (res.isEmpty())) {
            res = buscar(ab.getRightChild(), num);
        }
        return res;
    }
    
    private boolean isTwoTree(BinaryTree<Integer> arb) {
        int ramaIzq = -1;
        int ramaDer = -1;
        if(arb.hasLeftChild()) ramaIzq = contarDosHijos(arb.getLeftChild());
        if(arb.hasRightChild()) ramaDer = contarDosHijos(arb.getRightChild());
        return ramaIzq == ramaDer;
    }
    
    private int contarDosHijos(BinaryTree<Integer> arb) {
        int cant = 0;
        if(arb.hasLeftChild()) cant+= this.contarDosHijos(arb.getLeftChild());
        if(arb.hasRightChild()) cant+= this.contarDosHijos(arb.getRightChild());
        if(arb.hasLeftChild() && arb.hasRightChild()) cant++;
        return cant;
    }
    
    public static void main (String[] args) { 
        
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
        
        ParcialArboles parcialArb = new ParcialArboles(ab);
        System.out.println("Resultado=" + parcialArb.isTwoTree(2));
        System.out.println("Resultado=" + parcialArb.isTwoTree(7));
        System.out.println("Resultado=" + parcialArb.isTwoTree(-3));
        System.out.println("Resultado=" + parcialArb.isTwoTree(4));
        System.out.println("Resultado=" + parcialArb.isTwoTree(55));
    }

}
