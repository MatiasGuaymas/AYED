package Parciales.Arboles.ParcialesPractica.Adicional1;

import Practica1.Ejercicio8.*;
import Practica2.Ejercicio1y2.*;

public class ParcialArboles {
    private BinaryTree<Integer> arbol;
    
    public ParcialArboles(BinaryTree<Integer> arbol) {
        this.arbol = arbol;
    }
    
    public boolean isLeftTree(int num) {
        boolean ok = false;
        BinaryTree<Integer> ab = this.buscar(this.arbol, num);
        //BinaryTree<Integer> ab = this.buscar(this.arbol, num, new BinaryTree<Integer>());
        if(!ab.isEmpty()) {
            ok = this.isLeftTree(ab);
        }
        return ok;
        //return (!ab.isEmpty()) ? this.isLeftTree(ab) : false;
    }
    
    private int contarUnicoHijo(BinaryTree<Integer> ab) {
        int cant = 0;
        if((ab.hasLeftChild() && !ab.hasRightChild()) || (!ab.hasLeftChild() && ab.hasRightChild())) cant++;
        if(ab.hasLeftChild()) cant+= this.contarUnicoHijo(ab.getLeftChild());
        if(ab.hasRightChild()) cant+= this.contarUnicoHijo(ab.getRightChild());
        return cant;
    }
    
    private boolean isLeftTree(BinaryTree<Integer> ab) {
        int ramaIzq = -1;
        int ramaDer = -1;
        if(ab.hasLeftChild()) ramaIzq = this.contarUnicoHijo(ab.getLeftChild());
        if(ab.hasRightChild()) ramaDer = this.contarUnicoHijo(ab.getRightChild());
        return ramaIzq > ramaDer;
    }
    
    private BinaryTree<Integer> buscar(BinaryTree<Integer> ab, int num) {
        Queue<BinaryTree<Integer>> q = new Queue<BinaryTree<Integer>>();
        BinaryTree<Integer> nodoNum = null;
        q.enqueue(ab);
        while(!q.isEmpty() && nodoNum == null) {
            int i = 0;
            int size = q.size();
            while(i < size && nodoNum == null) {
                BinaryTree<Integer> aux = q.dequeue();
                if(aux.getData() == num) {
                    nodoNum = aux;
                } else {
                    if(aux.hasLeftChild()) {
                        q.enqueue(aux.getLeftChild());
                    }
                    if(aux.hasRightChild()) {
                        q.enqueue(aux.getRightChild());
                    }
                }
                i++;
            }
        }
        return nodoNum;
    }
    
    /* private BinaryTree<Integer> buscar (BinaryTree<Integer> ab, int num, BinaryTree<Integer> nodo) {
        if(ab.getData() == num) 
            nodo = ab;
        else {
            if(ab.hasLeftChild()) {
                nodo = this.buscar(ab.getLeftChild(), num, nodo);
            }
            if((ab.hasRightChild()) && (nodo.isEmpty())) {
                nodo = this.buscar(ab.getRightChild(), num, nodo);
            }
        }
        return nodo;
    } */
    
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
        
        ParcialArboles parcialArb = new ParcialArboles(ab);
        System.out.println("Resultado=" + parcialArb.isLeftTree(7));
        System.out.println("Resultado=" + parcialArb.isLeftTree(2));
        System.out.println("Resultado=" + parcialArb.isLeftTree(-5));
        System.out.println("Resultado=" + parcialArb.isLeftTree(19));
        System.out.println("Resultado=" + parcialArb.isLeftTree(-3));
    }
}


