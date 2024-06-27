package Parciales.Arboles.ParcialesPractica.Adicional3;

import Practica2.Ejercicio1y2.BinaryTree;

public class ParcialArboles {
    public BinaryTree<SumDif> sumAndDif(BinaryTree<Integer> arbol) {
        BinaryTree<SumDif> ab = new BinaryTree<SumDif>();
        if(!arbol.isEmpty()) {
            int dato = arbol.getData();
            this.sumAndDif(arbol, ab, dato, dato);
        }
        return ab;
    }
    
    //Mi nodo hijo tiene la cuenta total, y su dato fue previamente sumado y restado
    private void sumAndDif(BinaryTree<Integer> arbol, BinaryTree<SumDif> ab, int sumaTotal, int resta) {
        SumDif sd = new SumDif(sumaTotal, resta);
        ab.setData(sd);
        if(arbol.hasLeftChild()) {
            ab.addLeftChild(new BinaryTree<SumDif>());
            int datoIzq = arbol.getLeftChild().getData();
            this.sumAndDif(arbol.getLeftChild(), ab.getLeftChild(), sumaTotal + datoIzq, datoIzq - resta);
        }
        if(arbol.hasRightChild()) {
            ab.addRightChild(new BinaryTree<SumDif>());
            int datoDer = arbol.getRightChild().getData();
            this.sumAndDif(arbol.getRightChild(), ab.getRightChild(), sumaTotal + datoDer, datoDer - resta);
        }
    }
    
    //Mi nodo hijo tiene la cuenta total, y su dato NO fue previamente sumado y restado, debo hacerlo yo
    /* private void sumAndDif(BinaryTree<Integer> arbol, BinaryTree<SumDif> arbSad, int sum, int padre) {
        int datoActual = arbol.getData();
        SumDif sad = new SumDif(datoActual+sum, datoActual - padre);
        arbSad.setData(sad);
        if(arbol.hasLeftChild()) {
            arbSad.addLeftChild(new BinaryTree<SumDif>());
            sumAndDif(arbol.getLeftChild(), arbSad.getLeftChild(), sum+datoActual, datoActual);
        }
        if(arbol.hasRightChild()) {
            arbSad.addRightChild(new BinaryTree<SumDif>());
            sumAndDif(arbol.getRightChild(), arbSad.getRightChild(), sum+datoActual, datoActual);
        }
    }   */
    
    public static void main(String[] args) {
        ParcialArboles parcialArb = new ParcialArboles();
        
        BinaryTree<Integer> ab = new BinaryTree<Integer>(20);
        ab.addLeftChild(new BinaryTree<Integer>(5));
        ab.getLeftChild().addLeftChild(new BinaryTree<Integer>(-5));
        ab.getLeftChild().addRightChild(new BinaryTree<Integer>(10));
        ab.getLeftChild().getRightChild().addLeftChild(new BinaryTree<Integer>(1));
        ab.addRightChild(new BinaryTree<Integer>(30));
        ab.getRightChild().addLeftChild(new BinaryTree<Integer>(50));
        ab.getRightChild().addRightChild(new BinaryTree<Integer>(-9));
        ab.getRightChild().getLeftChild().addRightChild(new BinaryTree<Integer>(4));
        ab.getRightChild().getLeftChild().getRightChild().addRightChild(new BinaryTree<Integer>(6));
        
        parcialArb.sumAndDif(ab).entreNiveles(0, 4);
    }
}
