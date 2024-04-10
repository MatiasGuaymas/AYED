package Practica2.Ejercicio7;

import Practica2.Ejercicio1y2.BinaryTree;

public class ParcialArboles {
    private BinaryTree<Integer> ab;
    
    public ParcialArboles(BinaryTree<Integer> ab) {
        this.ab = ab;
    }

    public BinaryTree<Integer> getAb() {
        return ab;
    }
    
    private int contarUnicoHijo(BinaryTree<Integer> arb) {
        int cant = 0;
        if(arb.hasLeftChild()) cant += contarUnicoHijo(arb.getLeftChild());
        if(arb.hasRightChild()) cant += contarUnicoHijo(arb.getRightChild());
        if( (arb.hasLeftChild() && !arb.hasRightChild()) || (!arb.hasLeftChild() && arb.hasRightChild())) cant++;
        return cant;
    }
    
    private boolean isLeftTree(BinaryTree<Integer> arb) {
        int ramaIzq = -1;
        int ramaDer = -1;
        if(arb.hasLeftChild()) ramaIzq = contarUnicoHijo(arb.getLeftChild());
        if(arb.hasRightChild()) ramaDer = contarUnicoHijo(arb.getRightChild());
        return ramaIzq > ramaDer;
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
    
    public boolean isLeftTree(int num) {
        BinaryTree<Integer> arb = buscar(ab, num);
        return (!arb.isEmpty()) ? isLeftTree(arb) : false;
    }
    
    public static void main (String[] args) { 
        System.out.println("Test Ejercicio 7");
        
        BinaryTree<Integer> ab = new BinaryTree<Integer>(2);
        ab.addLeftChild(new BinaryTree<Integer>(7));
        ab.addRightChild(new BinaryTree<Integer>(-5));
        ab.getLeftChild().addLeftChild(new BinaryTree<Integer>(23));
        ab.getLeftChild().addRightChild(new BinaryTree<Integer>(6));
        ab.getLeftChild().getLeftChild().addLeftChild(new BinaryTree<Integer>(-3));
        ab.getLeftChild().getRightChild().addLeftChild(new BinaryTree<Integer>(55));
        ab.getLeftChild().getRightChild().addRightChild(new BinaryTree<Integer>(11));
        ab.getRightChild().addLeftChild(new BinaryTree<Integer>(19));
        ab.getRightChild().getLeftChild().addRightChild(new BinaryTree<Integer>(4));
        ab.getRightChild().getLeftChild().getRightChild().addLeftChild(new BinaryTree<Integer>(18));
        ab.entreNiveles(0, 4);
        
        ParcialArboles parcialArb = new ParcialArboles(ab);
        System.out.println("Resultado=" + parcialArb.isLeftTree(7));
        System.out.println("Resultado=" + parcialArb.isLeftTree(2));
        System.out.println("Resultado=" + parcialArb.isLeftTree(-5));
        System.out.println("Resultado=" + parcialArb.isLeftTree(19));
        System.out.println("Resultado=" + parcialArb.isLeftTree(-3));
    }
}
