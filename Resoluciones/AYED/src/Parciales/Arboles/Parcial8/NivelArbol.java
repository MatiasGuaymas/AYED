package Parciales.Arboles.Parcial8;

import Practica1.Ejercicio8.Queue;
import Practica2.Ejercicio1y2.BinaryTree;

public class NivelArbol {
    private BinaryTree<Integer> arb;
    
    public NivelArbol(BinaryTree<Integer> arb) {
        this.arb = arb;
    }
    
    public BinaryTree<Integer> minEnNivelABConNull(int n) {
        BinaryTree<Integer> hojaMenor = new BinaryTree<Integer>();
        if(!arb.isEmpty()) {
            Queue<BinaryTree<Integer>> cola = new Queue<BinaryTree<Integer>>();
            int nivelActual = 0;
            int min = Integer.MAX_VALUE;
            cola.enqueue(arb);
            cola.enqueue(null);
            while(!cola.isEmpty() && nivelActual <= n) {
                BinaryTree<Integer> aux = cola.dequeue();
                if(aux != null) {
                    if(nivelActual == n & aux.isLeaf() & aux.getData() < min) {
                        min = aux.getData();
                        hojaMenor = aux;
                    } else {
                        if(aux.hasLeftChild()) cola.enqueue(aux.getLeftChild());
                        if(aux.hasRightChild()) cola.enqueue(aux.getRightChild());
                    }
                } else if(!cola.isEmpty()) {
                    nivelActual++;
                    cola.enqueue(null);
                }
            }
        }
        return hojaMenor;
    } 
    
    public BinaryTree<Integer> minEnNivelABSinNull(int n) {
        BinaryTree<Integer> hojaMenor = new BinaryTree<Integer>();
        if(!arb.isEmpty()) {
            Queue<BinaryTree<Integer>> cola = new Queue<BinaryTree<Integer>>();
            int nivelActual = 0;
            int min = Integer.MAX_VALUE;
            cola.enqueue(arb);
            while(!cola.isEmpty() && nivelActual <= n) {
                int i = cola.size();
                for(int j=0; j < i; j++) {
                    BinaryTree<Integer> aux = cola.dequeue();
                    if(nivelActual == n & aux.isLeaf() & aux.getData() < min) {
                        min = aux.getData();
                        hojaMenor = aux;
                    }
                    if(aux.hasLeftChild()) cola.enqueue(aux.getLeftChild());
                    if(aux.hasRightChild()) cola.enqueue(aux.getRightChild());
                }
                nivelActual++;
            }
        }
        return hojaMenor;
    }
    
    public static void main(String[] args) {
        BinaryTree<Integer> ab = new BinaryTree<Integer>(2);
        ab.addLeftChild(new BinaryTree<Integer>(7));
        ab.getLeftChild().addLeftChild(new BinaryTree<Integer>(2));
        ab.getLeftChild().addRightChild(new BinaryTree<Integer>(6));
        ab.getLeftChild().getRightChild().addLeftChild(new BinaryTree<Integer>(5));
        ab.getLeftChild().getRightChild().addRightChild(new BinaryTree<Integer>(11));
        ab.addRightChild(new BinaryTree<Integer>(5));
        ab.getRightChild().addRightChild(new BinaryTree<Integer>(9));
        ab.getRightChild().getRightChild().addLeftChild(new BinaryTree<Integer>(4));
        
        NivelArbol a = new NivelArbol(ab);
        
        //Es necesario .getData(), porque si retorna null, se hace un .toString() a un objeto nulo, tirando nullPointerException.
        
        System.out.println(a.minEnNivelABConNull(1).getData());
        System.out.println(a.minEnNivelABConNull(2).getData());
        System.out.println(a.minEnNivelABConNull(3).getData());
        
        System.out.println("");
        
        System.out.println(a.minEnNivelABSinNull(1).getData());
        System.out.println(a.minEnNivelABSinNull(2).getData());
        System.out.println(a.minEnNivelABSinNull(3).getData());
    }
    
}
